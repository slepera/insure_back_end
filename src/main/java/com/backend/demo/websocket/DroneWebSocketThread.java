package com.backend.demo.websocket;

import com.backend.demo.data_model.ChartOutputMessage;
import com.backend.demo.data_model.DroneOutputMessage;
import io.jenetics.jpx.GPX;
import io.jenetics.jpx.Track;
import io.jenetics.jpx.TrackSegment;
import io.jenetics.jpx.WayPoint;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.stream.Stream;

public class DroneWebSocketThread extends Thread{
    private final WebSocketSession webSocketSession;
    private boolean isSending = false;
    DroneWebSocketThread(WebSocketSession webSocketSession)
    {
        this.webSocketSession = webSocketSession;
    }


    @Override
    public void run() {
        Stream<Track> trackStream;

        Stream<TrackSegment> segmentStream;

        Stream<WayPoint> wayPointStream = null;

        WayPoint[] wayPointsArray = null;

        int count = 0;

        try {
            trackStream = GPX.reader(GPX.Version.V10).read("./data/6.gpx").tracks();

            segmentStream = trackStream.flatMap(Track::segments);

            wayPointStream = segmentStream.flatMap(TrackSegment::points);

            wayPointsArray = wayPointStream.toArray(WayPoint[]::new);

        } catch (IOException e) {
            e.printStackTrace();
        }
        while(true)
        {
            int size = wayPointsArray.length;
            while(count<size && isSending){
                try {
                    this.webSocketSession.sendMessage(new TextMessage(
                            new DroneOutputMessage(String.valueOf(wayPointsArray[count].getLatitude().toDegrees()), String.valueOf(wayPointsArray[count].getLongitude().toDegrees()), String.valueOf(wayPointsArray[count].getElevation().get().doubleValue()), String.valueOf(wayPointsArray[count].getTime().get().toEpochSecond())).getJSONObject().toString()));
                    Thread.sleep(1000);
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
                count++;
            }
            if (count == size){
                count = 0;
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
