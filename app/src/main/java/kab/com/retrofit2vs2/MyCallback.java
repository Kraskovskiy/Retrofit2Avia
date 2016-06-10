package kab.com.retrofit2vs2;

import java.util.List;

/**
 * Created by Suxx on 27.05.2016.
 */
public interface MyCallback {
    void callbackCall(List<Airport> air);
    void callbackError(String err);
    void callbackFragment(String sity);
    void callbackGetDataToFragment(List<Data> data);
}
