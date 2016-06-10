package kab.com.retrofit2vs2;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Suxx on 26.05.16.
 */
public class GetData {
    private AirportService mAirportServiceConnector;
    private Context mContext;
    public static List<Airport> mModel;
    MyCallback mCallback;
    public static List<Data> mData;
    public static List<City> mCity;

    public GetData(Context context,MyCallback callback) {
        mAirportServiceConnector =  ServiceConnector.createService(AirportService.class);
        mCallback = callback;
        mContext = context;
    }

    public GetData(Context context,MyCallback callback,String base_url) {
        ServiceConnector.ini(base_url);
        mAirportServiceConnector =  ServiceConnector.createService(AirportService.class);
        mCallback = callback;
        mContext = context;
    }

    public void getAirport(String gps) {
        Call<List<Airport>> call = mAirportServiceConnector.airports(gps);
        call.enqueue(new Callback<List<Airport>>() {
                @Override
            public void onResponse(Call<List<Airport>> call, Response<List<Airport>> response) {
                Log.e("getAirport","getAirport_resp = " + response.headers()+"\nresp2" + response.message()+"\nCall  "+call.request().url());
                mModel = response.body();
                resultResponse(response);
            }

            @Override
            public void onFailure(Call<List<Airport>> call, Throwable t) {
                Log.e("getAirport","t = " + t.getMessage());
                searchResultToActivity("Exception = " + t.getMessage());
            }
        });
    }

    public void getAuth(String pop) {
        Call<Auth> call = mAirportServiceConnector.pop2(pop);
        call.enqueue(new Callback<Auth>() {
            @Override
            public void onResponse(Call<Auth> call, Response<Auth> response) {
                Log.e("getAuth","resp = " + response.message());
                if(response.message().equals("OK")) {
                    jsonCityToList(response.body().getData());
                }
            }

            @Override
            public void onFailure(Call<Auth> call, Throwable t) {
                Log.e("getAuth","t = " + t.toString());
                searchResultToActivity("Exception = " + t.toString());
            }
        });
    }


    public void jsonCityToList(JsonObject obj) {
        Gson gson = new Gson();
        mData = new ArrayList<>();
        try {
            Set<Map.Entry<String, JsonElement>> entries = obj.entrySet();
            for (Map.Entry<String, JsonElement> entry : entries) {
                Log.e("jsonCityToList", " obj = " + entry.getKey() + " val " + entry.getValue());
                mData.add(gson.fromJson(entry.getValue(), Data.class));
            }

            loadJSONFromAssetCity(mContext, getCityCode(mData));
            for (Data data : mData) {
                for (City city : mCity) {
                    if (data.getDestination().equals(city.getCode())) {
                        data.setDestination(city.getName());
                    }
                }
            }

        mCallback.callbackGetDataToFragment(mData);
        } catch (Exception e) {
            Log.e("jsonCityToList", "jsonCityToList: " + e.toString());
        }
    }

    public List<String> getCityCode(List<Data> data){
        List<String> list = new ArrayList<String>();
        for (Data entry : data) {
            list.add(entry.getDestination());
        }
        return  list;
    }

    public void loadJSONFromAssetCity(Context context, List<String> cityCode) {
        try {
            mCity = readJsonStream(context.getAssets().open("cities.json"), cityCode);
        } catch (Exception e) {
            Log.e("loadJSONFromAsset", e.toString());
        }
    }

    public List<City> readJsonStream(InputStream in,List<String> cityCode) throws IOException {
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        List<City> cities = new ArrayList<City>();
        reader.beginArray();

        while (reader.hasNext()) {
            City city = gson.fromJson(reader, City.class);
            if (cityCode.contains(city.getCode())) {
                cities.add(city);
                Log.e("cities.json", "name = " + city.getName() + " code " + city.getCode());
            }
        }
        reader.endArray();
        reader.close();
        return cities;
    }

    public  void searchResultToActivity(String response){
        mCallback.callbackError(response);
    }

    public void resultHandler(List<Airport> model) {
        for (Airport air : model) {
            if (air.getAirportName() == null) {
                Log.e("resultHandler", air.getAirportName() + " ");
            }
        }
        Iterator<Airport> itr = model.iterator();
        while (itr.hasNext()) {
            if (itr.next().getAirportName() == null) {
                itr.remove();
            }
        }
        mCallback.callbackCall(model);
    }

    public List<String> resultResponse(Response<List<Airport>> response) {
        if (mModel == null) {
            ResponseBody responseBody = response.errorBody();
            if (responseBody != null) {
                try {
                    Log.e("resultResponse","responseBody = " + responseBody.string());
                    searchResultToActivity("responseBody = " + responseBody.string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("resultResponse","responseBody  = null");
                searchResultToActivity("responseBody  = null");
            }
        } else {
            for (int i=0;i<mModel.size();i++) {
                Log.e("resultResponse", "Name :" + mModel.get(i).getName() + "\ngetAirportName :" + mModel.get(i).getAirportName() + "\ngetIata:" + mModel.get(i).getIata());
                searchResultToActivity("");
            }
            resultHandler(mModel);
        }
        return null;
    }
}
