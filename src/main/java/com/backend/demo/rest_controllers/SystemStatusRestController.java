package com.backend.demo.rest_controllers;


import com.backend.demo.SatOrbitGenerator;
import com.backend.demo.data_model.SystemStatus;
import com.backend.demo.data_model.SystemStatusDataModel;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/system_status")
public class SystemStatusRestController {
    @GetMapping
    private ObjectNode getOrbit() {
        int fiveg = (int) (Math.random()*3);
        int satcom = (int) (Math.random()*3);
        if(fiveg==0)
        {
            SystemStatus.fiveGStatus = "NO_SIG";
        }
        else if (fiveg == 1)
        {
            SystemStatus.fiveGStatus = "SIG_LOW";
        }
        else
        {
            SystemStatus.fiveGStatus = "SIG_OK";
        }
        if(satcom==0)
        {
            SystemStatus.satcomStatus = "NO_SIG";
        }
        else if (satcom == 1)
        {
            SystemStatus.satcomStatus = "SIG_LOW";
        }
        else
        {
            SystemStatus.satcomStatus = "SIG_OK";
        }
        return new SystemStatusDataModel(
                SystemStatus.fiveGStatus,
                SystemStatus.satcomStatus,
                SystemStatus.droneStatus,
                SystemStatus.droneStatus,
                System.currentTimeMillis()).getJSONObject();
    }
}
