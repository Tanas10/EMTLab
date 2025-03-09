package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Country;
import mk.finki.ukim.mk.lab.model.dto.AuthorDto;
import mk.finki.ukim.mk.lab.repository.AuthorRepository;
import mk.finki.ukim.mk.lab.repository.BookRepository;
import mk.finki.ukim.mk.lab.repository.CountryRepository;
import mk.finki.ukim.mk.lab.service.AuthorService;
import mk.finki.ukim.mk.lab.service.CountryService;
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
    public Optional<Author> save(AuthorDto authorDto) {
        Author author = new Author();
        author.setName(authorDto.getName());
        author.setSurname(authorDto.getSurname());
        Country country = countryService.findById(authorDto.getCountryId())
                .orElseThrow(()->new RuntimeException("Country not found"));
        author.setCountry(country);
        return Optional.of(authorRepository.save(author));
    }

    @Override
    public Optional<Author> update(Long id ,AuthorDto authorDto) {
        return authorRepository.findById(id)
                .map(existingAuthor -> {
          if(existingAuthor.getName() != null) {
              existingAuthor.setName(authorDto.getName());
          }
          if(existingAuthor.getSurname() != null) {
              existingAuthor.setSurname(authorDto.getSurname());
          }
          if(existingAuthor.getCountry() != null && countryService.findById(existingAuthor.getCountry().getId()).isPresent()) {
              existingAuthor.setCountry(countryService.findById(authorDto.getCountryId()).get());
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
