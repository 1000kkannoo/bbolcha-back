package project.BBolCha.domain.board.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.BBolCha.domain.board.Entity.Comment;

import java.time.LocalDateTime;

public class CommentDto {

    @Getter
    @NoArgsConstructor
    public static class AddDto {
        private String note;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Builder
    public static class DetailDto {
        private String userName;
        private String userProfileImageUrl;
        private String note;
        private LocalDateTime createdAt;

        public static DetailDto response(Comment comment) {
            return DetailDto.builder()
                    .userName(comment.getUser().getName())
                    .userProfileImageUrl(comment.getUser().getProfileImageUrl())
                    .note(comment.getNote())
                    .build();
        }
    }

}
