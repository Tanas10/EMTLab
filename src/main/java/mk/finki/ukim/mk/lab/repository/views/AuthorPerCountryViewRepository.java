package mk.finki.ukim.mk.lab.repository.views;


import mk.finki.ukim.mk.lab.model.views.AuthorsPerCountryView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorPerCountryViewRepository extends JpaRepository<AuthorsPerCountryView,Long> {
    AuthorsPerCountryView findByCountryId(Long countryId);
}