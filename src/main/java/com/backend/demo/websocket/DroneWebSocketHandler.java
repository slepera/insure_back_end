package com.backend.demo.websocket;

import com.backend.demo.data_model.DroneOutputMessage;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

@Component
public class DroneWebSocketHandler extends TextWebSocketHandler {

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        new DroneWebSocketThread(session).start();
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        System.out.println(message);
        try {
            session.sendMessage(new TextMessage(
                    new DroneOutputMessage("10","20","drone").getJSONObject().toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}