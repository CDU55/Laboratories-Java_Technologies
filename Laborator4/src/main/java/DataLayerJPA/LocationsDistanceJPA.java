package DataLayerJPA;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "LocationsDistance")
public class LocationsDistanceJPA {
    @Basic
    @Column(name = "Location1", nullable = false)
    private Integer location1;

    @Basic
    @Column(name = "Location2", nullable = false)
    private Integer location2;

    @Basic
    @Column(name = "Distance", nullable = false)
    private Integer distance;
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "Id", nullable = false)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "Location1", referencedColumnName = "Id")
    private LocationsJPA locationsByLocation1;

    @ManyToOne
    @JoinColumn(name = "Location2", referencedColumnName = "Id")
    private LocationsJPA locationsByLocation2;

    public Integer getLocation1() {
        return location1;
    }

    public void setLocation1(Integer location1) {
        this.location1 = location1;
    }

    public Integer getLocation2() {
        return location2;
    }

    public void setLocation2(Integer location2) {
        this.location2 = location2;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
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
        LocationsDistanceJPA that = (LocationsDistanceJPA) o;
        return Objects.equals(location1, that.location1) &&
                Objects.equals(location2, that.location2) &&
                Objects.equals(distance, that.distance) &&
                Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location1, location2, distance, id);
    }


    public LocationsJPA getLocationsByLocation1() {
        return locationsByLocation1;
    }

    public void setLocationsByLocation1(LocationsJPA locationsByLocation1) {
        this.locationsByLocation1 = locationsByLocation1;
    }

    public LocationsJPA getLocationsByLocation2() {
        return locationsByLocation2;
    }

    public void setLocationsByLocation2(LocationsJPA locationsByLocation2) {
        this.locationsByLocation2 = locationsByLocation2;
    }
}
