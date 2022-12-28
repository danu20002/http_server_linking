package com.example.okhttp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
        private final OkHttpClient client=new OkHttpClient();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView my_text=(TextView) findViewById(R.id.textview);
        Request request=new Request.Builder().url("https://reqres.in/api/users?page=2").build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
             if(response.isSuccessful()){
                 final String responsebody=response.body().string();
                 MainActivity.this.runOnUiThread(new Runnable() {
                     @Override
                     public void run() {

                         my_text.setText(responsebody);
                     }
                 });
             }
            }
        });
    }
}