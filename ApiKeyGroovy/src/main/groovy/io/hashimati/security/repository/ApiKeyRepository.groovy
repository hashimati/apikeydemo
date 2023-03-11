package io.hashimati.security.repository

import io.hashimati.security.domains.APIKey
import io.micronaut.data.repository.CrudRepository
import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect

import java.util.Optional


@JdbcRepository(dialect = Dialect.H2)
interface ApiKeyRepository extends CrudRepository<APIKey, Long > {

    Optional<APIKey> findByName(String name)
    Optional<APIKey> findBySecret(String key)

}


