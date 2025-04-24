package mk.finki.ukim.mk.lab.model.views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

@Entity
@Subselect("SELECT * FROM public.books_per_country")
@Immutable
public class BookPerCountryView {
    @Id
    @Column(name = "country_id")
    private Long countryId;
    @Column(name = "num_books")
    private Integer books_num;

    public Long getCountry_id() {
        return countryId;
    }

    public void setCountry_id(Long country_id) {
        this.countryId = country_id;
    }

    public Integer getBooks_num() {
        return books_num;
    }

    public void setBooks_num(Integer books_num) {
        this.books_num = books_num;
    }
}