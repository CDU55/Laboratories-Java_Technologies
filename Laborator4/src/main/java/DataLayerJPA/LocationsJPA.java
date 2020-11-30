package DataLayerJPA;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "Locations")
public class LocationsJPA {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "Id", nullable = false)
    private Integer id;
    @Column(name = "Name", nullable = false)
    private String name;
    @OneToMany(mappedBy = "locationsByLocation1")
    private Collection<LocationsDistanceJPA> locationsDistancesById;
    @OneToMany(mappedBy = "locationsByLocation2")
    private Collection<LocationsDistanceJPA> locationsDistancesById_0;
    @OneToMany(mappedBy = "locationsByLocationId")
    private Collection<MeetingsJPA> meetingsById;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocationsJPA that = (LocationsJPA) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public Collection<LocationsDistanceJPA> getLocationsDistancesById() {
        return locationsDistancesById;
    }

    public void setLocationsDistancesById(Collection<LocationsDistanceJPA> locationsDistancesById) {
        this.locationsDistancesById = locationsDistancesById;
    }

    public Collection<LocationsDistanceJPA> getLocationsDistancesById_0() {
        return locationsDistancesById_0;
    }

    public void setLocationsDistancesById_0(Collection<LocationsDistanceJPA> locationsDistancesById_0) {
        this.locationsDistancesById_0 = locationsDistancesById_0;
    }

    public Collection<MeetingsJPA> getMeetingsById() {
        return meetingsById;
    }

    public void setMeetingsById(Collection<MeetingsJPA> meetingsById) {
        this.meetingsById = meetingsById;
    }
}
