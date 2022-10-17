package project.BBolCha.domain.user.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.BBolCha.domain.user.Dto.UserDto;
import project.BBolCha.domain.user.Entity.Authority;
import project.BBolCha.domain.user.Entity.User;
import project.BBolCha.domain.user.Entity.UserToken;
import project.BBolCha.domain.user.Repository.TokenRepository;
import project.BBolCha.domain.user.Repository.UserRepository;
import project.BBolCha.global.Exception.CustomException;
import project.BBolCha.global.config.Jwt.TokenProvider;

import java.util.Collections;

import static project.BBolCha.global.Exception.CustomErrorCode.LOGIN_FALSE;


@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    Authority authority = Authority.builder()
            .authorityName("ROLE_USER")
            .build();

    // Validate 및 단순화 메소드

    private UserToken saveRefreshToken() {
        return tokenRepository.save(
                UserToken.builder()
                        .token(tokenProvider.createRefreshToken())
                        .build()
        );
    }

    private User saveUserInfo(UserDto.register request) {
        return userRepository.save(
                User.builder()
                        .name(request.getName())
                        .email(request.getEmail())
                        .pw(passwordEncoder.encode(request.getPw()))
                        .authorities(Collections.singleton(authority))
                        .build()
        );
    }

    private String getAccessToken(UserDto.register request) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPw());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return tokenProvider.createToken(authentication);
    }

    private void LOGIN_VALIDATE(UserDto.login request) {
        userRepository.findByEmail(request.getEmail())
                .orElseThrow(
                        () -> new CustomException(LOGIN_FALSE)
                );

        if (!passwordEncoder.matches(
                request.getPw(),
                userRepository.findByEmail(request.getEmail())
                        .orElseThrow(
                                () -> new CustomException(LOGIN_FALSE)
                        ).getPw())
        ) {
            throw new CustomException(LOGIN_FALSE);
        }
    }

    // Service
    // 회원가입
    @Transactional
    public ResponseEntity<UserDto.registerResponse> register(UserDto.register request) {
        return new ResponseEntity<>(UserDto.registerResponse.response(
                saveUserInfo(request)
                , getAccessToken(request)
                , saveRefreshToken()
        ), HttpStatus.CREATED);
    }

    //로그인
    @Transactional
    public ResponseEntity<UserDto.loginResponse> login(UserDto.login request) {

        LOGIN_VALIDATE(request);

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPw());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new ResponseEntity<>(UserDto.loginResponse.response(
                tokenProvider.createToken(authentication),
                saveRefreshToken()
        ), HttpStatus.OK);
    }
}
