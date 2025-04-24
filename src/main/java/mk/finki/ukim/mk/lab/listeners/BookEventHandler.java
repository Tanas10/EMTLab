package mk.finki.ukim.mk.lab.listeners;


import mk.finki.ukim.mk.lab.model.events.BookCreatedEvent;
import mk.finki.ukim.mk.lab.service.domain.BookService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

//@Component
public class BookEventHandler {
    private  final BookService bookService;

    public BookEventHandler(BookService bookService) {
        this.bookService = bookService;
    }
    @EventListener
    public  void onBookCreated(BookCreatedEvent event){
//        this.bookService.refreshMaterializedView();

    }
}