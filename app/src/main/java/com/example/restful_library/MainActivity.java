package com.example.restful_library;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    Restful r;

    public void onClick(View view) throws InterruptedException {
        textView.setText("Response:" + r.getResponse());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://cloud-bci.duckdns.org";

        textView = (TextView) findViewById(R.id.text);
        r = new Restful(this);
        r.setBaseUrl(url);
        r.setFunction("upload_data");
        String[][] parameters = {{"a", "1"},{"b", "2"}};
        r.setParameters(parameters);
        r.access();
    }
}
