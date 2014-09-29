package facades;

import com.google.gson.Gson;
import exceptions.NotFoundException;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import model.Person;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PersonFacadeDBTest {

    PersonFacadeDB facade;
    Gson gson = new Gson();

    public PersonFacadeDBTest() {
    }

    @Before
    public void setUp() {
        EntityManagerFactory emf
                = Persistence.createEntityManagerFactory("RestCRUDPU");
        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        try {
            em.createNativeQuery("DELETE FROM PERSONENITITY");
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
        facade = new PersonFacadeDB();
    }

    @Test
    public void testAddPerson() throws NotFoundException {
        Person person = facade.addPerson(gson.toJson(new Person("bbb", "bbb", "bbb")));
        String expectedJsonString = gson.toJson(person);
        String actual = facade.getPerson(person.getId());
        assertEquals(expectedJsonString, actual);
    }

    @Test
    public void testGetPersons() {
        Person p = new Person("aaa", "aaa", "aaa");
        Person person1 = facade.addPerson(gson.toJson(p));
        Person p2 = new Person("bbb", "bbb", "bbb");
        Person person2 = facade.addPerson(gson.toJson(p2));

        //Make the Expected String
        Map<Integer, Person> test = new HashMap();
        test.put(person1.getId(), person1);
        test.put(person2.getId(), person2);
        String expected = gson.toJson(test.values());
        String result = facade.getPersons();
        assertEquals(expected, result);
    }

    @Test(expected = NotFoundException.class)
    public void testDeletePerson() throws NotFoundException {
        Person person = facade.addPerson(gson.toJson(new Person("bbb", "bbb", "bbb")));
        facade.deletePerson(person.getId());
        facade.getPerson(person.getId());

    }

    @Test
    public void testSOMETHING() throws NotFoundException {
        Person p = new Person("aaa", "bbbb", "cccc");
        Person pe = facade.addPerson(gson.toJson(p));
        facade.addPerson(gson.toJson(new Person("aaa", "bbbb", "cccc")));
        Person p2 = gson.fromJson(facade.getPerson(1), Person.class);
        System.out.println(p2);
        assertEquals(1, p2.getId());
    }

    @Test
    public void testSOMETHING2() {
        Person p = new Person("aaa", "bbbb", "cccc");
        Person pe = facade.addPerson(gson.toJson(p));
        facade.getPersons();
        assertEquals(0, p.getId());
    }

    @After
    public void tearDown() {
    }

}
