package DataLayer;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.Min;
import java.sql.SQLException;

@ManagedBean(name = "locationsDistance")
@RequestScoped
public class LocationDistance {

    @Min(value = 1,message = "The first location id must be a positive number")
    private int location1;
    @Min(value = 1,message = "The second location id must be a positive number")
    private int location2;
    @Min(value = 1,message = "The distance must be a positive number")
    private int distance;

    public int getLocation1() {
        return location1;
    }

    public void setLocation1(int location1) {
        this.location1 = location1;
    }

    public int getLocation2() {
        return location2;
    }

    public void setLocation2(int location2) {
        this.location2 = location2;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public void add() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            DataProvider.addLocationDistance(this);
            context.addMessage(null, new FacesMessage("Successful",  "The distance between the locations has been set"));
        } catch (SQLException throwables) {
            context.addMessage(null, new FacesMessage("Failure",  throwables.getMessage()));
        } catch (ClassNotFoundException e) {
            context.addMessage(null, new FacesMessage("Failure",  e.getMessage()));
        }
    }

    public void update() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            DataProvider.updateLocationDistance(this);
            context.addMessage(null, new FacesMessage("Successful",  "The distance between the two locations was updated"));
        } catch (SQLException throwables) {
            context.addMessage(null, new FacesMessage("Failure",  throwables.getMessage()));
        } catch (ClassNotFoundException e) {
            context.addMessage(null, new FacesMessage("Failure",  e.getMessage()));
        }
    }

    public void delete() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            DataProvider.updateLocationDistance(this);
            context.addMessage(null, new FacesMessage("Successful",  "The distance between the two locations was deleted from the database"));
        } catch (SQLException throwables) {
            context.addMessage(null, new FacesMessage("Failure",  throwables.getMessage()));
        } catch (ClassNotFoundException e) {
            context.addMessage(null, new FacesMessage("Failure",  e.getMessage()));
        }
    }
}
