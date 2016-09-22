package com.BasicJava.TextAnalyzer;

/**
 * Created by olmer on 19.09.16.
 */
public class NegativeTextAnalyzer extends KeywordAnalyzer {
    final private String [] keyWords = {";(","=(",":|"};

    public NegativeTextAnalyzer() {
    }

    @Override
    public Label processText(String text) {
        if (text.contains(":(")||text.contains("=(")||text.contains(":|")){
            return this.getLabel();
        }
        return Label.OK;
    }

    @Override
    protected String[] getKeywords() {
        return this.keyWords;
    }

    @Override
    protected Label getLabel() {
        return Label.NEGATIVE_TEXT;
    }
}
