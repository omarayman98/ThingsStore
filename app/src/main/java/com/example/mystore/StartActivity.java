package com.example.mystore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class StartActivity extends AppCompatActivity {
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        auth=FirebaseAuth.getInstance();
        if (isConnected()){
        if(auth.getCurrentUser()!=null){
            Toast.makeText(this, "Please wait", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
            Toast.makeText(this, "You're Already Logged In", Toast.LENGTH_SHORT).show();

        }}else Toast.makeText(this, "No Internet Connection!", Toast.LENGTH_LONG).show();
    }

    public void OnStartActivity(View view) {
        switch (view.getId()){
            case R.id.to_LoginLayout:
                if (isConnected()){
                        Intent intent= new Intent(this,LoginActivity.class);
                        startActivity(intent);
                    }else Toast.makeText(this, "No Internet Connection!", Toast.LENGTH_LONG).show();
                break;
            case R.id.to_signupLayout:
                if (isConnected()){
                    Intent intent2= new Intent(this,SignUpActivity.class);
                    startActivity(intent2);
                }else Toast.makeText(this, "No Internet Connection!", Toast.LENGTH_SHORT).show();

                break;
        }
    }
    public boolean isConnected() {
        boolean connection = false;
        try {
            ConnectivityManager cm = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo nInfo = cm.getActiveNetworkInfo();
            connection = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();
            return connection;
        } catch (Exception e) {
            Log.e("Connectivity Exception", e.getMessage());
        }
        return connection;
    }
}