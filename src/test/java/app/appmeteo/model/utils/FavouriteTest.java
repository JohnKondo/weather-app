package app.appmeteo.model.utils;

import app.appmeteo.controller.exeptions.FavouriteException;
import app.appmeteo.model.favourite.Favourite;
import app.appmeteo.model.favourite.Favourites;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static app.appmeteo.model.utils.FavouritesUtils.updateFavouritesCsv;
import static org.junit.jupiter.api.Assertions.*;


public class FavouriteTest {

    @Test
    public void loadFavouriteFromCSVTest() throws FileNotFoundException {
        String path = "src/main/resources/data/Favourites.csv";
        ArrayList<String> expected = new ArrayList<>(Arrays.asList("Marseille", "Brest", "Paris"));
        assertEquals(expected, FavouritesUtils.LoadFavouritesFromCsv(path));
    }

    @Test
    public void updateFavouritesCsvTest() throws FavouriteException {

        Favourites favourites = new Favourites();
        Favourite favourite = new Favourite(16, "Marseille", "13:00", "France", 16, 1600,
                true);
        Favourite favourite1 = new Favourite(12, "Paris", "13:00", "France", 15, 1600,
                true);
        favourites.addFavourites(favourite);
        favourites.addFavourites(favourite1);

        updateFavouritesCsv(favourites);

        try {
            Scanner scanner = new Scanner(new FileReader("src/main/resources/data/Favourites.csv"));
            String lastLine = "";
            while(scanner.hasNextLine()){
                lastLine = scanner.nextLine();
            }
            scanner.close();
            assertEquals(favourite1.getCityName(), lastLine);
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }


    }

}
