package app.appmeteo.views;

import app.appmeteo.controller.api.JSONReader5Days;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

import static app.appmeteo.model.utils.CommandesUtils.chooseIcon;

public class WeatherOn5daysView implements Initializable {
    public ImageView first_icon;
    public Label first_day;
    public Label first_max;
    public Label first_min;
    public ImageView second_icon;
    public Label second_day;
    public Label second_max;
    public Label second_min;
    public ImageView third_icon;
    public Label third_day;
    public Label third_max;
    public Label third_min;
    public ImageView fourth_icon;
    public Label fourth_day;
    public Label fourth_max;
    public Label fourth_min;
    public ImageView fifth_icon;
    public Label fifth_day;
    public Label fifth_max;
    public Label fifth_min;


    public void updateWeather(JSONReader5Days jsonReader5Days) {
        first_day.setText(jsonReader5Days.getSpecificDay(1).getDay());
        first_max.setText(jsonReader5Days.getSpecificDay(1).getMax() + "\u00b0");
        first_min.setText(jsonReader5Days.getSpecificDay(1).getMin() + "\u00b0");
        setImage(jsonReader5Days.getSpecificDay(1).getId(), first_icon);

        second_day.setText(jsonReader5Days.getSpecificDay(2).getDay());
        second_max.setText(jsonReader5Days.getSpecificDay(2).getMax() + "\u00b0");
        second_min.setText(jsonReader5Days.getSpecificDay(2).getMin() + "\u00b0");
        setImage(jsonReader5Days.getSpecificDay(2).getId(), second_icon);

        third_day.setText(jsonReader5Days.getSpecificDay(3).getDay());
        third_max.setText(jsonReader5Days.getSpecificDay(3).getMax() + "\u00b0");
        third_min.setText(jsonReader5Days.getSpecificDay(3).getMin() + "\u00b0");
        setImage(jsonReader5Days.getSpecificDay(3).getId(), third_icon);

        fourth_day.setText(jsonReader5Days.getSpecificDay(4).getDay());
        fourth_max.setText(jsonReader5Days.getSpecificDay(4).getMax() + "\u00b0");
        fourth_min.setText(jsonReader5Days.getSpecificDay(5).getMin() + "\u00b0");
        setImage(jsonReader5Days.getSpecificDay(4).getId(), fourth_icon);

        fifth_day.setText(jsonReader5Days.getSpecificDay(5).getDay());
        fifth_max.setText(jsonReader5Days.getSpecificDay(5).getMax() + "\u00b0");
        fifth_min.setText(jsonReader5Days.getSpecificDay(5).getMin() + "\u00b0");
        setImage(jsonReader5Days.getSpecificDay(5).getId(), fifth_icon);
    }

    private void setImage(int id, ImageView imageView) {
        String path = "src/main/resources/icons/day/";
        path += chooseIcon(id);
        try {
            InputStream inputStream = new FileInputStream(path);
            Image image = new Image(inputStream);
            imageView.setImage(image);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
