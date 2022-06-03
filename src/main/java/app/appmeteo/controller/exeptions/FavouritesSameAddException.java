package app.appmeteo.controller.exeptions;

public class FavouritesSameAddException extends FavouriteException {

    @Override
    public String getMessage() {
        return "This fav is already in.";
    }
}
