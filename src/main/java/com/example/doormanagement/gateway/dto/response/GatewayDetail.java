package com.example.doormanagement.gateway.dto.response;

import com.example.doormanagement.gateway.EntityGateway;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class GatewayDetail {

    private Long id;
    private String code;
    private String name;
    private String description;

    public GatewayDetail(EntityGateway gateway) {
        this.id = gateway.getId();
        this.code = gateway.getCode();
        this.name = gateway.getName();
        this.description = gateway.getDescription();
    }

}
