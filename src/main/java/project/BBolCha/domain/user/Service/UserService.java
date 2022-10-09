package project.BBolCha.domain.user.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import project.BBolCha.domain.Model.Status;
import project.BBolCha.domain.user.Dto.UserDto;
import project.BBolCha.domain.user.Entity.User;
import project.BBolCha.domain.user.Repository.UserRepository;

import static project.BBolCha.domain.Model.Status.LOGIN_TRUE;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public ResponseEntity<Status> register(UserDto request) {
      log.info(request.getEmail());
        userRepository.save(
                User.builder()
                        .name(request.getName())
                        .email(request.getEmail())
                        .pw(request.getPw())
                        .build()
        );
        return new ResponseEntity<>(LOGIN_TRUE, HttpStatus.OK);
    }
}
