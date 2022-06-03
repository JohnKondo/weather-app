package app.appmeteo.model.utils;

import app.appmeteo.model.favourite.Favourite;
import app.appmeteo.model.favourite.Favourites;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class FavouritesUtils {

    public static ArrayList<String> LoadFavouritesFromCsv(String path) throws FileNotFoundException {
        ArrayList<String> result = new ArrayList<>();
        Scanner scanner = new Scanner( new File(path));
        while(scanner.hasNextLine()){
            result.add(scanner.nextLine());
        }
        scanner.close();
        return result;
    }

    public static void updateFavouritesCsv(Favourites favourites){
        try {
            PrintWriter writer = new PrintWriter("src/main/resources/data/Favourites.csv");
            for(Favourite fav : favourites.getFavourites()){
                writer.print(fav.getCityName() + "\n");
            }
            writer.close();
        }
        catch (FileNotFoundException e){
            System.out.println("Can't update database because the file is not found");
        }
    }

}
