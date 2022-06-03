package app.appmeteo.views;

import app.appmeteo.model.favourite.Favourite;
import app.appmeteo.model.favourite.Favourites;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FavoritesView implements Initializable {

    private final View mainView;
    private final ArrayList<FavouriteCell> favouriteCellArrayList;
    @FXML
    public VBox favouritesVbox;
    @FXML
    public Label favouriteCounter;
    private int nbFavourites;

    public FavoritesView(View view) {
        this.mainView = view;
        favouriteCellArrayList = new ArrayList<>();
    }

    public void addLastFavourite(Favourites favourites) {
        Favourite favourite = favourites.getLastFavourite();
        View mainView = this.mainView;
        FavouriteCell favouriteCell = new FavouriteCell(
                favourite.getCityName(),
                favourite.getHour(),
                favourite.getTemperature(),
                favourite.getID(),
                favourite.getCounty(),
                mainView,
                favourite.isDay(),
                favourite.getWeatherId()
        );
        favouritesVbox.getChildren().add(favouriteCell);
        favouriteCellArrayList.add(favouriteCell);
        nbFavourites = favourites.getFavourites().size();

        favouriteCounter.setText("Favoris : " + nbFavourites + "/6");
    }

    public void addAllFavourites(Favourites favourites) {
        favouriteCellArrayList.clear();
        for (Favourite favourite : favourites.getFavourites()) {
            FavouriteCell favouriteCell = new FavouriteCell(
                    favourite.getCityName(),
                    favourite.getHour(),
                    favourite.getTemperature(),
                    favourite.getID(),
                    favourite.getCounty(),
                    mainView,
                    favourite.isDay(),
                    favourite.getWeatherId()
            );
            favouriteCellArrayList.add(favouriteCell);
            favouritesVbox.getChildren().add(favouriteCell);
        }
        nbFavourites = favourites.getFavourites().size();
        favouriteCounter.setText("Favoris : " + nbFavourites + "/6");
    }

    public void removeFavourite(String cityName) throws RuntimeException {
        FavouriteCell f = null;
        for (FavouriteCell fav : favouriteCellArrayList) {
            if (fav.getCityName().equals(cityName)) f = fav;
        }
        if (f == null) throw new RuntimeException();
        removeFavourite(f);
    }

    public void removeFavourite(FavouriteCell favouriteCellToRemove) {
        favouritesVbox.getChildren().remove(favouriteCellToRemove);
        favouriteCellArrayList.remove(favouriteCellToRemove);
        favouriteCounter.setText("Favoris : " + (nbFavourites -= 1) + "/6");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        favouriteCounter.setText("Favoris : 0/6");
    }

}
