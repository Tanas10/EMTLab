//package mk.finki.ukim.mk.lab.model;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import jakarta.persistence.*;
//import lombok.Data;
//import mk.finki.ukim.mk.lab.model.enumerations.Role;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Collections;
//import java.util.List;
//
//@Data
//@Entity
//public class User implements UserDetails {
//
//    @Id
//    private String username;
//
//    @JsonIgnore
//    private String password;
//
//    private boolean isAccountNonExpired = true;
//    private boolean isAccountNonLocked = true;
//    private boolean isCredentialsNonExpired = true;
//    private boolean isEnabled = true;
//
//    @Enumerated(value = EnumType.STRING)
//    private Role role;
//
//    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
//    private List<Book> books ;
//
//    public User() {}
//
//
//    public User(String username, String password, Role role) {
//        this.username = username;
//        this.password = password;
//        this.role = role;
//    }
//
//
//    public User(String username,Role role) {
//
//    }
//
//    public User(UserDetails userDetails) {
//        this.username = userDetails.getUsername();
//        this.password = userDetails.getPassword();
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return Collections.singletonList((GrantedAuthority) role);
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return isAccountNonExpired;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return isAccountNonLocked;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return isCredentialsNonExpired;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return isEnabled;
//    }
//
//}
