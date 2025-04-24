package mk.finki.ukim.mk.lab.listeners;

import mk.finki.ukim.mk.lab.model.events.AuthorChangedEvent;
import mk.finki.ukim.mk.lab.service.AuthorsPerCountryService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AuthorEventListener {

    private final AuthorsPerCountryService authorsPerCountryService;

    public AuthorEventListener(AuthorsPerCountryService authorsPerCountryService) {
        this.authorsPerCountryService = authorsPerCountryService;
    }

    @EventListener
    public void onAuthorChanged(AuthorChangedEvent event) {
        authorsPerCountryService.refreshMaterializedView();
    }
}
