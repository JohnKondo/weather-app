package app.appmeteo.model.utils;

import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;

import static app.appmeteo.model.utils.CommandesUtils.*;
import static app.appmeteo.model.utils.HoursLabelUtils.buildHoursLabel;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandUtilsTests {
        @Test
    public void timeStampToDateSimpleTest(){
        long date = 1614245100;
        String expectedDate = "25/02/2021";
        assertEquals(expectedDate, TimeStampToDateSimple(date));
    }

    @Test
    public void headingToStringTest(){
        double x = 282;
        String expected = "Ouest (282.0°)";
        assertEquals(expected,headingToString(x));
    }

    @Test
    public void parseSearchFieldTest() throws InvalidParameterException {
        String search = "New York";
        String expected = "New+York";
        assertEquals(expected, parseSearchField(search));
    }

    @Test
    public void buildHoursLabelTest(){
        int now = 12;
        ArrayList<String> result = new ArrayList<>(Arrays.asList(" ", "13h", "14h", "15h",
                "16h", "17h", "18h", "19h", "20h", "21h", "22h", "23h"));
        assertEquals(result, buildHoursLabel(now));
    }

}


