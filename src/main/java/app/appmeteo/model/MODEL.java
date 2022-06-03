package app.appmeteo.model;

import app.appmeteo.controller.api.JSONReader5Days;
import app.appmeteo.controller.api.JSONReaderHourly;
import app.appmeteo.controller.api.JsonReader;
import app.appmeteo.controller.exeptions.CurrentWeatherViewEmptyException;
import app.appmeteo.controller.exeptions.FavouriteException;
import app.appmeteo.model.favourite.Favourite;
import app.appmeteo.model.favourite.Favourites;
import app.appmeteo.model.utils.Request;

import java.util.ArrayList;

import static app.appmeteo.controller.api.WeatherAPI.*;
import static app.appmeteo.model.utils.FavouritesUtils.updateFavouritesCsv;

public class MODEL {

    private final Favourites favourites;
    private String response;
    private JsonReader currentWeatherData;
    private JSONReader5Days weatherOn5Days;
    private JSONReaderHourly hourlyWeather;
    private String urlMap;
    private ArrayList<Integer> tempList;
    private Boolean isCurrentCityInFavourites;

    public MODEL() {
        this.response = "";
        ArrayList<Request> requests = new ArrayList<>();
        favourites = new Favourites();
    }

    public void printHelper(String s) {
        response = s;
    }

    public void getWeatherByCity(String city) throws Exception {
        currentWeatherData = new JsonReader(weatherByName(city));
        weatherOn5Days = new JSONReader5Days(forecastFiveDays(city));
        hourlyWeather = new JSONReaderHourly(forecastHourly(currentWeatherData.getLongitude(), currentWeatherData.getLatitude()));
        urlMap = getMapURL(currentWeatherData.getName());
        tempList = hourlyWeather.getTempList();
        isCurrentCityInFavourites = favourites.getFavourites().stream().anyMatch(f -> f.getCityName().equals(currentWeatherData.getName()));
    }

    public void getWeatherByCityCLI(String city)throws Exception {
        response = new JsonReader(weatherByName(city)).dataToString();
    }

    public JsonReader getCurrentWeatherData() {
        return currentWeatherData;
    }

    public JSONReader5Days getWeatherOn5days() {
        return weatherOn5Days;
    }

    public JSONReaderHourly getHourlyWeather() {
        return hourlyWeather;
    }

    public Boolean isCurrentCityInFavourites() {
        return isCurrentCityInFavourites;
    }

    public ArrayList<Integer> getTempList() {
        return tempList;
    }

    public long[] getSunriseAndSunset() {
        long[] result = new long[2];
        result[0] = currentWeatherData.getSunriseUnix();
        result[1] = currentWeatherData.getSunsetUnix();
        return result;
    }

    public Favourites getFavourites() {
        return this.favourites;
    }

    public void addFavourite() throws FavouriteException {
        isCurrentCityInFavourites = true;
        if (currentWeatherData == null) throw new CurrentWeatherViewEmptyException();
        favourites.addFavourites(new Favourite(currentWeatherData.getTemp(),
                currentWeatherData.getName(),
                String.valueOf(currentWeatherData.getHour()), currentWeatherData.getCountry(),
                favourites.getNewId(), currentWeatherData.getId(), currentWeatherData.isDay()));
        updateFavouritesCsv(favourites);
    }

    public void loadFavourites(ArrayList<String> favouritesListCityName) throws Exception {
        if (!favouritesListCityName.isEmpty()) {
            for (String cityName : favouritesListCityName) {
                JsonReader reader = new JsonReader(weatherByName(cityName));
                favourites.addFavourites(new Favourite(reader.getTemp(),
                        reader.getName(),
                        String.valueOf(reader.getHour()), reader.getCountry(),
                        favourites.getNewId(), reader.getId(), reader.isDay()));
            }
        }
    }

    public String getUrlMap() {
        return this.urlMap;
    }

    public void removeFavorite(String cityName) {
        isCurrentCityInFavourites = false;
        favourites.removeFavourite(cityName);
        updateFavouritesCsv(favourites);
    }

    public void removeFavorite(int ID) {
        favourites.removeFavourite(ID);
        updateFavouritesCsv(favourites);
    }

    public String getResponse() {
        return response;
    }

    public void getWeatherByLocationCLI(double latitude, double longitude) throws Exception{
        response = new JsonReader(weatherByCoord(String.valueOf(latitude), String.valueOf(longitude))).dataToString();
    }

    public void getWeatherByIDCLI(String id) throws Exception{
        response = new JsonReader(weatherByID(id)).dataToString();
    }
}




