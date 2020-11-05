package ch.zhaw.soe.swen1.le10.chat.api.websocket;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

/**
 * A simple echo service for testing a WebSocket connection.
 */
@ServerEndpoint("/echo")
public class Echo {
	private AtomicInteger counter = new AtomicInteger();
	private final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	@OnOpen
	public void onOpen(Session session) {
		logger.fine(session.getId() + ": onOpen");
		try {
			session.getBasicRemote().sendText("connected");
		} catch (IOException e) {
		}
	}

	@OnClose
	public void onClose(Session session) {
		logger.fine(session.getId() + ": onClose");
	}

	@OnError
	public void onError(Session session, Throwable error) {
		logger.fine(session.getId() + ": " + error.getMessage());
	}

	@OnMessage
	public void onMessage(Session session, String message) {
		try {
			logger.fine(session.getId() + ": " + message);
			session.getBasicRemote().sendText(counter.incrementAndGet() + ". " + message);
		} catch (IOException e) {
			try {
				session.close();
			} catch (IOException e1) {
			}
		}
	}
}
