import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<String> Races = new ArrayList<String>();
    static ArrayList<String> ActionWords = new ArrayList<String>();
    static ArrayList<String> Slangs = new ArrayList<String>();
    static ArrayList<String> Sentences = new ArrayList<String>();
    static ArrayList<String> Symbols = new ArrayList<String>();


    public static void main(String[] args) {
        importDataSet("C:\\Users\\cleme\\IdeaProjects\\APCSProjcetHateSpeech\\scr\\All Data\\DiscriminativeDataBase");

}





    public static String importDataSet(String filename) {
        Scanner scanner;
        StringBuilder output = new StringBuilder();

        try {
            scanner = new Scanner(new FileInputStream(filename), "UTF-8");
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] splitLines = line.split(",");
                ArrayList<String> Lines = (ArrayList) Main.class.getField("splitLines[0]");
                for (int i = 0; i < splitLines.length; i++) {

                }

            }

            scanner.close();

        } catch (FileNotFoundException | NoSuchFieldException e) {
            System.out.println("File not found " + filename);
        }

        return output.toString();
    }
}
