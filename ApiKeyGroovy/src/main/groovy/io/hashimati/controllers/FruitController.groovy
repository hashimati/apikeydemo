package io.hashimati.controllers

import io.micronaut.context.annotation.Parameter
import io.micronaut.http.annotation.*
import io.micronaut.http.MediaType
import io.micronaut.http.multipart.CompletedFileUpload
import io.micronaut.core.version.annotation.Version
import io.micronaut.retry.annotation.CircuitBreaker
import jakarta.inject.Singleton
import io.hashimati.domains.Fruit
import io.hashimati.services.FruitService
import java.security.Principal
import io.micronaut.security.annotation.Secured
import io.micronaut.security.rules.SecurityRule

import jakarta.inject.Inject

import io.micronaut.core.annotation.NonNull;
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import io.micrometer.core.annotation.Timed
import io.micronaut.tracing.annotation.NewSpan
import io.micronaut.tracing.annotation.SpanTag







@Tag(name = "Fruit")
@Controller("/api/v1/fruit")
@CircuitBreaker(attempts = "5", maxDelay = "3s", reset = "30")
class FruitController {

    @Inject FruitService fruitService
    static final Logger log = LoggerFactory.getLogger(FruitController.class)

    @NewSpan("Fruit-service")
    @Timed(value = "io.hashimati.controllers.fruitController.save", percentiles = [ 0.5d, 0.95d, 0.99d ], description = "Observing all service metric for saving fruit object")
    @Secured(SecurityRule.IS_AUTHENTICATED)
    @Post("/save")
    @Version("1")
    @Operation(summary = "Creating a fruit and Storing in the database",
            description = "A REST service, which saves Fruit objects to the database.",
            operationId = "SaveFruit"
    )
    @ApiResponse(
            content = @Content(mediaType = "application/json")
    )
    @ApiResponse(responseCode = "400", description = "Invalid Object Supplied")
    @ApiResponse(responseCode = "404", description = "Fruit not stored")
    Fruit save(@SpanTag("save.fruit") @Body @NonNull Fruit fruit, Principal principle ){
        log.info("Saving  Fruit : {}", fruit)
        //TODO insert your logic here!
        //saving Object
        return fruitService.save(fruit, principle )
    }


    @NewSpan("Fruit-service")
    @Timed(value = "io.hashimati.controllers.fruitController.findById", percentiles = [ 0.5d, 0.95d, 0.99d ], description = "Observing all service metric for finding a fruit object by id")
    @Secured(SecurityRule.IS_AUTHENTICATED)
    @Get("/get")
    @Version("1")
    @Operation(summary = "Getting a fruit by Id",
        description = "A REST service, which retrieves a Fruit object by Id.",
        operationId = "FindByIdFruit"
    )
    @ApiResponse(
        content = @Content(mediaType = "application/json")
    )
    @ApiResponse(responseCode = "400", description = "Invalid Id Supplied")
    @ApiResponse(responseCode = "404", description = "Fruit not found")
    Fruit findById(@SpanTag("findById.id") @Parameter("id") long id, Principal principle ){

        log.info("Finding Fruit By Id: {}", id)
        return fruitService.findById(id, principle )
    }

    @NewSpan("Fruit-service")
    @Timed(value = "io.hashimati.controllers.fruitController.deleteById", percentiles = [ 0.5d, 0.95d, 0.99d ], description = "Observing all service metric for deleting a fruit object by id")
    @Secured(SecurityRule.IS_AUTHENTICATED)
    @Delete("/delete/{id}")
    @Version("1")
    @Operation(summary = "Deleting a fruit by ID",
            description = "A REST service, which deletes Fruit object from the database.",
            operationId = "DeleteByIdFruit"
    )
    @ApiResponse(
            content = @Content(mediaType = "boolean")
    )
    @ApiResponse(responseCode = "400", description = "Invalid Id Supplied")
    @ApiResponse(responseCode = "404", description = "Fruit not found")
    Boolean deleteById(@SpanTag("deleteById.id") @PathVariable("id") long id, Principal principle ){
        log.info("Deleting Fruit by Id: {}", id)
        return  fruitService.deleteById(id, principle )
    }


    @NewSpan("Fruit-service")
    @Timed(value = "io.hashimati.controllers.fruitController.findAll", percentiles = [ 0.5d, 0.95d, 0.99d ], description = "Observing all service metric for finding all fruit objects")
    @Secured(SecurityRule.IS_AUTHENTICATED)
    @Get("/findAll")
    @Version("1")
    @Operation(summary = "Retrieving all fruit objects as Json",
            description = "A REST service, which returns all Fruit objects from the database.",
            operationId = "FindAllFruit"
    )
    @ApiResponse(
            content = @Content(mediaType = "application/json")
    )
    Iterable<Fruit> findAll(Principal principle){
        log.info("find All")
        return fruitService.findAll(principle )
    }

    @NewSpan("Fruit-service")
    @Timed(value = "io.hashimati.controllers.fruitController.update", percentiles = [ 0.5d, 0.95d, 0.99d ], description = "Observing all service metric for update a fruit object")
    @Secured(SecurityRule.IS_AUTHENTICATED)
    @Put("/update")
    @Version("1")
    @Operation(summary = "Updating a fruit.",
            description = "A REST service, which update a Fruit objects to the database.",
            operationId = "updateFruit"
    )
    @ApiResponse(
            content = @Content(mediaType = "application/json")
    )
    @ApiResponse(responseCode = "404", description = "Fruit not found")
    Fruit update(@SpanTag("update.fruit") @NonNull @Body Fruit fruit, Principal principle )
    {
        log.info("update {}", fruit)
        return fruitService.update(fruit, principle )
    }

}


