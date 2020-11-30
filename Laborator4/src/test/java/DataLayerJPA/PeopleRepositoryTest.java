package DataLayerJPA;

import DataLayer.Person;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PeopleRepositoryTest {

    @Test
    void addPeople() {
        Person person =new Person();
        person.setName("John");
        person.setPersonId("U1111");
        PeopleRepository repo=new PeopleRepository();
        int initialSize=repo.getPeople().size();
        repo.addPeople(person);
        int finalSize=repo.getPeople().size();
        assertEquals(initialSize,finalSize-1);

    }

    @Test
    void getPeople() {
        PeopleRepository repo=new PeopleRepository();
        assertEquals(repo.getPeople().size(),2);
    }

    @Test
    void updatePeople() {

        Person person =new Person();
        person.setName("Jimmy");
        person.setPersonId("U1111");
        PeopleRepository repo=new PeopleRepository();
        int initialSize=repo.getPeople().size();
        repo.updatePeople(person);
        int finalSize=repo.getPeople().size();
        assertEquals(initialSize,finalSize);
        boolean found=false;
        for(PersonsJPA pers : repo.getPeople())
        {
            if(pers.getName().equals("Jimmy") && pers.getName().equals("U1111"))
            {
                found=true;
                break;
            }
        }
        assertTrue(found);
    }

    @Test
    void deletePeople() {
        Person person =new Person();
        person.setName("John");
        person.setPersonId("U1111");
        PeopleRepository repo=new PeopleRepository();
        int initialSize=repo.getPeople().size();
        repo.deletePeople(person);
        int finalSize=repo.getPeople().size();
        assertEquals(initialSize,finalSize+1);
    }
}