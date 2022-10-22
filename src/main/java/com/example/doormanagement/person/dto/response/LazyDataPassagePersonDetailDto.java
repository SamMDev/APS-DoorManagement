package com.example.doormanagement.person.dto.response;

import com.example.doormanagement.gateway.EntityGateway;
import com.example.doormanagement.jdbi.JoinedEntity;
import com.example.doormanagement.passage.EntityPassage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class LazyDataPassagePersonDetailDto {
    private Long passageId;
    private LocalDateTime time;

    private Long gatewayId;
    private String gatewayCode;
    private String gatewayName;

    public LazyDataPassagePersonDetailDto(JoinedEntity joined) {
        final EntityPassage passage = joined.getJoinedOne(EntityPassage.class);
        final EntityGateway gateway = joined.getJoinedOne(EntityGateway.class);

        this.passageId = passage.getId();
        this.time = passage.getTime();

        this.gatewayId = gateway.getId();
        this.gatewayCode = gateway.getCode();
        this.gatewayName = gateway.getName();
    }
}
