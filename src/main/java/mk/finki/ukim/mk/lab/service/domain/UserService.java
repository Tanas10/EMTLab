package mk.finki.ukim.mk.lab.service.domain;


import mk.finki.ukim.mk.lab.model.domain.User;
import mk.finki.ukim.mk.lab.model.enumerations.Role;

public interface UserService {
    User register(String username, String password, String repeatPassword, String name, String surname, Role role);

    User login(String username, String password);

    User findByUsername(String username);


}