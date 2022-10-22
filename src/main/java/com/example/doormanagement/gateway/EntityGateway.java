package com.example.doormanagement.gateway;

import com.example.doormanagement.jdbi.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter @Setter

@Entity
@Table(name = "GATEWAY")
public class EntityGateway extends BaseEntity {

    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

}
