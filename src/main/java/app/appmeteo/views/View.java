package app.appmeteo.views;

import app.appmeteo.controller.WeatherAppController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class View implements Initializable {
    @FXML
    public TextField searchInput;
    @FXML
    public Button searchButton;
    public Button closeApp;
    public Button reduceApp;
    @FXML
    public AnchorPane searchResult;
    public AnchorPane searchResultError;
    public AnchorPane Favourites;
    public AnchorPane homePage;
    public AnchorPane forecastFiveDays;
    public AnchorPane forecastFiveDaysStandby;
    public AnchorPane forecastHourly;
    public AnchorPane forecastHourlyStandby;
    public AnchorPane anchorMap;
    public AnchorPane anchorMapStandby;
    public AnchorPane tempGraph;
    public AnchorPane tempGraphStandby;
    public Pane dragPane;
    public WebView map;
    private WebEngine webEngine;
    private WeatherAppController controller;
    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        homePage.setVisible(true);
        searchResult.setVisible(false);
        searchResultError.setVisible(false);
        forecastFiveDaysStandby.setVisible(true);
        forecastFiveDays.setVisible(false);
        forecastHourlyStandby.setVisible(true);
        forecastHourly.setVisible(false);
        anchorMapStandby.setVisible(true);
        anchorMap.setVisible(false);
        tempGraph.setVisible(false);
        tempGraphStandby.setVisible(true);
        webEngine = map.getEngine();
    }

    public void askCurrentWeather(String cityName) {
        if (homePage.isVisible()) {
            homePage.setVisible(false);
        }
        controller.searchWeatherByCity(cityName);
    }

    public void search() {
        askCurrentWeather(searchInput.getText());
    }

    public void close() {
        Stage stage = (Stage) closeApp.getScene().getWindow();
        stage.close();
    }

    public void reduce() {
        Stage stage = (Stage) closeApp.getScene().getWindow();
        stage.setIconified(true);
    }

    public void setSearchResultVisible() {
        searchResultError.setVisible(false);
        searchResult.setVisible(true);
        forecastFiveDaysStandby.setVisible(false);
        forecastFiveDays.setVisible(true);
        forecastHourlyStandby.setVisible(false);
        forecastHourly.setVisible(true);
        anchorMapStandby.setVisible(false);
        anchorMap.setVisible(true);
        tempGraph.setVisible(true);
        tempGraphStandby.setVisible(false);
    }

    public void addController(WeatherAppController controller) {
        this.controller = controller;
    }

    public void setErrorState() {
        searchResult.setVisible(false);
        searchResultError.setVisible(true);
        forecastFiveDays.setVisible(false);
        forecastFiveDaysStandby.setVisible(true);
        forecastHourlyStandby.setVisible(true);
        forecastHourly.setVisible(false);
        anchorMapStandby.setVisible(true);
        anchorMap.setVisible(false);
        tempGraph.setVisible(false);
        tempGraphStandby.setVisible(true);
    }

    public void removeFavourite(FavouriteCell favouriteCell) {
        controller.removeFavourite(favouriteCell);
    }

    public TextField getSearchInput() {
        return searchInput;
    }

    public Button getSearchButton() {
        return searchButton;
    }

    public Button getCloseApp() {
        return closeApp;
    }

    public Button getReduceApp() {
        return reduceApp;
    }

    public AnchorPane getSearchResult() {
        return searchResult;
    }

    public AnchorPane getSearchResultError() {
        return searchResultError;
    }

    public AnchorPane getFavourites() {
        return Favourites;
    }

    public AnchorPane getHomePage() {
        return homePage;
    }

    public AnchorPane getForecastFiveDays() {
        return forecastFiveDays;
    }

    public AnchorPane getForecastFiveDaysStandby() {
        return forecastFiveDaysStandby;
    }

    public AnchorPane getForecastHourly() {
        return forecastHourly;
    }

    public AnchorPane getForecastHourlyStandby() {
        return forecastHourlyStandby;
    }

    public AnchorPane getAnchorMap() {
        return anchorMap;
    }

    public AnchorPane getAnchorMapStandby() {
        return anchorMapStandby;
    }

    public AnchorPane getTempGraph() {
        return tempGraph;
    }

    public AnchorPane getTempGraphStandby() {
        return tempGraphStandby;
    }

    public Pane getDragPane() {
        return dragPane;
    }

    public void setDragPane(Stage primaryStage) {
        dragPane.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        dragPane.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - xOffset);
            primaryStage.setY(event.getScreenY() - yOffset);
        });
    }

    public WebView getMap() {
        return map;
    }

    public void setMap(String urlMap) {
        webEngine.load(urlMap);
    }
}
