package DataLayerJPA;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "Meetings")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="meeting_type",
        discriminatorType = DiscriminatorType.STRING)

public class MeetingsJPA {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "Id", nullable = false)
    private Integer id;
    @Column(name = "MeetingId", nullable = false)
    private String meetingId;
    @Column(name = "Topic", nullable = false)
    private String topic;

    @Column(name = "StartTime", nullable = false)
    private String startTime;
    @Column(name = "Duration", nullable = false)
    private int duration;

    @Column(name = "LocationId", nullable = false)
    private Integer locationId;
    @ManyToOne
    @JoinColumn(name = "LocationId", referencedColumnName = "Id")
    private LocationsJPA locationsByLocationId;
    @OneToMany(mappedBy = "meetingsByMeetingId")
    private Collection<PersonsMeetingsJPA> personsMeetingsById;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }


    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MeetingsJPA that = (MeetingsJPA) o;
        return duration == that.duration &&
                Objects.equals(id, that.id) &&
                Objects.equals(meetingId, that.meetingId) &&
                Objects.equals(topic, that.topic) &&
                Objects.equals(startTime, that.startTime) &&
                Objects.equals(locationId, that.locationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, meetingId, topic, startTime, duration, locationId);
    }


    public LocationsJPA getLocationsByLocationId() {
        return locationsByLocationId;
    }

    public void setLocationsByLocationId(LocationsJPA locationsByLocationId) {
        this.locationsByLocationId = locationsByLocationId;
    }


    public Collection<PersonsMeetingsJPA> getPersonsMeetingsById() {
        return personsMeetingsById;
    }

    public void setPersonsMeetingsById(Collection<PersonsMeetingsJPA> personsMeetingsById) {
        this.personsMeetingsById = personsMeetingsById;
    }
}
