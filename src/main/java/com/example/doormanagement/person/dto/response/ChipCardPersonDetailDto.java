package com.example.doormanagement.person.dto.response;

import com.example.doormanagement.chipcard.EntityChipCard;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChipCardPersonDetailDto {
    private Long chipCardId;
    private String chipCardSerialNumber;

    public ChipCardPersonDetailDto(EntityChipCard card) {
        this.chipCardId = card.getId();
        this.chipCardSerialNumber = card.getSerialNumber();
    }

}
