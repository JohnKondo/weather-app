package app.appmeteo.controller;

import app.appmeteo.model.MODEL;
import app.appmeteo.model.utils.Request;
import app.appmeteo.views.WeatherAppView_CLI;

import java.security.InvalidParameterException;

import static app.appmeteo.model.utils.CommandesUtils.*;


public class WeatherAppController_CLI {
    final MODEL MODEL;
    WeatherAppView_CLI view;

    public WeatherAppController_CLI(MODEL MODEL) {
        this.MODEL = MODEL;
    }

    public Boolean request(String c){
        String[] c2 = c.split(" ");
        if (c2[0].equals("getMeteoByCity") || c2[0].equals("m")) {
            try {
                String[] s = parseCommandeL(c);
                Request request;
                if (s.length == 2) request = new Request(s[1]);
                else if (s.length == 3) request = new Request(s[1] + " " + s[2]);
                else request = new Request();
                MODEL.getWeatherByCityCLI(request.getLocation());
                view.modelChanged();
                return true;
            } catch (Exception e) {
                MODEL.printHelper(helper());
                view.modelChanged();
                return false;
            }

        } else if (c.equals("help") || c.equals("h")) {
            MODEL.printHelper(helper());
            view.modelChanged();
            return false;
        } else if (c2[0].equals("getWeatherByLocation") || c2[0].equals("c")) {
            try {
                Request request;
                String[] s = parseCommandeLocation(c);
                if (s.length == 1) request = new Request(43.3, 5.5);
                else request = new Request(Double.parseDouble(s[1]), Double.parseDouble(s[2]));
                callGetWeatherByLocation(request.getLatitude(), request.getLongitude());
                return true;
            } catch (Exception e) {
                MODEL.printHelper(helper());
                view.modelChanged();
                return false;
            }
        } else if (c2[0].equals("getWeatherById") || c2[0].equals("i")) {
            try {
                Request request;
                String[] s = parseCommandeID(c);
                if (c.length() != 2) request = new Request(String.valueOf(2995468));
                else request = new Request(s[1]);
                callGetWeatherByID(request.getLocation());
                return true;
            } catch (Exception e) {
                MODEL.printHelper(helper());
                view.modelChanged();
                return false;
            }
        } else {
            MODEL.printHelper(helper());
            view.modelChanged();
            return false;
        }

    }

    private void callGetWeatherByID(String id) throws Exception{
        MODEL.getWeatherByIDCLI(id);
        view.modelChanged();
    }

    private void callGetWeatherByLocation(double latitude, double longitude) throws Exception {
        MODEL.getWeatherByLocationCLI(latitude, longitude);
        view.modelChanged();
    }

    public void addView(WeatherAppView_CLI view) {
        this.view = view;
    }
}
