package DataLayer;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.SQLException;

@ManagedBean(name = "location")
@SessionScoped
public class Location {
    @NotNull
    private int Id;
    @NotNull(message = "The location name must be provided")
    @Size(min = 1,message = "Location Name must not be empty")
    private String name;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void add() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            this.Id=DataProvider.getLocationId();
            DataProvider.addLocation(this);
            context.addMessage(null, new FacesMessage("Successful",  "The location was successfully added to the database"));
        } catch (SQLException throwables) {
            context.addMessage(null, new FacesMessage("Failure",  throwables.getMessage()));
        } catch (ClassNotFoundException e) {
            context.addMessage(null, new FacesMessage("Failure",  e.getMessage()));
        }
    }

    public void delete() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            DataProvider.deleteLocation(this);
            context.addMessage(null, new FacesMessage("Successful",  "The location was deleted from the database"));
        } catch (SQLException throwables) {
            context.addMessage(null, new FacesMessage("Failure",  throwables.getMessage()));
        } catch (ClassNotFoundException e) {
            context.addMessage(null, new FacesMessage("Failure",  e.getMessage()));
        }
    }
}
