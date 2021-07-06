package com.backend.demo.websocket;

import com.backend.demo.data_model.ChartOutputMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

public class ChartWebSocketThread extends Thread{
    private final WebSocketSession webSocketSession;

    ChartWebSocketThread(WebSocketSession webSocketSession)
    {
        this.webSocketSession = webSocketSession;
    }
    @Override
    public void run() {
        while (true) {
            try {
                this.webSocketSession.sendMessage(new TextMessage(
                        new ChartOutputMessage(String.valueOf(System.currentTimeMillis()), "203").getJSONObject().toString()));
                Thread.sleep(1000);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
