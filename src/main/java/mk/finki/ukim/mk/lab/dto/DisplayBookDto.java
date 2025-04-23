package mk.finki.ukim.mk.lab.dto;

import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.enumerations.Category;

import java.util.List;
import java.util.stream.Collectors;




public record DisplayBookDto(Long id, String name, Category category, Long author, Integer availableCopies, boolean rented) {


    public static DisplayBookDto from(Book book){
        return new DisplayBookDto(
                book.getId(),
                book.getName(),
                book.getCategory(),
                book.getAuthor().getId(),
                book.getAvailableCopies(),
                book.isRented()
        );
    }
    public static List<DisplayBookDto> from(List<Book> books){
        return books.stream().map(DisplayBookDto::from).collect(Collectors.toList());
    }
    public Book toBook(Author author){
        return new Book(name,category,author,availableCopies,rented);
    }
}