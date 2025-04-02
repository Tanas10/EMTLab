package mk.finki.ukim.mk.lab.service.domain;

import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.dto.CreateAuthorDto;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    Optional<Author> findById(Long id);
    Optional<Author> save(Author author);
    Optional<Author> update(Long id, Author author);
    List<Author> findAll();
    void deleteById(Long id);
}
