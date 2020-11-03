package filters;

import org.jsoup.Jsoup;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.PrintWriter;
import java.io.StringWriter;

public class ResponseWrapper
        extends HttpServletResponseWrapper {
    private final StringWriter output;
    public ResponseWrapper(HttpServletResponse response) {
        super(response);
        output = new StringWriter();
        output.flush();
    }
    @Override
    public PrintWriter getWriter() {
        return new PrintWriter(output);
    }
    @Override
    public String toString() {
        return output.toString();
    }
}
