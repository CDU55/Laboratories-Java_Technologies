package utils;

import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WordsService {

    private static String selectCommand="SELECT * FROM 'Words';";
    private static String insertWord="INSERT INTO 'Words' VALUES(?,?,?,?);";
    private static String getWordDefinition="SELECT Definition FROM 'Words' Where Word=? ;";
    public static List<WordModel> wordsList;

    static {
        try {
            wordsList = getWordsList();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static List<WordModel> getWordsList() throws SQLException, ClassNotFoundException {
        List<WordModel> words=new ArrayList<WordModel>();
        Connection conn=DbConnection.getConnection();
        PreparedStatement st=conn.prepareStatement(selectCommand);
        ResultSet result=st.executeQuery();
        while (result.next())
        {
            words.add(new WordModel(result.getString("Word"),result.getString("Language"),result.getString("Definition"),result.getString(4)));
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
        st.setString(4,new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()).toString());
        st.executeUpdate();
        st.close();
        conn.close();
    }

    public static String getDefinition(String word)
    {
        Connection conn= null;
        try {
            conn = DbConnection.getConnection();
            PreparedStatement st=conn.prepareStatement(getWordDefinition);
            st.setString(1, word);
            ResultSet result=st.executeQuery();
            String definition=null;
            if(result.next()) {
                definition=result.getString(1);
            }
            else
            {
                definition="No definition found";
            }
            st.close();
            conn.close();
            return definition;
        } catch (SQLException e) {
            return e.getMessage();
        } catch (ClassNotFoundException e) {
            return "An error occurred";
        }
    }

    public Object getWords() {
        return (Object)getWords();

    }
}
