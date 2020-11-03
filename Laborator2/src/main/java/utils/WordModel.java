package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WordModel {

    public WordModel(String word,String language,String definition,String date)
    {
        this.word=word;
        this.language=language;
        this.definition=definition;
        try {
            this.date=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String word;
    public String language;
    public String definition;
    public Date date;

    public String getWord() {
        return word;
    }

    public String getLanguage() {
        return language;
    }

    public String getDefinition() {
        return definition;
    }

    public Date getDate() {
        return date;
    }
}
