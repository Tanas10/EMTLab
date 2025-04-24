package mk.finki.ukim.mk.lab.service.domain;

import mk.finki.ukim.mk.lab.model.domain.Book;
import mk.finki.ukim.mk.lab.model.domain.WishList;

import java.util.List;
import java.util.Optional;

public interface WishListService {
    List<Book> listAllProductsInShoppingCart(Long cartId);

    Optional<WishList> getActiveShoppingCart(String username);

    Optional<WishList> addProductToShoppingCart(String username, Long productId);
    Optional<WishList> rentAllBooks(String username);
}
