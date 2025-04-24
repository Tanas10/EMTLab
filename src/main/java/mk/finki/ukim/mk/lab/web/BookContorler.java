package mk.finki.ukim.mk.lab.web;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.finki.ukim.mk.lab.dto.*;
import mk.finki.ukim.mk.lab.model.views.BooksPerAuthorView;
import mk.finki.ukim.mk.lab.service.BooksPerAuthorService;
import mk.finki.ukim.mk.lab.service.application.BookApplicationService;
import mk.finki.ukim.mk.lab.service.application.CountryApplicationService;
import mk.finki.ukim.mk.lab.service.domain.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@Tag(name = "Books API", description = "Endpoints for managing books")

public class BookContorler {


    private final BookApplicationService bookApplicationService;
    private final CountryApplicationService countryApplicationService;
    private final BooksPerAuthorService booksPerAuthorService;
    public BookContorler(BookApplicationService bookApplicationService, CountryApplicationService countryApplicationService, BooksPerAuthorService booksPerAuthorService) {
        this.bookApplicationService = bookApplicationService;
        this.countryApplicationService = countryApplicationService;
        this.booksPerAuthorService = booksPerAuthorService;
    }


    @GetMapping
    @Operation(summary = "Get all books", description = "Retrieves a list of all available books.")
    public List<DisplayBookDto> findAll() {
        return bookApplicationService.findAll();
    }

    @Operation(summary = "Get book by id", description = "Retrieves a book by id.")
    @GetMapping("/{id}")
    public ResponseEntity<DisplayBookDto> findById(@PathVariable Long id) {
        return bookApplicationService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @Operation(summary = "Add a new book", description = "Creates a new book.")
    @PostMapping("/add")
    public ResponseEntity<DisplayBookDto> save(@RequestBody CreateBookDto createBookDTO) {
        return bookApplicationService.save(createBookDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @Operation(summary = "Update an existing book", description = "Updates a book by ID.")
    @PutMapping("/edit/{id}")
    public ResponseEntity<DisplayBookDto> update(@PathVariable Long id, @RequestBody CreateBookDto createBookDto) {
        return bookApplicationService.update(id, createBookDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete a book", description = "Deletes a book by its ID.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (bookApplicationService.findById(id).isPresent()) {
            bookApplicationService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
    @Operation(summary = "Get books filtered by title and author", description = "Retrieves a list of all available books by title and author.")
    @GetMapping("/find")
    public List<DisplayBookDto> findByNameAndAuthor(
            @RequestParam String bookname,
            @RequestParam String authorName,
            @RequestParam String authorSurname,
            @RequestParam String authorCountryName) {

        CreateAuthorDto createAuthorDto = new CreateAuthorDto(authorName,authorSurname,countryApplicationService.findByName(authorCountryName).getFirst().toCountry().getId());
        return bookApplicationService.findByTitleOrAuthor(bookname, createAuthorDto);
    }
    @GetMapping("/by-author")
    @Operation(summary = "Get number of books by author", description = "Returns book count per author from a materialized view.")
    public List<BooksPerAuthorView> getBooksPerAuthor() {
        return booksPerAuthorService.getBooksPerAuthor();
    }


}
