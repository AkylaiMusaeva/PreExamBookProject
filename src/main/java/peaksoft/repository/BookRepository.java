package peaksoft.repository;

import peaksoft.entity.Book;
import peaksoft.entity.Publisher;

import java.util.Map;

public interface BookRepository {
    void updateBookAuthor(Long id, Book book);

    void saveBookWithAuthor(Book book,Long authorId);

    void deleteBookByAuthorId(Long id);

    void assignPublisherToBook(Long bookId, Long publisherId);

    Map<Book, Publisher> getBookAndPublisherByBookId(Long bookId);
}
