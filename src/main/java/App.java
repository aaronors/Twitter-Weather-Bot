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
import twitter4j.conf.ConfigurationBuilder;

import java.io.IOException;
import java.util.List;


public class App
{
    public static void main( String[] args ) throws TwitterException, JSONException, IOException, org.json.JSONException, InterruptedException, ApiException, ForecastException {


        // -- only include tweet processing in main
        // -- all other things go into functions


        // sets up connection w/ twitter

        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("JCpkdJ2Nk989r7AjyBbw0Tk6I")
                .setOAuthConsumerSecret("gFWfhVibbmeeZuHmnGGZn4TvnuGxEQqWSAXUUngytjw7lRFBgF")
                .setOAuthAccessToken("859646796460683267-wgzhgtTdfVom2yzuLXPtAjHMdNIUrYJ")
                .setOAuthAccessTokenSecret("8xsMCa2CXlGVD4br1SDiFl8wtMw01HhKWKxaPuCYo18G5");
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();




        // -- get address and user from a @mention to Woppy

        List<Status> statusList = twitter.getMentionsTimeline();

        Status status = statusList.get(0);                  // gets previous mention

        String statusText = status.getText();

        //System.out.println("User that tweeted @Woppy");
        User user1 = status.getUser();

        String userName = user1.getName();
        long UserId = user1.getId();

        String addr = parseTweet(statusText);

        //String addr = "Placentia,CA";


        // -- convert address to lat and long using Java Client for Google Maps Services

        GeoApiContext context = new GeoApiContext().setApiKey("AIzaSyCJjJuIkuPV9B8RWK_3hNcQR6aWQ9NvKVE");

        GeocodingResult[] results =  GeocodingApi.geocode(context,addr).await();

        LatLng coordinates = results[0].geometry.location;


        // -- use darksky api to get weather using @coordinates

        ForecastRequest request = new ForecastRequestBuilder()
                .key(new APIKey("e80eeba2d43761f8ef748ed60abe3391"))
                .location(new GeoCoordinates(new Longitude(coordinates.lng), new Latitude(coordinates.lat))).build();


        DarkSkyJacksonClient client = new DarkSkyJacksonClient();
        Forecast forecast = client.forecast(request);
        System.out.println("forecast " + forecast);
        System.out.println("forecast " + forecast.getCurrently().getTemperature());

        // -- reply to the user that @mentioned woppy






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




}


/** ------------------------------------------------ NOTES ---------------------------------------------------
 * WOPPY BOT ACTIVAED

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