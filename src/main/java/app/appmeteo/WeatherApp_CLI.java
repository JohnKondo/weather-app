package app.appmeteo;

import app.appmeteo.controller.WeatherAppController_CLI;
import app.appmeteo.model.MODEL;
import app.appmeteo.views.WeatherAppView_CLI;


public class WeatherApp_CLI {

    public static void main(String[] args) {
        MODEL MODEL = new MODEL(); // model
        WeatherAppController_CLI weatherAppControllerCLI = new WeatherAppController_CLI(MODEL); // controller
        WeatherAppView_CLI weatherAppViewCLI_ = new WeatherAppView_CLI(weatherAppControllerCLI, MODEL); // view
        weatherAppControllerCLI.addView(weatherAppViewCLI_);
        weatherAppViewCLI_.show(); // display the prompt
    }
}
