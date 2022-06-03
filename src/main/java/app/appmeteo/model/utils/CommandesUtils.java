package app.appmeteo.model.utils;

import java.security.InvalidParameterException;
import java.text.SimpleDateFormat;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class CommandesUtils {

    public static String helper() {
        return  "usage: WeatherApp\n" +
                "-------------------------------------------\n"+
                " h                             shows commands\n\n" +
                " m <City_name>                 get the current weather of the input city\n"+
                "                               default : city=Marseille\n\n"+
                " i <City_id>                   get the current weather of the input ID\n"+
                "                               default : latitude = 43.3 longitude = 5.5\n\n"+
                " c <latitude> <longitude>      get the current weather of the input city\n"+
                "-------------------------------------------\n";
    }

    public static String[] parseCommandeL(String c) throws InvalidParameterException{
        String[] s = c.split(" ");
        if(s.length != 3 && !s[0].equals("m")) throw new InvalidParameterException();
        return s;
    }

    public static String[] parseCommandeLocation(String c) throws InvalidParameterException{
        String[] s = c.split(" ");
        if(s.length != 2 && !s[0].equals("c")) throw new InvalidParameterException();
        return s;
    }

    public static String[] parseCommandeID(String c) throws InvalidParameterException{
        String[] s = c.split(" ");
        if(s.length != 2 && !s[0].equals("i")) throw new InvalidParameterException();
        return s;
    }

    public static String parseSearchField(String s) throws InvalidParameterException{
        s = s.replace(" ","+");
        return s;
    }

    public static String TimeStampToDateSimple(long yourDate){
        Date date = new Date(yourDate*1000);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        return format.format(date);
    }

    @Deprecated
    public static String TimeStampToWeekDay(long yourDate){
        Date date = new Date(yourDate*1000);
        switch (date.getDay()){
            case 0 : return "DIMANCHE";
            case 1 : return "LUNDI";
            case 2: return "MARDI";
            case 3 : return "MERCREDI";
            case 4 : return "JEUDI";
            case 5: return "VENDREDI";
            case 6 : return "SAMEDI";
        }
        return "pb";
    }

    public static String TimeStampToDateHours(long yourDate, long timezone){
        String utcString = ZonedDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.ofPattern("HH"));
        Date date = new Date((yourDate+timezone+(Integer.parseInt(utcString)-Calendar.getInstance().get(Calendar.HOUR_OF_DAY))* 3600L)*1000);
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        return format.format(date);
    }

    public static boolean isDay(long sunrise,long sunset, long currentTime){
        return currentTime > sunrise && currentTime < sunset;
    }

    public static void printWelcome(){
        System.out.println("" +
                "  ______                         __       __            __                       \n" +
                " /      \\                       |  \\     /  \\          |  \\                      \n" +
                "|  ▓▓▓▓▓▓\\ ______   ______      | ▓▓\\   /  ▓▓ ______  _| ▓▓_    ______   ______  \n" +
                "| ▓▓__| ▓▓/      \\ /      \\     | ▓▓▓\\ /  ▓▓▓/      \\|   ▓▓ \\  /      \\ /      \\ \n" +
                "| ▓▓    ▓▓  ▓▓▓▓▓▓\\  ▓▓▓▓▓▓\\    | ▓▓▓▓\\  ▓▓▓▓  ▓▓▓▓▓▓\\\\▓▓▓▓▓▓ |  ▓▓▓▓▓▓\\  ▓▓▓▓▓▓\\\n" +
                "| ▓▓▓▓▓▓▓▓ ▓▓  | ▓▓ ▓▓  | ▓▓    | ▓▓\\▓▓ ▓▓ ▓▓ ▓▓    ▓▓ | ▓▓ __| ▓▓    ▓▓ ▓▓  | ▓▓\n" +
                "| ▓▓  | ▓▓ ▓▓__/ ▓▓ ▓▓__/ ▓▓    | ▓▓ \\▓▓▓| ▓▓ ▓▓▓▓▓▓▓▓ | ▓▓|  \\ ▓▓▓▓▓▓▓▓ ▓▓__/ ▓▓\n" +
                "| ▓▓  | ▓▓ ▓▓    ▓▓ ▓▓    ▓▓    | ▓▓  \\▓ | ▓▓\\▓▓     \\  \\▓▓  ▓▓\\▓▓     \\\\▓▓    ▓▓\n" +
                " \\▓▓   \\▓▓ ▓▓▓▓▓▓▓| ▓▓▓▓▓▓▓      \\▓▓      \\▓▓ \\▓▓▓▓▓▓▓   \\▓▓▓▓  \\▓▓▓▓▓▓▓ \\▓▓▓▓▓▓ \n" +
                "         | ▓▓     | ▓▓                                                           \n" +
                "         | ▓▓     | ▓▓                                                           \n" +
                "          \\▓▓      \\▓▓                                                           \n" +
                "                                                                           ©Gated-Inc\n\n" +
                "Bienvenue sur notre application météo.\n\n"
        );
    }

    public static String chooseIcon(int id) {
        if (id >= 200 && id < 300) {
            return "thunderStorm.png";
        } else if (id >= 300 && id < 400) {
            return "showerRain.png";
        }
        else if (id >= 500 && id < 600) {
            return "showerRain.png";
        }
        else if (id >= 600 && id < 700) {
            return "blizzard.png";
        }
        else if(id >= 700 && id < 800){
            return "mist.png";
        }
        else if( id == 800){
            return "clear.png";
        }
        else if (id >= 801 && id < 805 ){
            return "cloud.png";
        }
        return "";
    }

    public static String description(int id) {
        if (id == 200)
            return "Orage avec l\u00e9g\u00e8re pluie";

        else if (id == 201)
            return "Orage avec pluie";

        else if (id == 202)
            return "Orages avec pluie intense";

        else if (id == 210)
            return "L\u00e9gers orages";

        else if (id == 211)
            return "Orages";

        else if (id == 212)
            return "Violents orages";

        else if (id == 221)
            return "Violents orages";

        else if (id == 230)
            return "Orages avec de l\u00e9g\u00e8re averses";

        else if (id == 231)
            return "Orages avec averses";

        else if (id == 232)
            return "Orages avec violentes averses";

        else if (id == 300)
            return "L\u00e9g\u00e8re bruine";

        else if (id == 301)
            return "Bruine";

        else if (id == 302)
            return "Forte bruine";

        else if (id == 310)
            return "Pluie fine";

        else if (id == 311)
            return "Pluie fine";

        else if (id == 312)
            return "Pluie fine";

        else if (id == 313)
            return "Averses et pluie fine";

        else if (id == 314)
            return "Grosses averses et pluie fine";

        else if (id == 321)
            return "Averses";

        else if (id == 500)
            return "L\u00e9g\u00e8re pluie";

        else if (id == 501)
            return "Pluie mod\u00e9r\u00e9e";

        else if (id == 502)
            return "Forte pluie";

        else if (id == 503)
            return "Tr\u00e8s forte pluie";

        else if (id == 504)
            return "Violente pluie";

        else if (id == 511)
            return "Pluie vergla\u00e7ante";

        else if (id == 520)
            return "L\u00e9g\u00e8res averses";

        else if (id == 521)
            return "Averses";

        else if (id == 522)
            return "Grosses averses";

        else if (id == 531)
            return "Averses irr\u00e9guli\u00e8res";

        else if (id == 600)
            return "L\u00e9g\u00e8re neige";

        else if (id == 601)
            return "Neige";

        else if (id == 602)
            return "Forte neige";

        else if (id == 611)
            return "Gr\u00eale";

        else if (id == 612)
            return "L\u00e9g\u00e8re gr\u00eale";

        else if (id == 613)
            return "Forte gr\u00eale";

        else if (id == 615)
            return "Neige et pluie";

        else if (id == 616)
            return "Neige et pluie";

        else if (id == 620)
            return "L\u00e9g\u00e8re temp\u00eate de neige";

        else if (id == 621)
            return "Temp\u00eate de neige";

        else if (id == 622)
            return "Violente temp\u00eate de neige";

        else if (id == 701)
            return "Brume";

        else if (id == 711)
            return "Fum\u00e9e";

        else if (id == 721)
            return "Brume";

        else if (id == 731)
            return "Tourbillons de sable";

        else if (id == 741)
            return "Brouillard";

        else if (id == 751)
            return "Temp\u00eate de sable";

        else if (id == 761)
            return "Temp\u00eate de poussi\u00e8re";

        else if (id == 762)
            return "Cendres volcaniques";

        else if (id == 771)
            return "Rafales";

        else if (id == 781)
            return "Tornade";

        else if (id == 800)
            return "Ciel d\u00e9gag\u00e9";

        else if (id == 801)
            return "Ciel peu nuageux";

        else if (id == 802)
            return "Nuages \u00e9pars";

        else if (id == 803)
            return "Nuages morcel\u00e9s";

        else if (id == 804)
            return "Ciel couvert";

        return "";
    }

    public static String headingToString(double x)
    {
        String[] directions = {"Nord", "Nord-Est", "Est", "Sud-Est", "Sud", "Sud-Ouest", "Ouest", "Nord-Ouest", "Nord"};
        return directions[ (int)Math.round(((x % 360) / 45)) ] + " (" + x + "\u00b0)";
    }

}
