package ch.zhaw.soe.swen1.le10.chat.api.websocket;

/**
 * Data transfer object (DTO) for a message.
 */
public class Message {
    public enum Action {
        JOIN, LEAVE, SAY, JOIN_ERROR, ACTIVE_USERS_CHANGED
    }
    private Action action;
    private String user;
    private String text;

    public Message(Action action, String user, String text) {
        this.action = action;
        this.user = user;
        this.text = text;
    }

    public Action getAction() {
        return action;
    }

    public String getUser() {
        return user;
    }

    public String getText() {
        return text;
    }
}