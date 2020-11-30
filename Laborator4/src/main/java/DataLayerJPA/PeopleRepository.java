package DataLayerJPA;

import DataLayer.Person;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.*;
import java.util.List;

@SessionScoped
@ManagedBean(name = "peopleRepo")
public class PeopleRepository {
    @PersistenceContext
    EntityManager em;

    public void addPeople(Person person){
        PersonsJPA prs=new PersonsJPA();
        prs.setName(person.getName());
        prs.setPersonId(person.getPersonId());
        em.getTransaction().begin();
        em.persist(prs);
        em.getTransaction().commit();
    }

    public List<PersonsJPA> getPeople()
    {
        Query q= em.createQuery("SELECT p FROM PersonsJPA p");
        return q.getResultList();
    }

    public void updatePeople(Person person)
    {
        em.getTransaction().begin();
        Query q=em.createQuery("UPDATE PersonsJPA p SET name=:persName WHERE p.personId=:persId ");
        q.setParameter("persId",person.getPersonId());
        q.setParameter("persName",person.getName());
        q.executeUpdate();
        em.getTransaction().commit();
    }

    public void deletePeople(Person person)
    {
        em.getTransaction().begin();
        Query q=em.createQuery("DELETE FROM PersonsJPA p WHERE p.personId=:persId ");
        q.setParameter("persId",person.getPersonId());
        q.executeUpdate();
        em.getTransaction().commit();
    }
}
