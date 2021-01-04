package yyyq.chat.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserGame {
    private Long userGameId;

    private Long userId;

    private Long gameId;

    private Boolean disabled;
}