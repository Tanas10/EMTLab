//package mk.finki.ukim.mk.lab.repository;
//
//import mk.finki.ukim.mk.lab.model.User;
//import mk.finki.ukim.mk.lab.model.enumerations.Role;
//import mk.finki.ukim.mk.lab.model.projections.UserProjection;
//import org.springframework.data.jpa.repository.EntityGraph;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//public interface UserRepository extends JpaRepository<User, String> {
//
//    Optional<User> findByUsernameAndPassword(String username, String password);
//
//    Optional<User> findByUsername(String username);
//
//
//    @Query("select u from User u")
//    List<User> fetchAll();
//
//    @Query("SELECT u.username AS username, u.role AS role FROM User u WHERE u.role = :role")
//    UserProjection findByRole(@Param("role") Role role);
//
//    @Query("select u.username from User u")
//    List<UserProjection> takeUsernameByProjection();
//
//}
//
