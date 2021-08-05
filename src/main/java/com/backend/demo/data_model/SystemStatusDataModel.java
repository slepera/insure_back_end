package com.backend.demo.data_model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.json.JSONException;

public class SystemStatusDataModel {
    String fiveGSignal;
    String satComSignal;
    String droneStatus;
    String subMarineStatus;
    long time;

    public SystemStatusDataModel(String fiveGSignal, String satComSignal, String droneStatus, String subMarineStatus, long time) {
        this.fiveGSignal = fiveGSignal;
        this.satComSignal = satComSignal;
        this.droneStatus = droneStatus;
        this.subMarineStatus = subMarineStatus;
        this.time = time;
    }

    public ObjectNode getJSONObject() throws JSONException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();
        objectNode.put("five_g_status", this.fiveGSignal);
        objectNode.put("satcom_status", this.satComSignal);
        objectNode.put("drone_status", this.droneStatus);
        objectNode.put("submarine_status", this.subMarineStatus);
        return objectNode;
    }
}
