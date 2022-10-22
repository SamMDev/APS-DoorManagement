package com.example.doormanagement.passage.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class RequestSavePassageDto {
    private String chipCardSerialNumber;
    private String gatewayCode;
}
