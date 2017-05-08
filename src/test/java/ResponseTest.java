import org.junit.Test;
import tk.plogitech.darksky.forecast.model.Forecast;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by aaronors on 5/8/2017.
 */
public class ResponseTest {
    @Test
    public void getWeather() throws Exception {

        String addr = "54872";

        Forecast forecast = App.getForecast(addr);

        Response response = new Response(forecast,addr);

        System.out.println(response.getWeather());





    }

}