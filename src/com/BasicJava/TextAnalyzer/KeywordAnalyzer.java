package com.BasicJava.TextAnalyzer;

/**
 * Created by bogomolov on 19.09.2016.
 */
public abstract class KeywordAnalyzer implements TextAnalyzer{
    abstract protected String [] getKeywords();
    abstract protected Label getLabel();

    @Override
    public Label processText(String text) {
        return null;
    }
}
