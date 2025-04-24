package mk.finki.ukim.mk.lab.service.application;

import mk.finki.ukim.mk.lab.dto.CreateAuthorDto;
import mk.finki.ukim.mk.lab.dto.DisplayAuthorDto;
import mk.finki.ukim.mk.lab.model.projections.AuthorNameProjection;

import java.util.List;
import java.util.Optional;

public interface AuthorApplicationService {

    Optional<DisplayAuthorDto> findById(Long id);
    Optional<DisplayAuthorDto> save(CreateAuthorDto createAuthorDto);
    Optional<DisplayAuthorDto> update(Long id, CreateAuthorDto createAuthorDto);
    List<DisplayAuthorDto> findAll();
    void deleteById(Long id);
    List<AuthorNameProjection> getAuthorNames();

}
