package com.backend.demo.data_model;

import org.json.JSONException;
import org.json.JSONObject;

public class ChartOutputMessage {
    private String msg_type;
    private String x;
    private String y;


    public ChartOutputMessage(String msg_type, String x, String y) {
        this.msg_type = msg_type;
        this.x = x;
        this.y = y;
    }

    public ChartOutputMessage(JSONObject json_object) {
        this.x = json_object.getJSONObject("chart_msg").getString("msg_type");
        this.x = json_object.getJSONObject("chart_msg").getString("x");
        this.y = json_object.getJSONObject("chart_msg").getString("y");
    }

    public JSONObject getJSONObject() throws JSONException {
        JSONObject jo = new JSONObject();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg_type", this.msg_type);
        jsonObject.put("x", this.x);
        jsonObject.put("y", this.y);
        jo.put("chart_msg", jsonObject);
        return jo;
    }

}
