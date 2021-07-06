package com.backend.demo.websocket;

import com.backend.demo.data_model.ChartOutputMessage;
import com.backend.demo.data_model.DroneOutputMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

public class DroneWebSocketThread extends Thread{
    private final WebSocketSession webSocketSession;
    private boolean isSending = false;
    DroneWebSocketThread(WebSocketSession webSocketSession)
    {
        this.webSocketSession = webSocketSession;
    }


    @Override
    public void run() {
        while(true)
        {
            while (isSending) {
                try {
                    this.webSocketSession.sendMessage(new TextMessage(
                            new DroneOutputMessage("242", "203", "drone", "drone").getJSONObject().toString()));
                    Thread.sleep(1000);
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public void send(boolean b) {
        this.isSending = b;
    }
}
