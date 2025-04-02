package mk.finki.ukim.mk.lab.service.application;

import mk.finki.ukim.mk.lab.dto.CreateCountryDto;
import mk.finki.ukim.mk.lab.dto.DisplayCountryDto;

import java.util.List;
import java.util.Optional;

public interface CountryApplicationService {
    Optional<DisplayCountryDto> findById(Long id);
    Optional<DisplayCountryDto> save(CreateCountryDto createCountryDto);
    Optional<DisplayCountryDto> update(Long id, CreateCountryDto createCountryDto);
    List<DisplayCountryDto> findAll();
    void deleteById(Long id);
    List<DisplayCountryDto> findByName(String name);
}
