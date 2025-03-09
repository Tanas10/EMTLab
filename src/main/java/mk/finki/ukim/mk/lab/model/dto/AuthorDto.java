package mk.finki.ukim.mk.lab.model.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import mk.finki.ukim.mk.lab.model.Country;
@Getter
@Setter
public class AuthorDto {

    private String name;
    private String surname;
    private Long countryId;


}
