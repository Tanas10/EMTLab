package mk.finki.ukim.mk.lab.dto;


import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.enumerations.Category;

import java.util.List;
import java.util.stream.Collectors;

public record CreateBookDto(String name,
                            Category category,
                            Long authorId,
                            Integer availableCopies) {

    public static CreateBookDto fromBook(Book book) {
        return new CreateBookDto(book.getName(), book.getCategory(), book.getAuthor().getId(), book.getAvailableCopies());
    }
    public static List<CreateBookDto> fromBooks(List<Book> books) {
        return books.stream().map(CreateBookDto::fromBook).collect(Collectors.toList());
    }
    public Book toBook(Author author) {
        return new Book(name,category,author,availableCopies);
    }
}
