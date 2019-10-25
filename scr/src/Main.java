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

    public static void main(String[] args) {
        d = new Document(importTexT(ExampleFileName));
        dataByName = importDataSet("C:\\Users\\cleme\\IdeaProjects\\APCSProjcetHateSpeech\\scr\\All Data\\DiscriminativeDataBase");
            //Check each sentence if it contains the slangs or symbols
        for (String sentence: d.getSentences()) {
            if(getLocSymbole(sentence) != -1){
                System.out.println(sentence);
                continue;
            }
            int Slang =  getLocSlang(sentence);
            int Race = getLocRace(sentence);
            int ActionWords =  getLocActionWord(sentence);
            if(Race == -1 || Slang ==-1 ){
                continue;
            }
            if(Race < ActionWords && Slang >  ActionWords){
                System.out.println(sentence);
            }

        }
            


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
        for (int i = 0; i <dataByName.get("Slang").size(); i++) {
            if(sentence.contains(dataByName.get("Slang").get(i))){
               return sentence.indexOf(dataByName.get("Slang").get(i));
            }
        }
        return -1;
    }
    public static int getLocSymbole(String sentence){
        for (int i = 0; i <dataByName.get("Symbols").size(); i++) {
            if(sentence.contains(dataByName.get("Symbols").get(i))){
                return sentence.indexOf(dataByName.get("Symbols").get(i));
            }
        }
        return -1;
    }

    public static int getLocRace(String sentence){
        for (int i = 0; i <dataByName.get("Races").size(); i++) {
            if(sentence.contains(dataByName.get("Races").get(i))){
                return sentence.indexOf(dataByName.get("Races").get(i));
            }
        }
        return -1;
    }

    public static int getLocActionWord(String sentence){
        for (int i = 0; i <dataByName.get("ActionWords").size(); i++) {
            if(sentence.contains(dataByName.get("ActionWords").get(i))){
                return sentence.indexOf(dataByName.get("ActionWords").get(i));
            }
        }
        return -1;
    }
}
