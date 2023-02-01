package com.mobile.languagelearner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mobile.languagelearner.model.WordKit;
import com.mobile.languagelearner.utils.ImageReader;
import com.mobile.languagelearner.utils.WordsReader;

import java.util.ArrayList;
import java.util.List;

public class LearningModeActivity extends AppCompatActivity {

    private Button goToMenuButton;
    private ImageView imageView;
    private TextView ukrainnianWord;
    private TextView polishWord;
    private TextView progressCounter;
    private Button leftButton;
    private Button rightButton;

    List<WordKit> wordKits = new ArrayList<>();
    int currentIdx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_mode);

        goToMenuButton = findViewById(R.id.GoToMenuButton);
        leftButton = findViewById(R.id.btnLeft);
        rightButton = findViewById(R.id.btnRight);

        imageView = findViewById(R.id.ImageView);
        ukrainnianWord = findViewById(R.id.UkrainianWord);
        polishWord = findViewById(R.id.PolishWords);
        progressCounter = findViewById(R.id.ProgressCounter);

        currentIdx = 0;
        wordKits = getWordsFromChapter(1);

        if(wordKits == null) {
            Toast.makeText(getApplicationContext(), "Problem z załadowaniem danych do nauki", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(LearningModeActivity.this, HomeActivity.class);
            startActivity(intent);
        }


        WordKit firstWord = wordKits.get(0);
        polishWord.setText(firstWord.getPolishWord());
        ukrainnianWord.setText(firstWord.getUkrainianWord());
        imageView.setImageResource(firstWord.getImageId());

        leftButton.setOnClickListener(v -> {
            if(!isIdxOutOfBound(currentIdx-1)) {
                setCurrentKit(--currentIdx);
            }
        });

        rightButton.setOnClickListener(v -> {
            if(!isIdxOutOfBound(currentIdx+1)) {
                setCurrentKit(++currentIdx);
            }
        });

        goToMenuButton.setOnClickListener(v -> {
            Intent intent = new Intent(LearningModeActivity.this, HomeActivity.class);
            startActivity(intent);
        });
    }

    public List<WordKit> getWordsFromChapter(int chapter) {
        List<WordKit> wordKits = new ArrayList<>();
        List<String> polishWords = WordsReader.polishWords1();
        List<String> ukrainianWords = WordsReader.ukrainianWords1();
        List<Integer> imageIds = ImageReader.getImageIds1();

        if(polishWords == null || ukrainianWords == null || imageIds == null)
            return null;
        if(!(polishWords.size() == ukrainianWords.size() && polishWords.size() == imageIds.size()))
            return null;

        for(int i=0; i<imageIds.size(); i++){
            String plWord = polishWords.get(i);
            String ukWord = ukrainianWords.get(i);
            int imageId = imageIds.get(i);

            WordKit wordKit = new WordKit(plWord, ukWord, imageId);

            wordKits.add(wordKit);
        }

        return wordKits;
    }

    private boolean isIdxOutOfBound(int i) {
        return (i < 0 || i >= 10);
    }

    private void setCurrentKit(int idx) {
        WordKit wordKit = wordKits.get(idx);
        polishWord.setText(wordKit.getPolishWord());
        ukrainnianWord.setText(wordKit.getUkrainianWord());
        imageView.setImageResource(wordKit.getImageId());
        progressCounter.setText(idx+"/10");
    }

}
