package com.BasicJava.TextAnalyzer;

/**
 * Created by bogomolov on 19.09.2016.
 */
public class SpamAnalyzer extends KeywordAnalyzer implements TextAnalyzer{

    public SpamAnalyzer(String[] keyWords) {
        super(keyWords);
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
    protected Label getLabel() {
        return Label.SPAM;
    }
}
