package com.homework2.converters.api;

import java.util.List;

public interface Converter<E, D> {
    D toDto(E e);

    E fromDto(D d);

    default List<D> toDto(List<E> e) {
        return e.stream().map(this::toDto).toList();
    }

    default List<E> fromDto(List<D> d) {
        return d.stream().map(this::fromDto).toList();
    }
}
    