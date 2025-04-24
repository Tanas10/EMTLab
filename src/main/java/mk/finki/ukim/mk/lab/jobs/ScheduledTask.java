package mk.finki.ukim.mk.lab.jobs;




import mk.finki.ukim.mk.lab.service.domain.BookService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//@Component
public class ScheduledTask {
    private final BookService bookService;

    public ScheduledTask(BookService bookService) {
        this.bookService = bookService;
    }
    @Scheduled(fixedDelay = 5000)
    public void refreshMaterializedView(){

    }
}
