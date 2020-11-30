package DataLayerJPA;

import DataLayer.LocationDistance;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.*;
import java.util.List;

@SessionScoped
@ManagedBean(name = "locDistRepo")
public class LocationsDistanceRepository {

    @PersistenceContext
    EntityManager em;

    public void addLocationsDistance(LocationDistance locDist) {
        LocationsDistanceJPA toAdd=new LocationsDistanceJPA();
        toAdd.setDistance(locDist.getDistance());
        toAdd.setLocationsByLocation1(em.find(LocationsJPA.class,locDist.getLocation1()));
        toAdd.setLocationsByLocation2(em.find(LocationsJPA.class,locDist.getLocation2()));
        em.getTransaction().begin();
        em.persist(toAdd);
        em.getTransaction().commit();
    }

    public void editLocationsDistance(LocationDistance locDist){
        em.getTransaction().begin();
        Query q=em.createQuery("UPDATE LocationsDistanceJPA ld SET distance=:dist WHERE ld.locationsByLocation1.id=:locId1 AND ld.locationsByLocation2.id=:locId2 ");
        q.setParameter("dist",locDist.getDistance());
        q.setParameter("locId1",locDist.getLocation1());
        q.setParameter("locId2",locDist.getLocation2());
        q.executeUpdate();
        em.getTransaction().commit();
    }

    public List<LocationsDistanceJPA> getLocationsDistances()
    {
        Query q= em.createQuery("SELECT ld FROM LocationsDistanceJPA ld");
        return q.getResultList();
    }

    public void deleteLocationsDistance(LocationDistance locDist)
    {
        em.getTransaction().begin();
        Query q=em.createQuery("DELETE FROM LocationsDistanceJPA ld WHERE ld.locationsByLocation1.id=:locId1 AND ld.locationsByLocation2.id=:locId2 ");
        q.setParameter("locId1",locDist.getLocation1());
        q.setParameter("locId2",locDist.getLocation2());
        q.executeUpdate();
        em.getTransaction().commit();
    }
}
