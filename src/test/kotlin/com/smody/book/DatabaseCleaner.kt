package com.smody.book

import jakarta.persistence.Entity
import jakarta.persistence.EntityManager
import jakarta.persistence.metamodel.EntityType
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class DatabaseCleaner(private val entityManager: EntityManager) {

    private val tableNames: List<String> = extractTableNames(entityManager)

    private fun extractTableNames(entityManager: EntityManager): List<String> {
        return entityManager.metamodel.entities
            .filter(::isEntity)
            .map(::convertCamelToSnake)
            .toList()
    }

    private fun isEntity(entityType: EntityType<*>): Boolean {
        return entityType.javaType.getAnnotation(Entity::class.java) != null
    }

    private fun convertCamelToSnake(entityType: EntityType<*>): String {
        val regex = "([a-z])([A-Z]+)".toRegex()
        val replacement = "$1_$2"
        return entityType.name.replace(regex, replacement).uppercase()
    }

    @Transactional
    fun truncateTables() {
        entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY FALSE").executeUpdate()
        for (tableName in tableNames) {
            entityManager.createNativeQuery("TRUNCATE TABLE $tableName").executeUpdate()
            entityManager.createNativeQuery(
                "ALTER TABLE " + tableName + " ALTER COLUMN " + tableName + "_ID" + " RESTART WITH 1"
            ).executeUpdate()
        }
        entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY TRUE").executeUpdate()
    }
}