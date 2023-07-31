package peaksoft.repository.repositoryImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.SessionFactory;
import peaksoft.config.DatabaseConnection;
import peaksoft.entity.Publisher;
import peaksoft.repository.PublisherRepository;
import peaksoft.service.PublisherService;

import java.util.ArrayList;
import java.util.List;

public class PublisherRepoImpl implements PublisherRepository {
    private final EntityManagerFactory entityManagerFactory = DatabaseConnection.getEntityManagerFactory();
    private final SessionFactory sessionFactory = DatabaseConnection.getSessionFactory();

    @Override
    public Publisher savePublisher(Publisher publisher) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Publisher publisher1 = new Publisher();
        publisher1.setName(publisher.getName());
        publisher1.setAddress(publisher.getAddress());
        entityManager.persist(publisher1);
        entityManager.getTransaction().commit();
        entityManager.close();
        return publisher1;
    }

    @Override
    public Publisher getPublisherById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Publisher publisher = entityManager.find(Publisher.class, id);
        entityManager.getTransaction().commit();
        entityManager.close();
        return publisher;
    }

    @Override
    public List<Publisher> getAllPublishers(String ascOrDesc) {//(аты боюнча сорттоп чыгаруу),
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Publisher> publishers = entityManager.createQuery("""
                select p from Publisher p order by name 
                """ + ascOrDesc, Publisher.class).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return publishers;
    }

    @Override
    public void updatePublisher(Long id, Publisher publisher) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Publisher publisher1 = entityManager.find(Publisher.class, id);
        publisher1.setName(publisher.getName());
        publisher1.setAddress(publisher.getAddress());
        entityManager.persist(publisher1);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void deletePublisherByName(String name) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Publisher publisher = entityManager.createQuery("""
                select p from Publisher p where name ilike ?1
                """, Publisher.class).setParameter(1, name + '%').getSingleResult();
        entityManager.remove(publisher);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}

