package app.appmeteo.controller.api;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static app.appmeteo.model.utils.CommandesUtils.TimeStampToDateHours;
import static app.appmeteo.model.utils.CommandesUtils.TimeStampToDateSimple;

public class JsonReader {

    //Coord


    private boolean isDay;
    private int currentHour;
    private Double longitude;
    private Double latitude;

    //"sys"
    private String country;
    private String sunrise;
    private String sunset;
    private long sunriseUnix;
    private long sunsetUnix;

    //"weather"
    private int id;
    private String main;
    private String description;
    private String icon;

    //"base"
    private String base;

    //main
    private int temp;
    private int feels_like;
    private Double pressure;
    private int humidity;
    private int temp_min;
    private int temp_max;

    //"wind"
    private int speed;
    private int deg;

    //"clouds"
    private int cloud;

    //"dt"
    private String dt;
    private String hour;

    //"id"
    private int ID;

    //"name"
    private String name;

    //"cod"
    private int cod;


    public JsonReader(String json) throws JSONException {
        ParseResult(json);
    }

    public void isDay(long sunrise, long sunset, long currentTime) {
        isDay = currentTime > sunrise && currentTime < sunset;
    }


    private void ParseResult(String json) throws JSONException {
        JSONObject jsonObject = new JSONObject(json);

        //"coord"
        JSONObject JSONObject_coord = jsonObject.getJSONObject("coord");
        longitude = JSONObject_coord.getDouble("lon");
        latitude = JSONObject_coord.getDouble("lat");

        //"sys"
        JSONObject JSONObject_sys = jsonObject.getJSONObject("sys");
        country = JSONObject_sys.getString("country");
        sunrise = TimeStampToDateHours(JSONObject_sys.getInt("sunrise"), jsonObject.getInt("timezone"));
        sunset = TimeStampToDateHours(JSONObject_sys.getInt("sunset"), jsonObject.getInt("timezone"));
        sunriseUnix = JSONObject_sys.getInt("sunrise");
        sunsetUnix = JSONObject_sys.getInt("sunset");
        isDay(JSONObject_sys.getInt("sunrise"), JSONObject_sys.getInt("sunset"), jsonObject.getInt("dt"));

        //"weather"
        String result_weather;
        JSONArray JSONArray_weather = jsonObject.getJSONArray("weather");
        if (JSONArray_weather.length() > 0) {
            JSONObject JSONObject_weather = JSONArray_weather.getJSONObject(0);
            id = JSONObject_weather.getInt("id");
            main = JSONObject_weather.getString("main");
            description = JSONObject_weather.getString("description");
            icon = JSONObject_weather.getString("icon");
        }

        //"base"
        base = jsonObject.getString("base");

        //"main"
        JSONObject JSONObject_main = jsonObject.getJSONObject("main");
        temp = (int) Math.round(JSONObject_main.getDouble("temp"));
        feels_like = (int) Math.round(JSONObject_main.getDouble("feels_like"));
        pressure = JSONObject_main.getDouble("pressure");
        humidity = (int) JSONObject_main.getDouble("humidity");
        temp_min = (int) Math.round(JSONObject_main.getDouble("temp_min"));
        temp_max = (int) Math.round(JSONObject_main.getDouble("temp_max"));

        //"wind"
        JSONObject JSONObject_wind = jsonObject.getJSONObject("wind");
        speed = (int) Math.round(JSONObject_wind.getDouble("speed") * 3.6);
        deg = (int) JSONObject_wind.getDouble("deg");


        //"clouds"
        JSONObject JSONObject_clouds = jsonObject.getJSONObject("clouds");
        cloud = JSONObject_clouds.getInt("all");

        //"dt"
        dt = TimeStampToDateSimple(jsonObject.getInt("dt"));
        hour = TimeStampToDateHours(jsonObject.getInt("dt"), jsonObject.getInt("timezone"));
        currentHour = Integer.parseInt(hour.substring(0, 2));

        //"id"
        ID = jsonObject.getInt("id");

        //"name"
        name = jsonObject.getString("name");

        //"cod"
        cod = jsonObject.getInt("cod");


    }

    public String dataToString() {
        return "\nDate: " + dt + "" +
                "\nID de la ville: " + ID +
                "\nCoordonnées -> \tlongitude: " + longitude + "\t\tlatitude: " + latitude + "\n" +
                "Lever du soleil: \t" + sunrise + "\t\tCoucher du soleil : " + sunset + "\n" +
                "Température: :\t" + temp + "\u00b0C" +
                "\nTemps" + description +
                "\nHumidité:    " + humidity + "%\n" +
                "Pression: " + pressure + "hpa\n" +
                "Température minimale:  \t\t" + temp_min + "°C\t\tTempérature maximale: " + temp_max + "°C\n" +
                "Vent\t" + "Vitesse: \t\t" + speed + " km/h\t\tDirection: " + deg + "°\n" +
                "Couverture nuageuse: \t\t" + cloud + " %\n\n";
    }

    ////////////////////
    ///getter's data///
    ///////////////////
    public Double getLongitude() {
        return longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public String getCountry() {
        return country;
    }

    public String getSunrise() {
        return sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public int getId() {
        return id;
    }

    public String getMain() {
        return main;
    }

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }

    public String getBase() {
        return base;
    }

    public int getTemp() {
        return temp;
    }

    public int getFeels_like() {
        return feels_like;
    }

    public Double getPressure() {
        return pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public int getTemp_min() {
        return temp_min;
    }

    public int getTemp_max() {
        return temp_max;
    }

    public int getSpeed() {
        return speed;
    }

    public int getDeg() {
        return deg;
    }

    public int getCloud() {
        return cloud;
    }

    public String getDt() {
        return dt;
    }

    public String getHour() {
        return hour;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public int getCod() {
        return cod;
    }

    public boolean isDay() {
        return isDay;
    }

    public long getSunriseUnix() {
        return sunriseUnix;
    }

    public long getSunsetUnix() {
        return sunsetUnix;
    }

    public int getCurrentHour() {
        return currentHour;
    }
}
