package com.backend.demo.websocket;

import com.backend.demo.data_model.ChartOutputMessage;
import com.sun.management.OperatingSystemMXBean;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.lang.management.ManagementFactory;

public class ChartWebSocketThread extends Thread{
    private final WebSocketSession webSocketSession;
    private boolean isSending = false;
    ChartWebSocketThread(WebSocketSession webSocketSession)
    {
        this.webSocketSession = webSocketSession;
    }
    @Override
    public void run() {
        OperatingSystemMXBean bean = (com.sun.management.OperatingSystemMXBean) ManagementFactory
                .getOperatingSystemMXBean();

        int cpu_usage = 0;
        long mem_usage = 0;


        while (true) {
            while(isSending){

                cpu_usage = (int) (bean.getSystemCpuLoad()*100);
                mem_usage =  bean.getTotalPhysicalMemorySize()-bean.getFreePhysicalMemorySize();
                try {
                    this.webSocketSession.sendMessage(new TextMessage(
                            new ChartOutputMessage("cpu", String.valueOf(System.currentTimeMillis()), String.valueOf(cpu_usage)).getJSONObject().toString()));
                    Thread.sleep(500);
                    this.webSocketSession.sendMessage(new TextMessage(
                            new ChartOutputMessage("mem", String.valueOf(System.currentTimeMillis()), String.valueOf(mem_usage).substring(0,2)).getJSONObject().toString()));
                    Thread.sleep(500);
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }
    public void send(boolean b) {
        this.isSending = b;
    }

}
