package com.mobile.languagelearner.utils;

import com.mobile.languagelearner.model.WordKit;

import java.util.ArrayList;
import java.util.List;

public class Utills {
    public static List<WordKit> getWordsFromChapter(int chapter) {
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
}
