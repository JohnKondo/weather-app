package app.appmeteo.controller.api;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import static app.appmeteo.model.utils.CommandesUtils.parseSearchField;

public class WeatherAPI {

    private static String weather(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.connect();

        int responseCode = connection.getResponseCode();

        if (responseCode != 200) throw new RuntimeException("HttpResponseCode: " + responseCode);

        else {
            StringBuilder inline = new StringBuilder();
            Scanner scanner = new Scanner(url.openStream());

            while (scanner.hasNext()) {
                inline.append(scanner.nextLine());
            }

            scanner.close();

            return inline.toString();
        }
    }


    public static String weatherByName(String location) throws IOException {
        String urlString = "http://api.openweathermap.org/data/2.5/weather?q=" + location + "&appid=5b253fb82afb37c092624c1503aa499a&units=metric&lang=fr";
        return weather(urlString);
    }

    public static String weatherByCoord(String lat, String lon) throws IOException {
        String urlString = "http://api.openweathermap.org/data/2.5/weather?lat=" + lon + "&lon=" + lat + "&appid=5b253fb82afb37c092624c1503aa499a&units=metric&lang=fr";
        return weather(urlString);
    }

    public static String weatherByID(String id) throws IOException {
        String urlString = "http://api.openweathermap.org/data/2.5/weather?id=" + id + "&appid=5b253fb82afb37c092624c1503aa499a&units=metric&lang=fr";
        return weather(urlString);
    }

    public static String forecastFiveDays(String location) throws IOException {
        String urlString = "http://api.openweathermap.org/data/2.5/forecast?q=" + location + "&appid=5b253fb82afb37c092624c1503aa499a&units=metric&lang=fr";
        return weather(urlString);
    }

    public static String forecastHourly(double lon, double lat) throws IOException {
        String urlString = "https://api.openweathermap.org/data/2.5/onecall?lat=" + lat + "&lon=" + lon + "&exclude=current,minutely,daily,alerts&appid=5b253fb82afb37c092624c1503aa499a&units=metric&lang=fr";
        return weather(urlString);
    }

    public static String getMapURL(String location) {
        return "https://www.mapquestapi.com/staticmap/v5/map?key=pG7IY3y05w2Bmhtjy3cSaxRtmI19nSpo&locations=" + parseSearchField(location) + "&size=252,219&zoom=2&type=dark&defaultMarker=marker-262626-sm";
    }

}


