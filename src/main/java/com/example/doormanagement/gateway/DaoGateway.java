package com.example.doormanagement.gateway;

import com.example.doormanagement.api.LazyCriteria;
import com.example.doormanagement.jdbi.BaseDao;
import com.example.doormanagement.jdbi.DaoFilterQueryBuilder;
import com.example.doormanagement.jdbi.DaoFilterQueryBuilderWithoutWhereStatement;
import org.jdbi.v3.core.Jdbi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DaoGateway extends BaseDao<EntityGateway> {

    @Autowired
    public DaoGateway(Jdbi jdbi) {
        super(EntityGateway.class, jdbi);
    }

    public Optional<EntityGateway> findByCode(String code) {
        return
                this.jdbi.withHandle(handle ->
                        handle
                                .createQuery("SELECT * FROM GATEWAY g WHERE g.CODE = :code")
                                .bind("code", code)
                                .mapTo(EntityGateway.class)
                                .findOne()
                        );
    }

    public List<EntityGateway> load(LazyCriteria criteria) {
        final DaoFilterQueryBuilder queryBuilder = new DaoFilterQueryBuilderWithoutWhereStatement("SELECT * FROM GATEWAY");
        queryBuilder.buildAndAddLimitOffsetStatement(criteria.getLimit(), criteria.getOffset());

        return this.jdbi.withHandle(handle ->
                handle
                        .createQuery(queryBuilder.build())
                        .mapTo(EntityGateway.class)
                        .list()
        );
    }

}
