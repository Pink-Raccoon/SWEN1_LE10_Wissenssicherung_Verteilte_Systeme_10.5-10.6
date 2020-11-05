package ch.zhaw.soe.swen1.le10.chat.presentation.gui;

import java.rmi.Naming;
import java.util.List;

import ch.zhaw.soe.swen1.le10.chat.api.rmi.ChatClient;
import ch.zhaw.soe.swen1.le10.chat.api.rmi.ChatServer;
import ch.zhaw.soe.swen1.le10.chat.domain.NicknameAlreadyUsedException;
import ch.zhaw.soe.swen1.le10.chat.domain.OnlineChat;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * An Implementation of a simple JavaFX chat client.
 */
public class ChatClientMain extends Application {
    private static final String CHAT_SERVER = "ChatServer";

    public void start(Stage primaryStage) {
        List<String> args = getParameters().getUnnamed();
        String nickname = args.get(0);
        String server = args.get(1);
        String chatroomname = (args.size() == 3) ? args.get(2) : OnlineChat.PUBLIC_CHATROOM;
        ChatClientPresenter p = new ChatClientPresenter(nickname, chatroomname);
        ChatClientView v = new ChatClientView(p);
        try {
            ChatServer m = (ChatServer) Naming.lookup("rmi://" + server + "/" + CHAT_SERVER);
            p.setModel(m);
            ChatClient callback = new ChatClientImpl(v, chatroomname);
            m.join(nickname, chatroomname, callback);
        } catch (NicknameAlreadyUsedException e) {
            System.out.println(String.format("Nickname %s is already in use! Start with a new name.", nickname));
            System.exit(0);
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
            System.exit(0);
        }

        Scene scene = new Scene(v.getUI());
        String styles = getClass().getResource("/styles.css").toExternalForm();
        scene.getStylesheets().add(styles);
        primaryStage.setScene(scene);
        primaryStage.setTitle(String.format("Online Chat %s", p.getChatName()));
        primaryStage.setOnCloseRequest(e -> p.leave(nickname));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        if (args.length < 2 || args.length > 3) {
            System.out.println("Usage: <nickname> <server> [<chatroom>]");
            Platform.exit();
            return;
        }
        launch(args);
        System.exit(0);
    }
}