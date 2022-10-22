package com.example.doormanagement.refuselog;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServiceRefuseLog {

    private final DaoRefuseLog daoRefuseLog;

    public void logChipCardNotFound(String chipCardSerialNumber, Long gatewayId) {
        this.log(chipCardSerialNumber, gatewayId, Cause.CARD_NOT_EXISTS);
    }

    public void log(String chipCardSerialNumber, Long gatewayId, Cause cause) {
        this.daoRefuseLog.insert(new EntityRefuseLog(chipCardSerialNumber, gatewayId, cause));
    }
}
