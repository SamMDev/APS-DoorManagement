package com.example.doormanagement.jdbi;

import java.util.Map;

/**
 * Query builder for queries that don't need where statement
 */
public class DaoFilterQueryBuilderWithoutWhereStatement extends DaoFilterQueryBuilder {

    public DaoFilterQueryBuilderWithoutWhereStatement(String select) {
        super(select);
    }

    @Override
    public void buildAndAddWhereStatement(Map<String, Object> filters) {
        ;
    }
}
