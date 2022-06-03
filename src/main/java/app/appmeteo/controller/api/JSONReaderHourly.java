package app.appmeteo.controller.api;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JSONReaderHourly {

    private final List<Forecast> lisOfForecast = new ArrayList<>();
    private int id;
    private String name;
    private String dt_txt;
    private String goodHour;
    private final ArrayList<Integer> tempList;

    public JSONReaderHourly(String json) throws JSONException {
        tempList = new ArrayList<>();
        ParseResult(json);
    }

    private void ParseResult(String json) throws JSONException {
        JSONObject jsonObject = new JSONObject(json);

        String timezone = jsonObject.getString("timezone");

        JSONArray JSONArray_list = jsonObject.getJSONArray("hourly");

        for (int i = 0; i < 24; i++) {

            JSONObject objects = JSONArray_list.getJSONObject(i);
            Double temp = objects.getDouble("temp");
            tempList.add((int) Math.round(temp));
            JSONArray weather = objects.getJSONArray("weather");
            JSONObject weather_object = (JSONObject) weather.get(0);
            int weather_id = weather_object.getInt("id");
            String description = weather_object.getString("description");

            int clouds = objects.getInt("clouds");

            int wind_speed = (int) Math.round(objects.getDouble("wind_speed") * 3.6);
            Double wind_deg = objects.getDouble("wind_deg");
            Double precipitations = objects.getDouble("pop");

            int dt = objects.getInt("dt");

            Forecast forecast = new Forecast(dt, (int) Math.round(temp), description, weather_id,
                    wind_speed, wind_deg, clouds, precipitations);
            lisOfForecast.add(forecast);
        }


        // for (int i = 24; i < 48; i++) { -> si je veux le 2e jour
    }

    public Forecast getSpecificHour(int hour) {
        return lisOfForecast.get(hour);
    }


    public String toString() {
        StringBuilder content = new StringBuilder();
        int index = 0;

        for (Forecast forecast : lisOfForecast) {
            content.append(forecast.getUnix()).append(": ").append(forecast.getTemp()).append(" - ").append(forecast.getDescription()).append(", ").append(forecast.getWind_speed()).append("\n");
            index++;
        }
        return "Voici votre météo à " + this.name + " sur les cinq prochains jours:\n" + content;
    }

    public ArrayList<Integer> getTempList() {
        return tempList;
    }

    public int getId() {
        return id;
    }
}



