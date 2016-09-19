package com.BasicJava.TextAnalyzer;

/**
 * Created by olmer on 19.09.16.
 */
class TooLongTextAnalyzer implements TextAnalyzer {
    private int length;
    TooLongTextAnalyzer(int length){
        this.length=length;
    }
    @Override
    public Label processText(String text) {
        if (text.length()>=length){
            return this.getLabel();
        }
        return Label.OK;
    }
    private Label getLabel() {
        return Label.TOO_LONG;
    }
}
