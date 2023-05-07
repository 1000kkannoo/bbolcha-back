package project.BBolCha.domain.board.Entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
@Entity
public class Tag {

    @Id
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    private Boolean horror;

    private Boolean daily;

    private Boolean romance;

    private Boolean fantasy;

    private Boolean sf;
}