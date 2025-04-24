package mk.finki.ukim.mk.lab.model.views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.Immutable;

@Entity
@Table(name = "authors_per_country_view")
@Immutable
public class AuthorsPerCountryView {

    @Id
    @Column(name = "country_id")
    private Long countryId;

    @Column(name = "country_name")
    private String countryName;

    @Column(name = "author_count")
    private Long authorCount;

    public Long getCountryId() { return countryId; }
    public String getCountryName() { return countryName; }
    public Long getAuthorCount() { return authorCount; }
}
