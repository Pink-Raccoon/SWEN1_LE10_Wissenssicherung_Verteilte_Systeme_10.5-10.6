package ch.zhaw.soe.swen1.le10.chat.domain;

import java.util.Objects;
import java.util.Optional;

/**
* An online platform for users to exchange messages in real-time in chat rooms.
*/
public class OnlineChat implements ChatService {
    private final String name;
    public static final String PUBLIC_CHATROOM = "Public";

    /**
     * Creates an online chat with a public chat room.
     * 
     * @param chatroomRepository must be not null
     */
    public OnlineChat(String name) {
        Objects.requireNonNull(name, "name");
        this.name = name;
        //TODO
    }

    /**
     * Join a chat room.
     * 
     * @param nickname     must be not null and not blank
     * @param chatroomname must be not null and not blank
     * @param callback     must be not null and not blank
     * @throws NicknameAlreadyUsedException if nickame already is used
     */
    public void join(String nickname, String chatroomname, ChatListener callback) throws NicknameAlreadyUsedException {
        //TODO
    }

    /**
     * Leave a chat room.
     * 
     * @param nickname     must be not null and not blank
     * @param chatroomname must be not null and not blank
     */
    public void leave(String nickname, String chatroomname) {
        //TODO
    }

    /**
     * Post a message to all chat users in a chat room.
     * 
     * @param nickname     must be not null and not blank
     * @param chatroomname must be not null and not blank
     * @param message      must be not null and not blank
     */
    public void post(String nickname, String chatroomname, String message) {
        //TODO
    }

    public String getName() {
        return name;
    }
}