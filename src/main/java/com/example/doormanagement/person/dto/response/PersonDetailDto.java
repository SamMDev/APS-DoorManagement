package com.example.doormanagement.person.dto.response;


import com.example.doormanagement.chipcard.EntityChipCard;
import com.example.doormanagement.jdbi.JoinedEntity;
import com.example.doormanagement.person.EntityPerson;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonDetailDto {
    private Long id;
    private String name;

    private Set<ChipCardPersonDetailDto> chipCards;

    public PersonDetailDto(JoinedEntity joined) {
        final EntityPerson person = joined.getJoinedOne(EntityPerson.class);
        final Set<EntityChipCard> personChipCard = joined.getJoinedMany(EntityChipCard.class);

        this.id = person.getId();
        this.name = person.getName();
        this.chipCards = personChipCard.stream().map(ChipCardPersonDetailDto::new).collect(Collectors.toSet());
    }
}
