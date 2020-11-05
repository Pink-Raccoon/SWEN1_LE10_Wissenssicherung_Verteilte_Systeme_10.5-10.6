package ch.zhaw.soe.swen1.le10.chat.presentation.cli;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.Naming;

import ch.zhaw.soe.swen1.le10.chat.api.rmi.ChatServer;
import ch.zhaw.soe.swen1.le10.chat.domain.NicknameAlreadyUsedException;
import ch.zhaw.soe.swen1.le10.chat.domain.OnlineChat;

/**
 * An Implementation of a simple CLI chat client.
 */
public class ChatClientMain {
    private static final String CHAT_SERVER = "ChatServer";

    public static void main(String[] args) {
        if (args.length < 2 || args.length > 3) {
            System.out.println("Usage: <nickname> <server> [<chatroom>]");
            return;
        }
        String nickname = args[0];
        String hostname = args[1];
        String chatroomname = (args.length == 3) ? args[2] : OnlineChat.PUBLIC_CHATROOM;
        try {
            ChatServer server = (ChatServer) Naming.lookup("rmi://" + hostname + "/" + CHAT_SERVER);
            ChatClientImpl client = new ChatClientImpl(chatroomname);
            System.out.println(String.format("%s is joining the chat room %s in chat %s...", nickname,
                    chatroomname.toLowerCase(), server.getName()));
            System.out.println("Leave the chat by entering 'quit'");
            server.join(nickname, chatroomname, client);
            sendInputToServer(server, chatroomname, nickname);
            server.leave(nickname, chatroomname);
        } catch (NicknameAlreadyUsedException e) {
            System.out.println(String.format("Nickname %s is already in use! Start with a new name.", nickname));
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        System.exit(0);
    }

    private static void sendInputToServer(ChatServer server, String chatroomname, String nickname) {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            String line;
            while ((line = input.readLine()) != null) {
                if (line.equals("quit") || line.equals("q")) {
                    break;
                }
                if (!line.isBlank()) {
                    server.post(nickname, chatroomname, line);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }
}