package peaksoft.repository.repositoryImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.SessionFactory;
import peaksoft.config.DatabaseConnection;
import peaksoft.entity.Author;
import peaksoft.entity.Book;
import peaksoft.entity.Reader;
import peaksoft.repository.ReaderRepository;

import java.util.ArrayList;
import java.util.List;

public class ReaderRepoImpl implements ReaderRepository {
    private final EntityManagerFactory entityManagerFactory = DatabaseConnection.getEntityManagerFactory();
    private final SessionFactory sessionFactory = DatabaseConnection.getSessionFactory();

    @Override
    public void saveReader(Reader reader) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(reader);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void updateReaderById(Long id, Reader reader) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Reader reader1 = entityManager.find(Reader.class, id);
        reader1.setName(reader.getName());
        reader1.setAge(reader.getAge());
        reader1.setEmail(reader.getEmail());
        entityManager.merge(reader1);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public Reader getReaderByBookId(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Book book = entityManager.find(Book.class, id);
        Reader reader = book.getReader();
        entityManager.getTransaction().commit();
        entityManager.close();
        return reader;
    }

    @Override
    public void deleteReaderById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Reader reader = entityManager.find(Reader.class, id);
        entityManager.remove(reader);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void assignReaderToBook(Long readerId, Long bookId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Reader reader = entityManager.find(Reader.class, readerId);
        Book book = entityManager.find(Book.class, bookId);
        book.setReader(reader);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public List<Reader> getReadersByAuthorId(Long authorId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Author author = entityManager.find(Author.class, authorId);
        List<Reader> resultList = entityManager.createQuery("""
                select r from Book b join Reader r on r.id=b.reader.id where b.author.id=?1
                """, Reader.class).setParameter(1, authorId).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return resultList;
    }
}


