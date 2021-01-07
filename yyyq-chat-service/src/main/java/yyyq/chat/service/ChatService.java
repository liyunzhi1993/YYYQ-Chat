package yyyq.chat.service;

import yyyq.chat.model.ChatListModel;
import yyyq.chat.model.SendMessageModel;

import java.util.List;

public interface ChatService {
    public List<ChatListModel> getChatList(Long acctID);
    public boolean sendMessage(SendMessageModel sendMessageModel);
}
