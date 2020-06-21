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
    private static ChromeDriver driver;
    public static void TestCity(String path, String City) throws IOException {
        String ndtvTemp;
        String openWeatherTemp;
        ndtvTemp = ReusableComponent.ReuseFunction.BrowseWeather(path,City);

        int h = ndtvTemp.indexOf("Humidity:");
        int p = ndtvTemp.indexOf("%");
        int d = ndtvTemp.indexOf("Degrees:");
        String ndtvhumid = ndtvTemp.substring(h+10, p);
        String ndtvtempd = ndtvTemp.substring(d+9, d+11);

        openWeatherTemp = ReusableComponent.ReuseFunction.MyGETRequest(City);
        int s = openWeatherTemp.indexOf(":");
        int l = openWeatherTemp.length();
        String oWhumid = openWeatherTemp.substring(s+1, l);
        String oWtemp = openWeatherTemp.substring(0, s);

        double nData = Double.parseDouble(ndtvtempd) ;
        double oWData = Double.parseDouble(oWtemp);
        double n1Data = Double.parseDouble(ndtvhumid);
        double o1WData = Double.parseDouble(oWhumid);
        System.out.println("Test Completed for " + City + ", Please find result below:");
        ReusableComponent.ReuseFunction.CompareData("Temperature",nData,oWData,2.0);
        ReusableComponent.ReuseFunction.CompareData("Humidity",n1Data,o1WData,10.0);
    }
    public static void CompareData(String tempType, double ndata, double oWdata, double Variance)  {
        double d = ndata - oWdata;

        if (tempType.compareTo("Temperature") == 0){
            if (Double.compare(d,Variance) == 0 || d < Variance) {
                System.out.println("Passed: For "+ tempType +"; NDTV Data is successfully compared with oWeather Data and is within Variance");
            } else {
                System.out.println("Failed: For "+ tempType +"; NDTV Data and oWeather Data comparison failed with difference of " + d);
            }
        } else {
            double dP = (d/oWdata)*100;
            dP = Math.abs(dP);
            if (Double.compare(dP,Variance) == 0 || dP < Variance) {
                System.out.println("Passed: For "+ tempType +"; NDTV Data is successfully compared with oWeather Data and is within Variance");
            } else {
                System.out.println("Failed: For "+ tempType +"; NDTV Data and oWeather Data comparison failed with difference of " + dP);
            }
        }

    }
    public static String BrowseWeather(String path, String City){
        //Define Web browser and open through chrome driver.
        System.setProperty("webdriver.chrome.driver","C:\\Users\\mohabbas\\Desktop\\Resumes\\Scripts & Softwares\\Automation\\chromedriver.exe");
        driver = new ChromeDriver();
        System.out.println(driver.getSessionId().toString());
        driver.get(path);
        driver.manage().timeouts().pageLoadTimeout(145, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(145, TimeUnit.SECONDS);

        driver.findElement(By.id("h_sub_menu")).click();
        driver.findElement(By.linkText("WEATHER")).click();
        driver.manage().timeouts().implicitlyWait(45,TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);

        ReusableComponent.ReuseFunction.clickElement("Srinagar");
        ReusableComponent.ReuseFunction.clickElement("Lucknow");
        ReusableComponent.ReuseFunction.clickElement("Patna");
        ReusableComponent.ReuseFunction.clickElement("Kolkata");
        ReusableComponent.ReuseFunction.clickElement("Bhopal");
        ReusableComponent.ReuseFunction.clickElement("Hyderabad");
        ReusableComponent.ReuseFunction.clickElement("Mumbai");
        ReusableComponent.ReuseFunction.clickElement("Visakhapatnam");
        ReusableComponent.ReuseFunction.clickElement("Bengaluru");
        ReusableComponent.ReuseFunction.clickElement("Chennai");

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
    public static void clickElement(String strCity){
        driver.manage().timeouts().pageLoadTimeout(300, TimeUnit.SECONDS);
        driver.findElement(By.id("searchBox")).sendKeys(strCity);

        if ( driver.findElement(By.id(strCity)).isSelected() )
        {
            driver.findElement(By.id(strCity)).click();
        }
        driver.findElement(By.id("searchBox")).clear();
    }
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
