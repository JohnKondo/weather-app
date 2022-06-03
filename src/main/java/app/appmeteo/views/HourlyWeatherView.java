package app.appmeteo.views;

import app.appmeteo.controller.WeatherAppController;
import app.appmeteo.controller.api.Forecast;
import app.appmeteo.controller.api.JSONReaderHourly;
import app.appmeteo.model.utils.CommandesUtils;
import app.appmeteo.views.UX_utils.SliderC;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static app.appmeteo.model.utils.CommandesUtils.chooseIcon;
import static app.appmeteo.model.utils.CommandesUtils.description;

public class HourlyWeatherView implements Initializable {
    @FXML
    public ImageView weatherIcon;
    public Label temp;
    public Label cloudCover;
    public Label windVelocity;
    public Slider slider;
    public Pane pane;
    public Label precipitation;
    private WeatherAppController weatherAppController;
    private JSONReaderHourly jsonReaderHourly;

    public void setJsonReaderHourly(JSONReaderHourly jsonReaderHourly) {
        this.jsonReaderHourly = jsonReaderHourly;
    }

    public void setController(WeatherAppController weatherAppController) {
        this.weatherAppController = weatherAppController;
    }

    public void updateHourlyWeather() {
        updateHourlyWeather(0);
    }

    public void updateHourlyWeather(int hour) {
        Forecast hourToShow = jsonReaderHourly.getSpecificHour(hour);
        temp.setText(hourToShow.getTemp() + "\u00b0");
        cloudCover.setText("Couverture nuageuse : " + description(hourToShow.getId()));
        windVelocity.setText("Vent : " + hourToShow.getWind_speed() + " km/h");
        precipitation.setText("Pr\u00e9cipitation : " + Math.round(hourToShow.getPrecipitations()) + "%");
        setImage(hourToShow.getId(), hour);
    }

    public void changeSlider(ArrayList<String> labelsName) {
        pane.getChildren().remove(0);
        slider = new SliderC(this, labelsName);
        pane.getChildren().add(slider);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        slider = new SliderC(this);
        pane.getChildren().add(slider);
    }

    public void setValueSlider(int v) {
        slider.setValue(v);
    }

    private boolean isDay(int hour) {
        long[] buf = weatherAppController.getSunriseSunset();
        return CommandesUtils.isDay(buf[0], buf[1], jsonReaderHourly.getSpecificHour(hour).getUnix());
    }

    private void setImage(int id, int hour) {
        String path = "src/main/resources/icons/";
        path += isDay(hour) ? "day/" : "night/";
        path += chooseIcon(id);
        try {
            InputStream inputStream = new FileInputStream(path);
            Image image = new Image(inputStream);
            weatherIcon.setImage(image);
        } catch (FileNotFoundException e) {
            System.out.println("icon not found for favourites");
        }
    }
}
