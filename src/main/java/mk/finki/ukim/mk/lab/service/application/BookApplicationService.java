package mk.finki.ukim.mk.lab.service.application;

import mk.finki.ukim.mk.lab.dto.CreateAuthorDto;
import mk.finki.ukim.mk.lab.dto.CreateBookDto;
import mk.finki.ukim.mk.lab.dto.DisplayBookDto;

import java.util.List;
import java.util.Optional;

public interface BookApplicationService {
    Optional<DisplayBookDto> save(CreateBookDto createBookDto);
    Optional<DisplayBookDto> findById(Long id);
    List<DisplayBookDto> findByTitleOrAuthor(String name, CreateAuthorDto createAuthorDto);
    List<DisplayBookDto> findAll();
    void deleteById(Long id);
    Optional<DisplayBookDto> update(Long id, CreateBookDto createBookDto);
    Optional<DisplayBookDto> changeAvailability(Long id);
    void refreshMaterializedView();
}
