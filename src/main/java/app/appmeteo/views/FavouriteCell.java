package app.appmeteo.views;

import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static app.appmeteo.model.utils.CommandesUtils.chooseIcon;

public class FavouriteCell extends Pane {

    private final int ID;
    private final View mainView;
    private final String cityName;
    private javafx.scene.control.Label labelCityName;
    private javafx.scene.control.Label rightClickPanel;
    private ImageView iconView;

    public FavouriteCell(String cityName, String hour, int temp, int ID, String country, View view, Boolean isDay, int weatherId) {
        super();
        setPrefHeight(100);
        setPrefWidth(160);
        setStyle("-fx-background-color: rgb(55, 55, 55);");
        mainView = view;
        this.ID = ID;
        setLabel(cityName, hour, temp, country, isDay, weatherId);
        setDeleteOptionAndEvent();
        setVisible(true);
        this.cityName = cityName;
    }

    private void setDeleteOptionAndEvent() {
        rightClickPanel = new Label();
        rightClickPanel.setPrefHeight(100);
        rightClickPanel.setPrefWidth(160);
        rightClickPanel.relocate(0, 0);
        setEvent(rightClickPanel);
        rightClickPanel.setVisible(true);
        rightClickPanel.setContextMenu(addContextMenu());
        getChildren().add(rightClickPanel);
    }

    private void setEvent(Label label) {
        label.setOnMouseEntered((event -> label.setStyle("-fx-border-width: 1px; -fx-border-color: #454545;-fx-effect: " +
                "dropshadow(three-pass-box, rgba(255,255,255,0.8), 10, 0, 0, 0);")));

        label.setOnMouseExited((event -> label.setStyle("-fx-border-width: 0px;")));

        rightClickPanel.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                if (event.getClickCount() == 2) showInCurrentWeather();
            }
        });
    }

    private ContextMenu addContextMenu() {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem menuItemDelete = new MenuItem();
        menuItemDelete.setOnAction((event -> deleteItem()));
        menuItemDelete.setText("Supprimer");
        MenuItem menuItemShowCurrentWeather = new MenuItem();
        menuItemShowCurrentWeather.setOnAction((event -> showInCurrentWeather()));
        menuItemShowCurrentWeather.setText("Afficher la m\u00e9t\u00e9o");
        contextMenu.getItems().addAll(menuItemShowCurrentWeather, menuItemDelete);
        return contextMenu;
    }

    public int getID() {
        return ID;
    }

    private void deleteItem() {
        mainView.removeFavourite(this);
    }

    private void showInCurrentWeather() {
        mainView.askCurrentWeather(labelCityName.getText());
    }

    private void setLabel(String cityName, String hour, int temp, String country, boolean isDay, int weatherId) {
        labelCityName = new javafx.scene.control.Label();
        labelCityName.relocate(0, 7);
        labelCityName.setPrefWidth(160);
        labelCityName.setPrefHeight(16);
        labelCityName.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        labelCityName.setText(cityName + ", " + country);
        labelCityName.setContentDisplay(ContentDisplay.CENTER);
        labelCityName.setAlignment(Pos.CENTER);
        labelCityName.setVisible(true);

        Label labelTemp = new Label();
        labelTemp.relocate(100, 35);
        labelTemp.setAlignment(Pos.CENTER_RIGHT);
        labelTemp.prefHeight(49);
        labelTemp.prefWidth(62);
        labelTemp.setFont(new Font(27));
        labelTemp.setText(temp + "\u00b0");
        labelTemp.setVisible(true);

        Label labelHour = new Label();
        labelHour.relocate(115, 79);
        labelHour.prefHeight(15);
        labelHour.setPrefWidth(34);
        labelHour.setText(hour);
        labelHour.setTextFill(Color.GRAY);
        labelHour.setFont(new Font(10));
        labelHour.setVisible(true);

        iconView = new ImageView();
        iconView.setFitHeight(45);
        iconView.setFitWidth(45);
        iconView.relocate(15, 32);
        iconView.setPreserveRatio(true);

        setImage(weatherId, isDay);
        iconView.setVisible(true);

        getChildren().add(labelCityName);
        getChildren().add(labelTemp);
        getChildren().add(labelHour);
        getChildren().add(iconView);
    }

    private void setImage(int id, Boolean isDay) {
        String path = "src/main/resources/icons/";
        path += isDay ? "day/" : "night/";
        path += chooseIcon(id);
        try {
            InputStream inputStream = new FileInputStream(path);
            Image image = new Image(inputStream);
            iconView.setImage(image);
        } catch (FileNotFoundException e) {
            System.out.println("icon not found for favourites");
        }
    }

    public String getCityName() {
        return this.cityName;
    }

}
