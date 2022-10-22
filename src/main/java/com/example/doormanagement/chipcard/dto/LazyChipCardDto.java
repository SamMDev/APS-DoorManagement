package com.example.doormanagement.chipcard.dto;

import com.example.doormanagement.chipcard.EntityChipCard;
import com.example.doormanagement.jdbi.JoinedEntity;
import com.example.doormanagement.person.EntityPerson;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LazyChipCardDto {
    private Long id;
    private String serialNumber;
    private String ownerName;

    public LazyChipCardDto(JoinedEntity joined) {
        final EntityChipCard chipCard = joined.getJoinedOne(EntityChipCard.class);
        final EntityPerson entityPerson = joined.getJoinedOne(EntityPerson.class);

        this.id = chipCard.getId();
        this.serialNumber = chipCard.getSerialNumber();
        this.ownerName = entityPerson.getName();
    }
}
