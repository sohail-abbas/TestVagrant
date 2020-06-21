package TestVagrant;

import java.io.IOException;

public class WeatherComparison {
    public static void main(String[] args) throws IOException {

        String ndtvTemp;
        String openWeatherTemp;
        ndtvTemp = ReusableComponent.ReuseFunction.BrowseWeather("https://www.ndtv.com/","New Delhi");
        System.out.println(ndtvTemp);
        System.out.println("-------------1-----------");

        int h = ndtvTemp.indexOf("Humidity:");
        int d = ndtvTemp.indexOf("Degrees:");
        String ndtvhumid = ndtvTemp.substring(h+10, h+14);
        String ndtvtempd = ndtvTemp.substring(d+9, d+11);
        System.out.println(ndtvhumid);
        System.out.println(ndtvtempd);
        System.out.println("-------------2-----------");

        openWeatherTemp = ReusableComponent.ReuseFunction.MyGETRequest("New Delhi");
        System.out.println(openWeatherTemp);
        System.out.println("-------------3-----------");
        int s = openWeatherTemp.indexOf(":");
        int l = openWeatherTemp.length();
        String oWhumid = openWeatherTemp.substring(s+1, l);
        String oWtemp = openWeatherTemp.substring(0, s);
        System.out.println(oWhumid);
        System.out.println(oWtemp);



    }
}
