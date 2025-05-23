package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
