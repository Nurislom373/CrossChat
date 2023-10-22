package org.khasanof;

import java.util.List;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 10/22/2023 9:26 PM
 */
public interface EntityMapper<D, E> {

    E toEntity(D dto);

    D toDto(E entity);

    List<E> toEntity(List<D> dtoList);

    List<D> toDto(List<E> entityList);

}
