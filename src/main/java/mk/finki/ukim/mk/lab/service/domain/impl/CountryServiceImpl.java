package mk.finki.ukim.mk.lab.service.domain.impl;

import mk.finki.ukim.mk.lab.model.domain.Country;
import mk.finki.ukim.mk.lab.repository.CountryRepository;
import mk.finki.ukim.mk.lab.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public Optional<Country> findById(Long id) {
        return countryRepository.findById(id);
    }

    @Override
    public Optional<Country> save(Country country) {
        Country newcountry = new Country();
        newcountry.setName(country.getName());
        newcountry.setContinent(country.getContinent());

        return Optional.of(countryRepository.save(country));
    }

    @Override
    public Optional<Country> update(Long id, Country country) {
        return countryRepository.findById(id)
                .map(existingCountry->{
                    if(existingCountry.getName()!=null){
                        existingCountry.setName(country.getName());
                    }
                    if(existingCountry.getContinent()!=null){
                        existingCountry.setContinent(country.getContinent());
                    }
            return countryRepository.save(existingCountry);
        });
    }

    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        countryRepository.deleteById(id);
    }

    @Override
    public List<Country> findByName(String name) {
        List<Country> countries = new ArrayList<Country>();
        for (Country country : this.findAll()) {
            if (country.getName().equals(name)) {
                countries.add(country);
            }
        }
        return countries;
    }
}
