package mk.finki.ukim.mk.lab.model.exceptions;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(Long bookId) {
        super("Book not found with this id "+bookId);
    }
}
