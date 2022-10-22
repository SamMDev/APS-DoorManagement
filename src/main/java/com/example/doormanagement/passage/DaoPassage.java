package com.example.doormanagement.passage;

import com.example.doormanagement.api.LazyCriteria;
import com.example.doormanagement.chipcard.EntityChipCard;
import com.example.doormanagement.gateway.EntityGateway;
import com.example.doormanagement.jdbi.*;
import com.example.doormanagement.person.EntityPerson;
import org.apache.commons.lang3.tuple.Pair;
import org.jdbi.v3.core.Jdbi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DaoPassage extends BaseDao<EntityPassage> {

    @Autowired
    public DaoPassage(Jdbi jdbi) {
        super(EntityPassage.class, jdbi);
    }

    public List<JoinedEntity> loadLazyPassageForTable(LazyCriteria criteria) {
        final DaoFilterQueryBuilder passageLazyQueryBuilder = new DaoFilterQueryBuilderWithoutWhereStatement(
                """
                        SELECT * FROM PASSAGE p
                        JOIN GATEWAY g ON p.GATEWAY_ID = g.ID
                        JOIN CHIP_CARD cc ON p.CHIP_CARD_ID = cc.ID
                        JOIN PERSON pe ON cc.PERSON_ID = pe.ID"""
        );
        passageLazyQueryBuilder.buildAndAddLimitOffsetStatement(criteria.getLimit(), criteria.getOffset());
        passageLazyQueryBuilder.buildAndAddOrderByStatement(DaoFilterQueryBuilder.OrderType.DESC, "p.TIME");

        return this.jdbi.withHandle(handle ->
                handle
                        .createQuery(passageLazyQueryBuilder.build())
                        .reduceRows(
                            new JoinEntityRowReducer<>(
                                    EntityPassage.class,
                                    Pair.of(EntityGateway.class, JoinedEntity.JoinType.ONE_TO_ONE),
                                    Pair.of(EntityChipCard.class, JoinedEntity.JoinType.ONE_TO_ONE),
                                    Pair.of(EntityPerson.class, JoinedEntity.JoinType.ONE_TO_ONE)
                            )
                        )
                        .toList()

        );
    }

    public Optional<JoinedEntity> loadPassageDetail(Long id) {
        return this.jdbi.withHandle(handle ->
                handle
                        .createQuery(
                                """
                                    SELECT * FROM PASSAGE p
                                    JOIN GATEWAY g ON p.GATEWAY_ID = g.ID
                                    JOIN CHIP_CARD cc ON p.CHIP_CARD_ID = cc.ID
                                    JOIN PERSON pe ON cc.PERSON_ID = pe.ID
                                    WHERE p.ID = :id"""
                        )
                        .bind("id", id)
                        .reduceRows(
                                new JoinEntityRowReducer<>(
                                        EntityPassage.class,
                                        Pair.of(EntityGateway.class, JoinedEntity.JoinType.ONE_TO_ONE),
                                        Pair.of(EntityChipCard.class, JoinedEntity.JoinType.ONE_TO_ONE),
                                        Pair.of(EntityPerson.class, JoinedEntity.JoinType.ONE_TO_ONE)
                                )
                        )
                        .findFirst()
        );
    }

    public List<JoinedEntity> loadPassagesForPersonDetail(LazyCriteria criteria, Long personId) {
        final DaoFilterQueryBuilder daoFilterQueryBuilder = new DaoFilterQueryBuilderWithoutWhereStatement(
                """
                            SELECT p.*, g.* FROM PASSAGE p
                            JOIN GATEWAY g on p.GATEWAY_ID = g.ID
                            JOIN CHIP_CARD CC on CC.ID = p.CHIP_CARD_ID
                            JOIN PERSON pe on CC.PERSON_ID = pe.ID
                            WHERE pe.ID = :personId"""
        );
        daoFilterQueryBuilder.buildAndAddLimitOffsetStatement(criteria.getLimit(), criteria.getOffset());
        daoFilterQueryBuilder.buildAndAddOrderByStatement(DaoFilterQueryBuilder.OrderType.DESC, "p.TIME");

        return this.jdbi.withHandle(handle ->
                handle.createQuery(daoFilterQueryBuilder.build())
                .bind("personId", personId)
                .reduceRows(
                        new JoinEntityRowReducer<>(
                                EntityPassage.class,
                                Pair.of(EntityGateway.class, JoinedEntity.JoinType.ONE_TO_ONE)
                        )
                )
                .toList()
        );
    }

    public Long countPassagesForPerson(Long personId) {
        return this.jdbi.withHandle(handle ->
                handle
                        .createQuery("""
                            SELECT count(*) FROM PASSAGE p
                            JOIN CHIP_CARD CC on CC.ID = p.CHIP_CARD_ID
                            JOIN PERSON pe on CC.PERSON_ID = pe.ID
                            WHERE pe.ID = :personId""")
                        .bind("personId", personId)
                        .mapTo(Long.class)
                        .one()
        );
    }

    public List<JoinedEntity> loadForGateway(Long gatewayId, LazyCriteria criteria) {
        final DaoFilterQueryBuilder queryBuilder = new DaoFilterQueryBuilderWithoutWhereStatement(
                """
                    SELECT * FROM PASSAGE p
                    JOIN CHIP_CARD cc ON p.CHIP_CARD_ID = cc.ID
                    JOIN PERSON pe ON cc.PERSON_ID = pe.id
                    WHERE p.GATEWAY_ID = :gatewayId"""
        );
        queryBuilder.buildAndAddLimitOffsetStatement(criteria.getLimit(), criteria.getOffset());
        queryBuilder.buildAndAddOrderByStatement(DaoFilterQueryBuilder.OrderType.DESC, "p.TIME");

        return this.jdbi.withHandle(handle ->
                handle
                        .createQuery(queryBuilder.build())
                        .bind("gatewayId", gatewayId)
                        .reduceRows(
                                new JoinEntityRowReducer<>(
                                        EntityPassage.class,
                                        Pair.of(EntityChipCard.class, JoinedEntity.JoinType.ONE_TO_ONE),
                                        Pair.of(EntityPerson.class, JoinedEntity.JoinType.ONE_TO_ONE)
                                )
                        )
                        .toList()
        );
    }

    public Long countForGateway(Long gatewayId) {
        return this.jdbi.withHandle(handle ->
                handle
                        .createQuery("SELECT count(*) FROM PASSAGE p WHERE p.GATEWAY_ID = :id")
                        .bind("id", gatewayId)
                        .mapTo(Long.class)
                        .one()
        );
    }

    public List<JoinedEntity> loadForChipCard(LazyCriteria criteria, Long cardId) {
        final DaoFilterQueryBuilder queryBuilder = new DaoFilterQueryBuilderWithoutWhereStatement(
                """
                        SELECT * FROM PASSAGE p
                        JOIN GATEWAY g ON g.ID = p.GATEWAY_ID
                        WHERE p.CHIP_CARD_ID = :id"""
        );
        queryBuilder.buildAndAddOrderByStatement(DaoFilterQueryBuilder.OrderType.DESC, "p.TIME");
        queryBuilder.buildAndAddLimitOffsetStatement(criteria.getLimit(), criteria.getOffset());

        return this.jdbi.withHandle(handle ->
                handle
                        .createQuery(queryBuilder.build())
                        .bind("id", cardId)
                        .reduceRows(
                                new JoinEntityRowReducer<>(
                                        EntityPassage.class,
                                        Pair.of(EntityGateway.class, JoinedEntity.JoinType.ONE_TO_ONE)
                                )
                        )
                        .toList()
        );
    }

    public Long countForChipCard(Long cardId) {
        return this.jdbi.withHandle(handle ->
                handle
                        .createQuery("SELECT count(*) FROM PASSAGE WHERE CHIP_CARD_ID = :id")
                        .bind("id", cardId)
                        .mapTo(Long.class)
                        .one()
        );
    }
}
