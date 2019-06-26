package com.example.restful_library;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class Restful {
    String baseUrl = "";
    RequestQueue queue;
    String [][] parameters;
    String function;
    StringRequest stringRequest;
    public String main_response;
    public boolean isCompelete = false;

    public Restful(Context context)
    {
        queue = (RequestQueue) Volley.newRequestQueue(context);
    }

    public void setBaseUrl(String baseUrl)
    {
        this.baseUrl = baseUrl;
    }

    public void  setParameters(String [][] parameters)
    {
        this.parameters = parameters;
    }

    public void setFunction(String function)
    {
        this.function = function;
    }

    public static String urlWithParameters(String baseUrl, String function, String[][] parameters)
    {
        for(int i = 0; i < parameters.length; i++)
        {
            if(parameters[i].length != 2)
                throw new IllegalArgumentException("Parameters should be paired!");
        }

        String ret = "";
        ret = ret + baseUrl + "/" +function;

        if(parameters.length == 0)
            return baseUrl;

        ret = ret + "?";

        for(int i = 0; i < parameters.length; i++)
        {
            ret = ret + parameters[i][0] + "=" + parameters[i][1] + "&";
        }

        return ret;
    }


    public void access()
    {
        stringRequest = new StringRequest(Request.Method.GET, urlWithParameters(this.baseUrl,
                                                                                this.function,
                                                                                this.parameters),
                new Response.Listener<String>() {
                    String response;
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        main_response = response;
                        isCompelete = true;
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try {
                    throw error;
                } catch (VolleyError volleyError) {
                    volleyError.printStackTrace();
                }
                main_response = "ERROR";
                isCompelete = true;
            }
        });
        queue.add(stringRequest);
    }

    public String getFinalUrl()
    {
        return urlWithParameters(baseUrl, function, parameters);
    }

    public boolean isCompelete() {
        return isCompelete;
    }

    public String getResponse()
    {
        return main_response;
    }

}
