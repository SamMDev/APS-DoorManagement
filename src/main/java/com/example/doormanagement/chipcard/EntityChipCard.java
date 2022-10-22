package com.example.doormanagement.chipcard;

import com.example.doormanagement.jdbi.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter @Setter
@Entity
@Table(name = "CHIP_CARD")
public class EntityChipCard extends BaseEntity {

    @Column(name = "person_id", nullable = false)
    private Long personId;

    @Column(name = "SERIAL_NUMBER", nullable = false)
    private String serialNumber;
}
