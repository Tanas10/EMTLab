package mk.finki.ukim.mk.lab.web;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import mk.finki.ukim.mk.lab.dto.WishListDto;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.service.application.WishListApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wishlist")
@Tag(name = "wish list API", description = "Endpoints for managing the wish list")
public class WishListController {
    private final WishListApplicationService shoppingCartApplicationService;

    public WishListController(WishListApplicationService shoppingCartApplicationService) {
        this.shoppingCartApplicationService = shoppingCartApplicationService;
    }

    @Operation(
            summary = "Get active wish list",
            description = "Retrieves the active wish list for the logged-in user"
    )
    @ApiResponses(
            value = {@ApiResponse(
                    responseCode = "200",
                    description = "wish list retrieved successfully"
            ), @ApiResponse(responseCode = "404", description = "wish list not found")}
    )
    @GetMapping
    public ResponseEntity<WishListDto> getActiveShoppingCart(HttpServletRequest req) {
        String username = req.getRemoteUser();
        return shoppingCartApplicationService.getActiveShoppingCart(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Add product to wish list",
            description = "Adds a product to the wish list for the logged-in user"
    )
    @ApiResponses(
            value = {@ApiResponse(
                    responseCode = "200", description = "Product added to wish list successfully"
            ), @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request"
            ), @ApiResponse(responseCode = "404", description = "Product not found")}
    )
    @PostMapping("/add-books/{id}")
    public ResponseEntity<WishListDto> addProductToShoppingCart(
            @PathVariable Long id,
            Authentication authentication
    ) {
        try {
            User user = (User) authentication.getPrincipal();
            return shoppingCartApplicationService.addProductToShoppingCart(user.getUsername(), id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (RuntimeException exception) {
            return ResponseEntity.badRequest().build();
        }
    }
    @Operation(
            summary = "Add product to wish list",
            description = "Adds a product to the wish list for the logged-in user"
    )
    @ApiResponses(
            value = {@ApiResponse(
                    responseCode = "200", description = "Product added to wish list successfully"
            ), @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request"
            ), @ApiResponse(responseCode = "404", description = "Product not found")}
    )
    @PostMapping("/rent-all-books/")
    public ResponseEntity<WishListDto> rentAllBooks(HttpServletRequest req) {
        String username = req.getRemoteUser();
        return shoppingCartApplicationService.rentAllBooks(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}