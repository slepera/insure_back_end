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
        this.x = json_object.getJSONObject("chart_msg").getString("x");
        this.y = json_object.getJSONObject("chart_msg").getString("y");
    }

    public JSONObject getJSONObject() throws JSONException {
        JSONObject jo = new JSONObject();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("x", this.x);
        jsonObject.put("y", this.y);
        jo.put("chart_msg", jsonObject);
        return jo;
    }

}
