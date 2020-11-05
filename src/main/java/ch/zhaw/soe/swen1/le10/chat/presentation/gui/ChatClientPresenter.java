package ch.zhaw.soe.swen1.le10.chat.presentation.gui;

import java.rmi.RemoteException;

import ch.zhaw.soe.swen1.le10.chat.api.rmi.ChatServer;

/**
 * Presenter aka controller of the chat client for a chat user and a chat room.
 */
class ChatClientPresenter {
    private ChatServer model;
    private final String nickname;
    private final String chatroomname;

    public ChatClientPresenter(String nickname, String chatroomname) {
        this.nickname = nickname;
        this.chatroomname = chatroomname;
    }

    public void setModel(ChatServer model) {
        this.model = model;
    }

    public String getChatName() {
        try {
            return model.getName();
        } catch (RemoteException e) {
            System.out.println(e);
            return "Unknown";
        }
    }

    public String getChatuserName() {
        return nickname;
    }

    public String getChatroomName() {
        return chatroomname;
    }

    public void post(String message) {
        new Thread(() -> postInThread(message)).start();
    }

    private void postInThread(String message) {
        try {
            model.post(nickname, chatroomname, message);
        } catch (RemoteException e) {
            System.out.println(e);
        }
    }

    public void leave(String nickname) {
        try {
            model.leave(nickname, chatroomname);
        } catch (RemoteException e) {
            System.out.println(e);
        }
    }
}