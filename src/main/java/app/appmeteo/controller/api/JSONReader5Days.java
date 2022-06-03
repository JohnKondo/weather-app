package app.appmeteo.controller.api;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JSONReader5Days {

    private final List<Double> listOfMaxs = new ArrayList<>();
    private final List<Double> listOfMins = new ArrayList<>();
    private final List<Integer> listOfID = new ArrayList<>();
    private final List<Integer> listOfUnixDate = new ArrayList<>();
    private final List<String> listOfTextDate = new ArrayList<>();
    private final List<Forecast> listOfForecast = new ArrayList<>();
    private int id;
    private String name;


    public JSONReader5Days(String json) throws JSONException {
        ParseResult(json);
    }

    private void ParseResult(String json) throws JSONException {
        JSONObject jsonObject = new JSONObject(json);

        JSONObject JSONObject_city = jsonObject.getJSONObject("city");
        id = JSONObject_city.getInt("id");
        name = JSONObject_city.getString("name");
        String country = JSONObject_city.getString("country");

        int timezone = JSONObject_city.getInt("timezone");

        JSONArray JSONArray_list = jsonObject.getJSONArray("list");

        for (int i = 0; i < JSONArray_list.length(); i++) {
            JSONObject objects = JSONArray_list.getJSONObject(i);
            JSONObject main = objects.getJSONObject("main");
            Double temp_min = main.getDouble("temp_min");
            Double temp_max = main.getDouble("temp_max");

            JSONArray weather = objects.getJSONArray("weather");
            JSONObject weather_object = (JSONObject) weather.get(0);
            int weather_id = weather_object.getInt("id");

            int dt = objects.getInt("dt");
            String dt_txt = objects.getString("dt_txt");

            listOfMaxs.add(temp_max);
            listOfMins.add(temp_min);
            listOfID.add(weather_id);
            listOfUnixDate.add(dt);
            listOfTextDate.add(dt_txt);
        }
        determineForecast();
    }

    public void determineForecast() {
        Double min = 1000.0;
        Double max = -1000.0;
        int noon_index = 4;
        int index = 0;

        // On a des listes de 40 éléments, chaque groupe de 8 éléments correspondent à un jour
        // On veut donc naviguer jour par jour (=5 fois) à travers les 8 éléments.
        for (int day = 0; day < 5; day++) {
            for (int time_slot = 0; time_slot < 8; time_slot++) {

                if (min > listOfMins.get(time_slot + index)) {
                    min = listOfMins.get(time_slot + index);
                }
                if (max < listOfMaxs.get(time_slot + index)) {
                    max = listOfMaxs.get(time_slot + index);
                }
            }


            int weather_id = findWorstId(listOfID, index); // à midi
            int unix_date = listOfUnixDate.get(noon_index); // à midi

            Forecast forecast = new Forecast(unix_date, (int) Math.round(min), (int) Math.round(max), weather_id);
            listOfForecast.add(forecast);


            noon_index = noon_index + 8;
            index = index + 8;
            min = 1000.0;
            max = -1000.0;
        }
    }

    public int findWorstId(List<Integer> idList, int offset) {
        int worstId = 1000;
        for (int i = offset; i < 8 + offset; i++) {
            int idListVar = idList.get(i);
            if (worstId == 800 && idListVar > 800) {
                worstId = idListVar;
                continue;
            }
            if (idListVar < worstId) worstId = idListVar;

        }
        return worstId;
    }

    public Forecast getSpecificDay(int day) {
        return listOfForecast.get(day - 1);
    }

    public String dataToString() {
        StringBuilder content = new StringBuilder();
        int index = 0;

        for (Forecast forecast : listOfForecast) {
            content.append(forecast.getDay()).append(": ").append(forecast.getMin()).append(" - ").append(forecast.getMax()).append(", ").append(forecast.getId()).append("\n");
            index++;
        }
        return "Voici votre météo à " + this.name + " sur les cinq prochains jours:\n" + content;
    }

    public int getId() {
        return id;
    }


}





