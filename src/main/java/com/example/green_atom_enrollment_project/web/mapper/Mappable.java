package com.example.green_atom_enrollment_project.web.mapper;

import java.util.List;

public interface Mappable<E, D> {
    D toDto(E e);

    E toEntity(D d);

    List<D> toDtos(List<E> entities);

    List<E> toEntities(List<D> dtos);
}
