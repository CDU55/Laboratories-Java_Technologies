package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WordsService {

    private static String selectCommand="SELECT Word,Language,Definition FROM 'Words';";
    private static String insertWord="INSERT INTO 'Words' VALUES(?,?,?);";
    public static List<WordModel> getWords() throws SQLException, ClassNotFoundException {
        List<WordModel> words=new ArrayList<WordModel>();
        Connection conn=DbConnection.getConnection();
        PreparedStatement st=conn.prepareStatement(selectCommand);
        ResultSet result=st.executeQuery();
        while (result.next())
        {
            words.add(new WordModel(result.getString("Word"),result.getString("Language"),result.getString("Definition")));
        }
        result.close();
        st.close();
        conn.close();
        return words;
    }
    public static void addWord(String word,String language,String definition) throws SQLException, ClassNotFoundException {
        Connection conn=DbConnection.getConnection();
        PreparedStatement st=conn.prepareStatement(insertWord);
        st.setString(1, word);
        st.setString(2, language);
        st.setString(3, definition);
        st.executeUpdate();
        st.close();
        conn.close();
    }
}
