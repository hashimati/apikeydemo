package io.hashimati.clients



import io.micronaut.context.annotation.Parameter
import io.micronaut.http.annotation.*
import io.micronaut.http.client.annotation.Client


import io.micronaut.http.MediaType
import io.micronaut.http.multipart.CompletedFileUpload



import io.hashimati.domains.Fruit




@Client("/api/v1/fruit")
interface FruitClient {

    @Post("/save")
    Fruit save(@Body Fruit fruit)

    @Get("/get")
    Fruit findById(@Parameter("id") long id)

    @Delete("/delete/{id}")
    boolean deleteById(@PathVariable("id") long id)

    @Get("/findAll")
    Iterable<Fruit> findAll()

    @Put("/update")
    Fruit update(@Body Fruit fruit)

}


