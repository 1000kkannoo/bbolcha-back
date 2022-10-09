package project.BBolCha.domain.user.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.BBolCha.domain.Model.Status;
import project.BBolCha.domain.user.Dto.UserDto;
import project.BBolCha.domain.user.Service.UserService;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<Status> register(
            @RequestBody UserDto request
    ) {
        return userService.register(request);
    }
}
