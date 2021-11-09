package com.example.mystore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
        if(auth.getCurrentUser()!=null){
            Toast.makeText(this, "Please wait", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
            Toast.makeText(this, "You're Already Logged In", Toast.LENGTH_SHORT).show();

        }
    }

    public void OnStartActivity(View view) {
        switch (view.getId()){
            case R.id.to_LoginLayout:
                Intent intent= new Intent(this,LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.to_signupLayout:
                Intent intent2= new Intent(this,SignUpActivity.class);
                startActivity(intent2);
                break;
        }
    }
}