package app.appmeteo.model.utils;

import java.util.ArrayList;

public class HoursLabelUtils {

    public static ArrayList<String> buildHoursLabel(int now){
        ArrayList<String> result = new ArrayList<>();
        result.add(" ");
        for(int i = 1; i < 12; i++){
            int currentHour = now + i;
            if(currentHour > 23){
                currentHour = currentHour - 24;
            }
            result.add(currentHour + "h");
        }
        return result;
    }
}
