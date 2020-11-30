package DataLayerJPA;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "PersonsMeetings")
public class PersonsMeetingsJPA {
    @Column(name = "PersonId", nullable = false)
    private Integer personId;
    @Column(name = "MeetingId", nullable = false)
    private Integer meetingId;
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "Id", nullable = false)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "PersonId", referencedColumnName = "Id")
    private PersonsJPA personsByPersonId;
    @ManyToOne
    @JoinColumn(name = "MeetingId", referencedColumnName = "Id")
    private MeetingsJPA meetingsByMeetingId;


    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }


    public Integer getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(Integer meetingId) {
        this.meetingId = meetingId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonsMeetingsJPA that = (PersonsMeetingsJPA) o;
        return Objects.equals(personId, that.personId) &&
                Objects.equals(meetingId, that.meetingId) &&
                Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId, meetingId, id);
    }


    public PersonsJPA getPersonsByPersonId() {
        return personsByPersonId;
    }

    public void setPersonsByPersonId(PersonsJPA personsByPersonId) {
        this.personsByPersonId = personsByPersonId;
    }


    public MeetingsJPA getMeetingsByMeetingId() {
        return meetingsByMeetingId;
    }

    public void setMeetingsByMeetingId(MeetingsJPA meetingsByMeetingId) {
        this.meetingsByMeetingId = meetingsByMeetingId;
    }
}
