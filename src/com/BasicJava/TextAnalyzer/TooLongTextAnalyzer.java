package com.BasicJava.TextAnalyzer;

/**
 * Created by olmer on 19.09.16.
 */
public class TooLongTextAnalyzer implements TextAnalyzer {
    private int maxLength;
    public TooLongTextAnalyzer(int maxLength){
        this.maxLength = maxLength;
    }
    @Override
    public Label processText(String text) {
        if (text.length()> maxLength){
            return this.getLabel();
        }
        return Label.OK;
    }
    private Label getLabel() {
        return Label.TOO_LONG;
    }
}
