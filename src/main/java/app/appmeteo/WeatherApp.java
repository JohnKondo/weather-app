package app.appmeteo;

import app.appmeteo.controller.WeatherAppController;
import app.appmeteo.model.MODEL;
import app.appmeteo.views.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;

public class WeatherApp extends Application {
    public static void startApp(String[] args) {
        launch(args);

    }

    public static void main(String[] args) {
        startApp(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {

            URL url = getClass().getResource("/app/appmeteo/view/appmeteo.fxml");

            FXMLLoader fxmlLoader = new FXMLLoader(url);

            View view = new View(); // création des controller des view
            CurrentWeatherView currentWeatherView = new CurrentWeatherView();
            FavoritesView favoritesView = new FavoritesView(view);
            WeatherOn5daysView weatherOn5daysView = new WeatherOn5daysView();
            HourlyWeatherView hourlyWeatherView = new HourlyWeatherView();
            TempGraphView tempGraphView = new TempGraphView();

            // load controller
            fxmlLoader.setControllerFactory(param -> {
                if (param.equals(View.class)) {
                    return view;
                } else if (param.equals(CurrentWeatherView.class)) {
                    return currentWeatherView;
                } else if (param.equals(FavoritesView.class)) {
                    return favoritesView;
                } else if (param.equals(WeatherOn5daysView.class)) {
                    return weatherOn5daysView;
                } else if (param.equals(HourlyWeatherView.class)) {
                    return hourlyWeatherView;
                } else if (param.equals(TempGraphView.class)) {
                    return tempGraphView;
                }
                return null;
            });

            AnchorPane root = fxmlLoader.load();

            WeatherAppController controller = new WeatherAppController(new MODEL(),
                    view,
                    currentWeatherView,
                    favoritesView,
                    weatherOn5daysView,
                    hourlyWeatherView,
                    tempGraphView);

            view.addController(controller);
            currentWeatherView.addController(controller);
            hourlyWeatherView.setController(controller);

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("{Weather-App}");
            primaryStage.initStyle(StageStyle.UNDECORATED); // remove title bar
            primaryStage.setResizable(false);
            view.setDragPane(primaryStage);
        } catch (IOException ex) {
            System.err.println("Erreur au chargement: " + ex);
        }
        primaryStage.show();
    }


}
