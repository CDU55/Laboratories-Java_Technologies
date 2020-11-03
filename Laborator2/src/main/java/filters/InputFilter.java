package filters;

import org.sqlite.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class InputFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req=(HttpServletRequest) servletRequest;
        if(req.getMethod().equalsIgnoreCase("GET"))
        {
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        else
        {
            String word=req.getParameter("word");
            String def=req.getParameter("definition");
            if(word.isEmpty() || def.isEmpty())
            {
                RequestDispatcher dispatcher =
                        req.getRequestDispatcher("/input.jsp");
                dispatcher.forward(servletRequest,servletResponse);
                return;
            }
        }
        if(req.getParameter("language")==null)
        {
            String defaultLanguage=req.getServletContext().getInitParameter("language");
            req.setAttribute("language",defaultLanguage);
        }
        filterChain.doFilter(servletRequest,servletResponse);
        return;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
