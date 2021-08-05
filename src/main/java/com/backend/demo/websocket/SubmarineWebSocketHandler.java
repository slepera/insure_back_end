package com.backend.demo.websocket;

import com.backend.demo.data_model.SystemStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class SubmarineWebSocketHandler extends TextWebSocketHandler {
    SubmarineWebSocketThread submarineWebSocketThread;
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        this.submarineWebSocketThread = new SubmarineWebSocketThread(session);
        this.submarineWebSocketThread.start();
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        if(message.getPayload().equals("start"))
        {
            this.submarineWebSocketThread.send(true);
            SystemStatus.submarineStatus = "TRANSMITTING";
        }
        if(message.getPayload().equals("stop"))
        {
            this.submarineWebSocketThread.send(false);
            SystemStatus.submarineStatus = "NOT_TRANSMITTING";
        }
    }

}