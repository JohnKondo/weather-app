package app.appmeteo.views;

import app.appmeteo.controller.WeatherAppController;
import app.appmeteo.controller.api.JsonReader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

import static app.appmeteo.model.utils.CommandesUtils.*;

public class CurrentWeatherView implements Initializable {
    private static final String IS_IN_FAVOURITE_PNG = "isFavourite.png";
    private static final String IS_NOT_FAVOURITE_PNG = "isNotFavourite.png";
    @FXML
    public Label date;
    public Label cityName;
    public Label description;
    public Label temperature;
    public Label maxTemperature;
    public ImageView currentWeatherIcon;
    public Label hour;
    public Label sunrise;
    public Label humidity;
    public Label windSpeed;
    public Label feelsLike;
    public Label sunset;
    public Label cloudsPercent;
    public Label windDirection;
    public Label pressure;
    public Label country;
    public Button buttonFavourite;
    private boolean isDay;
    private WeatherAppController controller;
    private Boolean isInFavourite;

    public void updateWeather(JsonReader data) throws FileNotFoundException {
        isDay = data.isDay();
        date.setText(data.getDt());
        cityName.setText(data.getName());
        description.setText(description(data.getId()));
        temperature.setText(data.getTemp() + "\u00b0");
        maxTemperature.setText("min : " + data.getTemp_min() + "\u00b0   max : " + data.getTemp_max() + "\u00b0");
        hour.setText(String.valueOf(data.getHour()));
        humidity.setText(data.getHumidity() + "%");
        windSpeed.setText(data.getSpeed() + " km/h");
        sunrise.setText(String.valueOf(data.getSunrise()));
        sunset.setText(String.valueOf(data.getSunset()));
        cloudsPercent.setText(data.getCloud() + "%");
        windDirection.setText(headingToString(data.getDeg()));
        pressure.setText(data.getPressure() + " hPa");
        country.setText(data.getCountry());
        feelsLike.setText(data.getFeels_like() + "\u00b0");
        setImage(data.getId());
        setIsInFavourite(controller.isInFavourite());
        setButtonValidate(controller.isInFavourite());
    }

    public void setStyleButton(String u) {
        buttonFavourite.setStyle("-fx-graphic: url(" + u + (");"));
    }

    public void setButtonValidate(boolean isInFavourite) {
        String url = "/icons/other/";
        url = isInFavourite ? url + IS_IN_FAVOURITE_PNG : url + IS_NOT_FAVOURITE_PNG;
        setStyleButton(url);
    }

    public void setIsInFavourite(Boolean isInFavourite) {
        this.isInFavourite = isInFavourite;
    }

    public void addController(WeatherAppController controller) {
        this.controller = controller;
    }

    public void toggleFavourite() {
        controller.toggleFavourite(cityName.getText(), isInFavourite);
    }

    private void setImage(int id) throws FileNotFoundException {
        String path = "src/main/resources/icons/";
        path += this.isDay ? "day/" : "night/";
        path += chooseIcon(id);
        InputStream inputStream = new FileInputStream(path);
        Image image = new Image(inputStream);
        currentWeatherIcon.setImage(image);
    }

    public Image getImgUrl() {
        return currentWeatherIcon.getImage();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
