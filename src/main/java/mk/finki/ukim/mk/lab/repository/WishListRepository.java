package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.domain.User;
import mk.finki.ukim.mk.lab.model.domain.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WishListRepository extends JpaRepository<WishList,Long> {
    Optional<WishList> findByUser(User user);
}
