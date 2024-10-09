package com.homework2.repository.impl;

import com.homework2.enums.AttractionNames;
import com.homework2.enums.AttractionsEnum;
import com.homework2.models.Attraction;
import com.homework2.repository.api.AttractionRepository;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Implementation of the {@link AttractionRepository} interface that provides data access operations for {@link Attraction} entities.
 * <p>
 * This class extends {@link CrudRepositoryImpl} to provide common CRUD operations and implements additional methods specific to {@link Attraction}.
 * It is annotated with {@link Repository} to mark it as a Spring Data repository.
 * </p>
 */
@Repository
public class AttractionRepositoryImpl extends CrudRepositoryImpl<Attraction, Long> implements AttractionRepository {

    private static final String FROM_ATTRACTION_A_WHERE_A_TYPE_ATTRACTION_TYPE_ATTRACTION_ORDER_BY_A_NAME_DESC =
            "From Attraction a where a.typeAttraction =:typeAttraction ORDER BY a.";
    public static final String ASC = " ASC";

    private static final String TYPE_ATTRACTION = "typeAttraction";
    public static final String NAME = "name";

    /**
     * Constructs an instance of {@link AttractionRepositoryImpl} with the specified {@link SessionFactory}.
     *
     * @param sessionFactory the {@link SessionFactory} to be used for data access.
     */
    public AttractionRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory, Attraction.class);
    }

    /**
     * Edits an existing {@link Attraction} entity in the database.
     *
     * @param attraction the {@link Attraction} entity to be updated.
     */
    @Override
    public void edit(Attraction attraction) {
        sessionFactory.getCurrentSession().merge(attraction);
    }

    /**
     * Retrieves a list of {@link Attraction} entities sorted by name in ascending order based on the specified type.
     *
     * @param attractionsEnum the type of attraction to filter by.
     * @param attractionName  the name of the attraction (currently not used in the query).
     * @return a list of {@link Attraction} entities that match the given type, sorted by name in ascending order.
     */
    @Override
    public List<Attraction> getBySort(AttractionsEnum attractionsEnum, AttractionNames attractionName) {

        final Query<Attraction> query = sessionFactory.getCurrentSession()
                .createQuery((FROM_ATTRACTION_A_WHERE_A_TYPE_ATTRACTION_TYPE_ATTRACTION_ORDER_BY_A_NAME_DESC + attractionName.toString().toLowerCase() + ASC), Attraction.class);
        query.setParameter(TYPE_ATTRACTION, attractionsEnum);
        return query.list();
    }
}
