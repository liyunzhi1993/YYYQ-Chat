package yyyq.auth.model;

import lombok.Getter;
import lombok.Setter;

public class AddFriendModel {
    @Getter
    @Setter
    public Long acctID;
    @Getter
    @Setter
    public String friendID;
}
