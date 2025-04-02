package mk.finki.ukim.mk.lab.service.application.impl;

import mk.finki.ukim.mk.lab.dto.CreateAuthorDto;
import mk.finki.ukim.mk.lab.dto.CreateBookDto;
import mk.finki.ukim.mk.lab.dto.DisplayBookDto;
import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Country;
import mk.finki.ukim.mk.lab.service.application.BookApplicationService;
import mk.finki.ukim.mk.lab.service.domain.AuthorService;
import mk.finki.ukim.mk.lab.service.domain.BookService;
import mk.finki.ukim.mk.lab.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class BookApplicationServiceImpl implements BookApplicationService {
    private final BookService bookService;
    private final CountryService countryService;
    private final AuthorService authorService;
    public BookApplicationServiceImpl(BookService bookService, CountryService countryService, AuthorService authorService) {
        this.bookService = bookService;
        this.countryService = countryService;
        this.authorService = authorService;
    }

    @Override
    public Optional<DisplayBookDto> save(CreateBookDto createBookDto) {
        Optional<Author> author = authorService.findById(createBookDto.authorId());
        return bookService.save(createBookDto.toBook(author.orElse(null))).map(DisplayBookDto::fromBook);
    }

    @Override
    public Optional<DisplayBookDto> findById(Long id) {
        return bookService.findById(id).map(DisplayBookDto::fromBook);
    }

    @Override
    public List<DisplayBookDto> findByTitleOrAuthor(String name, CreateAuthorDto createAuthorDto) {
        Optional<Country> country = countryService.findById(createAuthorDto.countryId());
        return bookService.findByTitleOrAuthor(name,createAuthorDto.toAuthor(country.orElse(null))).stream().map(DisplayBookDto::fromBook).collect(Collectors.toList());
    }

    @Override
    public List<DisplayBookDto> findAll() {
        return DisplayBookDto.fromBooks(bookService.findAll());
    }

    @Override
    public void deleteById(Long id) {
    bookService.deleteById(id);
    }

    @Override
    public Optional<DisplayBookDto> update(Long id, CreateBookDto createBookDto) {
        Optional<Author> author = authorService.findById(createBookDto.authorId());
        return bookService.update(id,createBookDto.toBook(author.orElse(null))).map(DisplayBookDto::fromBook);
    }

    @Override
    public Optional<DisplayBookDto> changeAvailability(Long id) {
        return bookService.changeAvailability(id).map(DisplayBookDto::fromBook);
    }
}
