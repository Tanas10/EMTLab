package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Country;
import mk.finki.ukim.mk.lab.model.dto.CountryDto;

import java.util.List;
import java.util.Optional;

public interface CountryService {


    Optional<Country> findById(Long id);
    Optional<Country> save(CountryDto countryDto);
    Optional<Country> update(Long id,CountryDto countryDto);
    List<Country> findAll();
    void deleteById(Long id);
}
