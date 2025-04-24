package mk.finki.ukim.mk.lab.model.events;

import org.springframework.context.ApplicationEvent;

public class AuthorChangedEvent extends ApplicationEvent {
    public AuthorChangedEvent(Object source) {
        super(source);
    }
}
