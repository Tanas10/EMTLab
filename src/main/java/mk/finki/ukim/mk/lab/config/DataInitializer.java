package mk.finki.ukim.mk.lab.config;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.Country;
import mk.finki.ukim.mk.lab.model.dto.CountryDto;
import mk.finki.ukim.mk.lab.model.enumerations.Category;
import mk.finki.ukim.mk.lab.repository.AuthorRepository;
import mk.finki.ukim.mk.lab.repository.BookRepository;
import mk.finki.ukim.mk.lab.repository.CountryRepository;
import mk.finki.ukim.mk.lab.service.CountryService;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    private final CountryRepository countryRepository;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;


    public DataInitializer(CountryRepository countryRepository, AuthorRepository authorRepository, BookRepository bookRepository) {
        this.countryRepository = countryRepository;
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @PostConstruct
    void init() {
        countryRepository.save(new Country("Macedonia","Europe"));
        countryRepository.save(new Country("USA","America"));
        countryRepository.save(new Country("China","Asia"));

        authorRepository.save(new Author("James","Elks",countryRepository.findById(1L).get()));
        authorRepository.save(new Author("Shanelle","Gonzalez",countryRepository.findById(2L).get()));
        authorRepository.save(new Author("Brian","Washington",countryRepository.findById(3L).get()));

        bookRepository.save(new Book("Book 1", Category.NOVEL,authorRepository.findById(1L).get(),3));
        bookRepository.save(new Book("Book 2", Category.DRAMA,authorRepository.findById(2L).get(),6));
        bookRepository.save(new Book("Book 3", Category.FANTASY,authorRepository.findById(3L).get(),7));
    }



}
