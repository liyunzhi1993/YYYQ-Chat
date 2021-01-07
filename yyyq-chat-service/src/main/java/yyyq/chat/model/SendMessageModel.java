package yyyq.chat.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendMessageModel {
    public Long acctID;
    public Long friendID;
    public String body;
}
