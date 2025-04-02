package mk.finki.ukim.mk.lab.service.application.impl;

import mk.finki.ukim.mk.lab.dto.CreateAuthorDto;
import mk.finki.ukim.mk.lab.dto.CreateCountryDto;
import mk.finki.ukim.mk.lab.dto.DisplayAuthorDto;
import mk.finki.ukim.mk.lab.dto.DisplayCountryDto;
import mk.finki.ukim.mk.lab.model.Country;
import mk.finki.ukim.mk.lab.repository.AuthorRepository;
import mk.finki.ukim.mk.lab.service.application.AuthorApplicationService;
import mk.finki.ukim.mk.lab.service.domain.AuthorService;
import mk.finki.ukim.mk.lab.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class AuthorApplicationServiceImpl implements AuthorApplicationService {
    private final AuthorService authorService;
    private final CountryService countryService;

    public AuthorApplicationServiceImpl(AuthorService authorService, CountryService countryService) {
        this.authorService = authorService;
        this.countryService = countryService;
    }

    @Override
    public Optional<DisplayAuthorDto> findById(Long id) {
        return authorService.findById(id).map(DisplayAuthorDto::fromAuthor);
    }

    @Override
    public Optional<DisplayAuthorDto> save(CreateAuthorDto createAuthorDto) {
        Optional<Country> country = countryService.findById(createAuthorDto.countryId());
        return authorService.save(createAuthorDto.toAuthor(country.orElse(null))).map(DisplayAuthorDto::fromAuthor);
    }

    @Override
    public Optional<DisplayAuthorDto> update(Long id, CreateAuthorDto createAuthorDto) {
        Optional<Country> country = countryService.findById(createAuthorDto.countryId());
        return authorService.update(id,createAuthorDto.toAuthor(country.orElse(null))).map(DisplayAuthorDto::fromAuthor);
    }

    @Override
    public List<DisplayAuthorDto> findAll() {
        return DisplayAuthorDto.fromAuthors(authorService.findAll());
    }

    @Override
    public void deleteById(Long id) {
        authorService.deleteById(id);
    }
}
