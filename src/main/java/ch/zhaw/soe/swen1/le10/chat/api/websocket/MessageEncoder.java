package ch.zhaw.soe.swen1.le10.chat.api.websocket;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import com.google.gson.Gson;

/**
 * Serializes a message object into a JSON message. 
 */
public class MessageEncoder implements Encoder.Text<Message> {

    private static Gson gson = new Gson();

    @Override
    public String encode(Message message) throws EncodeException {
        String json = gson.toJson(message);
        return json;
    }

    @Override
    public void init(EndpointConfig endpointConfig) {
        // Custom initialization logic
    }

    @Override
    public void destroy() {
        // Close resources
    }
}
