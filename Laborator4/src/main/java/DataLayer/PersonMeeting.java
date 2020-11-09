package DataLayer;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.Min;
import java.sql.SQLException;

@ManagedBean(name = "personMeeting")
@RequestScoped
public class PersonMeeting {

    @Min(value = 1,message = "Person id must be a positive number")
    private int personId;
    @Min(value = 1,message = "Meeting id must be a positive number")
    private int meetingId;

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public int getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(int meetingId) {
        this.meetingId = meetingId;
    }

    public void add() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            DataProvider.addPersonMeeting(this);
            context.addMessage(null, new FacesMessage("Successful",  "The person and the meeting were linked"));
        } catch (SQLException throwables) {
            context.addMessage(null, new FacesMessage("Failure",  throwables.getMessage()));
        } catch (ClassNotFoundException e) {
            context.addMessage(null, new FacesMessage("Failure",  e.getMessage()));
        }
    }

    public void delete() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            DataProvider.deletePersonMeeting(this);
            context.addMessage(null, new FacesMessage("Successful",  "The person was removed from the meeting attendance list"));
        } catch (SQLException throwables) {
            context.addMessage(null, new FacesMessage("Failure",  throwables.getMessage()));
        } catch (ClassNotFoundException e) {
            context.addMessage(null, new FacesMessage("Failure",  e.getMessage()));
        }
    }
}
