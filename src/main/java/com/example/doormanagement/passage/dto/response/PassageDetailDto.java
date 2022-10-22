package com.example.doormanagement.passage.dto.response;

import com.example.doormanagement.chipcard.EntityChipCard;
import com.example.doormanagement.gateway.EntityGateway;
import com.example.doormanagement.jdbi.JoinedEntity;
import com.example.doormanagement.passage.EntityPassage;
import com.example.doormanagement.person.EntityPerson;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class PassageDetailDto {
    private Long passageId;
    private LocalDateTime time;

    private Long gatewayId;
    private String gatewayCode;
    private String gatewayName;

    private Long chipCardId;
    private String chipCardSerialNumber;

    private Long personId;
    private String personName;

    public PassageDetailDto(JoinedEntity joined) {
        final EntityPassage passage = joined.getJoinedOne(EntityPassage.class);
        final EntityGateway gateway = joined.getJoinedOne(EntityGateway.class);
        final EntityChipCard card = joined.getJoinedOne(EntityChipCard.class);
        final EntityPerson person = joined.getJoinedOne(EntityPerson.class);

        this.passageId = passage.getId();
        this.time = passage.getTime();

        this.gatewayId = gateway.getId();
        this.gatewayCode = gateway.getCode();
        this.gatewayName = gateway.getName();

        this.chipCardId = card.getId();
        this.chipCardSerialNumber = card.getSerialNumber();

        this.personId = person.getId();
        this.personName = person.getName();
    }
}
