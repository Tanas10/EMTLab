package mk.finki.ukim.mk.lab.service.application.impl;

import mk.finki.ukim.mk.lab.dto.CreateAuthorDto;
import mk.finki.ukim.mk.lab.dto.DisplayAuthorDto;
import mk.finki.ukim.mk.lab.model.domain.Country;
import mk.finki.ukim.mk.lab.model.events.AuthorChangedEvent;
import mk.finki.ukim.mk.lab.model.projections.AuthorNameProjection;
import mk.finki.ukim.mk.lab.service.application.AuthorApplicationService;
import mk.finki.ukim.mk.lab.service.domain.AuthorService;
import mk.finki.ukim.mk.lab.service.domain.CountryService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class AuthorApplicationServiceImpl implements AuthorApplicationService {
    private final AuthorService authorService;
    private final CountryService countryService;
    private final ApplicationEventPublisher applicationEventPublisher;

    public AuthorApplicationServiceImpl(AuthorService authorService, CountryService countryService, ApplicationEventPublisher applicationEventPublisher) {
        this.authorService = authorService;
        this.countryService = countryService;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public Optional<DisplayAuthorDto> findById(Long id) {
        return authorService.findById(id).map(DisplayAuthorDto::fromAuthor);
    }

    @Override
    public Optional<DisplayAuthorDto> save(CreateAuthorDto createAuthorDto) {
        Optional<Country> country = countryService.findById(createAuthorDto.countryId());
        applicationEventPublisher.publishEvent(new AuthorChangedEvent(this));

        return authorService.save(createAuthorDto.toAuthor(country.orElse(null))).map(DisplayAuthorDto::fromAuthor);
    }

    @Override
    public Optional<DisplayAuthorDto> update(Long id, CreateAuthorDto createAuthorDto) {
        Optional<Country> country = countryService.findById(createAuthorDto.countryId());
        applicationEventPublisher.publishEvent(new AuthorChangedEvent(this));

        return authorService.update(id,createAuthorDto.toAuthor(country.orElse(null))).map(DisplayAuthorDto::fromAuthor);
    }

    @Override
    public List<DisplayAuthorDto> findAll() {
        return DisplayAuthorDto.fromAuthors(authorService.findAll());
    }

    @Override
    public void deleteById(Long id) {
        applicationEventPublisher.publishEvent(new AuthorChangedEvent(this));
        authorService.deleteById(id);
    }


    @Override
    public List<AuthorNameProjection> getAuthorNames() {
        return authorService.getAuthorNames();
    }
}
