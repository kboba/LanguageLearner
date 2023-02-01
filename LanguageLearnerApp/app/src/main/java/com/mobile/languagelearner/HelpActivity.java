package com.mobile.languagelearner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HelpActivity extends AppCompatActivity {

    private Button goToMenuButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        goToMenuButton = findViewById(R.id.GoToMenuButton);

        goToMenuButton.setOnClickListener(v -> {
            Intent intent = new Intent(HelpActivity.this, HomeActivity.class);
            startActivity(intent);
        });
    }

}
