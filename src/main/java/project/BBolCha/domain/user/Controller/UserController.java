package project.BBolCha.domain.user.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.BBolCha.domain.user.Dto.UserDto;
import project.BBolCha.domain.user.Service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    // 로그인
    @PostMapping("login")
    public ResponseEntity<UserDto.loginResponse> login(
            @RequestBody UserDto.login request
    ) {
        return userService.login(request);
    }

    // 로그인 만료시 atk 재발급
    @GetMapping("reissue")
    public ResponseEntity<UserDto.loginResponse> reissue(
            HttpServletRequest headerRequest
    ) {
        return userService.reissue(headerRequest);
    }

    // 정보 조회
    @GetMapping
    public ResponseEntity<UserDto.infoResponse> read(
            HttpServletRequest headerRequest
    ){
        return userService.read(headerRequest);
    }
}
