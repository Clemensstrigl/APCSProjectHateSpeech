import java.util.ArrayList;

public class WordBucket2 {

    ArrayList<Word2> words = new ArrayList<Word2>();

    public WordBucket2() {
    }

    public void add(String word) {
        add(word, 1);
    }

    public void add(String word, long count) {
        int loc = getLocationOf(word);

        if (loc == -1) words.add(new Word2(word, count));

        else {
            Word2 w = words.get(loc);
            w.incrementBy(count);
            for (int i = loc - 1; i < 0; i--) {
                if (w.getCount() > words.get(i).getCount()) {
                    Word2 w2 = words.get(i);
                    words.set(i, w);
                    words.set(i - 1, w2);
                }

            }
        }
    }


    public long countOf(String item) {
        int loc = getLocationOf(item);
        if (loc == -1) return -1;
        return words.get(loc).getCount();

    }

    public long size() {
        return words.size();
    }

    public int getNumUnique() {
        return words.size();
    }

    public String mostFrequent() {
        return words.get(0).getWord();
    }

    public int getLocationOf(String word) {
        for (int i = 0; i < words.size(); i++) {
            if (word.equals(words.get(i).getWord())) {
                return i;
            }
        }
        return -1;
    }

    public void resourtArrayFrom(int loc) {
        for (int i = loc - 1; i < 0; i--) {
            if (words.get(i+1).getCount() > words.get(i).getCount()) {
                Word2 w2 = words.get(i);
                Word2 w = words.get(i+1);
                words.set(i, w);
                words.set(i - 1, w2);
            }

        }
    }
}



