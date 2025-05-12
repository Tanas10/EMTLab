package mk.finki.ukim.mk.lab.service.application.impl;

import mk.finki.ukim.mk.lab.dto.CreateAuthorDto;
import mk.finki.ukim.mk.lab.dto.CreateBookDto;
import mk.finki.ukim.mk.lab.dto.DisplayBookDto;
import mk.finki.ukim.mk.lab.model.domain.Author;
import mk.finki.ukim.mk.lab.model.domain.Country;
import mk.finki.ukim.mk.lab.model.events.BookCreatedEvent;
import mk.finki.ukim.mk.lab.repository.views.BooksPerAuthorViewRepository;
import mk.finki.ukim.mk.lab.service.application.BookApplicationService;
import mk.finki.ukim.mk.lab.service.domain.AuthorService;
import mk.finki.ukim.mk.lab.service.domain.BookService;
import mk.finki.ukim.mk.lab.service.domain.CountryService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class BookApplicationServiceImpl implements BookApplicationService {
    private final BookService bookService;
    private final CountryService countryService;
    private final AuthorService authorService;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final BooksPerAuthorViewRepository booksPerAuthorViewRepository;
    private final JdbcTemplate jdbcTemplate;
    public BookApplicationServiceImpl(BookService bookService, CountryService countryService, AuthorService authorService, ApplicationEventPublisher applicationEventPublisher, BooksPerAuthorViewRepository booksPerAuthorViewRepository, JdbcTemplate jdbcTemplate) {
        this.bookService = bookService;
        this.countryService = countryService;
        this.authorService = authorService;
        this.applicationEventPublisher = applicationEventPublisher;
        this.booksPerAuthorViewRepository = booksPerAuthorViewRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<DisplayBookDto> save(CreateBookDto book) {
        Optional<Author> author=authorService.findById(book.authorId());
        if(author.isPresent()){
            this.applicationEventPublisher.publishEvent(new BookCreatedEvent(book.toBook(author.get())));

            return bookService.save(book.toBook(author.get()))
                    .map(DisplayBookDto::from);
        }
        return Optional.empty();
    }

    @Override
    public Optional<DisplayBookDto> findById(Long id) {
        return bookService.findById(id).map(DisplayBookDto::from);
    }

    @Override
    public List<DisplayBookDto> findByTitleOrAuthor(String name, CreateAuthorDto createAuthorDto) {
        Optional<Country> country = countryService.findById(createAuthorDto.countryId());
        return bookService.findByTitleOrAuthor(name,createAuthorDto.toAuthor(country.orElse(null))).stream().map(DisplayBookDto::from).collect(Collectors.toList());
    }

    @Override
    public List<DisplayBookDto> findAll() {
        return DisplayBookDto.from(bookService.findAll());
    }

    @Override
    public void deleteById(Long id) {
    bookService.deleteById(id);
    }

    @Override
    public Optional<DisplayBookDto> update(Long id, CreateBookDto createBookDto) {
        Optional<Author> author = authorService.findById(createBookDto.authorId());
        return bookService.update(id,createBookDto.toBook(author.orElse(null))).map(DisplayBookDto::from);
    }

    @Override
    public Optional<DisplayBookDto> changeAvailability(Long id) {
        return bookService.changeAvailability(id).map(DisplayBookDto::from);
    }

    @Scheduled(cron = "0 0 * * * *") // секој час
    public void refreshMaterializedView() {
        jdbcTemplate.execute("REFRESH MATERIALIZED VIEW books_per_author_view");
        System.out.println("Materialized view refreshed!");
    }
}
