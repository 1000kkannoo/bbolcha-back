package project.BBolCha.domain.user.Controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import project.BBolCha.domain.user.Dto.UserDto;
import project.BBolCha.domain.user.Service.UserService;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 회원가입
    @PostMapping
    public ResponseEntity<UserDto.registerResponse> register(
            @RequestBody UserDto.register request
    ) {
        return userService.register(request);
    }

    //로그인
    @PostMapping("login")
    public ResponseEntity<UserDto.loginResponse> register(
            @RequestBody UserDto.login request
    ){
        return userService.login(request);
    }

}
