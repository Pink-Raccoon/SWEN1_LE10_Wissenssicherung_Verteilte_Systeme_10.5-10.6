package ch.zhaw.soe.swen1.le10.chat.presentation.cli;

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import ch.zhaw.soe.swen1.le10.chat.api.rmi.ChatClient;

/**
 * Implements the chat client callback interface for a CLI client. Is presenter
 * for a chat room of the cli client.
 */
public class ChatClientImpl extends UnicastRemoteObject implements ChatClient {
    private static final long serialVersionUID = -2251831758529760348L;
    private final String chatroomname;

    public ChatClientImpl(String chatroomname) throws RemoteException {
        this.chatroomname = chatroomname;
    }

    @Override
    public void onMessage(String nickname, String chatroomname, String message) throws RemoteException {
        if (this.chatroomname.equals(chatroomname)) {
            System.out.println(String.format("%s: %s", nickname, message));
        }
    }

    @Override
    public void onActiveChatUsersChanged(String chatroomname, List<String> activeChatUsers) throws RemoteException {
        // Do noting in the cli client.
    }
}