package com.homework2.repository.impl;

import com.homework2.repository.api.CrudRepository;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

/**
 * Generic implementation of the {@link CrudRepository} interface for performing CRUD operations.
 *
 * @param <T> the type of the entity
 * @param <ID> the type of the entity's identifier
 */
public class CrudRepositoryImpl<T, ID> implements CrudRepository<T, ID> {

    final SessionFactory sessionFactory;
    private Class<T> type;

    /**
     * Constructs a new CrudRepositoryImpl with the specified SessionFactory and entity type.
     *
     * @param sessionFactory the SessionFactory for managing Hibernate sessions
     * @param type the class type of the entity
     */
    public CrudRepositoryImpl(SessionFactory sessionFactory, Class<T> type) {
        this.sessionFactory = sessionFactory;
        this.type = type;
    }

    /**
     * Saves the given entity to the database.
     *
     * @param t the entity to be saved
     */
    public void save(T t) {
        sessionFactory.getCurrentSession().persist(t);
    }

    /**
     * Finds an entity by its identifier.
     *
     * @param id the identifier of the entity
     * @return an Optional containing the found entity, or an empty Optional if no entity was found
     */
    @Override
    public Optional<T> findById(ID id) {
        return Optional.ofNullable(sessionFactory.getCurrentSession().get(type, id));
    }

    /**
     * Retrieves all entities of the specified type from the database.
     *
     * @return a list of all entities
     */
    @Override
    public List<T> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from " + type.getName(), type)
                .list();
    }

    /**
     * Deletes an entity by its identifier.
     *
     * @param id the identifier of the entity to be deleted
     */
    @Override
    public void deleteById(ID id) {
        T entity = sessionFactory.getCurrentSession().get(type, id);
        if (entity != null) {
            sessionFactory.getCurrentSession().remove(entity);
        }
    }

    /**
     * Deletes the given entity from the database.
     *
     * @param entity the entity to be deleted
     */
    @Override
    public void delete(T entity) {
        sessionFactory.getCurrentSession().remove(entity);
    }

}
