package com.example.restful_library;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CloudBciClient extends Thread{
    String baseUrl = "";
    OkHttpClient client;
    final static int accessingPeriod = 20;
    List<GetRequest> requestQueue;
    String dataBuffer = "";

    public CloudBciClient(String baseUrl)
    {
        this.baseUrl = baseUrl;
        requestQueue = new ArrayList<GetRequest>();
        this.client = new OkHttpClient();
        start();
    }


    public void run(){
        while(true) {
            Log.d("Queue Size:", " " + requestQueue.size());
            dataQueueToRequest();
            for (int i = 0; i < requestQueue.size(); i++) {
                GetRequest current_request = requestQueue.get(i);
                current_request.run();
            }

            for(int i = requestQueue.size() - 1; i>= 0; i--)
            {
                if(requestQueue.get(i).state == GetRequest_State.FINISHED)
                {
                    requestQueue.remove(i);
                }
            }
        }
    }

    public void insert_real_time_data(String data)
    {
        if(dataBuffer.equals(""))
        {
            dataBuffer = data;
        }
        else {
            dataBuffer = dataBuffer + ";" + data;
        }
    }

    private void dataQueueToRequest(){
        if(!dataBuffer.equals("")) {
            String url = baseUrl + "/insert_real_time_data?case_id=1&attribute=data&data=" + dataBuffer;
            Log.d("url", url);
            addRawRequest(url);
            dataBuffer = "";
        }
    }

    public void addRawRequest(String url)
    {
        requestQueue.add(new GetRequest(url, client));
    }
}
