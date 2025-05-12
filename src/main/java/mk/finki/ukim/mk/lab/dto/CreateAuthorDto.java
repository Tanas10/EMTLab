package mk.finki.ukim.mk.lab.dto;

import mk.finki.ukim.mk.lab.model.domain.Author;
import mk.finki.ukim.mk.lab.model.domain.Country;


import java.util.List;
import java.util.stream.Collectors;

public record CreateAuthorDto(
        String name,
        String surname,
        Long countryId
    )
{


    public static CreateAuthorDto fromAuthor(Author author) {
        return new CreateAuthorDto(
                author.getName(),
                author.getSurname(),
                author.getCountry().getId()
        );
    }
    public static List<CreateAuthorDto> fromAuthors(List<Author> authors) {
        return authors.stream().map(CreateAuthorDto::fromAuthor).collect(Collectors.toList());
    }
    public Author toAuthor(Country country) {

        return new Author(name, surname, country);
    }
}
