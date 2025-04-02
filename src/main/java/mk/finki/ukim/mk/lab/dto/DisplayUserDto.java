//package mk.finki.ukim.mk.lab.dto;
//
//import mk.finki.ukim.mk.lab.model.User;
//import mk.finki.ukim.mk.lab.model.enumerations.Role;
//
//public record DisplayUserDto(String username, Role role) {
//    public static DisplayUserDto from(User user) {
//        return new DisplayUserDto(
//                user.getUsername(),
//                user.getRole()
//        );
//    }
//    public User toUser() {
//        return new User(username, role);
//    }
//
//
//}
