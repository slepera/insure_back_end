package com.backend.demo.websocket;

import com.backend.demo.data_model.ChartOutputMessage;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

@Component
public class ChartWebSocketHandler extends TextWebSocketHandler {

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        new ChartWebSocketThread(session).start();
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
            super.afterConnectionClosed(session, status);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        System.out.println(message);
        try {
            session.sendMessage(new TextMessage(
                    new ChartOutputMessage("prova","10","20").getJSONObject().toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}