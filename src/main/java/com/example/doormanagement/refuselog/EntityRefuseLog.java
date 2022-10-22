package com.example.doormanagement.refuselog;

import com.example.doormanagement.jdbi.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter @Setter

@Entity
@Table(name = "REFUSE_LOG")
@AllArgsConstructor @NoArgsConstructor
public class EntityRefuseLog extends BaseEntity {

    @Column(name = "TIME", nullable = false)
    private LocalDateTime time;

    @Column(name = "CHIP_CARD_SERIAL_NUMBER", nullable = false)
    private String chipCardSerialNumber;

    @Column(name = "GATEWAY_ID", nullable = false)
    private Long gatewayId;

    @Column(name = "CAUSE", nullable = false)
    @Enumerated(EnumType.STRING)
    private Cause cause;

    public EntityRefuseLog(String chipCardSerialNumber, Long gatewayId, Cause cause) {
        this.time = LocalDateTime.now();
        this.chipCardSerialNumber = chipCardSerialNumber;
        this.gatewayId = gatewayId;
        this.cause = cause;
    }
}
