package yyyq.chat.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ChatListModel {
    //好友ID
    public String frindID;
    //好友昵称
    public String friendNickName;
    //最后一条消息
    public String lastMessage;
    //最后一条消息的时间
    public Date lastTime;
}
