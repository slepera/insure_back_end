package com.backend.demo.websocket;

import com.backend.demo.data_model.OutputMessage;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

@Component
public class MyWebSocketHandler extends TextWebSocketHandler {

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        System.out.println(message);
        try {
            session.sendMessage(new TextMessage(
                    new OutputMessage("x","y","time").getJSONObject().toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}