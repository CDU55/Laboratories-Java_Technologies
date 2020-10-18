import javax.servlet.AsyncContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

@WebServlet(name = "AsyncWordsText", urlPatterns = {"/words_text_async"},asyncSupported = true)
public class AsyncWordServlet extends HttpServlet {
    @Override
    public void service( HttpServletRequest request,
                         HttpServletResponse response ) throws IOException {
        response.setContentType("text/plaintext");
        final AsyncContext asyncContext=request.startAsync(request,response);
        asyncContext.start(new Runnable() {
            @Override
            public void run() {
                String name = request.getParameter("letters");
                HashMap<Character,Integer> arguments=LetterCount.lettersDictionary(name);
                PrintWriter out = null;
                Scanner wordsReader=null;
                try {
                    out = new PrintWriter(asyncContext.getResponse().getWriter());
                    File wordsFile = new File("C:\\Users\\Claudiu\\Desktop\\Master\\Java\\Laboratories-Java_Technologies\\Laborator1\\Lab_1\\src\\words.txt");
                    wordsReader = new Scanner(wordsFile);
                    //Thread.sleep(10000);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                while (wordsReader.hasNextLine()) {
                    while (wordsReader.hasNextLine()) {
                        String data = wordsReader.nextLine();
                        if(WordComparator.compareWords(arguments,LetterCount.lettersDictionary(data)))
                        {
                            out.println(data);
                        }
                    }
                }
                wordsReader.close();
                out.close();

            }
        });

    }
}