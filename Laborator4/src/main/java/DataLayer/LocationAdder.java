package DataLayer;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "locationAdder")
@SessionScoped
public class LocationAdder {

    public Location location;

    public Location getLocation() {
        return location;
    }

    @PostConstruct
    public void init()
    {
        this.location=new Location();
    }
    public void setLocation(Location location) {
        this.location = location;
    }

    public void add()
    {
        location.add();
    }
}
