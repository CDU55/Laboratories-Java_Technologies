package DataLayerJPA;

import DataLayer.DataProvider;
import DataLayer.Meeting;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

@SessionScoped
@ManagedBean(name = "meetingRepo")
public class MeetingsRepository {

    @PersistenceContext
    EntityManager em;

    public void addMeeting(Meeting meeting) throws SQLException, ClassNotFoundException {
        if(meeting.getType().equals("P"))
        {
            PaidMeeting toAdd=new PaidMeeting();
            em.getTransaction().begin();
            setFields(toAdd,meeting);
            toAdd.setFee(meeting.getFee());
            em.persist(toAdd);
        }
        else
        {
            AcademicMeeting toAdd=new AcademicMeeting();
            em.getTransaction().begin();
            setFields(toAdd,meeting);
            toAdd.setRequired_rank(meeting.getRequired_rank());
            em.persist(toAdd);
        }
        em.getTransaction().commit();
    }
    public void editMeetings(Meeting meeting)
    {
        em.getTransaction().begin();
        Query q=em.createQuery("UPDATE MeetingsJPA m SET topic=:mTopic,startTime=:mStartTime,duration=:mDuration,locationsByLocationId=:loc WHERE m.meetingId=:mId");
        q.setParameter("mId",meeting.getMeetingId());
        q.setParameter("mTopic",meeting.getTopic());
        q.setParameter("mStartTime",meeting.getStartTime().toString());
        q.setParameter("mDuration",meeting.getDuration());
        q.setParameter("loc",em.find(LocationsJPA.class,meeting.getLocationId()));
        q.executeUpdate();
        em.getTransaction().commit();
    }
    private void setFields(MeetingsJPA toAdd,Meeting meeting) throws SQLException, ClassNotFoundException {
        toAdd.setTopic(meeting.getTopic());
        toAdd.setId( DataProvider.getMeetingId());
        toAdd.setStartTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(meeting.getStartTime()));
        toAdd.setMeetingId(meeting.getMeetingId());
        toAdd.setDuration( meeting.getDuration());
        toAdd.setLocationsByLocationId(em.find(LocationsJPA.class,meeting.getLocationId()));

    }

    public List<MeetingsJPA> getMeetings()
    {
        Query q= em.createQuery("SELECT m FROM MeetingsJPA m");
        return q.getResultList();
    }

    public void deleteMeetings(Meeting meeting)
    {
        em.getTransaction().begin();
        Query q=em.createQuery("DELETE FROM MeetingsJPA m WHERE m.meetingId=:mId");
        q.setParameter("mId",meeting.getMeetingId());
        q.executeUpdate();
        em.getTransaction().commit();
    }
}
