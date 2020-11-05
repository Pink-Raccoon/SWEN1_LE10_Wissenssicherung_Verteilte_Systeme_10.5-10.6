package ch.zhaw.soe.swen1.le10.chat.presentation.gui;

import java.util.Collections;
import java.util.List;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * View of the chat client for a chat user and a chat room.
 */
class ChatClientView {
    private ChatClientPresenter presenter;
    private GridPane root;
    private TextField input;
    private TextArea output;
    private Label chatroomname;
    private Label participants;
    private Label chatuser;

    public ChatClientView(ChatClientPresenter presenter) {
        this.presenter = presenter;
        initView();
    }

    private void initView() {
        root = new GridPane();
        Insets ins = new Insets(16);
        root.setPadding(ins);
        chatroomname = new Label();
        chatroomname.setText(String.format("Chat Room %s", presenter.getChatroomName()));
        chatroomname.setId("chatroomname");
        root.add(chatroomname, 0, 0, 1, 1);
        chatuser = new Label();
        chatuser.setText(String.format("Signed in: %s", presenter.getChatuserName()));
        chatuser.setId("chatuser");
        root.add(chatuser, 1, 0, 1, 1);
        GridPane.setHalignment(chatuser, HPos.RIGHT);
        GridPane.setValignment(chatuser, VPos.TOP);
        participants = new Label();
        participants.setId("participants");
        participants.setText(String.format("%d Participants", 0));
        root.add(participants, 0, 1, 1, 1);
        output = new TextArea();
        output.setId("chathistory");
        output.setPrefSize(500, 300);
        output.setEditable(false);
        output.setWrapText(true);
        root.add(output, 0, 2, 2, 1);
        GridPane.setMargin(output, new Insets(8, 0, 8, 0));
        input = new TextField();
        input.setId("input");
        input.setOnAction(e -> handleInput());
        input.setPromptText("Enter a message.");
        root.add(input, 0, 3, 2, 1);
        input.requestFocus();
    }

    private void handleInput() {
        String message = input.getText();
        if (!message.isBlank()) {
            input.setText("");
            presenter.post(message);
        }
    }

    public Pane getUI() {
        return root;
    }

    public void showMessage(String message) {
        output.appendText(message + "\n");
        input.requestFocus();
    }

    public void showActiveChatUsers(List<String> activeChatusers) {
        participants.setText(String.format("%d Participants", activeChatusers.size()));
        List<String> users = activeChatusers;
        Collections.sort(users);
        participants.setTooltip(new Tooltip(users.toString()));
    }
}
