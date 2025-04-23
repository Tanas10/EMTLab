package mk.finki.ukim.mk.lab.model.projections;

import mk.finki.ukim.mk.lab.model.enumerations.Role;

public interface UserProjection {
    String getUsername();
    String getName();
    Role getRole();
}
