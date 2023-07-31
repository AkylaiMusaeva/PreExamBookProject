package peaksoft.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name="publishers")
@NoArgsConstructor
@Getter
@Setter
@ToString

public class Publisher {
    @Id//primary key
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "publisher_gen"
                    )
    @SequenceGenerator(
            name="publisher_gen",
            sequenceName = "publisher_seq",
            allocationSize = 1

    )
    private Long id;
    private String name;
    private String address;

    @OneToMany(mappedBy = "publisher",
            cascade = {
                    CascadeType.PERSIST,//save
                    CascadeType.MERGE,
                    CascadeType.DETACH,
                    //CascadeType.REMOVE,
                    CascadeType.REFRESH

            })
    private List<Book>books;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.PERSIST,//save
                    CascadeType.MERGE,
                    CascadeType.DETACH,
                   // CascadeType.REMOVE,
                    CascadeType.REFRESH

            })
    private List<Author>authors;

    public Publisher(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public Publisher(String name, String address, List<Book> books, List<Author> authors) {
        this.name = name;
        this.address = address;
        this.books = books;
        this.authors = authors;
    }

    @Override
    public String toString() {
        return "Publisher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
