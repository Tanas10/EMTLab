package mk.finki.ukim.mk.lab.service.application;

import mk.finki.ukim.mk.lab.dto.DisplayBookDto;
import mk.finki.ukim.mk.lab.dto.WishListDto;

import java.util.List;
import java.util.Optional;

public interface WishListApplicationService {
    List<DisplayBookDto> listAllProductsInShoppingCart(Long cartId);

    Optional<WishListDto> getActiveShoppingCart(String username);

    Optional<WishListDto> addProductToShoppingCart(String username, Long productId);
    Optional<WishListDto> rentAllBooks(String username);

}