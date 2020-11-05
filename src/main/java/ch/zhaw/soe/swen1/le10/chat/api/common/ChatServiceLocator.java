package ch.zhaw.soe.swen1.le10.chat.api.common;

import ch.zhaw.soe.swen1.le10.chat.domain.ChatService;

/**
 * Returns an thread-safe online chat service (singleton).
 * Could be made more generic and configurable. For now is just
 * simple service locator for the chat service.  
 */
public class ChatServiceLocator {

    private static final ChatService chatservice = new ChatServiceProxy();
    
    /**
     * @return the chat service  
     */
    public static ChatService getInstance() {
        return chatservice;
    }    
}
