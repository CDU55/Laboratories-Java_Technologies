package DataLayerJPA;

import DataLayer.DataProvider;
import DataLayer.Location;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.*;
import java.sql.SQLException;
import java.util.List;

@SessionScoped
@ManagedBean(name = "locRepo")
public class LocationsRepository {
    @PersistenceContext
    EntityManager em;

    public void addLocation(Location location) throws SQLException, ClassNotFoundException {
        LocationsJPA loc=new LocationsJPA();
        loc.setId(DataProvider.getLocationId());
        loc.setName(location.getName());
        em.getTransaction().begin();
        em.persist(loc);
        em.getTransaction().commit();
    }

    public List<LocationsJPA> getLocations()
    {
        Query q= em.createQuery("SELECT l FROM LocationsJPA l");
        return q.getResultList();
    }

    public void deleteLocation(Location location)
    {
        em.getTransaction().begin();
        Query q=em.createQuery("DELETE FROM LocationsJPA l WHERE l.name=:loc_name");
        q.setParameter("loc_name",location.getName());
        q.executeUpdate();
        em.getTransaction().commit();
    }
}
