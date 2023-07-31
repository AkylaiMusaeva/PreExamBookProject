package peaksoft.service.serviceImpl;

import peaksoft.entity.Book;
import peaksoft.entity.Publisher;
import peaksoft.repository.BookRepository;
import peaksoft.repository.repositoryImpl.BookRepoImpl;
import peaksoft.service.BookService;

import java.util.Map;

public class BookServiceImpl implements BookService {
    BookRepository bookRepository=new BookRepoImpl();
    @Override
    public String updateBookAuthor(Long id, Book book) {
        bookRepository.updateBookAuthor(id,book);
        return "Successfully updated book with id = "+id;
    }

    @Override
    public String saveBookWithAuthor(Book book,Long authorId) {
        bookRepository.saveBookWithAuthor(book,authorId);
        return book.getName()+" is successfully saved! ";
    }

    @Override
    public String deleteBookByAuthorId(Long id) {
        bookRepository.deleteBookByAuthorId(id);
        return "Successfully deleted book where author id = "+id;
    }

    @Override
    public String assignPublisherToBook(Long bookId, Long publisherId) {
         bookRepository.assignPublisherToBook(bookId,publisherId);
         return "Successfully assigned publisher with id="+publisherId+" to book with id="+bookId;
    }

    @Override
    public Map<Book, Publisher> getBookAndPublisherByBookId(Long bookId) {
        return bookRepository.getBookAndPublisherByBookId(bookId);
    }


}
