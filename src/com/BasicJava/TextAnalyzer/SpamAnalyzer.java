package com.BasicJava.TextAnalyzer;

/**
 * Created by bogomolov on 19.09.2016.
 */
public class SpamAnalyzer extends KeywordAnalyzer implements TextAnalyzer{
    public SpamAnalyzer() {
    }

    @Override
    public Label processText(String text) {
        return null;
    }

    @Override
    protected String[] getKeywords() {
        return super.getKeywords();
    }

    @Override
    protected Label getLabel() {
        return super.getLabel();
    }
}
