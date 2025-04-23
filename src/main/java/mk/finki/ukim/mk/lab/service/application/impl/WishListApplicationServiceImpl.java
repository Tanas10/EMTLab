package mk.finki.ukim.mk.lab.service.application.impl;

import mk.finki.ukim.mk.lab.dto.DisplayBookDto;
import mk.finki.ukim.mk.lab.dto.WishListDto;
import mk.finki.ukim.mk.lab.service.application.WishListApplicationService;
import mk.finki.ukim.mk.lab.service.domain.WishListService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class WishListApplicationServiceImpl implements WishListApplicationService {

    private final WishListService wishListService;

    public WishListApplicationServiceImpl(WishListService wishListService) {
        this.wishListService = wishListService;
    }

    @Override
    public List<DisplayBookDto> listAllProductsInShoppingCart(Long cartId) {
        return DisplayBookDto.from(wishListService.listAllProductsInShoppingCart(cartId));
    }

    @Override
    public Optional<WishListDto> getActiveShoppingCart(String username) {
        return wishListService.getActiveShoppingCart(username).map(WishListDto::from);
    }

    @Override
    public Optional<WishListDto> addProductToShoppingCart(String username, Long productId) {
        return wishListService.addProductToShoppingCart(username, productId).map(WishListDto::from);
    }

    @Override
    public Optional<WishListDto> rentAllBooks(String username) {
        return wishListService.rentAllBooks(username).map(WishListDto::from);
    }
}