package mk.finki.ukim.mk.lab.web;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.finki.ukim.mk.lab.dto.DisplayAuthorDto;
import mk.finki.ukim.mk.lab.dto.CreateAuthorDto;
import mk.finki.ukim.mk.lab.model.projections.AuthorNameProjection;
import mk.finki.ukim.mk.lab.model.views.AuthorsPerCountryView;
import mk.finki.ukim.mk.lab.service.AuthorsPerCountryService;
import mk.finki.ukim.mk.lab.service.application.AuthorApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
@Tag(name = "Authors API", description = "Endpoints for managing authors")
public class AuthorControler {


    private final AuthorApplicationService authorApplicationService;
    private final AuthorsPerCountryService authorsPerCountryService;
    public AuthorControler(AuthorApplicationService authorApplicationService, AuthorsPerCountryService authorsPerCountryService) {
        this.authorApplicationService = authorApplicationService;
        this.authorsPerCountryService = authorsPerCountryService;
    }


    @GetMapping
    @Operation(summary = "Get all authors", description = "Retrieves a list of all available authors.")
    public List<DisplayAuthorDto> findAll() {
        return authorApplicationService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get author by id", description = "Retrieves an author by id.")
    public ResponseEntity<DisplayAuthorDto> findById(@PathVariable Long id) {
        return authorApplicationService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping("/add")
    @Operation(summary = "Add a new author", description = "Creates a new author.")
    public ResponseEntity<DisplayAuthorDto> save(@RequestBody CreateAuthorDto createAuthorDto) {
        return authorApplicationService.save(createAuthorDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PutMapping("/edit/{id}")
    @Operation(summary = "Update an existing author", description = "Updates an author by ID.")
    public ResponseEntity<DisplayAuthorDto> update(@PathVariable Long id, @RequestBody CreateAuthorDto createAuthorDto) {
        return authorApplicationService.update(id, createAuthorDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete a author", description = "Deletes an author by its ID.")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (authorApplicationService.findById(id).isPresent()) {
            authorApplicationService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }


    @GetMapping("/by-country")
    public List<AuthorsPerCountryView> getAuthorsPerCountry() {
        return authorsPerCountryService.getAuthorsPerCountry();
    }

    @GetMapping("/names")
    public List<AuthorNameProjection> getAuthorNames() {
        return authorApplicationService.getAuthorNames();
    }

}
