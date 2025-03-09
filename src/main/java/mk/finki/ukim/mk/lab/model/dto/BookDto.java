package mk.finki.ukim.mk.lab.model.dto;

import lombok.Getter;
import lombok.Setter;
import mk.finki.ukim.mk.lab.model.enumerations.Category;

@Getter
@Setter
public class BookDto {
    private String name;
    private Category category;
    private Long authorId;
    private Integer availableCopies;


}
