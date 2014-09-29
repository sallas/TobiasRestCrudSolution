package restcrud;

import entity.PersonEntity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Program {

    private static EntityManager createEntityManager() {
        EntityManagerFactory emf
                = Persistence.createEntityManagerFactory("RestCRUDPU");
        return emf.createEntityManager();
    }

    private static void testJpaTables() {
        PersonEntity p = new PersonEntity("aaa", "bbb", "ccc");
        EntityManager em = createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        try {
            em.persist(p);
            //System.out.println("Cats in show "+);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            em.close();
        }
    }

    public static void main(String[] args) {
        testJpaTables();
    }

}
