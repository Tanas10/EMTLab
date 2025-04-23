package mk.finki.ukim.mk.lab.model.exceptions;

public class WishListNotFoundException extends RuntimeException {
    public WishListNotFoundException(Long id) {
        super("Wish List with for user: "+id+" does not exists");
    }
}
