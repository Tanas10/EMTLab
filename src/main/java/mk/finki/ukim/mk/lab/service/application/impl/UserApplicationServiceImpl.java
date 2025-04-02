//package mk.finki.ukim.mk.lab.service.application.impl;
//
//import mk.finki.ukim.mk.lab.dto.CreateUserDto;
//import mk.finki.ukim.mk.lab.dto.DisplayUserDto;
//import mk.finki.ukim.mk.lab.dto.LoginUserDto;
//import mk.finki.ukim.mk.lab.model.User;
//import mk.finki.ukim.mk.lab.service.application.UserApplicationService;
//import mk.finki.ukim.mk.lab.service.domain.UserService;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public class UserApplicationServiceImpl implements UserApplicationService {
//
//    private final UserService userService;
//
//    public UserApplicationServiceImpl(UserService userService) {
//        this.userService = userService;
//    }
//
//    @Override
//    public Optional<DisplayUserDto> register(CreateUserDto createUserDto) {
//        User user = userService.register(
//                createUserDto.username(),
//                createUserDto.password(),
//                createUserDto.repeatPassword(),
//                createUserDto.role()
//        );
//        return Optional.of(DisplayUserDto.from(user));
//    }
//
//    @Override
//    public Optional<DisplayUserDto> login(LoginUserDto loginUserDto) {
//        return Optional.of(DisplayUserDto.from(userService.login(
//                loginUserDto.username(),
//                loginUserDto.password()
//        )));
//    }
//
//    @Override
//    public Optional<DisplayUserDto> findByUsername(String username) {
//        return Optional.of(DisplayUserDto.from(userService.findByUsername(username)));
//    }
//}
