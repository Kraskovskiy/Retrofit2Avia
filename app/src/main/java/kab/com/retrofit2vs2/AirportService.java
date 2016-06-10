package kab.com.retrofit2vs2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by Suxx on 26.05.16.
 */
public interface AirportService {
    @GET("/places/coords_to_places_ru.json")
    Call<List<Airport>> airports(@Query("coords") String gps);

    @Headers({"X-Access-Token: "+ServiceConnector.token})
    @GET("/v1/city-directions?currency=usd")
    Call<Auth> pop2(@Query("origin") String origin);
}
