package ch.zhaw.soe.swen1.le10.chat.api.websocket;

import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import ch.zhaw.soe.swen1.le10.chat.api.common.ChatServiceLocator;
import ch.zhaw.soe.swen1.le10.chat.api.websocket.Message.Action;
import ch.zhaw.soe.swen1.le10.chat.domain.ChatListener;
import ch.zhaw.soe.swen1.le10.chat.domain.ListenerNotReachableException;
import ch.zhaw.soe.swen1.le10.chat.domain.NicknameAlreadyUsedException;
import ch.zhaw.soe.swen1.le10.chat.domain.OnlineChat;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

/**
 * The client session is created by the WebSocket API when a client opens the chat url.
 * Each client gets is own session.
 */
@ServerEndpoint(value = "/chat", decoders = MessageDecoder.class, encoders = MessageEncoder.class)
public class ChatClientSession implements ChatListener {
	private String user;
	private Session session;
	private static AtomicInteger id = new AtomicInteger();
	private final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	public ChatClientSession() {
		user = "User" + id.incrementAndGet();
	}

	@OnOpen
	public void onOpen(Session session) throws IOException, EncodeException {
		this.session = session;
		logger.fine(session.getId() + ": onOpen");
		try {
			ChatServiceLocator.getInstance().join(user, OnlineChat.PUBLIC_CHATROOM, this);
			Message msg = new Message(Action.JOINED, user, "");
			session.getBasicRemote().sendObject(msg);
		} catch (NicknameAlreadyUsedException e) {
			Message msg = new Message(Action.JOIN_ERROR, user,
					String.format("Nickname %s is already in use! Start with a new name.", user));
			session.getBasicRemote().sendObject(msg);
		}
	}

	@OnClose
	public void onClose(Session session) {
		logger.fine(session.getId() + ": onClose");
		ChatServiceLocator.getInstance().leave(user, OnlineChat.PUBLIC_CHATROOM);
	}

	@OnError
	public void onError(Session session, Throwable error) {
		logger.fine(session.getId() + ": " + error.getMessage());
	}

	@OnMessage
	public void onMessage(Session session, Message message) {
		switch (message.getAction()) {
			case SAY:
				ChatServiceLocator.getInstance().post(message.getUser(), OnlineChat.PUBLIC_CHATROOM, message.getText());
				break;
			case JOIN:
				try {
					ChatServiceLocator.getInstance().join(message.getUser(), OnlineChat.PUBLIC_CHATROOM, this);
				} catch (NicknameAlreadyUsedException e) {
					Message msg = new Message(Action.JOIN_ERROR, user,
					String.format("Nickname %s is already in use! Start with a new name.", user));
					try {
						session.getBasicRemote().sendObject(msg);
					} catch (IOException | EncodeException e1) {
						e1.printStackTrace();
					}
					e.printStackTrace();
				}
				break;
			case LEAVE:
				ChatServiceLocator.getInstance().leave(message.getUser(), OnlineChat.PUBLIC_CHATROOM);
				break;
			default:
				break;
		}
	}

	@Override
	public void onMessage(String nickname, String chatroomname, String message) throws ListenerNotReachableException {
		try {
			if (session.isOpen()) {
				Message msg = new Message(Action.SAY, nickname, nickname + ": " + message);
				session.getBasicRemote().sendObject(msg);
			} else
				throw new ListenerNotReachableException("Session closed.");
		} catch (IOException | EncodeException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onActiveChatUsersChanged(String chatroomname, List<String> activeChatUsers)
			throws ListenerNotReachableException {
		try {
			if (session.isOpen()) {
				Message msg = new Message(Action.ACTIVE_USERS_CHANGED, user,
						activeChatUsers.size() + " Participant(s)");
				session.getBasicRemote().sendObject(msg);
			} else
				throw new ListenerNotReachableException("Session closed.");
		} catch (IOException | EncodeException e) {
			e.printStackTrace();
		}
	}
}
