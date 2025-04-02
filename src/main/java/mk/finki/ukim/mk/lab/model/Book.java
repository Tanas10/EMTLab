package mk.finki.ukim.mk.lab.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;
import lombok.Data;
import mk.finki.ukim.mk.lab.model.enumerations.Category;


@Data
@Entity

public class Book {
   // id (Long), name (String), category (enum), author (Author), availableCopies (Integer)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;
    Category category;
    @ManyToOne
    @JoinColumn(name = "author_id")
    Author author;
    Integer availableCopies;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")  // Matches mappedBy="user"
//    private User user;
    public Book() {}

    public Book(String name, Category category, Author author, Integer availableCopies) {
        this.name = name;
        this.category = category;
        this.author = author;
        this.availableCopies = availableCopies;
    }
}
