package project.BBolCha.domain.user.Dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserDto implements Serializable {
    private final Long id;
    private final String name;
    private final String email;
    private final String pw;
}
