package filters;



import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class ResponseModifierFilter implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        ResponseWrapper wrapper
                = new ResponseWrapper((HttpServletResponse) servletResponse);
        String language=servletRequest.getParameter("language_used");
        String setLanguage="en_US";
        if(language!=null && language.equals("ro"))
        {
            setLanguage="ro_RO";
        }
        if(language!=null) {
            HttpServletRequest reqConverted = (HttpServletRequest) servletRequest;
            reqConverted.getSession().setAttribute("language_used", setLanguage);
        }
        filterChain.doFilter(servletRequest, wrapper);
        if (servletResponse.getContentType() != null
                && servletResponse.getContentType().contains("text/html")) {
            String content = wrapper.toString();
            Document parsedHTMLDocument = Jsoup.parse(content);
            Element body = parsedHTMLDocument.selectFirst("body");
            body.appendElement("h1").text("This html content was modified using a filter at " + new Date());
            PrintWriter out = servletResponse.getWriter();
            out.write(parsedHTMLDocument.toString());
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
