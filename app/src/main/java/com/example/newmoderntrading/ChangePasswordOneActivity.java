package com.example.newmoderntrading;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ChangePasswordOneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password_one);
        Button sendCodeToEmailButton = findViewById(R.id.changePaswordButton);
        sendCodeToEmailButton.setOnClickListener(view -> {//TODO: add if
            Intent intent = new Intent(ChangePasswordOneActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}