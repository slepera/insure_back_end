package com.backend.demo.rest_controllers;


import com.backend.demo.SatOrbitGenerator;
import com.backend.demo.data_model.DroneOutputMessage;
import com.backend.demo.data_model.OrbitDataModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.TextMessage;

@CrossOrigin
@RestController
@RequestMapping("/orbit")
public class OrbitRestController {
    @GetMapping("/{id}")
    private ObjectNode getOrbit(@PathVariable("id") String id) {
        if(id.equals("0"))
        {
            return SatOrbitGenerator.CalculatePositionSamples(id).getJSONObject();
        }
        else
        {
            System.out.println("Questa è una stampa di prova");

            return SatOrbitGenerator.CalculatePositionSamplesFromEphemeris(id).getJSONObjectECEF();
        }
    }
}
