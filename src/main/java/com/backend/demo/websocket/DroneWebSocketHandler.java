package com.backend.demo.websocket;

import com.backend.demo.data_model.DroneOutputMessage;
import com.backend.demo.data_model.SystemStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

@Component
public class DroneWebSocketHandler extends TextWebSocketHandler {
    DroneWebSocketThread droneWebSocketThread;
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        this.droneWebSocketThread = new DroneWebSocketThread(session);
        this.droneWebSocketThread.start();
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        if(message.getPayload().equals("start"))
        {
            this.droneWebSocketThread.send(true);
            SystemStatus.droneStatus = "TRANSMITTING";
        }
        if(message.getPayload().equals("stop"))
        {
            this.droneWebSocketThread.send(false);
            SystemStatus.droneStatus = "NOT_TRANSMITTING";
        }
    }

}