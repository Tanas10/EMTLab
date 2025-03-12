package mk.finki.ukim.mk.lab.web;


import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.dto.AuthorDto;
import mk.finki.ukim.mk.lab.model.dto.BookDto;
import mk.finki.ukim.mk.lab.service.BookService;
import mk.finki.ukim.mk.lab.service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookContorler {


    private final BookService bookService;
    private final CountryService countryService;


    public BookContorler(BookService bookService, CountryService countryService) {

        this.bookService = bookService;
        this.countryService = countryService;
    }

    @GetMapping
    public List<Book> findAll() {
        return bookService.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id) {
        return bookService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping("/add")
    public ResponseEntity<Book> save(@RequestBody BookDto bookDTO) {
        return bookService.save(bookDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Book> update(@PathVariable Long id, @RequestBody BookDto bookDto) {
        return bookService.update(id, bookDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (bookService.findById(id).isPresent()) {
            bookService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/find")
    public List<Book> findByNameAndAuthor(
            @RequestParam String bookname,
            @RequestParam String authorName,
            @RequestParam String authorSurname,
            @RequestParam Long authorCountryID) {

        AuthorDto authorDto = new AuthorDto(authorName, authorSurname,authorCountryID);
        return bookService.findByTitleAndAuthor(bookname, authorDto);
    }



}
