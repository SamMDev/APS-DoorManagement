package com.example.doormanagement.passage;

import com.example.doormanagement.jdbi.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter @Setter

@Entity
@Table(name = "PASSAGE")
@NoArgsConstructor @AllArgsConstructor
public class EntityPassage extends BaseEntity {

    @Column(name = "GATEWAY_ID", nullable = false)
    private Long gatewayId;

    @Column(name = "CHIP_CARD_ID", nullable = false)
    private Long chipCardId;

    @Column(name = "TIME", nullable = false)
    private LocalDateTime time;

}
