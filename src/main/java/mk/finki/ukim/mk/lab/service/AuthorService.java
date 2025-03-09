package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Country;
import mk.finki.ukim.mk.lab.model.dto.AuthorDto;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    Optional<Author> findById(Long id);
    Optional<Author> save(AuthorDto authorDto);
    Optional<Author> update(Long id,AuthorDto authorDto);
    List<Author> findAll();
    void deleteById(Long id);
}
