package app.appmeteo.views;

import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TempGraphView implements Initializable {
    public LineChart<String, Integer> graph;
    private XYChart.Series<String, Integer> series;

    public void updateGraph(ArrayList<String> labelsName, ArrayList<Integer> labelsTemp) {
        if (labelsName.size() != labelsTemp.size()) throw new InvalidParameterException(
                "labelsName and labelsTemp have different size labelsName:" +
                        labelsName.size() +
                        "labelsTemp" +
                        labelsTemp.size());
        if (!(graph.getData().isEmpty())) graph.getData().remove(series);
        series = new XYChart.Series<>();
        for (int i = 0; i < labelsTemp.size(); i++) {
            series.getData().add(new XYChart.Data<>(labelsName.get(i), labelsTemp.get(i)));
        }
        graph.getData().add(series);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
