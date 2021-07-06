package com.backend.demo.data_model;

import org.json.JSONException;
import org.json.JSONObject;

public class DroneOutputMessage {
    private String lat;
    private String lon;
    private String time;
    private String alt;

    public DroneOutputMessage(String lat, String lon, String alt, String time) {
        this.lat = lat;
        this.lon = lon;
        this.lon = alt;
        this.time = time;
    }

    public DroneOutputMessage(JSONObject json_object) {
        this.lat = json_object.getJSONObject("msg").getString("lat");
        this.lon = json_object.getJSONObject("msg").getString("lon");
        this.alt = json_object.getJSONObject("msg").getString("alt");
        this.time = json_object.getJSONObject("msg").getString("time");
    }

    public JSONObject getJSONObject() throws JSONException {
        JSONObject jo = new JSONObject();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("lat", this.lat);
        jsonObject.put("lon", this.lon);
        jsonObject.put("alt", this.alt);
        jsonObject.put("time", this.time);
        jo.put("msg", jsonObject);
        return jo;
    }

}
