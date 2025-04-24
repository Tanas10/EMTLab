package mk.finki.ukim.mk.lab.service.application;

import mk.finki.ukim.mk.lab.dto.CreateUserDto;
import mk.finki.ukim.mk.lab.dto.DisplayUserDto;
import mk.finki.ukim.mk.lab.dto.LoginResponseDto;
import mk.finki.ukim.mk.lab.dto.LoginUserDto;

import java.util.Optional;

public interface UserApplicationService {

    Optional<DisplayUserDto> register(CreateUserDto createUserDto);

    Optional<LoginResponseDto> login(LoginUserDto loginUserDto);

    Optional<DisplayUserDto> findByUsername(String username);

}
