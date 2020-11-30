package DataLayerJPA;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import java.util.Date;

@ManagedBean(name = "meetingFilter")
@SessionScoped
public class MeetingFilter {

    public boolean byTopic;

    public String topic;

    public boolean byDurationsLessThan;

    public int durationLessThan;

    public boolean byDurationsGreaterThan;

    public int durationGreaterThan;

    public boolean byLocation;

    public String location;

    public boolean byDate;

    public Date date;

    public boolean isByTopic() {
        return byTopic;
    }

    public void setByTopic(boolean byTopic) {
        this.byTopic = byTopic;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public boolean isByDurationsLessThan() {
        return byDurationsLessThan;
    }

    public void setByDurationsLessThan(boolean byDurationsLessThan) {
        this.byDurationsLessThan = byDurationsLessThan;
    }

    public int getDurationLessThan() {
        return durationLessThan;
    }

    public void setDurationLessThan(int durationLessThan) {
        this.durationLessThan = durationLessThan;
    }

    public boolean isByDurationsGreaterThan() {
        return byDurationsGreaterThan;
    }

    public void setByDurationsGreaterThan(boolean byDurationsGreaterThan) {
        this.byDurationsGreaterThan = byDurationsGreaterThan;
    }

    public int getDurationGreaterThan() {
        return durationGreaterThan;
    }

    public void setDurationGreaterThan(int durationGreaterThan) {
        this.durationGreaterThan = durationGreaterThan;
    }

    public boolean isByLocation() {
        return byLocation;
    }

    public void setByLocation(boolean byLocation) {
        this.byLocation = byLocation;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isByDate() {
        return byDate;
    }

    public void setByDate(boolean byDate) {
        this.byDate = byDate;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
