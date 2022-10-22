package com.example.doormanagement.jdbi.query_generator;

import com.example.doormanagement.jdbi.BaseEntity;
import com.example.doormanagement.jdbi.reflect.EntityReflect;
import lombok.NonNull;

public class CountAllQueryGenerator extends AbstractQueryGenerator {

    private static final String COUNT_ALL_TEMPLATE = "SELECT count(*) FROM <NAME>";

    public <E extends BaseEntity> CountAllQueryGenerator(@NonNull EntityReflect<E> reflect) {
        super(reflect);
    }

    @Override
    protected String generate() {
        return COUNT_ALL_TEMPLATE.replace("<NAME>", this.tableName);
    }
}
