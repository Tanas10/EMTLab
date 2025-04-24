package mk.finki.ukim.mk.lab.model.views;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

@Entity
@Table(name = "books_per_author_view")
@Immutable
public class BooksPerAuthorView {

    @Id
    @Column(name = "author_id")
    private Long authorId;

    @Column(name = "author_name")
    private String authorName;

    @Column(name = "book_count")
    private Long bookCount;

    public Long getAuthorId() { return authorId; }
    public String getAuthorName() { return authorName; }
    public Long getBookCount() { return bookCount; }
}
