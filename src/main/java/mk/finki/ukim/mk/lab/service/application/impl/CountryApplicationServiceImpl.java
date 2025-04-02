package mk.finki.ukim.mk.lab.service.application.impl;

import mk.finki.ukim.mk.lab.dto.CreateCountryDto;
import mk.finki.ukim.mk.lab.dto.DisplayCountryDto;
import mk.finki.ukim.mk.lab.service.application.CountryApplicationService;
import mk.finki.ukim.mk.lab.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CountryApplicationServiceImpl implements CountryApplicationService {
    private final CountryService countryService;

    public CountryApplicationServiceImpl(CountryService countryService) {
        this.countryService = countryService;
    }

    @Override
    public Optional<DisplayCountryDto> findById(Long id) {
        return countryService.findById(id).map(DisplayCountryDto::fromCountry);
    }

    @Override
    public Optional<DisplayCountryDto> save(CreateCountryDto createCountryDto) {
        return countryService.save(createCountryDto.toCountry()).map(DisplayCountryDto::fromCountry);
    }

    @Override
    public Optional<DisplayCountryDto> update(Long id, CreateCountryDto createCountryDto) {
        return countryService.update(id,createCountryDto.toCountry()).map(DisplayCountryDto::fromCountry);
    }

    @Override
    public List<DisplayCountryDto> findAll() {
        return DisplayCountryDto.fromCountrys(countryService.findAll());
    }

    @Override
    public void deleteById(Long id) {
        countryService.deleteById(id);
    }

    @Override
    public List<DisplayCountryDto> findByName(String name) {
        return countryService.findByName(name).stream().map(DisplayCountryDto::fromCountry).collect(Collectors.toList());
    }
}
