package com.homework2.repository.impl;

import com.homework2.repository.api.LocalityRepository;
import com.homework2.models.Locality;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class LocalityRepositoryImpl extends CrudRepositoryImpl<Locality, Long> implements LocalityRepository {
    public LocalityRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory, Locality.class);
    }
}
