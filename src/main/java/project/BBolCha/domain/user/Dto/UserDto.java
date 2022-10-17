package project.BBolCha.domain.user.Dto;

import lombok.Builder;
import lombok.Data;
import project.BBolCha.domain.user.Entity.User;
import project.BBolCha.domain.user.Entity.UserToken;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class UserDto implements Serializable {

    @Data
    @Builder
    public static class register{
        private final Long id;
        private final String name;
        private final String email;
        private final String pw;
    }
    @Data
    @Builder
    public static class registerResponse{
        private final String name;
        private final String email;
        private final String atk;
        private final String rtk;

        public static registerResponse response(User user, String atk, UserToken rtk){
            return registerResponse.builder()
                    .email(user.getEmail())
                    .name(user.getName())
                    .atk(atk)
                    .rtk(rtk.getToken())
                    .build();
        }
    }

    @Data
    @Builder
    public static class login{
        private final String pw;
        private final String email;
    }

    @Data
    @Builder
    public static class loginResponse{
        private final String atk;
        private final String rtk;
        public static loginResponse response(String atk, UserToken rtk){
            return loginResponse.builder()
                    .atk(atk)
                    .rtk(rtk.getToken())
                    .build();
        }
    }

}
