package DataLayer;

import javafx.scene.input.DataFormat;

import javax.naming.NamingException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DataProvider {
private static String getPersonsQuery="SELECT * FROM Persons";
private static String personsCurrentId="SELECT MAX(Id) FROM Persons";
private static String insertPersonCommand="INSERT INTO persons VALUES(?,?,?);";
private static String deletePerson="DELETE FROM persons WHERE PersonId=?";
private static String updatePerson="UPDATE persons SET Name=? WHERE PersonId=?";
private static String personsNumber="SELECT COUNT(*) FROM Persons";

private static String getMeetingsQuery="SELECT * FROM Meetings";
private static String meetingsCurrentId="SELECT MAX(Id) FROM Meetings";
private static String insertMeetingCommand="INSERT INTO Meetings VALUES(?,?,?,?,?,?)";
private static String deleteMeeting="DELETE FROM meetings WHERE MeetingId=?";
private static String updateMeeting="UPDATE meetings SET Topic=?,StartTime=?,Duration=?,LocationId=? WHERE MeetingId=?";
private static String meetingsNumber="SELECT COUNT(*) FROM Meetings";


private static String getLocationsQuery="SELECT * FROM Locations";
private static String locationsCurrentId="SELECT MAX(Id) FROM Locations";
private static String insertLocationCommand="INSERT INTO Locations VALUES(?,?)";
private static String deleteLocation="DELETE FROM Locations WHERE name=?";
private static String locationsNumber="SELECT COUNT(*) FROM Locations";


private static String getPersonsMeetingsQuery="SELECT * FROM PersonsMeetings";
private static String insertPersonMeetingsCommand="INSERT INTO PersonsMeetings VALUES(?,?);";
private static String deletePersonMeeting="DELETE FROM PersonsMeetings WHERE PersonId=? AND MeetingId=? ";

private static String getLocationsDistanceQuery="SELECT * FROM LocationsDistance";
private static String insertLocationsDistanceCommand="INSERT INTO LocationsDistance VALUES(?,?,?);";
private static String deleteLocationsDistance="DELETE FROM LocationsDistance WHERE Location1=? AND Location2=?";
private static String updateLocationsDistance="UPDATE LocationsDistance SET Distance=? WHERE Location1=? AND Location2=?";
private static String distanceExists="SELECT COUNT(*) FROM LocationsDistance WHERE Location1=? AND Location2=?";
private static String getSingleDistance="SELECT * FROM LocationsDistance WHERE Location1=? AND Location2=?";




public static List<Person> getPersons() throws SQLException, ClassNotFoundException {
    Connection conn=DbConnection.getConnection();
    Statement st=conn.createStatement();
    ResultSet rs=st.executeQuery(getPersonsQuery);
    List<Person> persons=new ArrayList<Person>();
    while(rs.next())
    {
        Person p=new Person();
        p.setId(rs.getInt(1));
        p.setPersonId(rs.getString(2));
        p.setName(rs.getString(3));
        persons.add(p);
    }
    return persons;
}

    public static List<Meeting> getMeetings() throws SQLException, ClassNotFoundException, ParseException {
        Connection conn=DbConnection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery(getMeetingsQuery);
        List<Meeting> meetings=new ArrayList<Meeting>();
        while(rs.next())
        {
            Meeting m=new Meeting();
            m.setId(rs.getInt(1));
            m.setMeetingId(rs.getString(2));
            m.setTopic(rs.getString(3));
            m.setStartTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(rs.getString(4)));
            m.setDuration(rs.getInt(5));
            m.setLocationId(rs.getInt(6));
            meetings.add(m);
        }
        return meetings;
    }



    public static List<Location> getLocations() throws SQLException, ClassNotFoundException, NamingException {
        Connection conn=DbConnectionJNDI.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery(getLocationsQuery);
        List<Location> locations=new ArrayList<Location>();
        while(rs.next())
        {
            Location l=new Location();
            l.setId(rs.getInt(1));
            l.setName(rs.getString(2));
            locations.add(l);
        }
        return locations;
    }

    public static List<LocationDistance> getLocationsDistance() throws SQLException, ClassNotFoundException {
        Connection conn=DbConnection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery(getLocationsDistanceQuery);
        List<LocationDistance> locationsDist=new ArrayList<LocationDistance>();
        while(rs.next())
        {
            LocationDistance d=new LocationDistance();
            d.setLocation1(rs.getInt(1));
            d.setLocation2(rs.getInt(2));
            d.setDistance(rs.getInt(3));
            locationsDist.add(d);
        }
        return locationsDist;
    }

    public static List<PersonMeeting> getPersonsMeetings() throws SQLException, ClassNotFoundException {
        Connection conn=DbConnection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery(getPersonsMeetingsQuery);
        List<PersonMeeting> personMeetings=new ArrayList<PersonMeeting>();
        while(rs.next())
        {
            PersonMeeting pm=new PersonMeeting();
            pm.setPersonId(rs.getInt(1));
            pm.setMeetingId(rs.getInt(2));
            personMeetings.add(pm);
        }
        return personMeetings;
    }

