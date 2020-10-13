import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Locale;

public class ClientInfoLogging {

    public static void logInfo(HttpServletRequest request, ServletContext context)
    {
        context.log("A new request has arrived !!!");
        context.log("Request time : "+ new java.util.Date());
        context.log("Request type : "+request.getMethod());
        context.log("Client IP : "+request.getRemoteAddr());
        context.log("Client User-Agent : "+request.getHeader("User-Agent"));
        Enumeration<Locale> locales=request.getLocales();
        StringBuilder localesOutput=new StringBuilder();
        while (locales.hasMoreElements())
        {
            localesOutput.append(locales.nextElement().toString()+" ");
        }
        context.log("Client languages : "+localesOutput);
        Enumeration<String> parameters=request.getParameterNames();
        StringBuilder parametersOutput=new StringBuilder();
        while (parameters.hasMoreElements())
        {
            parametersOutput.append(parameters.nextElement()+" ");
        }
        context.log("Parameters : "+parametersOutput);
        context.log(request.getMethod());
    }
}
