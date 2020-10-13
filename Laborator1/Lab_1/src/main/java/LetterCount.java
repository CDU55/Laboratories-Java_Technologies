import java.util.HashMap;

public class LetterCount {
    public static HashMap<Character,Integer> lettersDictionary(String word)
    {
        HashMap<Character,Integer> lettersCount=new HashMap<Character, Integer>();
        for(int charIndex=0;charIndex<word.length();charIndex++)
        {
            if(lettersCount.containsKey(word.charAt(charIndex)))
            {
                lettersCount.put(word.charAt(charIndex),lettersCount.get(word.charAt(charIndex))+1);
            }
            else
            {
                lettersCount.put(word.charAt(charIndex),1);
            }
        }
        return lettersCount;
    }
}
