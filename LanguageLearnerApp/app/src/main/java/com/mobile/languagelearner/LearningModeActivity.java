package com.mobile.languagelearner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mobile.languagelearner.model.WordKit;
import com.mobile.languagelearner.utils.Utills;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class LearningModeActivity extends AppCompatActivity {

    private Button goToMenuButton;
    private ImageView imageView;
    private TextView ukrainnianWord;
    private TextView polishWord;
    private TextView progressCounter;
    private Button leftButton;
    private Button rightButton;
    private TextToSpeech mTTS;

    List<WordKit> wordKits = new ArrayList<>();
    int currentIdx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_mode);

        initialize();

        currentIdx = 0;
        wordKits = Utills.getWordsFromChapter(1);

        mTTS = new TextToSpeech(this, status -> {
            mTTS.setLanguage(new Locale("pl"));
            mTTS.setPitch(1);
            mTTS.setSpeechRate(0.8f);

        });

        // Communicate if there is a problem with loading data and move to main menu
        /*if(wordKits == null) {
            Toast.makeText(getApplicationContext(), "Problem z załadowaniem danych do nauki", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(LearningModeActivity.this, HomeActivity.class);
            startActivity(intent);
        }*/

        WordKit firstWord = wordKits.get(0);
        polishWord.setText(firstWord.getPolishWord());
        ukrainnianWord.setText(firstWord.getUkrainianWord());
        imageView.setImageResource(firstWord.getImageId());
        progressCounter.setText("1/21");

        leftButton.setOnClickListener(v -> {
            if (!isIdxOutOfBound(currentIdx - 1)) {
                setCurrentKit(--currentIdx);
            }
        });

        rightButton.setOnClickListener(v -> {
            if(!isIdxOutOfBound(currentIdx+1)) {
                setCurrentKit(++currentIdx);
            }
        });

        polishWord.setOnClickListener(v -> {
            mTTS.speak(polishWord.getText().toString(), TextToSpeech.QUEUE_FLUSH, null, "");
        });

        goToMenuButton.setOnClickListener(v -> {
            Intent intent = new Intent(LearningModeActivity.this, HomeActivity.class);
            startActivity(intent);
        });
    }

    private boolean isIdxOutOfBound(int i) {
        return (i < 0 || i >= wordKits.size());
    }

    private void setCurrentKit(int idx) {
        WordKit wordKit = wordKits.get(idx);
        polishWord.setText(wordKit.getPolishWord());
        ukrainnianWord.setText(wordKit.getUkrainianWord());
        imageView.setImageResource(wordKit.getImageId());
        progressCounter.setText( (idx+1)+"/21");
    }


    private void initialize() {
        goToMenuButton = findViewById(R.id.GoToMenuButton);
        leftButton = findViewById(R.id.btnLeft);
        rightButton = findViewById(R.id.btnRight);

        imageView = findViewById(R.id.ImageView);
        ukrainnianWord = findViewById(R.id.UkrainianWord);
        polishWord = findViewById(R.id.PolishWords);
        progressCounter = findViewById(R.id.ProgressCounter);
    }

    @Override
    protected void onDestroy() {
        if (mTTS != null) {
            mTTS.stop();
            mTTS.shutdown();
        }

        super.onDestroy();
    }
}
