package peaksoft;

import peaksoft.config.DatabaseConnection;
import peaksoft.entity.Author;
import peaksoft.entity.Book;
import peaksoft.entity.Publisher;
import peaksoft.entity.Reader;
import peaksoft.enums.Gender;
import peaksoft.enums.Genre;
import peaksoft.service.AuthorService;
import peaksoft.service.BookService;
import peaksoft.service.PublisherService;
import peaksoft.service.ReaderService;
import peaksoft.service.serviceImpl.AuthorServiceImpl;
import peaksoft.service.serviceImpl.BookServiceImpl;
import peaksoft.service.serviceImpl.PublisherServiceImpl;
import peaksoft.service.serviceImpl.ReaderServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
       // DatabaseConnection.getEntityManagerFactory();
        Scanner scannerWord=new Scanner(System.in);
        Scanner scannerNum=new Scanner(System.in);
        AuthorService authorService=new AuthorServiceImpl();
        BookService bookService=new BookServiceImpl();
        PublisherService publisherService=new PublisherServiceImpl();
        ReaderService readerService=new ReaderServiceImpl();

        while(true){
            System.out.println("""
                    ---------------Choose operation-------------
                    Publisher                       Author                            Book                              Reader
                    1-save publisher             6-save author                    13-save book with author              20-save reader
                    2-get publisher by id        7-update author by id            14-updateBookAuthor                   21-update reader
                    3-getAllPublishersSort       8-getAuthorById                  15-assign publisher to book           22-deleteReaderById 
                    4-update publisher           9-assign author to publisher     16-getBookAndPublisherByBookId        23-assign reader to book
                    5-deletePublisherByName      10-get authors by publisher id   17-deleteBookByAuthorId               24-getReaderByBookId
                                                 -11-delete author by id                                                25-getReadersByAuthorId
                    
                                                        
                    """);
            switch (scannerNum.nextInt()){
                case 1-> {//done
                    System.out.println("Input publisher's name: ");
                    String name=scannerWord.nextLine();
                    System.out.println("Input publisher's address: ");
                    String address=scannerWord.nextLine();
                    System.out.println(publisherService.savePublisher(new Publisher(name,address)));
                }
                case 2->{//done
                    System.out.println("Input publisher id you want to find: ");
                    Long id=scannerNum.nextLong();
                    System.out.println(publisherService.getPublisherById(id));
                }
                case 3->{//done
                    System.out.println("Input asc or desc to sort in the needed order: ");
                    String ascOrDesc=scannerWord.nextLine();
                    publisherService.getAllPublishers(ascOrDesc).forEach(System.out::println);
                }
                case 4->{//done
                    System.out.println("Input publisher id you want to update: ");
                    Long id=scannerNum.nextLong();
                    System.out.println("Input new publisher's name: ");
                    String name=scannerWord.nextLine();
                    System.out.println("Input new publisher's address: ");
                    String address=scannerWord.nextLine();
                    System.out.println(publisherService.updatePublisher(id,new Publisher(name,address)));

                }
                case 5->{//done
                    System.out.println("Input publisher name to delete :");
                    String name=scannerWord.nextLine();
                    System.out.println(publisherService.deletePublisherByName(name));
                }
                case 6->{//done
                    System.out.println("Input author's first name: ");
                    String firstName=scannerWord.nextLine();
                    System.out.println("Input author's last name: ");
                    String lastName=scannerWord.nextLine();
                    System.out.println("Input author's email: ");
                    String email=scannerWord.nextLine();
                    System.out.println("Input year of birth");
                    int year=scannerNum.nextInt();
                    System.out.println("Input month of birth");
                    int month=scannerNum.nextInt();
                    System.out.println("Input day of birth");
                    int day=scannerNum.nextInt();
                    LocalDate dateOfBirth=LocalDate.of(year,month,day);
                    System.out.println("Input country: ");
                    String country=scannerWord.nextLine();
                    System.out.println("Input gender");
                    String gender=scannerWord.nextLine();
                    System.out.println(authorService.saveAuthor(
                            new Author(firstName,lastName,email,dateOfBirth,country, Gender.valueOf(gender.toUpperCase()))
                    ));
                }
                case 7->{//done
                    System.out.println("Input author id you want to update: ");
                    Long id=scannerNum.nextLong();
                    System.out.println("Input new author's first name: ");
                    String firstName=scannerWord.nextLine();
                    System.out.println("Input new author's last name: ");
                    String lastName=scannerWord.nextLine();
                    System.out.println("Input new author's email: ");
                    String email=scannerWord.nextLine();
                    System.out.println("Input new author's year of birth");
                    int year=scannerNum.nextInt();
                    System.out.println("Input new author's month of birth");
                    int month=scannerNum.nextInt();
                    System.out.println("Input new author's day of birth");
                    int day=scannerNum.nextInt();
                    LocalDate dateOfBirth=LocalDate.of(year,month,day);
                    System.out.println("Input new author's country: ");
                    String country=scannerWord.nextLine();
                    System.out.println("Input new author's gender");
                    String gender=scannerWord.nextLine();
                    System.out.println(
                            authorService.updateAuthorById(id,new Author(firstName,lastName,email,dateOfBirth,country, Gender.valueOf(gender.toUpperCase()))));
                }
                case 8->{//done
                    System.out.println("Input author id you want to find: ");
                    Long id=scannerNum.nextLong();
                    System.out.println(authorService.getAuthorById(id));
                }
                case 9->{//done
                    System.out.println("Input publisher id to assign authors to: ");
                    Long publisherId=scannerNum.nextLong();
                    System.out.println("Input author id: ");
                    Long authorId=scannerNum.nextLong();
                    System.out.println(authorService.assignAuthorToPublisher(publisherId,authorId));
                }
                case 10->{//done
                    System.out.println("Input publisher id to get authors: ");
                    Long publisherId=scannerNum.nextLong();
                    System.out.println(authorService.getAuthorsByPublisherId(publisherId));
                }
                case 11->{//error with relation existing
                    System.out.println("Input author id you want to delete: ");
                    Long id=scannerNum.nextLong();
                    System.out.println(authorService.deleteAuthorById(id));


                }
                case 13->{//done
                    System.out.println("-----Let's save book first-----");
                    System.out.println("Input book's name: ");
                    String name=scannerWord.nextLine();
                    System.out.println("Input book's country: ");
                    String country=scannerWord.nextLine();
                    System.out.println("Input book's published year: ");
                    int year=scannerNum.nextInt();
                    System.out.println("Input book's published month: ");
                    int month=scannerNum.nextInt();
                    System.out.println("Input book's published day: ");
                    int day=scannerNum.nextInt();
                    System.out.println("Input book's price: ");
                    int price=scannerNum.nextInt();
                    System.out.println("Input book's genre: ");
                    String genre=scannerWord.nextLine();

                    Book book=new Book(name,country,LocalDate.of(year,month,day),BigDecimal.valueOf(price),Genre.valueOf(genre.toUpperCase()));

                    System.out.println(bookService.saveBookWithAuthor(book,4L));


                }
                case 14->{
                    System.out.println("Input book id you want to update :");
                    Long id=scannerNum.nextLong();
                    System.out.println("-----Let's update book first-----");
                    System.out.println("Input new book's name: ");
                    String name=scannerWord.nextLine();
                    System.out.println("Input new book's country: ");
                    String country=scannerWord.nextLine();
                    System.out.println("Input new book's published year: ");
                    int year=scannerNum.nextInt();
                    System.out.println("Input new book's published month: ");
                    int month=scannerNum.nextInt();
                    System.out.println("Input new book's published day: ");
                    int day=scannerNum.nextInt();
                    System.out.println("Input new  book's price: ");
                    int price=scannerNum.nextInt();
                    System.out.println("Input new  book's genre: ");
                    String genre=scannerWord.nextLine();

                    Book book=new Book(name,country,LocalDate.of(year,month,day),BigDecimal.valueOf(price),Genre.valueOf(genre.toUpperCase()));

                    System.out.println("\n-------Let's update author now-----");
                    System.out.println("Input new author's first name: ");
                    String firstName=scannerWord.nextLine();
                    System.out.println("Input new author's last name: ");
                    String lastName=scannerWord.nextLine();
                    System.out.println("Input new author's email: ");
                    String email=scannerWord.nextLine();
                    System.out.println("Input new author's year of birth");
                    int birthYear=scannerNum.nextInt();
                    System.out.println("Input new author's month of birth");
                    int birthMonth=scannerNum.nextInt();
                    System.out.println("Input new author's day of birth");
                    int birthDay=scannerNum.nextInt();
                    LocalDate dateOfBirth=LocalDate.of(birthYear,birthMonth,birthDay);
                    System.out.println("Input new author's country: ");
                    String birthCountry=scannerWord.nextLine();
                    System.out.println("Input new author's gender");
                    String gender=scannerWord.nextLine();

                    Author author=new Author(firstName,lastName,email,dateOfBirth,birthCountry, Gender.valueOf(gender.toUpperCase()));

                    book.setAuthor(author);

                    System.out.println(bookService.updateBookAuthor(id,book));
                }
                case 15->{//done
                    System.out.println("Input book id you want to assign publisher to: ");
                    Long bookId=scannerNum.nextLong();
                    System.out.println("Input publisher id,which you want to assign to book: ");
                    Long publisherId=scannerNum.nextLong();
                    System.out.println(bookService.assignPublisherToBook(bookId, publisherId));
                }
                case 16->{//done
                    System.out.println("Input book id: ");
                    Long bookId=scannerNum.nextLong();
                    System.out.println(bookService.getBookAndPublisherByBookId(bookId));

                }
                case 17->{//done
                    System.out.println("Input author id to delete: ");
                    Long id=scannerNum.nextLong();
                    System.out.println(bookService.deleteBookByAuthorId(id));

                }
                case 20->{//done
                    System.out.println("Input reader's name: ");
                    String name=scannerWord.nextLine();
                    System.out.println("Input reader's age: ");
                    int age=scannerNum.nextInt();
                    System.out.println("Input reader's email: ");
                    String email=scannerWord.nextLine();
                    System.out.println(readerService.saveReader(new Reader(name,age,email)));
                }
                case 21->{//done
                    System.out.println("Input reader id you want to update: ");
                    Long id=scannerNum.nextLong();
                    System.out.println("Input new reader's name: ");
                    String name=scannerWord.nextLine();
                    System.out.println("Input new reader's age :");
                    int age=scannerNum.nextInt();
                    System.out.println("Input new reader's email: ");
                    String email=scannerWord.nextLine();
                    System.out.println(readerService.updateReaderById(id,new Reader(name,age,email)));
                }
                case 22->{//done
                    System.out.println("Input reader id you want to delete: ");
                    Long id=scannerNum.nextLong();
                    System.out.println(readerService.deleteReaderById(id));
                }
                case 23->{//done
                    System.out.println("Input reader id: ");
                    Long readerId=scannerNum.nextLong();
                    System.out.println("Input book id: ");
                    Long bookId= scannerNum.nextLong();
                    System.out.println(readerService.assignReaderToBook(readerId,bookId));
                }
                case 24->{//done
                    System.out.println("Input book id you want to get reader by : ");
                    Long bookId=scannerNum.nextLong();
                    System.out.println(readerService.getReaderByBookId(bookId));
                }
                case 25->{//done
                    System.out.println("Input author id to get reader by: ");
                    Long authorId=scannerNum.nextLong();
                   readerService.getReadersByAuthorId(authorId).forEach(System.out::println);
                }



            }
        }
    }
}
