public class Word2 {
    String word;
    long count;
    public Word2(String word, long count){
        this.word = word;
        this.count = count;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public void incrementBy(long count) {
        this.count += count;
    }
}
