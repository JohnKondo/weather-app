package app.appmeteo.views;

import app.appmeteo.controller.WeatherAppController_CLI;
import app.appmeteo.model.MODEL;

import java.util.Scanner;

import static app.appmeteo.model.utils.CommandesUtils.printWelcome;

public class WeatherAppView_CLI {
    private final WeatherAppController_CLI controller;
    private final MODEL model;
    private String response;

    public WeatherAppView_CLI(WeatherAppController_CLI controller, MODEL model){
        this.controller = controller;
        this.model = model;
        this.response ="";
        printWelcome();
    }

    public void show(){
        if(!response.isEmpty()) System.out.println(response);
        System.out.print("AppMeteo$> ");
        askForCommand();
    }

    public void modelChanged(){
        this.response = model.getResponse();
        show();
    }

    private void askForCommand() {
        try (Scanner scanner = new Scanner(System.in)) {
            String command;
            do {
                command = scanner.nextLine();
            } while (!controller.request(command));
        }
    }
}
