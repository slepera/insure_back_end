package com.backend.demo;
import com.backend.demo.data_model.OrbitDataModel;
import com.backend.demo.data_model.TTPos;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;

public class SatOrbitGenerator {



    /*public void createOrbit(){
        double lat = startLat;
        double lon = startLon;
        time = System.currentTimeMillis();
        JSONObject jsonString = new JSONObject();
        for(int i=0; i < pointNumber; i++ ){
            lat += deltaLat;
            lon += deltaLon;
            time += 1000;

            jsonString.put("lat", lat)
                    .put("lon", lon)
                    .put("time", time);
        }
        System.out.println(jsonString);
    }
*/

    public static OrbitDataModel CalculatePositionSamples(String id) {
        double startLat=37;
        double startLon=5;
        double stopLat=45;
        double stopLon=19;
        double elevation = 600000.0;
        long time = System.currentTimeMillis()/1000;
        int duration = 1000;
        int samplingPeriod = 1;

        double deltaLon = (stopLon-startLon)/(duration/samplingPeriod);
        double deltaLat = (stopLat-startLat)/(duration/samplingPeriod);
        double lat = startLat;
        double lon = startLon;

        OrbitDataModel orbitDataModel = new OrbitDataModel();
        orbitDataModel.setName(id);
        for(int i=0; i < duration/samplingPeriod; i++ ){
            lat += deltaLat;
            lon += deltaLon;
            time += samplingPeriod;
            orbitDataModel.add(new TTPos(lat, lon, elevation, time));
        }
        return orbitDataModel;
    }

    public static OrbitDataModel CalculatePositionSamplesFromEphemeris(String id) {
        long time = System.currentTimeMillis()/1000;
        OrbitDataModel orbitDataModel = new OrbitDataModel();
        orbitDataModel.setName(id);
        try (BufferedReader br = new BufferedReader(new FileReader("./data/cod06495.eph"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String after = line.trim().replaceAll(" +", " ");
                String[] tmp = after.split(" ");
                if((tmp[0].equals("P"))&&(tmp[1].equals(id)))
                {
                    double x = Double.valueOf(tmp[2]);
                    double y = Double.valueOf(tmp[3]);
                    double z = Double.valueOf(tmp[4]);
                    orbitDataModel.add(new TTPos(x, y, z, time));
                    time += 900;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return orbitDataModel;
    }


}
