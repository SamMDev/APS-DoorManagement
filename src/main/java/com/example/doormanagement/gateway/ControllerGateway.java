package com.example.doormanagement.gateway;

import com.example.doormanagement.api.LazyCriteria;
import com.example.doormanagement.api.LazyData;
import com.example.doormanagement.gateway.dto.response.GatewayDetail;
import com.example.doormanagement.gateway.dto.response.LazyGatewayDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gateway")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ControllerGateway {

    private final ServiceGateway serviceGateway;

    @PostMapping("/load")
    @Operation(description = "Lazy loads gateways for data table")
    public LazyData<LazyGatewayDto> load(@RequestBody LazyCriteria criteria) {
        return this.serviceGateway.load(criteria);
    }

    @GetMapping("/{id}")
    @Operation(description = "Get gateway detail by id")
    public GatewayDetail findDetail(@PathVariable("id") Long id) {
        return this.serviceGateway.findDetail(id);
    }
}
