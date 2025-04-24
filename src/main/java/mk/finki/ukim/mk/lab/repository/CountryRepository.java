package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
    //Optional<Country> findByName(String name);
}
