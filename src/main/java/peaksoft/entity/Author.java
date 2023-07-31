package peaksoft.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import peaksoft.enums.Gender;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="authors")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Author {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "author_gen"
    )
    @SequenceGenerator(
            sequenceName = "author_seq",
            name = "author_gen",
            allocationSize = 1
    )
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(unique = true)
    private String email;
    private LocalDate date_of_birth;
    private String country;
    @Enumerated(EnumType.STRING)
    private Gender gender;


    @OneToMany(mappedBy = "author",
            cascade = {
                    CascadeType.PERSIST,//save
                    CascadeType.MERGE,
                    CascadeType.DETACH,
                    CascadeType.REMOVE,
                    CascadeType.REFRESH

            })
    private List<Book> books;

    @ManyToMany(mappedBy = "authors",
            cascade = {
                    CascadeType.PERSIST,//save
                    CascadeType.MERGE,
                    CascadeType.DETACH,
                   // CascadeType.REMOVE,
                    CascadeType.REFRESH

            })
    private List<Publisher> publishers;


    public Author(String firstName, String lastName, String email, LocalDate date_of_birth, String country, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.date_of_birth = date_of_birth;
        this.country = country;
        this.gender = gender;


    }
    public Author(String firstName, String lastName, String email, LocalDate date_of_birth, String country, Gender gender, List<Book> books, List<Publisher> publishers) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.date_of_birth = date_of_birth;
        this.country = country;
        this.gender = gender;
        this.books = books;
        this.publishers = publishers;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", date_of_birth=" + date_of_birth +
                ", country='" + country + '\'' +
                ", gender=" + gender +
                '}';
    }
}