public static void addPerson(Person p) throws SQLException, ClassNotFoundException
{
    Connection conn=DbConnection.getConnection();
    PreparedStatement st=conn.prepareStatement(insertPersonCommand);
    st.setInt(1,p.getId());
    st.setString(2, p.getPersonId());
    st.setString(3,p.getName());
    int modified=st.executeUpdate();
    System.out.println("Lines :"+modified);
    st.close();
    conn.close();
}

    public static void addMeeting(Meeting m) throws SQLException, ClassNotFoundException
    {
        Connection conn=DbConnection.getConnection();
        PreparedStatement st=conn.prepareStatement(insertMeetingCommand);
        st.setInt(1,m.getId());
        st.setString(2, m.getMeetingId());
        st.setString(3,m.getTopic());
        st.setString(4,new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(m.getStartTime()).toString());
        st.setInt(5,m.getDuration());
        st.setInt(6,m.getLocationId());
        int modified=st.executeUpdate();
        System.out.println("Lines :"+modified);
        st.close();
        conn.close();
    }

    public static void addLocation(Location l) throws SQLException, ClassNotFoundException
    {
        Connection conn=DbConnection.getConnection();
        PreparedStatement st=conn.prepareStatement(insertLocationCommand);
        st.setInt(1,l.getId());
        st.setString(2,l.getName());
        int modified=st.executeUpdate();
        System.out.println("Lines :"+modified);
        st.close();
        conn.close();
    }

    public static void addLocationDistance(LocationDistance d) throws SQLException, ClassNotFoundException
    {
        Connection conn=DbConnection.getConnection();
        PreparedStatement st=conn.prepareStatement(insertLocationsDistanceCommand);
        st.setInt(1,d.getLocation1());
        st.setInt(2,d.getLocation2());
        st.setInt(3,d.getDistance());
        int modified=st.executeUpdate();
        System.out.println("Lines :"+modified);
        st.close();
        conn.close();
    }

    public static void addPersonMeeting(PersonMeeting pm) throws SQLException, ClassNotFoundException
    {
        Connection conn=DbConnection.getConnection();
        PreparedStatement st=conn.prepareStatement(insertPersonMeetingsCommand);
        st.setInt(1,pm.getPersonId());
        st.setInt(2,pm.getMeetingId());
        int modified=st.executeUpdate();
        System.out.println("Lines :"+modified);
        st.close();
        conn.close();
    }

    public static void updatePerson(Person p) throws SQLException, ClassNotFoundException
    {
        Connection conn=DbConnection.getConnection();
        PreparedStatement st=conn.prepareStatement(updatePerson);
        st.setString(1,p.getName());
        st.setString(2,p.getPersonId());
        int modified=st.executeUpdate();
        System.out.println("Lines :"+modified);
        st.close();
        conn.close();
    }

    public static void updateMeeting(Meeting m) throws SQLException, ClassNotFoundException
    {
        Connection conn=DbConnection.getConnection();
        PreparedStatement st=conn.prepareStatement(updateMeeting);
        st.setString(1,m.getTopic());
        st.setString(2,new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(m.getStartTime()).toString());
        st.setInt(3,m.getDuration());
        st.setInt(4,m.getLocationId());
        st.setString(5,m.getMeetingId());
        int modified=st.executeUpdate();
        System.out.println("Lines :"+modified);
        st.close();
        conn.close();
    }

    public static void updateLocationDistance(LocationDistance d) throws SQLException, ClassNotFoundException
    {
        Connection conn=DbConnection.getConnection();
        PreparedStatement st=conn.prepareStatement(updateLocationsDistance);
        st.setInt(1,d.getDistance());
        st.setInt(2,d.getLocation1());
        st.setInt(3,d.getLocation2());
        int modified=st.executeUpdate();
        System.out.println("Lines :"+modified);
        st.close();
        conn.close();
    }

    public static void deletePerson(Person p) throws SQLException, ClassNotFoundException
    {
        Connection conn=DbConnection.getConnection();
        PreparedStatement st=conn.prepareStatement(deletePerson);
        st.setString(1,p.getPersonId());
        int modified=st.executeUpdate();
        System.out.println("Lines :"+modified);
        st.close();
        conn.close();
    }

    public static void deleteLocation(Location l) throws SQLException, ClassNotFoundException
    {
        Connection conn=DbConnection.getConnection();
        PreparedStatement st=conn.prepareStatement(deleteLocation);
        st.setString(1,l.getName());
        int modified=st.executeUpdate();
        System.out.println("Lines :"+modified);
        st.close();
        conn.close();
    }

    public static void deleteMeeting(Meeting m) throws SQLException, ClassNotFoundException
    {
        Connection conn=DbConnection.getConnection();
        PreparedStatement st=conn.prepareStatement(deleteMeeting);
        st.setString(1,m.getMeetingId());
        int modified=st.executeUpdate();
        System.out.println("Lines :"+modified);
        st.close();
        conn.close();
    }

    public static void deleteLocationsDistance(LocationDistance d) throws SQLException, ClassNotFoundException
    {
        Connection conn=DbConnection.getConnection();
        PreparedStatement st=conn.prepareStatement(deleteLocationsDistance);
        st.setInt(1,d.getLocation1());
        st.setInt(2,d.getLocation2());
        int modified=st.executeUpdate();
        System.out.println("Lines :"+modified);
        st.close();
        conn.close();
    }

    public static void deletePersonMeeting(PersonMeeting pm) throws SQLException, ClassNotFoundException
    {
        Connection conn=DbConnection.getConnection();
        PreparedStatement st=conn.prepareStatement(deletePersonMeeting);
        st.setInt(1,pm.getPersonId());
        st.setInt(2,pm.getMeetingId());
        int modified=st.executeUpdate();
        System.out.println("Lines :"+modified);
        st.close();
        conn.close();
    }


