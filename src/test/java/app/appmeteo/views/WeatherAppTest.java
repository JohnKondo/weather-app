package app.appmeteo.views;

import app.appmeteo.controller.WeatherAppController;
import app.appmeteo.model.MODEL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import java.io.IOException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class WeatherAppTest extends ApplicationTest {

    private View view;
    private WeatherAppController weatherAppController;

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
            //primaryStage.initStyle(StageStyle.UNDECORATED); // remove title bar
            primaryStage.setResizable(false);
            view.setDragPane(primaryStage);
            primaryStage.show();

            this.view = view;
            this.weatherAppController = controller;


        } catch (
                IOException ex) {
            System.err.println("Erreur au chargement: " + ex);
        }
    }

    @Test
    public void testValidInput(){
        clickOn(view.getSearchInput());
        write("Marseille");
        press(KeyCode.ENTER);
        assertEquals(weatherAppController.getCurrentCityDisplayed(), "Marseille");
    }

    @Test
    public void testWrongInput(){
        clickOn(view.getSearchInput());
        write("dqpdq dqd p");
        press(KeyCode.ENTER);
        assertFalse(view.getSearchResult().isVisible());
    }
}