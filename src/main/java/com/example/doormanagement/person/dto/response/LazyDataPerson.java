package com.example.doormanagement.person.dto.response;

import com.example.doormanagement.person.EntityPerson;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class LazyDataPerson {
    private Long id;
    private String name;

    public LazyDataPerson(EntityPerson person) {
        this.id = person.getId();
        this.name = person.getName();
    }
}
