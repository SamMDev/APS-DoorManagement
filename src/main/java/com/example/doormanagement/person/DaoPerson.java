package com.example.doormanagement.person;

import com.example.doormanagement.api.LazyCriteria;
import com.example.doormanagement.chipcard.EntityChipCard;
import com.example.doormanagement.jdbi.*;
import org.apache.commons.lang3.tuple.Pair;
import org.jdbi.v3.core.Jdbi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DaoPerson extends BaseDao<EntityPerson> {

    @Autowired
    public DaoPerson(Jdbi jdbi) {
        super(EntityPerson.class, jdbi);
    }

    public Optional<JoinedEntity> getDataForPersonDetail(Long id) {
        return this.jdbi.withHandle(handle ->
                handle
                        .createQuery(
                                """
                                    SELECT * FROM PERSON
                                    LEFT JOIN CHIP_CARD CC on PERSON.ID = CC.PERSON_ID
                                    WHERE PERSON.ID = :id"""
                        )
                .bind("id", id)
                .reduceRows(
                        new JoinEntityRowReducer<>(
                                EntityPerson.class,
                                Pair.of(EntityChipCard.class, JoinedEntity.JoinType.ONE_TO_MANY)
                        )
                )
                .findFirst()
        );
    }


    public List<EntityPerson> loadByCriteria(LazyCriteria criteria) {
        final DaoFilterQueryBuilder queryBuilder = new DaoFilterQueryBuilderWithoutWhereStatement("SELECT * FROM PERSON");
        queryBuilder.buildAndAddLimitOffsetStatement(criteria.getLimit(), criteria.getOffset());

        return this.jdbi.withHandle(handle ->
                handle
                        .createQuery(queryBuilder.build())
                        .mapTo(EntityPerson.class)
                        .list()
        );
    }
}
