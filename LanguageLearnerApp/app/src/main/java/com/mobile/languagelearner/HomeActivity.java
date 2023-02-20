package com.mobile.languagelearner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    private Button testModeButton;
    private Button learningModeButton;
    private Button teacherModeButton;
    private Button helpActivityButton;
    private Button settingsActivityButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        testModeButton = findViewById(R.id.TestModeActivity);
        learningModeButton = findViewById(R.id.LearningModeActivity);
//        teacherModeButton = findViewById(R.id.TeacherModeActivity);
//        helpActivityButton = findViewById(R.id.HelpActivity);
//        settingsActivityButton = findViewById(R.id.SettingsActivity);

        testModeButton.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, TestModeActivity.class);
            HomeActivity.this.startActivity(intent);
        });

        learningModeButton.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, LearningModeActivity.class);
            startActivity(intent);
        });

//        teacherModeButton.setOnClickListener(v -> {
//            Intent intent = new Intent(HomeActivity.this, TeacherModeActivity.class);
//            startActivity(intent);
//        });
//
//        helpActivityButton.setOnClickListener(v -> {
//            Intent intent = new Intent(HomeActivity.this, HelpActivity.class);
//            startActivity(intent);
//        });
//
//        settingsActivityButton.setOnClickListener(v -> {
//            Intent intent = new Intent(HomeActivity.this, SettingsActivity.class);
//            startActivity(intent);
//        });
    }
}