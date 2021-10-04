package com.exam.country_app;

import android.app.ProgressDialog;
import android.content.Context;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RestAPI {
    public static final String URL = "https://restcountries.com/v2/all";
    private final Context context;
    private final RequestQueue requestQueue;
    public RestAPI(Context context) {
        this.context = context;
        this.requestQueue = Volley.newRequestQueue(context);
    }

    public void loadCountryAPI(APIListener apiListener){
            Response.Listener<JSONArray> successListener = new Response.Listener<JSONArray>() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onResponse(JSONArray response) {
                    int length = response.length();
                    String name = "";
                    String flag = "";
                    String capital;
                    String region = "";
                    String abbreviation = "";
                    JSONArray calling_codes = null;
                    int population = 0;
                    Currency[] currencies = null;
                    Currency currency;
                    double[] latlng = null;
                    Country[] countries = new Country[length];
                    for (int i = 0; i < countries.length; i++) {
                        JSONArray currencies_json = null;
                        try {
                            JSONObject jsonObject = (JSONObject) response.get(i);
                            name = jsonObject.getString("name");
                            flag = jsonObject.getJSONObject("flags").getString("png");
                            capital = jsonObject.getString("capital");
                            region = jsonObject.getString("region");
                            abbreviation = jsonObject.getString("alpha3Code");
                            calling_codes  = jsonObject.getJSONArray("callingCodes");
                            population  = jsonObject.getInt("population");
                            currencies_json  = jsonObject.getJSONArray("currencies");
                            currencies = new Currency[currencies_json.length()];
                            for (int j = 0; j < currencies_json.length(); j++){
                                currency = new Currency(currencies_json.getJSONObject(j).getString("code"),currencies_json.getJSONObject(j).getString("name"),currencies_json.getJSONObject(j).getString("symbol"));
                                currencies[j] = currency;
                            }
                            latlng = new double[]{jsonObject.getJSONArray("latlng").getDouble(0), jsonObject.getJSONArray("latlng").getDouble(1)};

                        } catch (JSONException e) {
                            capital = "No Capital";
                            region = "No Region";
                        }

                        Country country = new Country(name,flag,capital,region,abbreviation,calling_codes, population,currencies, latlng);
                        countries[i] = country;

                        apiListener.onLoadCountryAPI(countries);
                    }
                }
            };

            Response.ErrorListener errorListener = new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("error", "VolleyError");
                    error.printStackTrace();

                }
            };
            JsonArrayRequest request = new JsonArrayRequest (Request.Method.GET, URL, null, successListener, errorListener);
            ProgressDialog pd = new ProgressDialog(context);
            pd.setMessage("loading");
            pd.show();
            pd.setContentView(R.layout.progress_dialog);

            requestQueue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<String>() {
                @Override
                public void onRequestFinished(Request<String> request) {
                    if (pd !=  null && pd.isShowing())
                        pd.dismiss();
                }
            });
            requestQueue.add(request);

    }


}
