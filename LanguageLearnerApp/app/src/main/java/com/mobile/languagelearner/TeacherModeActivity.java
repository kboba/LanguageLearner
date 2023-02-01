package com.mobile.languagelearner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class TeacherModeActivity extends AppCompatActivity {

    private Button goToMenuButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_mode);

        goToMenuButton = findViewById(R.id.GoToMenuButton);

        goToMenuButton.setOnClickListener(v -> {
            Intent intent = new Intent(TeacherModeActivity.this, HomeActivity.class);
            startActivity(intent);
        });
    }
}