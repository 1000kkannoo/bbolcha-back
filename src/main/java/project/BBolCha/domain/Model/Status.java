package project.BBolCha.domain.Model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Status {
    LOGIN_TRUE(200, "로그인 성공");
    private Integer status;
    private String statusMessage;
}
