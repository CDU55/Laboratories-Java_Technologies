import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.time.LocalDate;
import java.time.LocalDateTime;

@ManagedBean(name = "countProvider")
@SessionScoped
public class SessionCountProvider  {

    private  String message;
    public void getSessionCount() {
        System.out.println("session count getter invoked");
        message="Sessions number : "+ SessionCounter.getCount()+" time :"+ LocalDateTime.now();
    }

    public  String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        message = message;
    }
}