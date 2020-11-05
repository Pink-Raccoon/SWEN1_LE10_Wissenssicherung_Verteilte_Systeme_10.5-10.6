package ch.zhaw.soe.swen1.le10.chat.api.common;

import ch.zhaw.soe.swen1.le10.chat.domain.ChatListener;
import ch.zhaw.soe.swen1.le10.chat.domain.ChatService;
import ch.zhaw.soe.swen1.le10.chat.domain.NicknameAlreadyUsedException;
import ch.zhaw.soe.swen1.le10.chat.domain.OnlineChat;

/**
 * Implements a thread-safe proxy for accessing the online chat from different
 * client aka channels.
 */
public class ChatServiceProxy implements ChatService {
    static final String CHAT_NAME = "SWEN1";
    private static final OnlineChat chatservice = new OnlineChat(CHAT_NAME);

    @Override
    public String getName() {
        return chatservice.getName();
    }

    @Override
    public synchronized void join(String nickname, String chatroomname, ChatListener callback)
            throws NicknameAlreadyUsedException {
        chatservice.join(nickname, chatroomname, callback);
    }

    @Override
    public synchronized void leave(String nickname, String chatroomname) {
        chatservice.leave(nickname, chatroomname);
    }

    @Override
    public synchronized void post(String nickname, String chatroomname, String message) {
        chatservice.post(nickname, chatroomname, message);
    }    
}
