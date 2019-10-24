import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {
    static ArrayList<String> Races = new ArrayList<String>();
    static ArrayList<String> ActionWords = new ArrayList<String>();
    static ArrayList<String> Slangs = new ArrayList<String>();
    static ArrayList<String> Sentences = new ArrayList<String>();
    static ArrayList<String> Symbols = new ArrayList<String>();
    private static Object ArrayList;


    public static void main(String[] args) {
        var dataByName = importDataSet("C:\\Users\\cleme\\IdeaProjects\\APCSProjcetHateSpeech\\scr\\All Data\\DiscriminativeDataBase");
        System.out.println(dataByName.size());
        System.out.println(dataByName.get("Races"));

}


    public static HashMap importDataSet(String filename) {
        var dataByName = new HashMap<String, List<String>>();
        Scanner scanner;
        StringBuilder output = new StringBuilder();

        try {
            scanner = new Scanner(new FileInputStream(filename), "UTF-8");
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] splitLines = line.split(",");
                var dataList = new ArrayList<String>();
                for (int s = 1; s < splitLines.length; s++) {
                    dataList.add(splitLines[s]);
                }
                dataByName.put(splitLines[0], dataList);

            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found " + filename);
        }

        return dataByName;
    }
}
