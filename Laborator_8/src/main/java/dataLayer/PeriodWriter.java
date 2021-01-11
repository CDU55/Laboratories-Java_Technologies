package dataLayer;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Named("periodWriter")
public class PeriodWriter {

    private Date before;
    private Date after;

    public Date getBefore() {
        return before;
    }

    public void setBefore(Date before) {
        this.before = before;
    }

    public Date getAfter() {
        return after;
    }

    public void setAfter(Date after) {
        this.after = after;
    }

    public void savePeriod(String currentUserRole)
    {
        FacesContext context = FacesContext.getCurrentInstance();
        if(currentUserRole==null ||!currentUserRole.toLowerCase().equals("admin"))
        {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"FAILURE",  "Only admins can edit the registration period"));
            return;
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String begin = dateFormat.format(before);
        String end=dateFormat.format(after);
        BufferedWriter out = null;

        try {
            FileWriter fstream = new FileWriter("C:\\Users\\Claudiu\\Desktop\\Master\\Java\\Laboratories-Java_Technologies\\Laborator_8\\src\\Resources\\period.txt", false);
            out = new BufferedWriter(fstream);
            out.write(begin+"\n");
            out.write(end);
            context.addMessage(null, new FacesMessage("Success",  "Period saved"));
        }

        catch (IOException e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"FAILURE",  "An error occurred saving the time period"+" " +e.getMessage()));
        }

        finally {
            if(out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"FAILURE",  "An error occurred saving the time period"+" " +e.getMessage()));
                }
            }
        }
    }
}
