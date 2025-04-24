package mk.finki.ukim.mk.lab.dto;


import mk.finki.ukim.mk.lab.model.domain.Author;
import mk.finki.ukim.mk.lab.model.domain.Book;
import mk.finki.ukim.mk.lab.model.enumerations.Category;

import java.util.List;
import java.util.stream.Collectors;

public record CreateBookDto(String name, Category category, Long author, Integer availableCopies, boolean rented) {
    public static CreateBookDto from(Book book) {
        return new CreateBookDto(
                book.getName(),
                book.getCategory(),
                book.getAuthor().getId(),
                book.getAvailableCopies(),
                book.isRented()
        );
    }

    public static List<CreateBookDto> from(List<Book> books) {
        return books.stream().map(CreateBookDto::from).collect(Collectors.toList());
    }

    public Book toBook(Author author) {
        return new Book(name, category, author, availableCopies, rented);
    }
}
