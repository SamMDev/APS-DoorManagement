package com.example.doormanagement.chipcard;

import com.example.doormanagement.api.LazyCriteria;
import com.example.doormanagement.jdbi.*;
import com.example.doormanagement.person.EntityPerson;
import org.apache.commons.lang3.tuple.Pair;
import org.jdbi.v3.core.Jdbi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DaoChipCard extends BaseDao<EntityChipCard> {

    @Autowired
    public DaoChipCard(Jdbi jdbi) {
        super(EntityChipCard.class, jdbi);
    }

    public Optional<EntityChipCard> findForSerialNumber(String number) {
        return
                this.jdbi.withHandle(handle ->
                        handle
                                .createQuery("SELECT * FROM CHIP_CARD cp WHERE :number = SERIAL_NUMBER LIMIT 1")
                                .bind("number", number)
                                .mapTo(EntityChipCard.class)
                                .findOne()
        );
    }

    public List<JoinedEntity> load(LazyCriteria criteria) {
        final DaoFilterQueryBuilder queryBuilder = new DaoFilterQueryBuilderWithoutWhereStatement(
                """
                        SELECT * FROM CHIP_CARD cc
                        JOIN PERSON p ON cc.PERSON_ID = p.ID"""
        );
        queryBuilder.buildAndAddLimitOffsetStatement(criteria.getLimit(), criteria.getOffset());

        return this.jdbi.withHandle(handle ->
                handle
                        .createQuery(queryBuilder.build())
                        .reduceRows(
                                new JoinEntityRowReducer<>(
                                        EntityChipCard.class,
                                        Pair.of(EntityPerson.class, JoinedEntity.JoinType.ONE_TO_ONE)
                                )
                        )
                        .toList()
        );
    }

    public Optional<JoinedEntity> findChipCardDetail(Long chipCardId) {
        return this.jdbi.withHandle(handle ->
                handle
                        .createQuery("""
                                    SELECT * FROM CHIP_CARD cc
                                    JOIN PERSON P2 on P2.ID = cc.PERSON_ID
                                    WHERE cc.ID = :id""")
                        .bind("id", chipCardId)
                        .reduceRows(
                                new JoinEntityRowReducer<>(
                                        EntityChipCard.class,
                                        Pair.of(EntityPerson.class, JoinedEntity.JoinType.ONE_TO_ONE)
                                )
                        )
                        .findFirst()
        );
    }
}
