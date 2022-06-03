package app.appmeteo.controller.exeptions;

public class CurrentWeatherViewEmptyException extends FavouriteException {

    public String getMessage() {
        return "Select a city to add it to favourites";
    }
}
