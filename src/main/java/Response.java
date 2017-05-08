import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import tk.plogitech.darksky.forecast.model.Forecast;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by aaronors on 5/7/2017.
 */
public class Response {

    String zipcode;
    String address;
    String temperature;
    String apparentTemperature;
    String precipitationProbability;
    String humidity;
    String summary;
    final String df  = "\u00b0F";


    public Response(Forecast forecast, String zip) throws InterruptedException, ApiException, IOException {

        GeoApiContext context = new GeoApiContext().setApiKey("AIzaSyCJjJuIkuPV9B8RWK_3hNcQR6aWQ9NvKVE");
        GeocodingResult[] results =  GeocodingApi.geocode(context,zip).await();
        LatLng coordinates = results[0].geometry.location;

        zipcode = zip;
        address = results[0].formattedAddress;


        Double f = toFahrenheit(forecast.getCurrently().getTemperature());


        temperature = f.toString();

        Double f1 = toFahrenheit(forecast.getCurrently().getApparentTemperature());

        apparentTemperature = f1.toString();

        Double precipPercentage = forecast.getCurrently().getPrecipProbability()*100;
        precipitationProbability = precipPercentage.toString();

        Double humidityPercentage = forecast.getCurrently().getHumidity()*100;
        humidity = humidityPercentage.toString();

        summary = forecast.getCurrently().getIcon();

        System.out.println(forecast.getCurrently().getTemperature());


    }

    public double toFahrenheit(double celsius){
        return 9*(celsius/5)+32;
    }


    public String getWeather(){

        StringBuilder reply = new StringBuilder(100);
        StringBuilder zipStr = new StringBuilder(100);

        List<String> list = new LinkedList<String>();

        // add each to list

        list.add("WOPPY ACTIVATED"+"\n"); // WOPPY ACTIVATED

        for(char c : zipcode.toCharArray()){
            zipStr.append(c);
            zipStr.append("..");
        }
        zipStr.append("!"+"\n");

        list.add(zipStr.toString()); // 	9..3..4..3..5!

        list.add(address+"\n"); //	Wisconsin, indiana

        list.add(temperature + df +"\n"); //42 degrees F

        list.add("Feels like "+ apparentTemperature +df+"\n"); // feelslike

        list.add("Rain: " + precipitationProbability +"%"+"\n"); // chance of rain

        list.add("Humidity: " + humidity +"%"+"\n"); // humidity

        list.add(summary+"\n");



        // append to list if not too long

        for(String item : list){

            //System.out.println(reply.length());
            //System.out.println(item.length());
            //System.out.println(summary.length());

            if( (reply.length() + item.length() + summary.length())<140         ) {
                reply.append(item);
            }
            else {
                reply.append(summary.toUpperCase());
                break;
            }

            //System.out.println(item);

        }

        return reply.toString();

    }

    public String getWeatherRandom(){

        String weather="";

        return weather;
    }



}


/*----------------- Notes ------------------------------


    System.out.println("forecast " + forecast);
    System.out.println("forecast " + forecast.getCurrently().getTemperature());



------------------------------------------------------*/


