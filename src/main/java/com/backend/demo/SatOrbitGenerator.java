package com.backend.demo;
import com.backend.demo.data_model.OrbitDataModel;
import com.backend.demo.data_model.TTPos;
import org.json.JSONObject;

import java.sql.Timestamp;

public class SatOrbitGenerator {
    long time;
    double startLat=40;
    double startLon=10;
    double stopLat=50;
    double stopLon=20;
    double elevation;
    int pointNumber = 1000;
    double deltaLon = (stopLon-startLon)/pointNumber;
    double deltaLat = (stopLat-startLat)/pointNumber;


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
        OrbitDataModel orbitDataModel = new OrbitDataModel();
        orbitDataModel.setName(id);
        for (int i=0; i<10; i++){
            orbitDataModel.add(new TTPos(15.1, 40.1, 1000.0, 100000));
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
