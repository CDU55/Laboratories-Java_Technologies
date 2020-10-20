import utils.WordModel;
import utils.WordsService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Words", urlPatterns = {"/words"})
public class WordsServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException  {

        List<WordModel> words=new ArrayList<WordModel>();
        words.add(new WordModel("Java","English","Programming language"));
        words.add(new WordModel("Tehnologii Java","Romanian","Materie facultat"));
        try {
            request.setAttribute("wordsList",WordsService.getWords());
        }
         catch (ClassNotFoundException|SQLException e) {
             RequestDispatcher dispatcher =
                     getServletContext().getRequestDispatcher("/error.jsp");
             request.setAttribute("errorMessage",e.getMessage());
             dispatcher.forward(request,response);
        }
        RequestDispatcher dispatcher =
                getServletContext().getRequestDispatcher("/result.jsp");
        dispatcher.forward(request,response);

    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String word=request.getParameter("word");
        String language=request.getParameter("language");
        String definition=request.getParameter("definition");
        Cookie wordCookie=new Cookie("word",word);
        Cookie languageCookie=new Cookie("language",language);
        wordCookie.setMaxAge(60*60*24*365);
        languageCookie.setMaxAge(60*60*24*365);
        response.addCookie(wordCookie);
        response.addCookie(languageCookie);
        boolean okInsert=false;
        try {
            WordsService.addWord(word,language,definition);
            okInsert=true;
        } catch (ClassNotFoundException|SQLException e) {

            RequestDispatcher dispatcher =
                    getServletContext().getRequestDispatcher("/error.jsp");
            request.setAttribute("errorMessage",e.getMessage());
            dispatcher.forward(request,response);
        }
        if(okInsert)
        {
            doGet(request,response);
        }
    }
}
