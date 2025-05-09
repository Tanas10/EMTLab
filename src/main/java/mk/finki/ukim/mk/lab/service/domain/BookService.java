package mk.finki.ukim.mk.lab.service.domain;

import mk.finki.ukim.mk.lab.model.domain.Author;
import mk.finki.ukim.mk.lab.model.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {


    Optional<Book> save(Book book);
    Optional<Book> findById(Long id);
    List<Book> findByTitleOrAuthor(String name, Author author);
    List<Book> findAll();
    void deleteById(Long id);
    Optional<Book> update(Long id, Book book);
    Optional<Book> changeAvailability(Long id);
}
