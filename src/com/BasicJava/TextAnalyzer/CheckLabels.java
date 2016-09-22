package com.BasicJava.TextAnalyzer;

/**
 * Created by olmer on 19.09.16.
 */
class CheckLabels {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        new CheckLabels().run();
        long finishTime = System.currentTimeMillis();
        System.out.println(finishTime - startTime + " ms");
    }

    private void run() {
        System.out.println(checkLabels(new TextAnalyzer[] {
                new SpamAnalyzer(new String[] {"buka","vuka"}),
                new NegativeTextAnalyzer(),
                new TooLongTextAnalyzer(10)
        },"duka dfgd"));
    }

    public Label checkLabels(TextAnalyzer[] analyzers, String text) {
        for (TextAnalyzer anlz :
                analyzers) {
            Label lbl = anlz.processText(text);
            if (lbl!=Label.OK) {
                return lbl;
            }
        }
        return Label.OK;
    }
}
