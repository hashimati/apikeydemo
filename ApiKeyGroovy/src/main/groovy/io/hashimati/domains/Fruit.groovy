package io.hashimati.domains

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.*
import groovy.transform.Canonical
import groovy.transform.CompileStatic

import io.micronaut.data.annotation.*
import io.swagger.v3.oas.annotations.media.Schema
import static io.micronaut.data.model.naming.NamingStrategies.*


import java.util.*

@Schema(name="Fruit", description="Fruit Description")

@MappedEntity(value = "fruits", namingStrategy = Raw.class)
@CompileStatic
@Canonical
class Fruit{
    
    @Id
    @GeneratedValue(GeneratedValue.Type.AUTO)
    long id
    
    String name
    @DateCreated
    Date dateCreated

    @DateUpdated
    Date dateUpdated

}

