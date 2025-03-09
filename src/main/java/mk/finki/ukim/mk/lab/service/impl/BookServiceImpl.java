package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.dto.BookDto;
import mk.finki.ukim.mk.lab.model.enumerations.Category;
import mk.finki.ukim.mk.lab.repository.AuthorRepository;
import mk.finki.ukim.mk.lab.repository.BookRepository;
import mk.finki.ukim.mk.lab.service.AuthorService;
import mk.finki.ukim.mk.lab.service.BookService;
import org.springframework.stereotype.Service;

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
    public Optional<Book> save(BookDto bookDto) {
        Book book = new Book();
        book.setName(bookDto.getName());

        // Parse category string to enum
        try {
            book.setCategory(Category.valueOf(bookDto.getCategory().name()));
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid category", e);
        }

        Author author = authorService.findById(bookDto.getAuthorId())
                .orElseThrow(() -> new RuntimeException("Author not found"));
        book.setAuthor(author);
        book.setAvailableCopies(bookDto.getAvailableCopies());

        return Optional.of(bookRepository.save(book));
    }


    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
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
    public Optional<Book> update(Long id, BookDto bookDto) {
        return bookRepository.findById(id)
                .map(existingBook -> {
               if(existingBook.getName() !=null)
                   existingBook.setName(bookDto.getName());
               if(existingBook.getCategory() != null)
                   existingBook.setCategory(bookDto.getCategory());
               if (existingBook.getAuthor() != null && authorService.findById(existingBook.getAuthor().getId()).isPresent())
                   existingBook.setAuthor(authorService.findById(bookDto.getAuthorId()).get());
               if(existingBook.getAvailableCopies() != null)
                   existingBook.setAvailableCopies(bookDto.getAvailableCopies());
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
