package com.backend.demo;
import com.backend.demo.data_model.OrbitDataModel;
import com.backend.demo.data_model.TTPos;
import org.json.JSONObject;

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
        double startLat=30;
        double startLon=5;
        double stopLat=60;
        double stopLon=25;
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
        //System.out.println(orbitDataModel);
        return orbitDataModel;
    }

//        var deltaStep = duration / (intervalCount > 0 ? intervalCount : 1);
//
//        var delta = {
//                lon: (endPoint.lon - point.lon) / intervalCount,
//                lat: (endPoint.lat - point.lat) / intervalCount
//    };
//
//        for (var since = 0; since <= duration; since += deltaStep) {
//            this.property.addSample(
//                    Cesium.JulianDate.addSeconds(startTime, since, new Cesium.JulianDate()),
//                    Cesium.Cartesian3.fromDegrees(point.lon += delta.lon, point.lat += delta.lat, 100000.0)
//            );
//            this.property_2.addSample(
//                    Cesium.JulianDate.addSeconds(startTime, since, new Cesium.JulianDate()),
//                    Cesium.Cartesian3.fromDegrees(point.lon, point.lat, 50000.0)
//            );
//        }
//        return;
//    }
}
