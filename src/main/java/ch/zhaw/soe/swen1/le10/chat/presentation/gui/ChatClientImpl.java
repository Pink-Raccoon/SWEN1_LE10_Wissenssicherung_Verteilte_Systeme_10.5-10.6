package ch.zhaw.soe.swen1.le10.chat.presentation.gui;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import ch.zhaw.soe.swen1.le10.chat.api.rmi.ChatClient;
import javafx.application.Platform;

/**
 * Implements the chat client callback interface for a JavaFX client. Is part of
 * the presenter for a chat room (could be a nested class in the
 * ChatClientPresenter).
 */
public class ChatClientImpl extends UnicastRemoteObject implements ChatClient {
    private static final long serialVersionUID = 5640640465003051787L;
    private final transient ChatClientView view;
    private final String chatroomname;

    public ChatClientImpl(ChatClientView view, String chatroomname) throws RemoteException {
        this.view = view;
        this.chatroomname = chatroomname;
    }

    @Override
    public void onMessage(String nickname, String chatroomname, String message) throws RemoteException {
        if (this.chatroomname.equals(chatroomname)) {
            Platform.runLater(() -> view.showMessage(String.format("%s: %s", nickname, message)));
        }
    }

    @Override
    public void onActiveChatUsersChanged(String chatroomname, List<String> activeChatusers) throws RemoteException {
        if (this.chatroomname.equals(chatroomname)) {
            Platform.runLater(() -> view.showActiveChatUsers(activeChatusers));
        }
    }
}