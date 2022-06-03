package app.appmeteo.model.utils;

import static app.appmeteo.model.utils.CommandesUtils.parseSearchField;

public class Request {
    private String id;
    private String location;
    private double longitude;
    private double latitude;

    public Request(String location){
        this.location = parseSearchField(location);
    }

    public Request(double latitude, double longitude){
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Request(){
        this.location = "marseille";
    }

    public double getLongitude(){
        return longitude;
    }

    public double getLatitude(){
        return latitude;
    }

    public String getLocation() {
        return location;
    }
}
