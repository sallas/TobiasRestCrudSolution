package facades;

import com.google.gson.Gson;
import exceptions.NotFoundException;
import java.util.HashMap;
import java.util.Map;
import model.Person;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PersonFacadeTest {

    IPersonFacade facade;
    Gson gson = new Gson();

    public PersonFacadeTest() {
    }

    @Before
    public void x() {
        //true will create a new facade instance for each test case
        facade = PersonFacade.getFacade(true);
    }

    @Test
    public void testAddPerson() throws NotFoundException {
        Person person = facade.addPerson(gson.toJson(new Person("bbb", "bbb", "bbb")));
        String expectedJsonString = gson.toJson(person);
        String actual = facade.getPerson(person.getId());
        assertEquals(expectedJsonString, actual);
    }

    @Test
    public void testGetPerson() throws Exception {
        testAddPerson();
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

    @Test(expected = NotFoundException.class)
    public void testGetNonExistingPerson() throws Exception {
        facade.getPerson(5);
    }

    @Test()
    public void testEditPerson() throws NotFoundException {
        Person person = facade.addPerson(gson.toJson(new Person("aaa", "bbb", "ccc")));
        String original = gson.toJson(person);
        String changed = original.replace("aaa", "abc");
        String oldValue = gson.toJson(facade.editPerson(changed));
        assertEquals(original, oldValue);
        String newValue = facade.getPerson(person.getId());
        assertEquals(changed, newValue);

    }

}
