package com.starlight.module.domain.mappers

interface DomainMapper<E, M> {
    fun toDomain(entity: E): M
    fun fromDomain(model: M): E

    fun toDomainList(entities: List<E>): List<M> {
        return entities.map {
            toDomain(it)
        }
    }

    fun fromDomainList(models: List<M>): List<E> {
        return models.map { fromDomain(it) }
    }
}