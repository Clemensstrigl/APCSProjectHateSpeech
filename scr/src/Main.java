import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {
    static HashMap<String, List<String>> dataByName;

    public static void main(String[] args) {
        dataByName = importDataSet("C:\\Users\\cleme\\IdeaProjects\\APCSProjcetHateSpeech\\scr\\All Data\\DiscriminativeDataBase");

//        for (String key : dataByName.keySet() ) {
////
////        }



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
}
