package ch.zhaw.soe.swen1.le10.chat.domain;

/**
 * Published services of the online chat (facade).
 */
public interface ChatService {
    /**
     * Get the name of the chat.       
     */ 
    public String getName();

    /**
     * Join for a chat room.
     * 
     * @param nickname     must be not null and not blank
     * @param chatroomname must be not null and not blank
     * @param callback     remote interface for calling back the client; must not be null
     * @throws NicknameAlreadyUsedException if nickname is already used
     */
    public void join(String nickname, String chatroomname, ChatListener callback)
            throws NicknameAlreadyUsedException;

    /**
     * Leave a chat room.
     * 
     * @param nickname     must be not null and not blank
     * @param chatroomname must be not null and not blank
     */
    public void leave(String nickname, String chatroomname);

    /**
     * Posts a message in a chat room.
     * 
     * @param nickname     must be not null and not blank
     * @param message      must be not null and not blank
     * @param chatroomname must be not null and not blank
     */
    public void post(String nickname, String chatroomname, String message);
}