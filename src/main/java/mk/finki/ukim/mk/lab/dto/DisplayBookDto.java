package mk.finki.ukim.mk.lab.dto;

import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.enumerations.Category;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayBookDto(String name,
                             Category category,
                             DisplayAuthorDto author,
                             Integer availableCopies) {

    public static DisplayBookDto fromBook(Book book) {
        return new DisplayBookDto(book.getName(), book.getCategory(), DisplayAuthorDto.fromAuthor(book.getAuthor()), book.getAvailableCopies());
    }
    public static List<DisplayBookDto> fromBooks(List<Book> books) {
        return books.stream().map(DisplayBookDto::fromBook).collect(Collectors.toList());
    }

}
