package com.example.doormanagement.passage.dto.response;

import com.example.doormanagement.gateway.EntityGateway;
import com.example.doormanagement.jdbi.JoinedEntity;
import com.example.doormanagement.passage.EntityPassage;
import com.example.doormanagement.person.EntityPerson;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class LazyDataPassageModel {
    private Long passageId;
    private LocalDateTime time;
    private String gatewayCode;
    private String gatewayName;
    private String personName;

    public LazyDataPassageModel(JoinedEntity joined) {
        final EntityPassage passage = joined.getJoinedOne(EntityPassage.class);
        final EntityGateway gateway = joined.getJoinedOne(EntityGateway.class);
        final EntityPerson person = joined.getJoinedOne(EntityPerson.class);

        this.passageId = passage.getId();
        this.time = passage.getTime();
        this.gatewayCode = gateway.getCode();
        this.gatewayName = gateway.getName();
        this.personName = person.getName();
    }
}
