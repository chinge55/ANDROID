package com.example.sangam.chatapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    Button login, register;
    FirebaseUser firebaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = (Button)findViewById(R.id.login);
        register = (Button)findViewById(R.id.registration);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        if (firebaseUser!=null){
            Intent intent = new Intent(MainActivity.this, Openedactivity.class);
            startActivity(intent);
            finish();

        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Login.class));

            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Registeractivity.class));
            }
        });
    }
}
