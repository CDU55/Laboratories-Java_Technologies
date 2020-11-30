package DataLayer;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.*;
import javax.xml.crypto.Data;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
@ManagedBean(name = "meeting")
@RequestScoped
public class Meeting {

    @NotNull
    private int Id;
    @Size(min = 5,max = 5,message = "The person id must be 5 character long")
    @Pattern(message = "Invalid id form",regexp = "M[0-9]{4}")
    @NotNull(message = "The id must pe provided")
    private String meetingId;
    @NotNull(message = "The topic must be provided")
    @Size(min = 1,message = "Topic must not be empty")
    private String topic;
    @NotNull(message = "The date must be provided")
    @Future(message = "A future date must be selected")
    private Date startTime;
    @Min(value = 0,message = "Duration must be positive")
    private int duration;
    @Min(value = 1,message = "Location Ids are pozitive numbers")
    private int locationId;

    public int fee;

    public String required_rank;

    public String type;


    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(String meetingId) {
        this.meetingId = meetingId;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) throws ParseException {
        String formatted=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(startTime);
        this.startTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(formatted);
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public String getRequired_rank() {
        return required_rank;
    }

    public void setRequired_rank(String required_rank) {
        this.required_rank = required_rank;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void add() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            this.Id=DataProvider.getMeetingId();
            DataProvider.addMeeting(this);
            context.addMessage(null, new FacesMessage("Successful",  "The meeting was successfully added to the database"));
        } catch (SQLException throwables) {
            context.addMessage(null, new FacesMessage("Failure",  throwables.getMessage()));
        } catch (ClassNotFoundException e) {
            context.addMessage(null, new FacesMessage("Failure",  e.getMessage()));
        }
    }

    public void update() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            DataProvider.updateMeeting(this);
            context.addMessage(null, new FacesMessage("Successful",  "The meeting data was successfully updated"));
        } catch (SQLException throwables) {
            context.addMessage(null, new FacesMessage("Failure",  throwables.getMessage()));
        } catch (ClassNotFoundException e) {
            context.addMessage(null, new FacesMessage("Failure",  e.getMessage()));
        }
    }

    public void delete() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            DataProvider.deleteMeeting(this);
            context.addMessage(null, new FacesMessage("Successful",  "The meeting  was deleted from the database"));
        } catch (SQLException throwables) {
            context.addMessage(null, new FacesMessage("Failure",  throwables.getMessage()));
        } catch (ClassNotFoundException e) {
            context.addMessage(null, new FacesMessage("Failure",  e.getMessage()));
        }
    }
}
