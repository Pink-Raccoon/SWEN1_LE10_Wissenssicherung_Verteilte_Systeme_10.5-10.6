package ch.zhaw.soe.swen1.le10.chat.api.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import ch.zhaw.soe.swen1.le10.chat.domain.NicknameAlreadyUsedException;

/**
 * Remote services for the online chat.
 */
public interface ChatServer extends Remote {
    /**
     * Get the name of the chat.
     * 
     * @throws RemoteException
     */
    public String getName() throws RemoteException;

    /**
     * Join for chatting a chat room.
     * 
     * @param nickname     must be not null and not blank
     * @param chatroomname must be not null and not blank
     * @param callback     remote interface for calling back the client; must not be
     *                     null
     * @throws RemoteException
     */
    public void join(String nickname, String chatroomname, ChatClient callback)
            throws RemoteException, NicknameAlreadyUsedException;

    /**
     * Leave a chat room.
     * 
     * @param nickname     must be not null and not blank
     * @param chatroomname must be not null and not blank
     * @throws RemoteException
     * @throws NicknameAlreadyUsedException if nickname is already used
     */
    public void leave(String nickname, String chatroomname) throws RemoteException;

    /**
     * Posts a message in a chat room.
     * 
     * @param nickname     must be not null and not blank
     * @param message      must be not null and not blank
     * @param chatroomname must be not null and not blank
     * @throws RemoteException
     */
    public void post(String nickname, String chatroomname, String message) throws RemoteException;
}