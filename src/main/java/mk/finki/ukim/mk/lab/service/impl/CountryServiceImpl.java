package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Country;
import mk.finki.ukim.mk.lab.model.dto.CountryDto;
import mk.finki.ukim.mk.lab.repository.CountryRepository;
import mk.finki.ukim.mk.lab.service.CountryService;
import org.springframework.stereotype.Service;

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
    public Optional<Country> save(CountryDto countryDto) {
        Country country = new Country();
        country.setName(countryDto.getName());
        country.setContinent(countryDto.getContinent());

        return Optional.of(countryRepository.save(country));
    }

    @Override
    public Optional<Country> update(Long id,CountryDto countryDto) {
        return countryRepository.findById(id)
                .map(existingCountry->{
                    if(existingCountry.getName()!=null){
                        existingCountry.setName(countryDto.getName());
                    }
                    if(existingCountry.getContinent()!=null){
                        existingCountry.setContinent(countryDto.getContinent());
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
}
