package mk.finki.ukim.mk.lab.service.domain.impl;

import mk.finki.ukim.mk.lab.dto.CreateCountryDto;
import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Country;
import mk.finki.ukim.mk.lab.dto.CreateAuthorDto;
import mk.finki.ukim.mk.lab.repository.AuthorRepository;
import mk.finki.ukim.mk.lab.service.domain.AuthorService;
import mk.finki.ukim.mk.lab.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    private final CountryService countryService;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryService countryService) {
        this.authorRepository = authorRepository;
        this.countryService = countryService;


    }

    @Override
    public Optional<Author> findById(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public Optional<Author> save(Author author) {
        Author authornew = new Author();
        authornew.setName(author.getName());
        authornew.setSurname(author.getSurname());
        Country country = countryService.findById(author.getCountry().getId())
                .orElseThrow(()->new RuntimeException("Country not found"));
        authornew.setCountry(country);
        return Optional.of(authorRepository.save(authornew));
    }

    @Override
    public Optional<Author> update(Long id , Author author) {
        return authorRepository.findById(id)
                .map(existingAuthor -> {
          if(existingAuthor.getName() != null) {
              existingAuthor.setName(author.getName());
          }
          if(existingAuthor.getSurname() != null) {
              existingAuthor.setSurname(author.getSurname());
          }
          if(existingAuthor.getCountry() != null && countryService.findById(existingAuthor.getCountry().getId()).isPresent()) {
              existingAuthor.setCountry(countryService.findById(author.getCountry().getId()).get());
          }

            return authorRepository.save(existingAuthor);
        });
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
    authorRepository.deleteById(id);
    }
}
