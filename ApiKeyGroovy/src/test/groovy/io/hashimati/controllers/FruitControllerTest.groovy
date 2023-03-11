package io.hashimati.controllers

import io.hashimati.domains.Fruit
import io.hashimati.utils.Randomizer
import io.micronaut.http.HttpRequest
import io.micronaut.http.MutableHttpRequest
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Test




import jakarta.inject.Inject


@MicronautTest
class FruitControllerTest {

    @Inject
    @Client("/api/v1/fruit")
    HttpClient client;
    

    Fruit body;
    @Test
    void save() {
        Fruit fruit = new Randomizer<Fruit>(Fruit.class).getRandomInstance()
        
        HttpRequest<Fruit> request = HttpRequest.POST("/save", fruit).basicAuth("admin", "admin")
        this.body = client.toBlocking().retrieve(request, Fruit.class)

        this.body = client.toBlocking().retrieve(request, Fruit.class)
        assert body != null;
    }

    @Test
    void findById() {


        save();
        
        MutableHttpRequest<Object> request = HttpRequest.GET("/get?id="+this.body.getId());
        Fruit body = client.toBlocking().retrieve(request, Fruit.class).basicAuth("admin", "admin");
        assert body != null
        assert body.getId() == this.body.getId();
    }

    @Test
    void deleteById() {
        save();
        
        MutableHttpRequest<Object> request = HttpRequest.DELETE("/delete/"+this.body.id).basicAuth("admin", "admin");

        Boolean body= client.toBlocking().retrieve(request, Boolean.class);

        println body
        assert body.booleanValue()== true;

    }

    @Test
    void findAll() {

        save();
        
        MutableHttpRequest<Object> request = HttpRequest.GET("/findAll").basicAuth("admin", "admin");
        Iterable<Fruit> list = client.toBlocking().retrieve(request, Iterable.class);
        System.out.println(list);
        assert list != null;

    }

    @Test
    void update() {
        save();
        
        HttpRequest<Fruit> request = HttpRequest.PUT("/update", this.body).basicAuth("admin", "admin");
        Fruit fruit = client.toBlocking().retrieve(request, Fruit.class);
        assert fruit != null;

    }
}

