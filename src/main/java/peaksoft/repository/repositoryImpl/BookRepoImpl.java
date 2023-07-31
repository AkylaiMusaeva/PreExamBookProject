package peaksoft.repository.repositoryImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.convert.spi.ConverterAutoApplyHandler;
import peaksoft.config.DatabaseConnection;
import peaksoft.entity.Author;
import peaksoft.entity.Book;
import peaksoft.entity.Publisher;
import peaksoft.repository.BookRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookRepoImpl implements BookRepository {
    private final EntityManagerFactory entityManagerFactory = DatabaseConnection.getEntityManagerFactory();
    private final SessionFactory sessionFactory = DatabaseConnection.getSessionFactory();

    @Override
    public void saveBookWithAuthor(Book book, Long authorId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Author author = entityManager.find(Author.class, authorId);
        book.setAuthor(author);
        entityManager.persist(book);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void deleteBookByAuthorId(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Author author = entityManager.find(Author.class, id);
        List<Book> books = author.getBooks();
        for (int i = 0; i < books.size(); i++) {
            entityManager.remove(books.get(i));
        }
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void assignPublisherToBook(Long bookId, Long publisherId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Book book = entityManager.find(Book.class, bookId);
        Publisher publisher = entityManager.find(Publisher.class, publisherId);
        book.setPublisher(publisher);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public Map<Book, Publisher> getBookAndPublisherByBookId(Long bookId) {
        Map<Book, Publisher> map = new HashMap<>();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Book book = entityManager.find(Book.class, bookId);
        Publisher publisher = book.getPublisher();
        map.put(book,publisher);
        entityManager.getTransaction().commit();
        entityManager.close();
        return map;
    }


    @Override
    public void updateBookAuthor(Long id, Book book) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
//        entityManager.createQuery("""
//                update Book b set
//                name=:newName,
//                country=:newCountry,
//                published_year=:newPublishedYear,
//                price=:newPrice,
//                genre=:newGenre,
//                author=:newAuthor
//                where b.id=:newId
//                """)
//                .setParameter("newName",book.getName())
//                .setParameter("newCountry",book.getCountry())
//                .setParameter("newPublishedYear",book.getPublished_year())
//                .setParameter("newPrice",book.getPrice())
//                .setParameter("newGenre",book.getGenre())
//                .setParameter("newAuthor",book.getAuthor())
//                .setParameter("newId",id).executeUpdate();

        Book book1 = entityManager.find(Book.class, id);
        book1.setName(book.getName());
        book1.setCountry(book.getCountry());
        book1.setPublished_year(book.getPublished_year());
        book1.setPrice(book.getPrice());
        book1.setGenre(book.getGenre());
        book1.setAuthor(book.getAuthor());
        entityManager.merge(book1);

        entityManager.getTransaction().commit();
        entityManager.close();
    }


}
