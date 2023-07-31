package peaksoft.service;

import peaksoft.entity.Book;
import peaksoft.entity.Publisher;

import java.util.Map;

public interface BookService {
   String updateBookAuthor(Long bookId, Book book);

   String saveBookWithAuthor(Book book,Long authorId);

    String deleteBookByAuthorId(Long id);


    String assignPublisherToBook(Long bookId, Long publisherId);

    Map<Book, Publisher> getBookAndPublisherByBookId(Long bookId);
}

