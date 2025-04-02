package mk.finki.ukim.mk.lab.dto;


import mk.finki.ukim.mk.lab.model.Country;

import java.util.List;
import java.util.stream.Collectors;

public record CreateCountryDto(String name, String continent) {
public static CreateCountryDto fromCountry(Country country) {
    return new CreateCountryDto(country.getName(), country.getContinent());
}
public static List<CreateCountryDto> fromCountrys(List<Country> countries) {
    return countries.stream().map(CreateCountryDto::fromCountry).collect(Collectors.toList());
}
public Country toCountry() {
    return new Country(name, continent);
}

}
