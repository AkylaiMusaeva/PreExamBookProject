package peaksoft.repository.repositoryImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.SessionFactory;
import peaksoft.config.DatabaseConnection;
import peaksoft.entity.Author;
import peaksoft.entity.Book;
import peaksoft.entity.Publisher;
import peaksoft.repository.AuthorRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AuthorRepoImpl implements AuthorRepository {
    private final EntityManagerFactory entityManagerFactory = DatabaseConnection.getEntityManagerFactory();
    private final SessionFactory sessionFactory = DatabaseConnection.getSessionFactory();


    @Override
    public void saveAuthor(Author author) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(author);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void updateAuthorById(Long id, Author author) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createQuery("""
                        update Author a set 
                        firstName=:newFirstName,
                        lastName=:newLastName,
                        email=:newEmail,
                        date_of_birth=:newDateOfBirth,
                        country=:newCountry,
                        gender=:newGender
                        where id=:newId
                                       """)
                .setParameter("newFirstName", author.getFirstName())
                .setParameter("newLastName", author.getLastName())
                .setParameter("newEmail", author.getEmail())
                .setParameter("newDateOfBirth", author.getDate_of_birth())
                .setParameter("newCountry", author.getCountry())
                .setParameter("newGender", author.getGender())
                .setParameter("newId", id).executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public Author getAuthorById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Author author = entityManager.createQuery("""
                select a from Author a where a.id=:newId
                """, Author.class).setParameter("newId", id).getSingleResult();
        entityManager.getTransaction().commit();
        entityManager.close();
        return author;
    }

    @Override
    public void assignAuthorToPublisher(Long publisherId, Long authorId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Publisher publisher = entityManager.find(Publisher.class, publisherId);
        Author author = entityManager.find(Author.class, authorId);

        List<Author>authors=publisher.getAuthors();
        authors.add(author);
        List<Publisher>publishers=author.getPublishers();
        publishers.add(publisher);

        author.setPublishers(publishers);
        publisher.setAuthors(authors);

        entityManager.getTransaction().commit();
        entityManager.close();

    }

    @Override//тиешелуу издательствонун авторлорун чыгарып беруу
    public Map<String,List<Author>> getAuthorsByPublisherId(Long publisherId) {
        Map<String,List<Author>> map=new HashMap<>();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Publisher publisher = entityManager.find(Publisher.class, publisherId);
        List<Author>authors=publisher.getAuthors();
        map.put(publisher.getName(),authors);
        entityManager.getTransaction().commit();
        entityManager.close();
        return map;

    }

    @Override
    public void deleteAuthorById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Author author = entityManager.find(Author.class, id);
        List<Book> books = author.getBooks();
        for (int i = 0; i < books.size(); i++) {
            books.get(i).setAuthor(null);
        }
        author.setBooks(null);
        entityManager.remove(author);
        entityManager.getTransaction().commit();
        entityManager.close();

    }
}

