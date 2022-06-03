package app.appmeteo.views.UX_utils;

import app.appmeteo.views.HourlyWeatherView;
import javafx.util.StringConverter;

import java.util.ArrayList;
import java.util.Arrays;

public class SliderC extends javafx.scene.control.Slider {

    private final HourlyWeatherView hourlyWeatherView;
    private int oldValue;

    public SliderC(HourlyWeatherView hourlyWeatherView) {
        super();
        relocate(57, 115);
        setPrefHeight(44);
        setPrefWidth(475);
        setMin(0);
        setMax(11);
        setValue(0);
        setMinorTickCount(0);
        setMajorTickUnit(1);
        setSnapToTicks(true);
        setShowTickMarks(true);
        setShowTickLabels(true);
        oldValue = 0;
        updateSliderLabels(new ArrayList<>(Arrays.asList("1h",
                "2h",
                "3h",
                "4h",
                "5h",
                "6h",
                "7h",
                "8h",
                "9h",
                "10h",
                "11h",
                "12h"
        )));
        addSliderListener();
        setVisible(true);
        this.hourlyWeatherView = hourlyWeatherView;
    }

    public SliderC(HourlyWeatherView hourlyWeatherView, ArrayList<String> labelsName) {
        super();
        relocate(57, 115);
        setPrefHeight(44);
        setPrefWidth(475);
        setMin(0);
        setMax(11);
        setValue(0);
        setMinorTickCount(0);
        setMajorTickUnit(1);
        setSnapToTicks(true);
        setShowTickMarks(true);
        setShowTickLabels(true);
        oldValue = 0;
        updateSliderLabels(labelsName);
        addSliderListener();
        setVisible(true);
        this.hourlyWeatherView = hourlyWeatherView;
    }

    private void addSliderListener() {

        valueProperty().addListener((changed, oldValue, newValue) -> {
            int nv = (int) Math.round((double) newValue);
            if (nv != this.oldValue) {
                hourlyWeatherView.updateHourlyWeather(nv);
                this.oldValue = nv;
            }
        });
    }


    private void updateSliderLabels(final ArrayList<String> labelNames) {

        setLabelFormatter(new StringConverter<>() {
            @Override
            public String toString(Double n) {
                if (n < 0.5) return labelNames.get(0);
                if (n < 1.5) return labelNames.get(1);
                if (n < 2.5) return labelNames.get(2);
                if (n < 3.5) return labelNames.get(3);
                if (n < 4.5) return labelNames.get(4);
                if (n < 5.5) return labelNames.get(5);
                if (n < 6.5) return labelNames.get(6);
                if (n < 7.5) return labelNames.get(7);
                if (n < 8.5) return labelNames.get(8);
                if (n < 9.5) return labelNames.get(9);
                if (n < 10.5) return labelNames.get(10);
                return labelNames.get(11);
            }

            @Override
            public Double fromString(String s) {
                if (s.equals(labelNames.get(0))) {
                    return 0d;
                }

                if (s.equals(labelNames.get(1))) {
                    return 1d;
                }
                if (s.equals(labelNames.get(2))) {
                    return 2d;
                }

                if (s.equals(labelNames.get(3))) {
                    return 3d;
                }
                if (s.equals(labelNames.get(4))) {
                    return 4d;
                }

                if (s.equals(labelNames.get(5))) {
                    return 5d;
                }
                if (s.equals(labelNames.get(6))) {
                    return 6d;
                }

                if (s.equals(labelNames.get(7))) {
                    return 7d;
                }
                if (s.equals(labelNames.get(8))) {
                    return 8d;
                }

                if (s.equals(labelNames.get(9))) {
                    return 9d;
                }
                if (s.equals(labelNames.get(10))) {
                    return 10d;
                }

                if (s.equals(labelNames.get(11))) {
                    return 11d;
                }

                return -1d;
            }
        });

    }

}