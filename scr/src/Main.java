import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {
    static HashMap<String, List<String>> dataByName;
    static Document d;
    static String ExampleFileName = "";
    static int counter = 0;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";

    public static void main(String[] args) {
       d = new Document(importTexT(ExampleFileName));
        dataByName = importDataSet("C:\\Users\\cleme\\IdeaProjects\\APCSProjcetHateSpeech\\scr\\All Data\\DiscriminativeDataBase");
            //Check each sentence if it contains the slangs or symbols
        for (String sentence: d.getSentences()) {
            sentence = sentence.toLowerCase();
            if(getLocSymbole(sentence)){
                System.out.println(ANSI_RED+ sentence + ANSI_RESET);
                counter++;
                continue;
            }
            int Slang =  getLocSlang(sentence);
            int Race = getLocRace(sentence);
            int ActionWords =  getLocActionWord(sentence);
            if(Race == -1 || Slang ==-1 ){
                System.out.println(sentence);
                continue;
            }
            if(Race < ActionWords && Slang >  ActionWords){
                counter++;
                System.out.println(ANSI_RED + sentence + ANSI_RESET);
            }

        }

        System.out.println("Number of Discriminative sentences: " + counter + "/" + d.getSentenceCount());


}

    public static int getDiff(int a, int b){
        return Math.abs(a-b);
    }

    public static String importTexT(String filename) {
        Scanner scanner;
        StringBuilder output = new StringBuilder();

        try {
            scanner = new Scanner(new FileInputStream(filename), "UTF-8");
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                output.append(line.trim()+"\n");
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found " + filename);
        }

        return output.toString();
    }


    public static HashMap importDataSet(String filename) {
        HashMap<String,List<String>> dataByName = new HashMap<String, List<String>>();
        Scanner scanner;
        StringBuilder output = new StringBuilder();

        try {
            scanner = new Scanner(new FileInputStream(filename), "UTF-8");
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] splitLines = line.split(",");
                ArrayList<String> dataList = new ArrayList<String>();
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

    public static int getLocSlang(String sentence){
        for (int i = 0; i <dataByName.get("Slangs").size(); i++) {
            String slang = dataByName.get("Slangs").get(i).toLowerCase();
            if(sentence.contains(slang)){
               return sentence.indexOf(slang);
            }
            else if(sentence.contains(slang + "s")){
                return sentence.indexOf(slang + "s");
            }

        }
        return -1;
    }
    public static boolean getLocSymbole(String sentence){
        for (int i = 0; i <dataByName.get("Symbols").size(); i++) {
            String symbole = dataByName.get("Symbols").get(i);
            if(sentence.contains(symbole)){
                return true;
            }
        }
        return false;
    }

    public static int getLocRace(String sentence){
        for (int i = 0; i <dataByName.get("Races").size(); i++) {
            String race = dataByName.get("Races").get(i).toLowerCase();
            if(sentence.contains(race)){
                return sentence.indexOf(race);
            }
        }
        return -1;
    }

    public static int getLocActionWord(String sentence){
        for (int i = 0; i <dataByName.get("ActionWords").size(); i++) {
            String ActionWord = dataByName.get("ActionWords").get(i).toLowerCase();
            if(sentence.contains(ActionWord)){
                return sentence.indexOf(ActionWord);
            }
        }
        return -1;
    }
}
