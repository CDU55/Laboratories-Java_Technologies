package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Languages {

    public static List<String> getLanguages()
    {
        List<String> allLanguages=new ArrayList<String>();
        File wordsFile = new File("C:\\Users\\Claudiu\\Desktop\\Master\\Java\\Laboratories-Java_Technologies\\Laborator2\\src\\Resources\\languages.txt");
        Scanner wordsReader = null;
        try {
            wordsReader = new Scanner(wordsFile);
        } catch (FileNotFoundException e) {
            return allLanguages;
        }
        while (wordsReader.hasNextLine()) {
            String language = wordsReader.nextLine();

                allLanguages.add(language);
        }
        return allLanguages;
    }
}
