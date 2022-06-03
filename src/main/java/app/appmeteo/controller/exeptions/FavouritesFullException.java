package app.appmeteo.controller.exeptions;

public class FavouritesFullException extends FavouriteException {

    @Override
    public String getMessage() {
        return "Favourites is full";
    }
}
