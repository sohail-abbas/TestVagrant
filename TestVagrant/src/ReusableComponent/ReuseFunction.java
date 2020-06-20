package ReusableComponent;

import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;



public class ReuseFunction {

    public static String MyGETRequest(String City) throws IOException {
        String strUrl;
        String oWTemp = null;
        String strCity = City;
        strUrl = "http://api.openweathermap.org/data/2.5/weather?q="+strCity+"&appid=7fe67bf08c80ded756e598d6f8fedaea&units=metric";
        URL urlForGetRequest = new URL(strUrl);
        String readLine = null;
        HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
        conection.setRequestMethod("GET");
        conection.setRequestProperty("userId", "a1bcdef"); // set userId its a sample here
        int responseCode = conection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conection.getInputStream()));
            StringBuffer response = new StringBuffer();
            while ((readLine = in .readLine()) != null) {
                response.append(readLine);
            } in .close();
            //System.out.println("JSON String Result " + response.toString());
            Object obj = null;
            Object mainobj = null;
            try {
                obj = new JSONParser().parse(response.toString());
                JSONObject jump = (JSONObject) obj;
                String main = (String) jump.get("main").toString();
                mainobj = new JSONParser().parse(main);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            JSONObject mainjson = (JSONObject) mainobj;
            String temp = (String) mainjson.get("temp").toString();
            String humidity = (String) mainjson.get("humidity").toString();
            oWTemp = temp+":"+humidity;


        } else {
            System.out.println("GET NOT WORKED");
        }
        return oWTemp;
    }

}
