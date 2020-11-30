package DataLayerJPA;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "Persons")
public class PersonsJPA {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "Id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "PersonId", nullable = false)
    private String personId;
    @Basic
    @Column(name = "Name", nullable = false)
    private String name;
    @OneToMany(mappedBy = "personsByPersonId")
    private Collection<PersonsMeetingsJPA> personsMeetingsById;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonsJPA that = (PersonsJPA) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(personId, that.personId) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, personId, name);
    }

    public Collection<PersonsMeetingsJPA> getPersonsMeetingsById() {
        return personsMeetingsById;
    }

    public void setPersonsMeetingsById(Collection<PersonsMeetingsJPA> personsMeetingsById) {
        this.personsMeetingsById = personsMeetingsById;
    }
}
