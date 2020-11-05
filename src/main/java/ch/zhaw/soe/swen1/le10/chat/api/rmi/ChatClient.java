package ch.zhaw.soe.swen1.le10.chat.api.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Remote callback interface for a chat client. 
 * Interface ChatListener could directly be used if the the 
 * methods trow a general exception. But for the sake of 
 * defining a clean domain interface this solution was choosen. 
 */
public interface ChatClient extends Remote {
     /**
     * Called when a message was posted by a chat user in the chat room {@code chatroomname}.
     * 
     * @param nickname  
     * @param chatroomname  
     * @param message  
     */
    void onMessage(String nickname, String chatroomname, String message) throws RemoteException;
     /**
     *  Called when the active chat users changed in the chat room {@code chatroomname}.
     * 
     * @param chatroomname
     * @param activeChatUsers
     * @throws ListenerNotReachableException if the client is not reachable anymore 
     */
    void onActiveChatUsersChanged(String chatroomname, List<String> activeChatUsers) throws RemoteException;
}