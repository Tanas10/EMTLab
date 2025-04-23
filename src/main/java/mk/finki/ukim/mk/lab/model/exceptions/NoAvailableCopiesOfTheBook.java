package mk.finki.ukim.mk.lab.model.exceptions;

public class NoAvailableCopiesOfTheBook extends RuntimeException {
    public NoAvailableCopiesOfTheBook(String name) {
        super("No available copies of book: "+name);
    }
}
