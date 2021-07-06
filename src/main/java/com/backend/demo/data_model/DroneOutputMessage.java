package com.backend.demo.data_model;

import org.json.JSONException;
import org.json.JSONObject;

public class DroneOutputMessage {
    private String x;
    private String y;
    private String time;

    public DroneOutputMessage(String x, String y, String time) {
        this.x = x;
        this.y = y;
        this.time = time;
    }

    public DroneOutputMessage(JSONObject json_object) {
        this.x = json_object.getJSONObject("msg").getString("x");
        this.y = json_object.getJSONObject("msg").getString("y");
        this.time = json_object.getJSONObject("msg").getString("time");
    }

    public JSONObject getJSONObject() throws JSONException {
        JSONObject jo = new JSONObject();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("x", this.x);
        jsonObject.put("y", this.y);
        jsonObject.put("time", this.time);
        jo.put("msg", jsonObject);
        return jo;
    }

}
