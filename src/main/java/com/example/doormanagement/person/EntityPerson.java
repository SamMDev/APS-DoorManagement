package com.example.doormanagement.person;

import com.example.doormanagement.jdbi.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter @Setter

@Entity
@Table(name = "PERSON")
public class EntityPerson extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

}
