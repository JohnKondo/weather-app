package app.appmeteo.model.utils;

import app.appmeteo.model.MODEL;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ModelTest {


    @Test
    public void addFavouriteTest() throws Exception {

        MODEL model = new MODEL();

        String city = "Marseille";

        Request request = new Request();

        model.getWeatherByCity(city);
        model.addFavourite();

        city = "Paris";
        model.getWeatherByCity(city);
        model.addFavourite();

        try {
            Scanner scanner = new Scanner(new FileReader("src/main/resources/data/Favourites.csv"));
            StringBuilder line = new StringBuilder();
            while(scanner.hasNextLine()){
                line.append(scanner.nextLine()).append("\n");
            }
            scanner.close();
            assertEquals("Marseille" + "\n" + "Paris" + "\n", line.toString());

        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }


    }

    @Test
    public void removeFavouriteTest() throws Exception {

        MODEL model = new MODEL();
        String city = "Marseille";

        Request request = new Request();

        model.getWeatherByCity(city);
        model.addFavourite();

        city = "Paris";
        model.getWeatherByCity(city);
        model.addFavourite();

        model.removeFavorite(city);

        try {
            Scanner scanner = new Scanner(new FileReader("src/main/resources/data/Favourites.csv"));
            StringBuilder line = new StringBuilder();
            while(scanner.hasNextLine()){
                line.append(scanner.nextLine()).append("\n");
            }
            scanner.close();
            assertEquals("Marseille" + "\n", line.toString());

        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }

    }

    /* ----------- Test à faire ----------- */

//    @Test
//    public void removeFavoriteIDTest() throws Exception {
//
//        MODEL model = new MODEL();
//
//        Favourites favourites = new Favourites();
//        Favourite favourite = new Favourite(16, "Marseille", "13:00", "France", 16, 1600,
//                true);
//
//        favourites.addFavourites(favourite);
//
//        int ID = 16;
//
//        model.removeFavorite(ID);
//
//
//        try {
//            Scanner scanner = new Scanner(new FileReader("src/main/resources/data/Favourites.csv"));
//            String line = "";
//            while(scanner.hasNextLine()){
//                line += scanner.nextLine() + "\n";
//            }
//            scanner.close();
//            assertEquals("", line);
//
//        } catch (FileNotFoundException exception) {
//            exception.printStackTrace();
//        }
//    }


    /* ----------- Test à faire ----------- */


//    @Test
//    public void loadFavouritesTest() throws Exception {
//
//        MODEL model = new MODEL();
//
//        ArrayList<String> favouritesListCityName = new ArrayList<>();
//        favouritesListCityName.addAll(Arrays.asList("Marseille", "Paris", "Brest"));
//
//        Favourites favourites = new Favourites();
//
//
//        model.loadFavourites(favouritesListCityName);
//
//        System.out.println("///////////////////////////////// " + "\n"
//                + favourites + "\n" + "////////////////////////////////");
//
//    }

}