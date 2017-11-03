package com.example.cryptocurrencyconverter.others;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import static com.example.cryptocurrencyconverter.others.Others.Constants.*;

/**
 * Created by victor mungai on 10/31/17.
 * This method is used to carry out requests like GET and process responses
 */

public class NetworkingClass {

    /**
     * Method that carries out GET requests using Android Volley library
     * This methods assumes that a JSON Object is returned upon request
     * @param url URL to request values from
     * @param context Current context of app
     */
    public static void GET(String url, Context context, final TextView textView, final String id){
        try {
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    JSONObject jsonObject;
                    try {
                        Log.e("INTERNET " + TAG, response);
                        // parse fetched Json String to JSON Object
                        jsonObject = new JSONObject(response);
                        // set received exchange rate to text view
                        textView.setText(jsonObject.getString(id));
                    } catch (JSONException ex) {
                        Log.e("INTERNET " + TAG, "Something went wrong");
                        ex.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e(ERROR, "Something went wrong");
                    error.printStackTrace();
                }
            });

            VolleyHelperClass.getVolleyHelperClass(context).addToRequestQueue(stringRequest);
        } catch (Exception ex) {
            Log.e(ERROR, "Something went wrong");
            ex.printStackTrace();
        }
    }

    /**
     * Method checks whether there is internet connectivity
     * It calls the getActiveNetworkInfo and then checks if returns null, which it then returns
     * as true for network connection or false if otherwise
     * @param context Current context
     * @return State of net connection
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        assert connectivityManager != null;
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
