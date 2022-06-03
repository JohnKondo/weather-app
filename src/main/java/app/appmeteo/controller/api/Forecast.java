package app.appmeteo.controller.api;

import static app.appmeteo.model.utils.CommandesUtils.TimeStampToWeekDay;

public class Forecast {
    private int min;
    private int max;
    private final int unix;
    private String day;
    private Double wind_deg;
    private int wind_speed;
    private Double precipitations;
    private int temp;
    private int clouds;
    private String description;
    private final int id;

    public Forecast(int unix, int min, int max, int id) {
        this.unix = unix;
        setDay(unix);

        this.min = min;
        this.max = max;
        this.id = id;
    }

    public Forecast(int unix, int temp, String description, int id,
                    int wind_speed, Double wind_deg, int clouds, Double precipitations) {
        this.unix = unix;
        this.temp = temp;
        this.description = description;
        this.id = id;
        this.clouds = clouds;
        this.wind_speed = wind_speed;
        this.wind_deg = wind_deg;
        this.precipitations = precipitations;
    }

    public String toString() {
        return "Jour: " + this.day
                + " Min: " + this.min
                + " Max: " + this.max + " ID: " + this.id;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public String getDay() {
        return day;
    }

    private void setDay(long unix) {
        this.day = TimeStampToWeekDay(unix);
    }

    public int getId() {
        return id;
    }

    public int unix() {
        return unix;
    }

    public int getUnix() {
        return unix;
    }

    public Double getWind_deg() {
        return wind_deg;
    }

    public int getWind_speed() {
        return wind_speed;
    }

    public Double getPrecipitations() {
        return precipitations;
    }

    public int getTemp() {
        return temp;
    }

    public int getClouds() {
        return clouds;
    }

    public String getDescription() {
        return description;
    }
}

