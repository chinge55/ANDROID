package com.example.sangam.chatapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    EditText email, password;
    Button login;
    FirebaseAuth auth;
    String txt_email, txt_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Login");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        email = (EditText)findViewById(R.id.username_login);
        password = (EditText)findViewById(R.id.password_login);
        login = (Button) findViewById(R.id.btn_login);

        // initialize the firebase auth
        auth = FirebaseAuth.getInstance();


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_email = email.getText().toString().trim();
                txt_password = password.getText().toString().trim();
                if (TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password)){

                    Toast.makeText(Login.this, "All fields are required", Toast.LENGTH_SHORT).show();
                } else{
                   loginact(txt_email, txt_password);

                }
            }
        });
    }
    public void loginact(String email, String password){
        try{
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Intent intent = new Intent(Login.this, Openedactivity.class );
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();
                        } else
                        {

                            Toast.makeText(Login.this, "Login failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        }catch (NullPointerException e){
            Toast.makeText(Login.this, "Null pointer exception", Toast.LENGTH_SHORT).show();
        }
    }
}
