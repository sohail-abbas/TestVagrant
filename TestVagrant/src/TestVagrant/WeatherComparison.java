package TestVagrant;

import java.io.IOException;

public class WeatherComparison {
    public static void main(String[] args) throws IOException {


        String openWeatherTemp;


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
