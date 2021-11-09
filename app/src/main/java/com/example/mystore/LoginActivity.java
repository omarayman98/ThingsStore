package com.example.mystore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {
    EditText email_login,password_login;
    FirebaseAuth auth;
    FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email_login=findViewById(R.id.email_login);
        password_login=findViewById(R.id.password_login);

        auth=FirebaseAuth.getInstance();

    }

    public void OnLogin(View view) {
        switch (view.getId()){
        case R.id.to_signup:
        Intent intent= new Intent(this,SignUpActivity.class);
        startActivity(intent);
        break;
            case R.id.login_btn:
            UserLogin();
    }}

    private void UserLogin() {
        String userEmail = email_login.getText().toString();
        String userPass = password_login.getText().toString();
        if (TextUtils.isEmpty(userEmail)||TextUtils.isEmpty(userPass)){
            Toast.makeText(this, "Please Fill Any Empty Field", Toast.LENGTH_SHORT).show();
        }else if(userPass.length()<6) {
            Toast.makeText(this, "Password Must Be At least 6 Digits", Toast.LENGTH_SHORT).show();
        }else {
            auth.signInWithEmailAndPassword(userEmail,userPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                        Intent toHome=new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(toHome);
                    }else {
                        Toast.makeText(LoginActivity.this, "Error!! "+task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}