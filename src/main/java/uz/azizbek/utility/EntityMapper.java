package uz.azizbek.utility;

import org.mapstruct.*;

import java.util.List;

public interface EntityMapper <D, E>{

    E toEntity(D dto);

    D toDto(E entity);

    List<D> toDto(List<E> eList);

    List<E> toEntity(List<D> dList);

    @Named("partialUpdate")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(@MappingTarget E entity, D dto);
}
