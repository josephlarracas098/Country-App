package com.exam.country_app;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Base64;
import android.util.Log;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;


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
                @Override
                public void onResponse(JSONArray response) {
                        Log.d("response", response.toString());
                        apiListener.onLoadCountryAPI(response.toString());
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
