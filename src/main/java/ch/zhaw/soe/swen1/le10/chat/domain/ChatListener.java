package ch.zhaw.soe.swen1.le10.chat.domain;

import java.util.List;

/**
 * Interface for a chat listener aka chat client.
 */
public interface ChatListener {
    /**
     * Called when a message was posted by a chat user in the chat room
     * {@code chatroomname}.
     * 
     * @param nickname
     * @param chatroomname
     * @param message
     * @throws ListenerNotReachableException if the client is not reachable anymore
     */
    void onMessage(String nickname, String chatroomname, String message) throws ListenerNotReachableException;

    /**
     * Called when the active chat users changed in in thechat room
     * {@code chatroomname}.
     * 
     * @param chatroomname
     * @param activeChatUsers
     * @throws ListenerNotReachableException if the client is not reachable anymore
     */
    void onActiveChatUsersChanged(String chatroomname, List<String> activeChatUsers)
            throws ListenerNotReachableException;
}