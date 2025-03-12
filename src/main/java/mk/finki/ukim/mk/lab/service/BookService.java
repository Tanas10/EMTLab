package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.dto.AuthorDto;
import mk.finki.ukim.mk.lab.model.dto.BookDto;

import java.util.List;
import java.util.Optional;

public interface BookService {


    Optional<Book> save(BookDto bookDto);
    Optional<Book> findById(Long id);
    List<Book> findByTitleAndAuthor(String name, AuthorDto authorDto);
    List<Book> findAll();
    void deleteById(Long id);
    Optional<Book> update(Long id,BookDto bookDto);
    Optional<Book> changeAvailability(Long id);
}
