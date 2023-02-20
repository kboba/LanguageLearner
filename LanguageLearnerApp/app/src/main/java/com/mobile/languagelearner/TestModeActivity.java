package com.mobile.languagelearner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mobile.languagelearner.model.WordKit;
import com.mobile.languagelearner.utils.Utills;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class TestModeActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView ukrainnianWord;
    private TextView[] polishAnswers = new TextView[4];
    private TextView correctAnswersCounter;
    private TextToSpeech mTTS;

    List<WordKit> wordKits = new ArrayList<>();
    int currentIdx;
    int correctAnswerId;
    int correctAnswers;

    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_mode);

        initialize();

        wordKits = Utills.getWordsFromChapter(1);
        // Communicate if there is a problem with loading data and move to main menu
        /*if(wordKits == null) {
            Toast.makeText(getApplicationContext(), "Problem z załadowaniem danych do nauki", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(TestModeActivity.this, HomeActivity.class);
            startActivity(intent);
        }*/
        mTTS = new TextToSpeech(this, status -> {
            mTTS.setLanguage(new Locale("pl"));

            mTTS.setPitch(1);
            mTTS.setSpeechRate(0.8f);
        });

        currentIdx = 0;
        correctAnswersCounter.setText("0/0");
        builder = new AlertDialog.Builder(TestModeActivity.this);
        builder.setPositiveButton("OK", (dialog, which) -> {});
        generateTestKit(0);

        // Listenery TextView
        for (TextView textView : polishAnswers)
            textView.setOnClickListener( v-> {
                mTTS.speak(textView.getText().toString(), TextToSpeech.QUEUE_FLUSH, null, "");
                if(textView.getText().equals(polishAnswers[correctAnswerId].getText())) {
                    correctAnswers++; // Add correct answer to counter
                    currentIdx++; // Increment current Ids
                    showDialog("Prawidłowo (правильно)", "");
                }
                else {
                    currentIdx++; // Increment current Ids
                    showDialog("Błędnie (помилково)",
                            ukrainnianWord.getText()+":"+polishAnswers[correctAnswerId].getText());
                }
                correctAnswersCounter.setText(correctAnswers+"/"+currentIdx);
                generateNextTestKit();
            });
    }

    private void generateNextTestKit() {
        if(!isIdxOutOfBound(currentIdx))
            generateTestKit(currentIdx);
        else {
            showDialog("Wynik(Результат):", correctAnswers + "/" + currentIdx); //Komunikat o punktach
            Intent intent = new Intent(TestModeActivity.this, HomeActivity.class);
            startActivity(intent);
        }
    }

    private void generateTestKit(int kitId) {
        WordKit currentWordKit = wordKits.get(kitId);

        ukrainnianWord.setText(currentWordKit.getUkrainianWord());
        imageView.setImageResource(currentWordKit.getImageId());

        Random random = new Random();
        correctAnswerId = random.nextInt(polishAnswers.length);
        polishAnswers[correctAnswerId].setText(currentWordKit.getPolishWord());
        int randomPolishWordId;
        for(int i = 0; i< polishAnswers.length; i++){
            if(i == correctAnswerId)
                continue;
            do {
                randomPolishWordId = random.nextInt(21);
            } while(randomPolishWordId == kitId);
            String randomPolishWord = wordKits.get(randomPolishWordId).getPolishWord();
            polishAnswers[i].setText(randomPolishWord);
        }
    }

    private boolean isIdxOutOfBound(int i) {
        return (i < 0 || i >= wordKits.size());
    }

    public void showDialog(String title, String message){
        builder.setTitle(title)
                .setMessage(message)
                .show();
    }

    private void initialize() {
        imageView = findViewById(R.id.ImageView);
        ukrainnianWord = findViewById(R.id.UkrainianWord);
        polishAnswers[0] = findViewById(R.id.PolishWords1);
        polishAnswers[1] = findViewById(R.id.PolishWords2);
        polishAnswers[2] = findViewById(R.id.PolishWords3);
        polishAnswers[3] = findViewById(R.id.PolishWords4);
        correctAnswersCounter = findViewById(R.id.CorrectAnswersCounter);
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
