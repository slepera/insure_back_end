package com.backend.demo.websocket;

import com.backend.demo.SatOrbitGenerator;
import com.backend.demo.data_model.ChartOutputMessage;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

@Component
public class ChartWebSocketHandler extends TextWebSocketHandler {
    ChartWebSocketThread chartWebSocketThread;
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        this.chartWebSocketThread = new ChartWebSocketThread(session);
        this.chartWebSocketThread.start();

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
            super.afterConnectionClosed(session, status);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        if(message.getPayload().equals("start"))
        {
            this.chartWebSocketThread.send(true);
        }
        if(message.getPayload().equals("stop"))
        {
            this.chartWebSocketThread.send(false);
        }

    }


}