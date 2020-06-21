package TestVagrant;

import java.io.IOException;

public class WeatherComparison {
    public static void main(String[] args) throws IOException {
        String ndtvPath = "https://www.ndtv.com/";
        ReusableComponent.ReuseFunction.TestCity(ndtvPath,"New Delhi");
        ReusableComponent.ReuseFunction.TestCity(ndtvPath,"Lucknow");
    }
}
