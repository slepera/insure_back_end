package com.backend.demo.data_model;

import com.backend.demo.SatOrbitGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/*{"orbit":{
      "name": "sat1",
      "positions": [{"lat": lat, "lon": lon, "ele": elevation, "time": time}]
  }}*/

public class OrbitDataModel {
    private String name;
    private ArrayList<TTPos> ttPosArray;

    public OrbitDataModel() {
        this.ttPosArray = new ArrayList<TTPos>();
    }

    public void setName(String name){
        this.name = name;
    }

    public void add (TTPos ttPos){
        this.ttPosArray.add(ttPos);
    }

    public ObjectNode getJSONObjectECEF() throws JSONException {
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode positions = mapper.createArrayNode();
        for (int i = 0; i<this.ttPosArray.size(); i++){
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("x", this.ttPosArray.get(i).getLat());
            objectNode.put("y", this.ttPosArray.get(i).getLon());
            objectNode.put("z", this.ttPosArray.get(i).getEle());
            objectNode.put("time", this.ttPosArray.get(i).getTime());
            positions.add(objectNode);
        }
        ObjectNode rootNode = mapper.createObjectNode();
        rootNode.put("name", this.name);
        rootNode.put("positions", positions);
        return rootNode;
    }

    public ObjectNode getJSONObject() throws JSONException {
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode positions = mapper.createArrayNode();
        for (int i = 0; i<this.ttPosArray.size(); i++){
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("lat", this.ttPosArray.get(i).getLat());
            objectNode.put("lon", this.ttPosArray.get(i).getLon());
            objectNode.put("ele", this.ttPosArray.get(i).getEle());
            objectNode.put("time", this.ttPosArray.get(i).getTime());
            positions.add(objectNode);
        }
        ObjectNode rootNode = mapper.createObjectNode();
        rootNode.put("name", this.name);
        rootNode.put("positions", positions);
        return rootNode;
    }

}