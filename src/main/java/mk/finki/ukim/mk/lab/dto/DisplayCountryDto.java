package mk.finki.ukim.mk.lab.dto;

import mk.finki.ukim.mk.lab.model.domain.Country;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayCountryDto(Long id,String name, String continent) {
    public static DisplayCountryDto fromCountry(Country country) {
        return new DisplayCountryDto(country.getId(),country.getName(), country.getContinent());
    }
    public static List<DisplayCountryDto> fromCountrys(List<Country> countries) {
        return countries.stream().map(DisplayCountryDto::fromCountry).collect(Collectors.toList());
    }
public Country toCountry() {
        return new Country(name, continent);
}
}
