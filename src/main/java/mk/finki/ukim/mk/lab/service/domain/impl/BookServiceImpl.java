package mk.finki.ukim.mk.lab.service.domain.impl;

import mk.finki.ukim.mk.lab.model.domain.Author;
import mk.finki.ukim.mk.lab.model.domain.Book;
import mk.finki.ukim.mk.lab.model.enumerations.Category;
import mk.finki.ukim.mk.lab.repository.BookRepository;
import mk.finki.ukim.mk.lab.repository.views.BooksPerAuthorViewRepository;
import mk.finki.ukim.mk.lab.service.domain.AuthorService;
import mk.finki.ukim.mk.lab.service.domain.BookService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final BooksPerAuthorViewRepository booksPerAuthorViewRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService, ApplicationEventPublisher applicationEventPublisher, BooksPerAuthorViewRepository booksPerAuthorViewRepository) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.applicationEventPublisher = applicationEventPublisher;
        this.booksPerAuthorViewRepository = booksPerAuthorViewRepository;
    }

    @Override
    public Optional<Book> save(Book book) {
        Optional<Book> savedBook=Optional.empty();
        if(book.getAuthor() != null && authorService.findById(book.getAuthor().getId()).isPresent()){
            savedBook=Optional.of(bookRepository.save(new Book(book.getName(),book.getCategory(),authorService.findById(book.getAuthor().getId()).get(),book.getAvailableCopies(),book.isRented())));
//            this.refreshMaterializedView();
        }
        return savedBook;
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
        return bookRepository.findById(id).map(
                existingBook -> {
                    if (book.getName() != null) {
                        existingBook.setName(book.getName());
                    }
                    if(book.getCategory() != null){
                        existingBook.setCategory(book.getCategory());
                    }
                    if(book.getAvailableCopies() != null){
                        existingBook.setAvailableCopies(book.getAvailableCopies());
                        existingBook.setRented(book.isRented());
                    }
                    if (book.getAuthor() != null &&
                            authorService.findById(book.getAuthor().getId()).isPresent()) {
                        existingBook.setAuthor(authorService.findById(book.getAuthor().getId()).get());
                    }
                    Book updatedBook=bookRepository.save(existingBook);
//                    this.refreshMaterializedView();
                    return updatedBook;
                }
        );
    }


    @Override
    public Optional<Book> changeAvailability(Long id) {
        return bookRepository.findById(id).map(book -> {

            book.setAvailableCopies(book.getAvailableCopies() - 1);
            return bookRepository.save(book);
        });
    }

}
