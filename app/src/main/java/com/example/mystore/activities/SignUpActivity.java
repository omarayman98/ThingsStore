package com.example.mystore.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mystore.R;
import com.example.mystore.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {
    EditText name_reg,email_reg,password_reg, confirm_Pass;
    FirebaseAuth auth;
    FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        name_reg=findViewById(R.id.name_reg);
        email_reg=findViewById(R.id.email_reg);
        password_reg=findViewById(R.id.password_reg);
        confirm_Pass=findViewById(R.id.ConfirmPassword_reg);
        auth= FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
    }
    public void OnSignUp(View view) {
        switch (view.getId()){
            case R.id.to_login:
        Intent intent= new Intent(this,LoginActivity.class);
        startActivity(intent);
        break;
            case R.id.signUp_btn:
                CreateUser();
                break;
    }}

    private void CreateUser() {
        String userName = name_reg.getText().toString();
        String userEmail = email_reg.getText().toString();
        String userPass = password_reg.getText().toString();
        if (TextUtils.isEmpty(userName)||TextUtils.isEmpty(userEmail)||TextUtils.isEmpty(userPass)){
            Toast.makeText(this, "Please Fill Any Empty Field", Toast.LENGTH_SHORT).show();
        }else if(userPass.length()<6){
            Toast.makeText(this, "Password Must Be At least 6 Digits", Toast.LENGTH_SHORT).show();
        }else if(!userPass.equals(confirm_Pass.getText().toString())){
            Toast.makeText(this, "Password Doesn't Match", Toast.LENGTH_SHORT).show();
        }else {
            auth.createUserWithEmailAndPassword(userEmail,userPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
               if (task.isSuccessful()){
                   User user = new User(userName,userEmail,userPass);
                    String id = task.getResult().getUser().getUid();
                    database.getReference().child("Users").child(id).setValue(user);
                   Intent toHome=new Intent(SignUpActivity.this,MainActivity.class);
                   startActivity(toHome);
                   Toast.makeText(SignUpActivity.this, "Signed Up Successfully", Toast.LENGTH_SHORT).show();
               }else{
                   Toast.makeText(SignUpActivity.this, "Error"+task.getException(), Toast.LENGTH_SHORT).show();
               }
                }
            });
        }

    }
}
