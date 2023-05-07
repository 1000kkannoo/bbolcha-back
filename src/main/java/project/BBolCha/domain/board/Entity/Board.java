package project.BBolCha.domain.board.Entity;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import project.BBolCha.domain.user.Entity.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
@Entity
@DynamicUpdate
public class Board extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    private String name;

    @Column(name = "title", length = 65)
    @NotNull
    private String title;

    @Column(name = "content", length = 10000)
    @NotNull
    private String content;

    @Column(name = "views")
    @NotNull
    private Integer views;

    @NotNull
    private String correct;

    @Column(name = "hint")
    private String hint;

    private String contentImageUrl;

    private String tag;
}
