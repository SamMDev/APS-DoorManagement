package com.example.doormanagement.jdbi;

import com.example.doormanagement.jdbi.query_generator.*;
import com.example.doormanagement.jdbi.reflect.EntityReflect;
import com.example.doormanagement.jdbi.reflect.EntityReflectionManager;
import org.jdbi.v3.core.Jdbi;

import java.util.List;
import java.util.Optional;

/**
 * BaseDao all the DAO's should extend
 * Provides common functionality for all the DAO's
 *
 * @param <E> entity clazz
 * @author Samuel Molčan
 */
@SuppressWarnings("squid:S1192")
public abstract class BaseDao<E extends BaseEntity> {
    private final EntityReflect<E> reflect;

    private final String insertEntityQuery;
    private final String updateEntityQuery;
    private final String deleteByIdQuery;
    private final String findAllQuery;
    private final String countAllQuery;
    private final String findByIdQuery;

    protected final Jdbi jdbi;

    protected BaseDao(Class<E> clazz, Jdbi jdbi) {

        this.jdbi = jdbi;
        this.reflect = EntityReflectionManager.reflectionOf(clazz);

        this.insertEntityQuery = new InsertQueryGenerator(this.reflect).getResult();
        this.updateEntityQuery = new UpdateQueryGenerator(this.reflect).getResult();
        this.deleteByIdQuery = new DeleteQueryGenerator(this.reflect).getResult();
        this.findAllQuery = new FindAllQueryGenerator(this.reflect).getResult();
        this.countAllQuery = new CountAllQueryGenerator(this.reflect).getResult();
        this.findByIdQuery = new FindByIdQueryGenerator(this.reflect).getResult();
    }

    public Long insert(E entity) {
        return this.jdbi.withHandle(handle -> handle.createUpdate(insertEntityQuery).bindBean(entity).executeAndReturnGeneratedKeys().mapTo(Long.class).one());
    }

    public void update(E entity) {
        this.jdbi.withHandle(handle -> handle.createUpdate(updateEntityQuery).bindBean(entity).bind("id", entity.getId()).execute());
    }

    public Optional<E> findById(Long id) {
        return this.jdbi.withHandle(
                handle ->
                        handle.createQuery(findByIdQuery)
                                .bind("id", id)
                                .mapTo(reflect.getClazz())
                                .findOne());
    }

    public void deleteById(Long id) {
        this.jdbi.withHandle(handle -> handle.createUpdate(deleteByIdQuery).bind("id", id).execute());
    }

    public List<E> findAll() {
        return this.jdbi.withHandle(handle -> handle
                .createQuery(findAllQuery)
                .mapTo(reflect.getClazz())
                .list()
        );
    }

    public Long countAll() {
        return this.jdbi.withHandle(handle ->
                handle
                        .createQuery(this.countAllQuery)
                        .mapTo(Long.class)
                        .one()
        );
    }

}
