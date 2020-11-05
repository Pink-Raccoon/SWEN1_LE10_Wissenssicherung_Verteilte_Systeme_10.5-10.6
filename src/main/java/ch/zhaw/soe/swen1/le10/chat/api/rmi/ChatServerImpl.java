package ch.zhaw.soe.swen1.le10.chat.api.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.logging.Logger;

import ch.zhaw.soe.swen1.le10.chat.domain.ChatListener;
import ch.zhaw.soe.swen1.le10.chat.domain.ChatService;
import ch.zhaw.soe.swen1.le10.chat.domain.ListenerNotReachableException;
import ch.zhaw.soe.swen1.le10.chat.domain.NicknameAlreadyUsedException;

/**
 * Implements the remote chat service for RMI clients.
 */
public class ChatServerImpl extends UnicastRemoteObject implements ChatServer {
    private static final long serialVersionUID = -7395286822850307597L;
    private final transient ChatService chat;
    transient Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
   
    // Delegate for calling back a chat client.
    private class Callback implements ChatListener {
        private ChatClient client;

        Callback(ChatClient client) {
            this.client = client;
        }

        @Override
        public void onMessage(String nickname, String chatroomname, String message)
                throws ListenerNotReachableException {
            try {
                logger.fine(String.format("onMessage(%s, %s, %s) called.", nickname, chatroomname, message));
                client.onMessage(nickname, chatroomname, message);
            } catch (RemoteException e) {
                // Client not reachable.
                logger.fine("Client not reachable.");
                throw new ListenerNotReachableException(e.getMessage());
            }
        }

        @Override
        public void onActiveChatUsersChanged(String chatroomname, List<String> activeChatUsers)
                throws ListenerNotReachableException {
            try {
                logger.fine(
                        String.format("onActiveUsersChanged(%s, %s) called.", chatroomname, activeChatUsers.size()));
                client.onActiveChatUsersChanged(chatroomname, activeChatUsers);
            } catch (RemoteException e) {
                // Client not reachable.
                logger.fine("Client not reachable.");
                throw new ListenerNotReachableException(e.getMessage());
            }
        }
    }

    public ChatServerImpl(ChatService chat) throws RemoteException {
        this.chat = chat;
    }

    @Override
    public String getName() throws RemoteException {
        logger.fine(String.format("getName() called."));
        return chat.getName();
    }

    @Override
    public void join(String nickname, String chatroomname, ChatClient objRef)
            throws RemoteException, NicknameAlreadyUsedException {
        logger.fine(String.format("join(%s, %s, %s) called.", nickname, chatroomname, objRef));
        chat.join(nickname, chatroomname, new Callback(objRef));
    }

    @Override
    public void leave(String nickname, String chatroomname) throws RemoteException {
        logger.fine(String.format("leave(%s, %s) called.", nickname, chatroomname));
        chat.leave(nickname, chatroomname);
    }

    @Override
    public void post(String nickname, String chatroomname, String message) throws RemoteException {
        logger.fine(String.format("post(%s, %s, %s) called.", nickname, chatroomname, message));
        chat.post(nickname, chatroomname, message);
    }
}