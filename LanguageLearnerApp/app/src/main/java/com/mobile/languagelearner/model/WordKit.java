package com.mobile.languagelearner.model;

public class WordKit {
    String polishWord;
    String ukrainianWord;
    int imageId;

    public WordKit() {
    }

    public WordKit(String polishWord, String ukrainianWord, int image) {
        this.polishWord = polishWord;
        this.ukrainianWord = ukrainianWord;
        this.imageId = image;
    }

    public String getPolishWord() {
        return polishWord;
    }

    public void setPolishWord(String polishWord) {
        this.polishWord = polishWord;
    }

    public String getUkrainianWord() {
        return ukrainianWord;
    }

    public void setUkrainianWord(String ukrainianWord) {
        this.ukrainianWord = ukrainianWord;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
