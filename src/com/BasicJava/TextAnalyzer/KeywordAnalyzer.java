package com.BasicJava.TextAnalyzer;

/**
 * Created by bogomolov on 19.09.2016.
 */
public abstract class KeywordAnalyzer {
    private String [] keyWords;
    private Label label;

    protected KeywordAnalyzer() {
    }

    protected String [] getKeywords(){
        return keyWords;
    }
    protected Label getLabel(){
        return label;
    }
    protected KeywordAnalyzer(String[] keyWords){
        this.keyWords=keyWords;
    }
}
