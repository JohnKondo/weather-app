package app.appmeteo.model.favourite;

import app.appmeteo.controller.exeptions.FavouriteException;
import app.appmeteo.controller.exeptions.FavouritesFullException;
import app.appmeteo.controller.exeptions.FavouritesSameAddException;

import java.util.ArrayList;

public class Favourites {
    private final ArrayList<Favourite> favourites;

    public Favourites() {
        favourites = new ArrayList<>();
    }

    public void addFavourites(Favourite favourite) throws FavouriteException {
        if (favourites.size() == 6) throw new FavouritesFullException();

        for (Favourite f : favourites) {
            if (f.getCityName().equals(favourite.getCityName())) throw new FavouritesSameAddException();
        }
        favourites.add(favourite);
    }

    public Favourite getLastFavourite() {
        return favourites.get(favourites.size() - 1);
    }

    public ArrayList<Favourite> getFavourites() {
        return favourites;
    }

    public int getNewId() {

        if (favourites.size() == 0) {
            return 0;
        }
        return favourites.size();
    }

    public void removeFavourite(int ID) {
        favourites.removeIf(favourite -> favourite.getID() == ID);
    }

    public void removeFavourite(String cityName) {
        favourites.removeIf(favourite ->
                favourite.getCityName().equals(cityName));
    }
}
