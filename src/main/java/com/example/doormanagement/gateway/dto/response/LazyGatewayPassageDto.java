package com.example.doormanagement.gateway.dto.response;

import com.example.doormanagement.chipcard.EntityChipCard;
import com.example.doormanagement.jdbi.JoinedEntity;
import com.example.doormanagement.passage.EntityPassage;
import com.example.doormanagement.person.EntityPerson;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LazyGatewayPassageDto {

    private Long id;
    private LocalDateTime time;

    private Long chipCardId;
    private String chipCardSerialNumber;

    private Long personId;
    private String personName;

    public LazyGatewayPassageDto(JoinedEntity joined) {
        final EntityPassage passage = joined.getJoinedOne(EntityPassage.class);
        final EntityChipCard chipCard = joined.getJoinedOne(EntityChipCard.class);
        final EntityPerson person = joined.getJoinedOne(EntityPerson.class);

        this.id = passage.getId();
        this.time = passage.getTime();

        this.chipCardId = chipCard.getId();
        this.chipCardSerialNumber = chipCard.getSerialNumber();

        this.personId = person.getId();
        this.personName = person.getName();
    }
}
