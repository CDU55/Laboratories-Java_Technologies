package DataLayer;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.SQLException;

@ManagedBean(name = "person")
@RequestScoped
public class Person {

    @NotNull
    private int Id;
    @Size(min = 5,max = 5,message = "The person id must be 5 character long")
    @Pattern(message = "Invalid id form",regexp = "U[0-9]{4}")
    @NotNull(message = "The id must pe provided")
    private String personId;
    @NotNull(message = "The name must be provided")
    @Size(min = 1,message = "Name must not be empty")
    private String name;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
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
            this.Id=DataProvider.getPersonId();
            DataProvider.addPerson(this);
            context.addMessage(null, new FacesMessage("Successful",  "The person was successfully added to the database"));
        } catch (SQLException throwables) {
            context.addMessage(null, new FacesMessage("Failure",  throwables.getMessage()));
        } catch (ClassNotFoundException e) {
            context.addMessage(null, new FacesMessage("Failure",  e.getMessage()));
        }
    }

    public void update() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            DataProvider.updatePerson(this);
            context.addMessage(null, new FacesMessage("Successful",  "The person data was successfully updated"));
        } catch (SQLException throwables) {
            context.addMessage(null, new FacesMessage("Failure",  throwables.getMessage()));
        } catch (ClassNotFoundException e) {
            context.addMessage(null, new FacesMessage("Failure",  e.getMessage()));
        }
    }

    public void delete() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            DataProvider.deletePerson(this);
            context.addMessage(null, new FacesMessage("Successful",  "The person was deleted from the database"));
        } catch (SQLException throwables) {
            context.addMessage(null, new FacesMessage("Failure",  throwables.getMessage()));
        } catch (ClassNotFoundException e) {
            context.addMessage(null, new FacesMessage("Failure",  e.getMessage()));
        }
    }
}
