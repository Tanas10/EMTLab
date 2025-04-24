package mk.finki.ukim.mk.lab.repository.views;


import jakarta.transaction.Transactional;
import mk.finki.ukim.mk.lab.model.views.BooksPerAuthorView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BooksPerAuthorViewRepository extends JpaRepository<BooksPerAuthorView, Long> {
    List<BooksPerAuthorView> findAll();
//    void refreshMaterializedView();
}
