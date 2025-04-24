package mk.finki.ukim.mk.lab.dto;

import mk.finki.ukim.mk.lab.model.domain.Author;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayAuthorDto(String authorName, String authorSurname, DisplayCountryDto country) {

    public static DisplayAuthorDto fromAuthor(Author author) {
        return new DisplayAuthorDto(author.getName(), author.getSurname(), DisplayCountryDto.fromCountry(author.getCountry()));
    }
    public static List<DisplayAuthorDto> fromAuthors(List<Author> authors) {
        return authors.stream().map(DisplayAuthorDto::fromAuthor).collect(Collectors.toList());
    }
    public Author toAuthor() {
        return new Author(authorName,authorSurname, country.toCountry());
    }
}
