package mk.finki.ukim.mk.lab.service.domain;

import mk.finki.ukim.mk.lab.model.domain.Country;

import java.util.List;
import java.util.Optional;

public interface CountryService {


    Optional<Country> findById(Long id);
    Optional<Country> save(Country country);
    Optional<Country> update(Long id, Country country);
    List<Country> findAll();
    void deleteById(Long id);
    List<Country> findByName(String name);
}
