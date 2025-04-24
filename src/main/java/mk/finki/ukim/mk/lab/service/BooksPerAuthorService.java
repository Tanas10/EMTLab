package mk.finki.ukim.mk.lab.repository.views;

import mk.finki.ukim.mk.lab.model.views.BooksPerAuthorView;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BooksPerAuthorService {
    private final BooksPerAuthorViewRepository viewRepository;

    public BooksPerAuthorService(BooksPerAuthorViewRepository viewRepository) {
        this.viewRepository = viewRepository;
    }

    public List<BooksPerAuthorView> getBooksPerAuthor() {
        return viewRepository.findAll();
    }
}
