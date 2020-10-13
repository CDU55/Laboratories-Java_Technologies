import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

@WebServlet(name = "Words", urlPatterns = {"/words"})
public class WordServlet extends HttpServlet {
    @Override
    public void service( HttpServletRequest request,
                       HttpServletResponse response ) throws IOException {
        response.setContentType("text/html");
        ClientInfoLogging.logInfo(request,this.getServletContext());
        String name = request.getParameter("letters");
        HashMap<Character,Integer> arguments=LetterCount.lettersDictionary(name);
        PrintWriter out = new PrintWriter(response.getWriter());
        File wordsFile = new File("C:\\Users\\Claudiu\\Desktop\\Master\\Java\\Laboratories-Java_Technologies\\Laborator1\\Lab_1\\src\\words.txt");
        Scanner wordsReader = new Scanner(wordsFile);
        while (wordsReader.hasNextLine()) {
            String data = wordsReader.nextLine();
            if(WordComparator.compareWords(arguments,LetterCount.lettersDictionary(data)))
            {
                out.println("<p>"+data+"<p>");
            }
        }
        out.close();
    }
}
