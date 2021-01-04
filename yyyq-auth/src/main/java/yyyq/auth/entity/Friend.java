package yyyq.auth.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Friend extends FriendKey {
    public String friendgroup;

    public Short status;
}