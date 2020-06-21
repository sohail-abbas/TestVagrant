package ReusableComponent;

import org.json.simple.JSONObject;
import org.json.simple.parser.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeUnit;



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
    public static String BrowseWeather(String path, String City){
        //Define Web browser and open through chrome driver.
        System.setProperty("webdriver.chrome.driver","C:\\Users\\mohabbas\\Desktop\\Resumes\\Scripts & Softwares\\Automation\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().pageLoadTimeout(145, TimeUnit.SECONDS);
        driver.get(path);
        driver.manage().timeouts().pageLoadTimeout(145, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(145, TimeUnit.SECONDS);

        driver.findElement(By.id("h_sub_menu")).click();
        driver.findElement(By.linkText("WEATHER")).click();
        driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(145, TimeUnit.SECONDS);

        driver.findElement(By.id("searchBox")).sendKeys("Srinagar");
        driver.findElement(By.id("Srinagar")).click();
        driver.findElement(By.id("searchBox")).clear();
//        driver.findElement(By.id("searchBox")).sendKeys("New Delhi");
//        driver.findElement(By.id("Srinagar")).click();
//        driver.findElement(By.id("searchBox")).clear();
        driver.findElement(By.id("searchBox")).sendKeys("Lucknow");
        driver.findElement(By.id("Lucknow")).click();
        driver.findElement(By.id("searchBox")).clear();
        driver.findElement(By.id("searchBox")).sendKeys("Patna");
        driver.findElement(By.id("Patna")).click();
        driver.findElement(By.id("searchBox")).clear();
        driver.findElement(By.id("searchBox")).sendKeys("Kolkata");
        driver.findElement(By.id("Kolkata")).click();
        driver.findElement(By.id("searchBox")).clear();
        driver.findElement(By.id("searchBox")).sendKeys("Bhopal");
        driver.findElement(By.id("Bhopal")).click();
        driver.findElement(By.id("searchBox")).clear();
        driver.findElement(By.id("searchBox")).sendKeys("Hyderabad");
        driver.findElement(By.id("Hyderabad")).click();
        driver.findElement(By.id("searchBox")).clear();
        driver.findElement(By.id("searchBox")).sendKeys("Mumbai");
        driver.findElement(By.id("Mumbai")).click();
        driver.findElement(By.id("searchBox")).clear();
        driver.findElement(By.id("searchBox")).sendKeys("Visakhapatnam");
        driver.findElement(By.id("Visakhapatnam")).click();
        driver.findElement(By.id("searchBox")).clear();
        driver.findElement(By.id("searchBox")).sendKeys("Bengaluru");
        driver.findElement(By.id("Bengaluru")).click();
        driver.findElement(By.id("searchBox")).clear();
        driver.findElement(By.id("searchBox")).sendKeys("Chennai");
        driver.findElement(By.id("Chennai")).click();
        driver.findElement(By.id("searchBox")).clear();

        driver.manage().timeouts().pageLoadTimeout(145, TimeUnit.SECONDS);
        driver.findElement(By.id("searchBox")).sendKeys(City);

        if ( !driver.findElement(By.id(City)).isSelected() )
        {
            driver.findElement(By.id(City)).click();
        }

        driver.manage().timeouts().pageLoadTimeout(145, TimeUnit.SECONDS);
        driver.findElement(By.className("cityText")).click();
        driver.manage().timeouts().pageLoadTimeout(145, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
        String getDetailText;
        getDetailText = driver.findElement(By.className("leaflet-popup-content")).getText();
        driver.quit();
        return getDetailText;
    }

}
