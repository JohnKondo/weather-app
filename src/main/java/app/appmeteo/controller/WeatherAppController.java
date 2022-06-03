package app.appmeteo.controller;

import app.appmeteo.controller.api.JSONReader5Days;
import app.appmeteo.controller.api.JSONReaderHourly;
import app.appmeteo.controller.exeptions.FavouriteException;
import app.appmeteo.model.MODEL;
import app.appmeteo.model.utils.Request;
import app.appmeteo.views.*;
import javafx.scene.image.Image;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static app.appmeteo.model.utils.CommandesUtils.parseSearchField;
import static app.appmeteo.model.utils.FavouritesUtils.LoadFavouritesFromCsv;
import static app.appmeteo.model.utils.HoursLabelUtils.buildHoursLabel;

public class WeatherAppController {
    private final MODEL model;
    private final View view;
    private final CurrentWeatherView currentWeatherView;
    private final FavoritesView favoritesView;
    private final WeatherOn5daysView weatherOn5daysView;
    private final HourlyWeatherView hourlyWeatherView;
    private final TempGraphView tempGraphView;

    public WeatherAppController(MODEL model,
                                View view,
                                CurrentWeatherView currentWeatherView,
                                FavoritesView favoritesView,
                                WeatherOn5daysView weatherOn5daysView,
                                HourlyWeatherView hourlyWeatherView,
                                TempGraphView tempGraphView) {
        this.model = model;
        this.view = view;
        this.currentWeatherView = currentWeatherView;
        this.favoritesView = favoritesView;
        this.weatherOn5daysView = weatherOn5daysView;
        this.hourlyWeatherView = hourlyWeatherView;
        this.tempGraphView = tempGraphView;
        loadFavourites();
    }

    public void searchWeatherByCity(String cityName) {
        try {
            Request request = new Request(parseSearchField(cityName));
            model.getWeatherByCity(request.getLocation());
            displayCurrentWeather(this.model);
            displayWeatherOn5Days(this.model.getWeatherOn5days());
            displayHourlyWeather(this.model.getHourlyWeather());
            displayMap(this.model.getUrlMap());
            displayGraph();
        } catch (Exception e) {
            view.setErrorState();
        }
    }

    private void displayGraph() {
        tempGraphView.updateGraph(buildHoursLabel(model.getCurrentWeatherData().getCurrentHour()),
                new ArrayList<>(model.getTempList().subList(0, 12)));
    }

    private void displayCurrentWeather(MODEL model) {
        try {
            currentWeatherView.updateWeather(model.getCurrentWeatherData());
            view.setSearchResultVisible();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    private void displayWeatherOn5Days(JSONReader5Days jsonReader5Days) {
        weatherOn5daysView.updateWeather(jsonReader5Days);
    }

    private void displayHourlyWeather(JSONReaderHourly jsonReaderHourly) {
        hourlyWeatherView.setJsonReaderHourly(jsonReaderHourly);
        hourlyWeatherView.setValueSlider(0);
        hourlyWeatherView.changeSlider(buildHoursLabel(model.getCurrentWeatherData().getCurrentHour()));
        hourlyWeatherView.updateHourlyWeather();
    }

    private void displayMap(String urlMap) {
        view.setMap(urlMap);
    }

    public Boolean isInFavourite() {
        return model.isCurrentCityInFavourites();
    }

    public void addFavourite() {
        try {
            model.addFavourite();
            favoritesView.addLastFavourite(model.getFavourites());
            currentWeatherView.setIsInFavourite(true);
            currentWeatherView.setButtonValidate(true);
        } catch (FavouriteException e) {
            System.err.println(e.getMessage());
        }
    }

    public void toggleFavourite(String cityName, boolean isInFavourite) {
        try {
            if (isInFavourite) {
                model.removeFavorite(cityName);
                favoritesView.removeFavourite(cityName);
                currentWeatherView.setIsInFavourite(false);
                currentWeatherView.setButtonValidate(false);
            } else {
                addFavourite();
            }
        } catch (RuntimeException e) {
            System.out.println("Error in : toggleFavourite in favouriteView");
        }
    }

    public void loadFavourites() {
        try {
            model.loadFavourites(LoadFavouritesFromCsv("src/main/resources/data/Favourites.csv"));
            favoritesView.addAllFavourites(model.getFavourites());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeFavourite(FavouriteCell favouriteCell) {
        model.removeFavorite(favouriteCell.getID());
        favoritesView.removeFavourite(favouriteCell);
        if (favouriteCell.getCityName().equals(model.getCurrentWeatherData().getName()))
            currentWeatherView.setIsInFavourite(false);
        currentWeatherView.setButtonValidate(false);
    }

    public Image getCurrentWeatherIconUrl() {
        return currentWeatherView.getImgUrl();
    }

    public String getCurrentCityDisplayed() {
        return model.getCurrentWeatherData().getName();
    }

    public long[] getSunriseSunset() {
        return model.getSunriseAndSunset();
    }

}
