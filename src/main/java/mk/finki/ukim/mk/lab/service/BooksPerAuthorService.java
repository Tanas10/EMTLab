package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.views.BooksPerAuthorView;
import mk.finki.ukim.mk.lab.repository.views.BooksPerAuthorViewRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BooksPerAuthorService {

    private final BooksPerAuthorViewRepository viewRepository;
    private final JdbcTemplate jdbcTemplate;

    public BooksPerAuthorService(BooksPerAuthorViewRepository viewRepository, JdbcTemplate jdbcTemplate) {
        this.viewRepository = viewRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<BooksPerAuthorView> getBooksPerAuthor() {
        return viewRepository.findAll();
    }

    @Scheduled(cron = "0 0 * * * *")
    public void refreshMaterializedView() {
        jdbcTemplate.execute("REFRESH MATERIALIZED VIEW books_per_author_view");
        System.out.println("Refreshed books_per_author_view ✅");
    }
}

