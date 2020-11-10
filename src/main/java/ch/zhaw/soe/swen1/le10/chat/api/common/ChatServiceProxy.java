package ch.zhaw.soe.swen1.le10.chat.api.common;

import ch.zhaw.soe.swen1.le10.chat.domain.ChatListener;
import ch.zhaw.soe.swen1.le10.chat.domain.ChatService;
import ch.zhaw.soe.swen1.le10.chat.domain.NicknameAlreadyUsedException;

/**
 * Implements a thread-safe proxy for accessing the chat service from different client aka channels. 
 */
class ChatServiceProxy implements ChatService {
   
    private final ChatService chatservice; 

    ChatServiceProxy(ChatService chatservice) {
        this.chatservice = chatservice;
    }

    @Override
    public String getName() {
        return chatservice.getName();
    }

    @Override
    public synchronized void join(String nickname, String chatroomname, ChatListener callback) throws NicknameAlreadyUsedException {
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
