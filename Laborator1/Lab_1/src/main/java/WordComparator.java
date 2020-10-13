import java.util.HashMap;

public class WordComparator {
    public static boolean compareWords(HashMap<Character,Integer> argument,HashMap<Character,Integer> currentWord)
    {
        boolean validWord=true;
        for(Character letter:currentWord.keySet())
        {
            if(!argument.containsKey(letter) || argument.get(letter)<currentWord.get(letter) )
            {
                validWord=false;
                break;
            }
        }
        return validWord;
    }
}
