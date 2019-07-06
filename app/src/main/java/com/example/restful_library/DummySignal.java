package com.example.restful_library;

import android.util.Log;

public class DummySignal extends Thread{
    @Override
    public void run()
    {
        /*
        Before using this CloudBciClient, you need first to upgrade the gradle build file by adding
        all components in the gradle file of this project.

        Then, you have to allow clear text network communication by changing the AndroidManifest.xml
        file correspond to such file in this repository.

        The way you use this restful client is very simple.
        First, you need to instantiate a CloudBciClient with specified URL and port.
        Then, for whatever data you have you simply call the function insert_real_time_data
        Noted that the insert_real_tine_data function is non-blocking, meaning that you don't
        have to worry about the delay caused by executing this function. All the network
        communications are running at background.

        Below shows a demo of how to use the CloudBciClient to send 4 channels simultaneously.
         */

        CloudBciClient client = new CloudBciClient("http://cloud-bci.duckdns.org:8000");
        int count = 1;

        while(true) {
            try {
                this.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(count > 255)
            {
                count = 1;
            }
            else
            {
                count += 1;
            }

            Log.d("Dummy Signal:", " " + count);
            int data1 = count;
            String data1_str = data1 + "";
            client.insert_real_time_data(1, data1_str);
            int data2 = count % 128 * 2;
            String data2_str = data2 + "";
            client.insert_real_time_data(2, data2_str);
            int data3 = count % 64 * 4;
            String data3_str = data3 + "";
            client.insert_real_time_data(3, data3_str);
            int data4 = count % 32 * 8;
            String data4_str = data4 + "";
            client.insert_real_time_data(4, data4_str);
        }
    }
}
