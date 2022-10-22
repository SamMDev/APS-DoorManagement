package com.example.doormanagement.arduino;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/arduino-gateway")
@RequiredArgsConstructor
public class ControllerArduinoGateway {

    private final ServiceArduinoGateway serviceArduinoGateway;

    @GetMapping("/process/{serialInput}")
    @Operation(description = "Processes request for passage, saves, determines if passage is approved of not")
    public boolean processArduinoPassageRequest(@PathVariable("serialInput") String serialInput) {
        return this.serviceArduinoGateway.processArduinoPassageRequest(serialInput);
    }


}