public  static int getPersonId() throws SQLException, ClassNotFoundException {
    Connection conn=DbConnection.getConnection();
    Statement st=conn.createStatement();
    ResultSet rs=st.executeQuery(personsCurrentId);
    rs.next();
    int id=rs.getInt(1);
    rs.close();
    st.close();
    conn.close();
    id+=1;
    return id;
}

    public  static int getMeetingId() throws SQLException, ClassNotFoundException {
        Connection conn=DbConnection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery(meetingsCurrentId);
        rs.next();
        int id=rs.getInt(1);
        rs.close();
        st.close();
        conn.close();
        id+=1;
        return id;
    }

    public static int getLocationId() throws SQLException, ClassNotFoundException {
        Connection conn=DbConnection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery(locationsCurrentId);
        rs.next();
        int id=rs.getInt(1);
        rs.close();
        st.close();
        conn.close();
        id+=1;
        return id;
    }

    public static int getPersonsNumber() throws SQLException, ClassNotFoundException {
        Connection conn=DbConnection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery(personsNumber);
        rs.next();
        int id=rs.getInt(1);
        rs.close();
        st.close();
        conn.close();
        return id;
    }

    public static int getMeetingsNumber() throws SQLException, ClassNotFoundException {
        Connection conn=DbConnection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery(meetingsNumber);
        rs.next();
        int id=rs.getInt(1);
        rs.close();
        st.close();
        conn.close();
        return id;
    }

    public static int getLocationsNumber() throws SQLException, ClassNotFoundException {
        Connection conn=DbConnection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery(locationsNumber);
        rs.next();
        int id=rs.getInt(1);
        rs.close();
        st.close();
        conn.close();
        return id;
    }

    public static int getDistanceBetweenLocations(int location1,int location2) throws SQLException, ClassNotFoundException
    {
        Connection conn=DbConnection.getConnection();
        PreparedStatement st=conn.prepareStatement(distanceExists);
        st.setInt(1,location1);
        st.setInt(2,location2);
        ResultSet rs=st.executeQuery();
        rs.next();
        int exists=rs.getInt(1);
        if(exists==0)
        {
            return 0;
        }
        rs.close();
        st.close();
        PreparedStatement st2=conn.prepareStatement(getSingleDistance);
        st2.setInt(1,location1);
        st2.setInt(2,location2);
        ResultSet rs2=st2.executeQuery();
        rs2.next();
        int distance=rs2.getInt(1);
        rs2.close();
        st2.close();
        conn.close();
        return distance;
    }


    public static int[][] getAttendance() throws SQLException, ClassNotFoundException {
        List<PersonMeeting> personMeetings=getPersonsMeetings();
        int persons=getPersonsNumber();
        int meetings=getMeetingsNumber();
        int[][] results=new int[persons][meetings];
        for(PersonMeeting elemen:personMeetings)
        {
            results[elemen.getPersonId()-1][elemen.getMeetingId()-1]=1;
        }
        return results;
    }

    public static int[][] getDistances() throws ParseException, SQLException, ClassNotFoundException {
    List<Meeting> meetings=getMeetings();
    int[][] result=new int[meetings.size()][meetings.size()];
    for(int i=0;i<meetings.size()-1;i++)
    {
        for(int j=i+1;j<meetings.size();j++)
        {
            int location1=meetings.get(i).getLocationId();
            int location2=meetings.get(j).getLocationId();
            int distance=getDistanceBetweenLocations(location1,location2);
            result[i][j]=distance;
        }
    }
    return result;
    }
}
