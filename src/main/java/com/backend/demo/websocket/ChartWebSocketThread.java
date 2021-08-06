package com.backend.demo.websocket;

import com.backend.demo.data_model.ChartOutputMessage;
import com.backend.demo.services.Utility;
import com.sun.management.OperatingSystemMXBean;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

public class ChartWebSocketThread extends Thread{
    private final WebSocketSession webSocketSession;
    private boolean isSending = false;

    ChartWebSocketThread(WebSocketSession webSocketSession)
    {
        this.webSocketSession = webSocketSession;
    }
    @Override
    public void run() {
        int ARRAY_SIZE = 10;

        double air_temperature[];
        double air_humidity[];
        double air_wind[];
        double water_temperature[];
        double water_salinity[];
        double water_ph[];

        air_temperature = new double[ARRAY_SIZE];
        air_humidity = new double[ARRAY_SIZE];
        air_wind = new double[ARRAY_SIZE];
        water_temperature = new double[ARRAY_SIZE];
        water_salinity = new double[ARRAY_SIZE];
        water_ph = new double[ARRAY_SIZE];
        int i = 0;
        int sign = 1;


        Utility.GenerateArray(air_temperature, 23, 27);
        Utility.GenerateArray(air_humidity, 69, 75);
        Utility.GenerateArray(air_wind, 11, 19);
        Utility.GenerateArray(water_temperature, 20, 22);
        Utility.GenerateArray(water_salinity, 3.2, 3.6);
        Utility.GenerateArray(water_ph, 5, 7);
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.GERMANY);
        otherSymbols.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("#.##", otherSymbols);

        while (true) {
            while(isSending)
            {
                try {
                    this.webSocketSession.sendMessage(new TextMessage(
                            new ChartOutputMessage("air_temperature", String.valueOf(System.currentTimeMillis()), df.format(air_temperature[i])).getJSONObject().toString()));
                    Thread.sleep(500);
                    this.webSocketSession.sendMessage(new TextMessage(
                            new ChartOutputMessage("air_humidity", String.valueOf(System.currentTimeMillis()), df.format(air_humidity[i])).getJSONObject().toString()));
                    Thread.sleep(500);
                    this.webSocketSession.sendMessage(new TextMessage(
                            new ChartOutputMessage("air_wind", String.valueOf(System.currentTimeMillis()), df.format(air_wind[i])).getJSONObject().toString()));
                    Thread.sleep(500);
                    this.webSocketSession.sendMessage(new TextMessage(
                            new ChartOutputMessage("water_temperature", String.valueOf(System.currentTimeMillis()), df.format(water_temperature[i])).getJSONObject().toString()));
                    Thread.sleep(500);
                    this.webSocketSession.sendMessage(new TextMessage(
                            new ChartOutputMessage("water_salinity", String.valueOf(System.currentTimeMillis()), df.format(water_salinity[i])).getJSONObject().toString()));
                    Thread.sleep(500);
                    this.webSocketSession.sendMessage(new TextMessage(
                            new ChartOutputMessage("water_ph", String.valueOf(System.currentTimeMillis()), df.format(water_ph[i])).getJSONObject().toString()));
                    Thread.sleep(500);
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
                if(((i == 0)&&(sign == -1)) || (i == ARRAY_SIZE-1))
                {
                    sign = sign * -1;
                }
                i = i + (1 * sign);
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
