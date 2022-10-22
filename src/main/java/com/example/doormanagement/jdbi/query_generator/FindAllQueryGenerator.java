package com.example.doormanagement.jdbi.query_generator;

import com.example.doormanagement.jdbi.BaseEntity;
import com.example.doormanagement.jdbi.reflect.EntityReflect;
import lombok.NonNull;

public class FindAllQueryGenerator extends AbstractQueryGenerator {

    private static final String FIND_ALL_TEMPLATE = "SELECT * FROM <NAME>";

    public <E extends BaseEntity> FindAllQueryGenerator(@NonNull EntityReflect<E> reflect) {
        super(reflect);
    }

    @Override
    protected String generate() {
        return FIND_ALL_TEMPLATE.replace("<NAME>", this.tableName);
    }
}
