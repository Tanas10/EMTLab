package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.views.AuthorsPerCountryView;
import mk.finki.ukim.mk.lab.repository.views.AuthorPerCountryViewRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorsPerCountryService {

    private final AuthorPerCountryViewRepository repository;
    private final JdbcTemplate jdbcTemplate;

    public AuthorsPerCountryService(AuthorPerCountryViewRepository repository, JdbcTemplate jdbcTemplate) {
        this.repository = repository;
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<AuthorsPerCountryView> getAuthorsPerCountry() {
        return repository.findAll();
    }

    public void refreshMaterializedView() {
        jdbcTemplate.execute("REFRESH MATERIALIZED VIEW authors_per_country_view");
        System.out.println("✅ Refreshed authors_per_country_view");
    }
}
