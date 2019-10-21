import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Locale;

public class Document {
    private String text;
    private ArrayList<String> sentences = new ArrayList<>();
    private ArrayList<String> words = new ArrayList<String>();
    private ArrayList<Character> characters = new ArrayList<Character>();
    public boolean DirtyFlag = false;


    public Document(String text) {
        this.text = text;
        sentences = splitIntoSentences(text);
        words = breakSentenceInToWord(sentences);
    }

    public void setText(String text) {
        this.text = text;
        DirtyFlag = true;
    }

    public String getText() {
        return text;
    }

    private static String getWordFromLine(String line) {
        return line.substring(0, line.indexOf("="));
    }

    public int getSentenceCount() {
        if (DirtyFlag) recalulateData();
        return sentences.size();


    }

    private void recalulateData() {
        sentences = splitIntoSentences(text);
        words = breakSentenceInToWord(sentences);
    }

    public int getWordCount() {
        if (DirtyFlag) recalulateData();
        return words.size();

    }

    public int getCharacterCount() {

        if (DirtyFlag) recalulateData();
        return characters.size();

    }

    public int getPositiveWordCount() {
        return 0;
    }

    public int getNegativeWordCount() {
        return 0;
    }


    public static ArrayList<String> breakSentenceInToWord(ArrayList<String> sentences) {
        ArrayList<String> words = new ArrayList<String>();
        for (int i = 0; i < sentences.size(); i++) {
            String[] WordsPerSentence = sentences.get(i).split(" ");
            for (int j = 0; j < WordsPerSentence.length; j++) {
                words.add(WordsPerSentence[j]);
                words.set(i, words.get(i).toLowerCase());
            }
        }
        return words;
    }

    public static ArrayList<String> breakSentenceInToWord(String sentence) {
        ArrayList<String> words = new ArrayList<String>();
        String[] WordsPerSentence = sentence.split(" ");
        for (int j = 0; j < WordsPerSentence.length; j++) {
            words.add(WordsPerSentence[j]);
            words.set(j, words.get(j).toLowerCase());
        }
        return words;
    }

    public String replaceAll(String Old, String New) {

        String fixedString = "";
        ArrayList<Integer> position = new ArrayList<Integer>();
        for (int i = 1; i < this.text.length(); i++) {
            if (this.text.substring(i - 1, i).equals(Old)) {
                position.add(i - 1);

            }
        }
        if (position.size() > 0) {
            fixedString = this.text.substring(0, position.get(0));
            fixedString += New;
            for (int i = 1; i < position.size() - 1; i++) {

                fixedString += this.text.substring(position.get(i - 1) + 1, position.get(i));
                fixedString += New;

            }
            fixedString += this.text.substring(position.get((position.size() - 2)) + 1);
            return fixedString;
        }
        return this.text;


    }

    public static ArrayList<String> splitIntoSentences(String text) {
        ArrayList<String> output = new ArrayList<>();

        Locale locale = Locale.US;
        BreakIterator breakIterator = BreakIterator.getSentenceInstance(locale);
        breakIterator.setText(text);

        int prevIndex = 0;
        int boundaryIndex = breakIterator.first();
        while (boundaryIndex != BreakIterator.DONE) {
            String sentence = text.substring(prevIndex, boundaryIndex).trim();
            if (sentence.length() > 0)
                output.add(sentence);
            prevIndex = boundaryIndex;
            boundaryIndex = breakIterator.next();
        }

        String sentence = text.substring(prevIndex).trim();
        if (sentence.length() > 0)
            output.add(sentence);

        return output;
    }

    public ArrayList<String> getWords() {

        if (DirtyFlag) recalulateData();
        return words;
    }

    public int getAvgWord() {
        if (DirtyFlag) recalulateData();
        return words.size() / sentences.size();

    }

    public int getAvgCharacter() {
        return 0;
    }

    public double FKReadability() {
        ArrayList<Integer> Syllables;
        int totalSyllables = 0;
        for (String sentence : sentences) {
            System.out.println(sentence.length() + ": " + sentence);
        }
        words = breakSentenceInToWord(sentences);
        removeSpaces(words);
        Syllables = howManySyllables(words);
        for (int i = 0; i < Syllables.size(); i++) {
            totalSyllables += Syllables.get(i);
        }

        return 206.835 - (1.015 * (words.size() / sentences.size())) - (84.6 * (totalSyllables) / words.size());
    }

    public static ArrayList<String> removeSpaces(ArrayList<String> words) {
        for (int i = 0; i < words.size(); i++) {
            if (words.get(i).length() == 0) {
                words.remove(i);
            }
        }
        return words;
    }

    public static ArrayList<Integer> howManySyllables(ArrayList<String> a) {
        ArrayList<Integer> Syllables = new ArrayList<Integer>();
        for (int i = 0; i < a.size(); i++) {
            Syllables.add(syllablesFor(a.get(i)));
        }
        return Syllables;
    }

    private static int syllablesFor(String testWord) {
        boolean inVowelChain = false;
        int boundaries = 0;

        for (int i = 0; i < testWord.length(); i++) {
            String letter = testWord.substring(i, i + 1);
            if (isVowel(letter)) {
                if (!inVowelChain) {
                    inVowelChain = true;
                    boundaries++;
                }
            } else {
                inVowelChain = false;
            }
        }

        return boundaries;
    }

    private static boolean isVowel(String letter) {
        return "aeiouy".contains(letter);
    }


    public int getTargetCount(String target) {
        if (DirtyFlag) recalulateData();
        int counter = 0;
        for (int i = 0; i < words.size(); i++) {
            if (words.get(i).equals(target)) counter++;
        }
        return counter;

    }

    public boolean TwoTargetOccurances(String sentence, String targetTwo, String targetOne) {
        if (DirtyFlag) recalulateData();
        if (sentence.contains(targetOne) && sentence.contains(targetTwo)) return true;

        return false;
    }

    public int getUnusualWordCount() {
        return 0;
    }


}
