package com.example.doormanagement.gateway.dto.response;

import com.example.doormanagement.gateway.EntityGateway;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class LazyGatewayDto {
    private Long id;
    private String code;
    private String name;

    public LazyGatewayDto(EntityGateway gateway) {
        this.id = gateway.getId();
        this.code = gateway.getCode();
        this.name = gateway.getName();
    }
}
