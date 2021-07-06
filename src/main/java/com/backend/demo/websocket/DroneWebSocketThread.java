package com.backend.demo.websocket;

import com.backend.demo.data_model.ChartOutputMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

public class DroneWebSocketThread extends Thread{
    private final WebSocketSession webSocketSession;

    DroneWebSocketThread(WebSocketSession webSocketSession)
    {
        this.webSocketSession = webSocketSession;
    }
    @Override
    public void run() {
        while (true) {
            try {
                this.webSocketSession.sendMessage(new TextMessage(
                        new ChartOutputMessage("242", "203", "drone").getJSONObject().toString()));
                Thread.sleep(1000);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
