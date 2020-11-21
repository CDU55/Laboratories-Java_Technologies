package DataLayer;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.naming.NamingException;
import javax.xml.crypto.Data;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

@ManagedBean(name = "data")
@RequestScoped
public class DataEntries {

    private List<Person> persons;
    private List<Meeting> meetings;
    private List<Location> locations;
    private List<LocationDistance> locationsDistance;
    private List<PersonMeeting> personMeetings;

    public List<Person> getPersons() throws SQLException, ClassNotFoundException {
        this.persons= DataProvider.getPersons();
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public List<Meeting> getMeetings() throws ParseException, SQLException, ClassNotFoundException, NamingException {
        this.meetings=DataProvider.getMeetings();
        return meetings;
    }

    public void setMeetings(List<Meeting> meetings) {
        this.meetings = meetings;
    }

    public List<Location> getLocations() throws SQLException, ClassNotFoundException, NamingException {
        this.locations=DataProvider.getLocations();
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public List<LocationDistance> getLocationsDistance() throws SQLException, ClassNotFoundException {
        this.locationsDistance=DataProvider.getLocationsDistance();
        return locationsDistance;
    }

    public List<PersonMeeting> getPersonMeetings() throws SQLException, ClassNotFoundException {
        this.personMeetings=DataProvider.getPersonsMeetings();
        return personMeetings;
    }

    public void setPersonMeetings(List<PersonMeeting> personMeetings) {
        this.personMeetings = personMeetings;
    }

    public void setLocationsDistance(List<LocationDistance> locationsDistance) {
        this.locationsDistance = locationsDistance;
    }
}
