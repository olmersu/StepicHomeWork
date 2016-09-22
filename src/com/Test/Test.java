package com.Test;

/**
 * Created by olmer on 19.09.16.
 */
public class Test {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        new Test().run();
        long finishTime = System.currentTimeMillis();
        System.out.println(finishTime - startTime + " ms");
    }

    private void run() {
        System.out.println(checkLabels(new TextAnalyzer[] {
                new SpamAnalyzer(new String[] {"buka","vuka"}),
                new NegativeTextAnalyzer(),
                new TooLongTextAnalyzer(10)
        },"Это весело"));
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

    public abstract class KeywordAnalyzer implements TextAnalyzer{
        abstract protected String [] getKeywords();
        abstract protected Label getLabel();

        @Override
        public Label processText(String text) {
            return null;
        }
    }

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

    public enum Label {
        SPAM, NEGATIVE_TEXT, TOO_LONG, OK
    }

    public interface TextAnalyzer {
        Label processText(String text);
    }

}
