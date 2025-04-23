package mk.finki.ukim.mk.lab.dto;

import mk.finki.ukim.mk.lab.model.WishList;

import java.util.List;

public record WishListDto(
        Long id,
        DisplayUserDto user,
        List<DisplayBookDto> books) {
    public static WishListDto from(WishList wishList){
        return new WishListDto(
                wishList.getId(),
                DisplayUserDto.from(wishList.getUser()),
                DisplayBookDto.from(wishList.getBooks())
        );
    }
}