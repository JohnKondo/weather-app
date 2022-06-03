package app.appmeteo.model.favourite;

public class Favourite {

    private final int temperature;
    private final String cityName;
    private final String country;
    private final String hour;
    private final int ID;
    private final int weatherId;
    private final boolean isDay;

    public Favourite(int temp, String cityName, String hour, String country, int ID, int weatherId, Boolean isDay) {
        this.temperature = temp;
        this.cityName = cityName;
        this.hour = hour;
        this.country = country;
        this.ID = ID;
        this.weatherId = weatherId;
        this.isDay = isDay;
    }


    public int getTemperature() {
        return temperature;
    }

    public String getCityName() {
        return cityName;
    }

    public String getHour() {
        return hour;
    }

    public String getCounty() {
        return country;
    }

    public int getID() {
        return ID;
    }

    public boolean isDay() {
        return isDay;
    }

    public int getWeatherId() {
        return weatherId;
    }
}
