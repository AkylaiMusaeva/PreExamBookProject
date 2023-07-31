package peaksoft.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import peaksoft.enums.Genre;

import java.math.BigDecimal;
import java.time.LocalDate;
@Entity
@Table(name="books")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Book {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "book_gen"
    )
    @SequenceGenerator(
            name = "book_gen",
            sequenceName = "book_seq",
            allocationSize = 1
    )
    private Long id;
    private String name;
    private String country;
    private LocalDate published_year;
    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    private Genre genre;

    @OneToOne(
            cascade = {
                    CascadeType.PERSIST,//save
                    CascadeType.MERGE,
                    CascadeType.DETACH,
                    CascadeType.REMOVE,
                    CascadeType.REFRESH

            })
    private Reader reader;

    @ManyToOne(
            cascade = {
                    CascadeType.PERSIST,//save
                    CascadeType.MERGE,
                    CascadeType.DETACH,
                   // CascadeType.REMOVE,
                    CascadeType.REFRESH

            })
    private Author author;

    @ManyToOne(
            cascade = {
                    CascadeType.PERSIST,//save
                    CascadeType.MERGE,
                    CascadeType.DETACH,
                    CascadeType.REMOVE,
                    CascadeType.REFRESH

            })
    private Publisher publisher;


    public Book(String name, String country, LocalDate published_year, BigDecimal price, Genre genre) {
        this.name = name;
        this.country = country;
        this.published_year = published_year;
        this.price = price;
        this.genre = genre;
    }

    public Book(String name, String country, LocalDate published_year, BigDecimal price, Genre genre, Reader reader, Author author, Publisher publisher) {
        this.name = name;
        this.country = country;
        this.published_year = published_year;
        this.price = price;
        this.genre = genre;
        this.reader = reader;
        this.author = author;
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", published_year=" + published_year +
                ", price=" + price +
                ", genre=" + genre +
                '}';
    }
}
