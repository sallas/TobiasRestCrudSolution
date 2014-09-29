package facades;

import com.google.gson.Gson;
import entity.PersonEntity;
import exceptions.NotFoundException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import model.Person;

public class PersonFacadeDB implements IPersonFacade {

    EntityManager em;
    Gson gson = new Gson();

    public PersonFacadeDB() {
        em = createEntityManager();
    }

    private static EntityManager createEntityManager() {
        EntityManagerFactory emf
                = Persistence.createEntityManagerFactory("RestCRUDPU");
        return emf.createEntityManager();
    }

    @Override
    public Person addPerson(String json) {
        PersonEntity p = gson.fromJson(json, PersonEntity.class);
       
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        try {
            em.persist(p);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
        return new Person(p);
    }

    @Override
    public Person deletePerson(int id) throws NotFoundException {
        PersonEntity p = em.find(PersonEntity.class, id);
        if (p == null) {
            throw new NotFoundException("No person with that id");
        }
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        try {
            em.remove(p);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
        return new Person(p);
    }

    @Override
    public String getPerson(int id) throws NotFoundException {
        PersonEntity p = em.find(PersonEntity.class, id);
        if (p == null) {
            throw new NotFoundException("No person with that id");
        }
        return gson.toJson(p);
    }

    @Override
    public String getPersons() {
        return gson.toJson(em.createNamedQuery("PersonEntity.findAll", PersonEntity.class).getResultList());
    }

    @Override
    public Person editPerson(String json) throws NotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
