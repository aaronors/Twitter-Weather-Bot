/**
 * Created by aaronors on 5/3/2017.
 */
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
import twitter4j.conf.ConfigurationBuilder;

import java.io.IOException;

// figure out how to run w/ command
// - create jar file. run jar file in heroku?


public class App
{
    public static void main( String[] args ) throws TwitterException, JSONException, IOException, org.json.JSONException, InterruptedException, ApiException, ForecastException {






        // sets up connection w/ twitter
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(" ")
                .setOAuthConsumerSecret(" ")
                .setOAuthAccessToken(" ")
                .setOAuthAccessTokenSecret(" ");
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();


        // generate weather

        String zip = generateZip();


        Forecast forecast = getForecast(zip);


        Response response = new Response(forecast,zip);

        //System.out.println(response.getWeather());

        twitter.updateStatus(response.getWeather());
        //twitter.updateStatus("BEEEEEEEEP");


        // respond to @mentions







/*        // -- get address and user from a @mention to Woppy

        List<Status> statusList = twitter.getMentionsTimeline();
        Status status = statusList.get(0);                  // gets previous mention
        User user1 = status.getUser();
        String userName = user1.getName();
        long UserId = user1.getId();
        String addr = parseTweet(status.getText());


        // -- convert address to lat and long using Java Client for Google Maps Services

        LatLng coordinates = getCoordinates(addr);


        // -- use darksky api to get weather using @coordinates

        ForecastRequest request = new ForecastRequestBuilder()
                .key(new APIKey("e80eeba2d43761f8ef748ed60abe3391"))
                .location(new GeoCoordinates(new Longitude(coordinates.lng), new Latitude(coordinates.lat))).build();


        DarkSkyJacksonClient client = new DarkSkyJacksonClient();
        Forecast forecast = client.forecast(request);
        System.out.println("forecast " + forecast);
        System.out.println("forecast " + forecast.getCurrently().getTemperature());

        // -- reply to the user that @mentioned woppy*/







    }

/*    public static String botResponse(){

    }*/


    public static String parseTweet(String tweet){

        String cmd = "woppyActivate(\""; // length of 14


        StringBuilder address = new StringBuilder(100);

        int pos = tweet.indexOf(cmd) + 15 ;

        for(int n = pos; n<tweet.length();n++){

            if(tweet.charAt(n) == '"'){
                //System.out.println("found it!");
                break;
            }
            address.append(tweet.charAt(n));

        }

        //System.out.println(address.toString());
        return address.toString();

    }


    public static String generateZip(){

        StringBuilder zip = new StringBuilder();


        for(int n = 0; n < 5; n++) {
            zip.append((int)(Math.random() * 9 + 0));
        }


        return zip.toString();

    }


    public static Forecast getForecast (String addr) throws InterruptedException, ApiException, IOException, ForecastException {

        GeoApiContext context = new GeoApiContext().setApiKey(" ");
        GeocodingResult[] results =  GeocodingApi.geocode(context,addr).await();
        LatLng coordinates = results[0].geometry.location;

        // -- use darksky api to get weather using @coordinates

        ForecastRequest request = new ForecastRequestBuilder()
                .key(new APIKey(" "))
                .location(new GeoCoordinates(new Longitude(coordinates.lng), new Latitude(coordinates.lat))).build();


        DarkSkyJacksonClient client = new DarkSkyJacksonClient();
        Forecast forecast = client.forecast(request);

        return forecast;

    }

    public static void weatherUpdate(LatLng coordinates){



    }


}


/** ------------------------------------------------ NOTES ---------------------------------------------------
 * WOPPY BOT ACTIVAED
 *
 *
 * // woppy.getWeather("Los Angeles,California")
 *
 *
 *  - factory method that implements one out of 3 classes, returns the message from that class
 *
 *  - wake up every hour, return weather for random zip code
 *
 *  - respond to missed messages

 - add a 1000 forecast der day rate limiting

 - add basic exceptions, and user response exceptions(syntax errors, city not found, etc. )



 use data point to retrieve data


 // running on heroku. how to activate upon startup?
 // when bot recieves reference with zipcode wake
 // process zip
 // return message


 --

 - turn code into function when simplifying it would make code more readable ie) function is not relevant to context


 Sources
 --http://www.javacreed.com/simple-gson-example/ !!!

 --http://stackoverflow.com/questions/19551242/parsing-a-complex-json-object-using-gson-in-java !!!!!

 --https://github.com/googlemaps/google-maps-services-java/blob/master/src/main/java/com/google/maps/model/GeocodingResult.java -> has functions for geocode info

 //URL url = new URL("https://maps.googleapis.com/maps/api/geocode/json?address=placentia+CA&key=AIzaSyCJjJuIkuPV9B8RWK_3hNcQR6aWQ9NvKVE");


 //https://github.com/200Puls/darksky-forecast-api/blob/master/darksky-forecast-api/src/main/java/tk/plogitech/darksky/forecast/model/DataPoint.java


 */
