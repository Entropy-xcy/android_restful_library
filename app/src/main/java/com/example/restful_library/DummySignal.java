package com.example.restful_library;

import android.util.Log;

public class DummySignal extends Thread{
    @Override
    public void run()
    {
        CloudBciClient client = new CloudBciClient("http://cloud-bci.duckdns.org");
        int count = 1;
        int max = 65536*2;

        while(true) {
            try {
                this.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(count > 65535)
            {
                count = 1;
            }
            else
            {
                count += 300;
            }

            int data_int = (int) (max/2 + max/2 * Math.sin(count / 65535.0 * 3.141592653 * 2));

            Log.d("Dummy Signal:", " " + data_int);
            String data = data_int + "";
            client.insert_real_time_data(data);
        }
    }
}
