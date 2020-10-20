import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Random;

@WebServlet(name = "Capcha", urlPatterns = {"/capcha"})
public class CapchaServlet extends HttpServlet {
    private static int result;
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int d = 300;
        Random r=new Random();
        int a=r.nextInt(10);
        int b=r.nextInt(10);
        result=a+b;
        BufferedImage bufferedImage = new BufferedImage(d, d,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bufferedImage.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0,0,d,d);
        g.setColor(Color.BLACK);
        g.setFont(new Font("TimesRoman",Font.PLAIN,d/3));
        g.drawString(a+" + "+b,25,175);
        g.dispose();
        response.setContentType("image/png");
        OutputStream os = response.getOutputStream();

        ImageIO.write(bufferedImage, "png", os);
        os.close();
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher;
        if(request.getParameter("result")==null || request.getParameter("result").equals(""))
        {
            dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
            request.setAttribute("errorMessage","Invalid capcha result");
        }
        int requestResult=Integer.parseInt(request.getParameter("result"));
        if(requestResult==result) {
            dispatcher = getServletContext().getRequestDispatcher("/words");
        }
        else
        {
            dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
            request.setAttribute("errorMessage","Invalid capcha result");
        }
        dispatcher.forward(request, response);

    }
}