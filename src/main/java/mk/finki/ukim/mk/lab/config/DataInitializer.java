package mk.finki.ukim.mk.lab.config;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.model.domain.Author;
import mk.finki.ukim.mk.lab.model.domain.Book;
import mk.finki.ukim.mk.lab.model.domain.Country;

import mk.finki.ukim.mk.lab.model.domain.User;
import mk.finki.ukim.mk.lab.model.enumerations.Category;
import mk.finki.ukim.mk.lab.repository.AuthorRepository;
import mk.finki.ukim.mk.lab.repository.BookRepository;
import mk.finki.ukim.mk.lab.repository.CountryRepository;

import mk.finki.ukim.mk.lab.repository.UserRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import mk.finki.ukim.mk.lab.model.enumerations.Role;
@Profile("test")

@Component
public class DataInitializer {

    private final CountryRepository countryRepository;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(CountryRepository countryRepository, AuthorRepository authorRepository, BookRepository bookRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.countryRepository = countryRepository;
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;

        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    void init() {
        countryRepository.save(new Country("Macedonia","Europe"));
        countryRepository.save(new Country("USA","America"));
        countryRepository.save(new Country("China","Asia"));

        authorRepository.save(new Author("James","Elks",countryRepository.findById(1L).get()));
        authorRepository.save(new Author("Shanelle","Gonzalez",countryRepository.findById(2L).get()));
        authorRepository.save(new Author("Brian","Washington",countryRepository.findById(3L).get()));

        bookRepository.save(new Book("Book 1", Category.NOVEL,authorRepository.findById(1L).get(),3,false));
        bookRepository.save(new Book("Book 2", Category.DRAMA,authorRepository.findById(2L).get(),6,false));
        bookRepository.save(new Book("Book 3", Category.FANTASY,authorRepository.findById(3L).get(),7,false));




            userRepository.save(new User(
                    "ad",
                    passwordEncoder.encode("ad"),
                    "Atanas",
                    "Despotovski",
                    Role.ROLE_ADMIN
            ));

            userRepository.save(new User(
                    "user",
                    passwordEncoder.encode("user"),
                    "user",
                    "user",
                    Role.ROLE_USER
            ));
            userRepository.save(new User(
                    "li",
                    passwordEncoder.encode("li"),
                    "linrarian1",
                    "linrarian1",
                    Role.ROLE_LIBRARIAN
            ));
        }
    }
