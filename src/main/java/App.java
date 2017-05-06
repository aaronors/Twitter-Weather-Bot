/**
 * Created by aaronors on 5/3/2017.
 */
import org.bitpipeline.lib.owm.*;
import org.bitpipeline.lib.owm.WeatherData;
import org.json.*;
import org.json.JSONObject;
import twitter4j.*;
import twitter4j.JSONException;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.json.DataObjectFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


import java.util.List;


/**
 * WOPPY BOT ACTIVAED

 - add a 1000 forecast der day rate limiting

 - user must activate woppy, WOPPY ACTIVATE


 - accepts US Zip codes, countries/city

 -- use geocoding api to covert address

 x -- woppy
    x -- repeats the address
        x -- returns

 */
public class App
{
    public static void main( String[] args ) throws TwitterException, JSONException, IOException, org.json.JSONException {

        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("pOVafGlwoMPFRfPNsYBtKnunB")
                .setOAuthConsumerSecret("NYEz37xYur28bZteqo5gHIdyrTSe6sh8glbC72aSqT4xrMUQN6")
                .setOAuthAccessToken("855625795494043650-Y5ihHLGjbQJD1JF0vNKiiaPeMJkJBoq")
                .setOAuthAccessTokenSecret("AwoO4V9hhs2OufI3zP6FQMKSLqp8P14PZHqM51taS90uw");
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();



        // running on heroku. how to activate upon startup?
        // when bot recieves reference with zipcode wake
        // process zip
        // return message


        // make http request with google geocode api

        // parse returning JSON

        // check status to see if valid

        // use lat, long as input to darksky api



    }


    // deals with various formatting types (zip,city/country, et.)
    public static void processAddr() throws IOException, org.json.JSONException {





    }





}
