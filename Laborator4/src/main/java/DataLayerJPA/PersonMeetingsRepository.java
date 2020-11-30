package DataLayerJPA;

import DataLayer.PersonMeeting;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.*;
import java.util.List;

@SessionScoped
@ManagedBean(name = "persMeetRepo")
public class PersonMeetingsRepository {
    @PersistenceContext
    EntityManager em;

    public void addPersonMeetings(PersonMeeting persMeet){
        PersonsMeetingsJPA toAdd=new PersonsMeetingsJPA();
        toAdd.setMeetingsByMeetingId(em.find(MeetingsJPA.class,persMeet.getMeetingId()));
        toAdd.setPersonsByPersonId(em.find(PersonsJPA.class,persMeet.getPersonId()));
        em.getTransaction().begin();
        em.persist(toAdd);
        em.getTransaction().commit();
    }

    public List<PersonsMeetingsJPA> getPersonsMeetings()
    {
        Query q= em.createQuery("SELECT pm FROM PersonsMeetingsJPA pm");
        return q.getResultList();
    }

    public void deletePersonMeetings(PersonMeeting persMeet)
    {
        em.getTransaction().begin();
        Query q=em.createQuery("DELETE FROM PersonsMeetingsJPA pm WHERE pm.meetingsByMeetingId.id=:meet_id AND pm.personsByPersonId.id=:person_id");
        q.setParameter("meet_id",persMeet.getMeetingId());
        q.setParameter("person_id",persMeet.getPersonId());
        q.executeUpdate();
        em.getTransaction().commit();
    }
}
