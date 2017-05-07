/**
 * Created by aaronors on 5/3/2017.
 */
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;

import tk.plogitech.darksky.api.jackson.DarkSkyJacksonClient;
import tk.plogitech.darksky.forecast.*;
import tk.plogitech.darksky.forecast.model.Forecast;
import twitter4j.*;
import twitter4j.JSONException;

import java.io.IOException;






public class App
{
    public static void main( String[] args ) throws TwitterException, JSONException, IOException, org.json.JSONException, InterruptedException, ApiException, ForecastException {



        // sets up connection w/ twitter

/*        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("pOVafGlwoMPFRfPNsYBtKnunB")
                .setOAuthConsumerSecret("NYEz37xYur28bZteqo5gHIdyrTSe6sh8glbC72aSqT4xrMUQN6")
                .setOAuthAccessToken("855625795494043650-Y5ihHLGjbQJD1JF0vNKiiaPeMJkJBoq")
                .setOAuthAccessTokenSecret("AwoO4V9hhs2OufI3zP6FQMKSLqp8P14PZHqM51taS90uw");
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();*/




        // get address from a @mention to Woppy

        String addr = "Placentia,CA";


        // convert address to lat and long using Java Client for Google Maps Services

        GeoApiContext context = new GeoApiContext().setApiKey("AIzaSyCJjJuIkuPV9B8RWK_3hNcQR6aWQ9NvKVE");

        GeocodingResult[] results =  GeocodingApi.geocode(context,addr).await();

        LatLng coordinates = results[0].geometry.location;


        // use darksky api to get weather using @coordinates

        ForecastRequest request = new ForecastRequestBuilder()
                .key(new APIKey("e80eeba2d43761f8ef748ed60abe3391"))
                .location(new GeoCoordinates(new Longitude(coordinates.lng), new Latitude(coordinates.lat))).build();


        DarkSkyJacksonClient client = new DarkSkyJacksonClient();
        Forecast forecast = client.forecast(request);
        System.out.println("forecast " + forecast);
        System.out.println("forecast " + forecast.getCurrently().getTemperature());

        // reply to the user that @mentioned woppy

    }







}


/** ------------------------------------------------ NOTES ---------------------------------------------------
 * WOPPY BOT ACTIVAED

 - add a 1000 forecast der day rate limiting

 - user must activate woppy, WOPPY ACTIVATE


 - accepts US Zip codes, countries/city

 -- use geocoding api to covert address

 x -- woppy
 x -- repeats the address
 x -- returns

 --http://www.javacreed.com/simple-gson-example/ !!!

 --http://stackoverflow.com/questions/19551242/parsing-a-complex-json-object-using-gson-in-java !!!!!

 --https://github.com/googlemaps/google-maps-services-java/blob/master/src/main/java/com/google/maps/model/GeocodingResult.java -> has functions for geocode info

 //URL url = new URL("https://maps.googleapis.com/maps/api/geocode/json?address=placentia+CA&key=AIzaSyCJjJuIkuPV9B8RWK_3hNcQR6aWQ9NvKVE");


 //https://github.com/200Puls/darksky-forecast-api/blob/master/darksky-forecast-api/src/main/java/tk/plogitech/darksky/forecast/model/DataPoint.java

 use data point to retrieve data


 // running on heroku. how to activate upon startup?
 // when bot recieves reference with zipcode wake
 // process zip
 // return message

 */