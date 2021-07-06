package com.backend.demo.data_model;

import org.json.JSONException;
import org.json.JSONObject;

public class ChartOutputMessage {
    private String x;
    private String y;


    public ChartOutputMessage(String x, String y) {
        this.x = x;
        this.y = y;
    }

    public ChartOutputMessage(JSONObject json_object) {
        this.x = json_object.getJSONObject("msg").getString("x");
        this.y = json_object.getJSONObject("msg").getString("y");
    }

    public JSONObject getJSONObject() throws JSONException {
        JSONObject jo = new JSONObject();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("x", this.x);
        jsonObject.put("y", this.y);
        jo.put("msg", jsonObject);
        return jo;
    }

}
