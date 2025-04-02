package mk.finki.ukim.mk.lab.service.domain.impl;

import mk.finki.ukim.mk.lab.dto.DisplayAuthorDto;
import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.dto.CreateBookDto;
import mk.finki.ukim.mk.lab.model.enumerations.Category;
import mk.finki.ukim.mk.lab.repository.BookRepository;
import mk.finki.ukim.mk.lab.service.domain.AuthorService;
import mk.finki.ukim.mk.lab.service.domain.BookService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorService authorService;
    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    @Override
    public Optional<Book> save(Book book) {
        Book newbook = new Book();
        newbook.setName(book.getName());

        // Parse category string to enum
        try {
            newbook.setCategory(Category.valueOf(book.getCategory().name()));
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid category", e);
        }

        Author author = authorService.findById(book.getAuthor().getId())
                .orElseThrow(() -> new RuntimeException("Author not found"));
        newbook.setAuthor(author);
        newbook.setAvailableCopies(book.getAvailableCopies());

        return Optional.of(bookRepository.save(newbook));
    }


    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public List<Book> findByTitleOrAuthor(String name, Author author) {
        List<Book> correctBooks = new ArrayList<>();
        List<Book> books = findAll();
        for (Book book : books) {
            if(book.getName().equalsIgnoreCase(name)||

                    (author.getName().equalsIgnoreCase(book.getAuthor().getName())
                    && author.getSurname().equalsIgnoreCase(book.getAuthor().getSurname())
                    && author.getCountry().getName().equals(book.getAuthor().getCountry().getName()))) {


                correctBooks.add(book);

            }
        }
        return correctBooks;
    }


    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Optional<Book> update(Long id, Book book) {
        return bookRepository.findById(id)
                .map(existingBook -> {
               if(existingBook.getName() !=null)
                   existingBook.setName(book.getName());
               if(existingBook.getCategory() != null)
                   existingBook.setCategory(book.getCategory());
               if (existingBook.getAuthor() != null && authorService.findById(existingBook.getAuthor().getId()).isPresent())
                   existingBook.setAuthor(authorService.findById(book.getAuthor().getId()).get());
               if(existingBook.getAvailableCopies() != null)
                   existingBook.setAvailableCopies(book.getAvailableCopies());
               return bookRepository.save(existingBook);
        });
    }


    @Override
    public Optional<Book> changeAvailability(Long id) {
        return bookRepository.findById(id).map(book -> {

            book.setAvailableCopies(book.getAvailableCopies() - 1);
            return bookRepository.save(book);
        });
    }
}
