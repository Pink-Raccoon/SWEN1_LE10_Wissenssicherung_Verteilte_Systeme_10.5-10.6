package ch.zhaw.soe.swen1.le10.chat.api.common;

import ch.zhaw.soe.swen1.le10.chat.domain.ChatService;
import ch.zhaw.soe.swen1.le10.chat.domain.OnlineChat;

/**
 * Returns an thread-safe online chat service (singleton).
 * Could be made more generic and configurable. For now is just
 * simple service locator for the chat service.  
 */
public class ChatServiceLocator {
    static final String CHAT_NAME = "SWEN1";
    private static final ChatService chatservice = new ChatServiceProxy(new OnlineChat(CHAT_NAME));
    
    /**
     * @return the chat service  
     */
    public static ChatService getInstance() {
        return chatservice;
    }    
}
