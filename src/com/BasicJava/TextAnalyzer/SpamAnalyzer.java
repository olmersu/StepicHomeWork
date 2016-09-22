package com.BasicJava.TextAnalyzer;

/**
 * Created by bogomolov on 19.09.2016.
 */
public class SpamAnalyzer extends KeywordAnalyzer {
    private String [] keywords;

    public SpamAnalyzer(String[] keywords) {
        this.keywords = keywords;
    }

    @Override
    public Label processText(String text) {
        for (String str :
                this.getKeywords()) {
            if (text.contains(str)){
                return this.getLabel();
            }
        }
        return Label.OK;
    }

    @Override
    protected String[] getKeywords() {
        return this.keywords;
    }

    @Override
    protected Label getLabel() {
        return Label.SPAM;
    }
}
