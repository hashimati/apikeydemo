package io.hashimati.services


import jakarta.inject.Inject
import jakarta.inject.Singleton
import javax.transaction.Transactional
import io.micronaut.http.multipart.CompletedFileUpload
import io.hashimati.domains.Fruit
import java.security.Principal
import io.hashimati.repositories.FruitRepository
import io.micrometer.core.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;








@Transactional
@Singleton
class FruitService {
     Logger log = LoggerFactory.getLogger(FruitService.class)
    @Inject FruitRepository fruitRepository
    

    @Timed(value = "io.hashimati.services.fruitService.save", percentiles = [ 0.5d, 0.95d, 0.99d ], description = "Observing all service metric for saving fruit object")
    Fruit save(Fruit fruit, Principal principle ){
        log.info("Saving  Fruit : {}", fruit)
         //TODO insert your logic here!
         //saving Object

         return fruitRepository.save(fruit)
     }

    
    @Timed(value = "io.hashimati.services.fruitService.findById", percentiles = [ 0.5d, 0.95d, 0.99d ], description = "Observing all service metric for finding a fruit object by id")
    Fruit findById(long id, Principal principle ){
        log.info("Finding Fruit By Id: {}", id)
        return fruitRepository.findById(id).orElse(null)
    }

    @Timed(value = "io.hashimati.services.fruitService.deleteById", percentiles = [ 0.5d, 0.95d, 0.99d ], description = "Observing all service metric for deleting a fruit object by id")
    boolean deleteById(long id, Principal principle ){
        try
        {
            fruitRepository.deleteById(id);
            log.info("Deleting Fruit by Id: {}", id);
            return true;
        }
        catch(Exception ex)
        {
            log.info("Failed to delete Fruit by Id: {}", id);
            ex.printStackTrace();
            return false;
        }
    }

    @Timed(value = "io.hashimati.services.fruitService.findAll", percentiles =[ 0.5d, 0.95d, 0.99d ], description = "Observing all service metric for finding all fruit objects")
    Iterable<Fruit> findAll(Principal principle ) {
        log.info("Find All")
        return  fruitRepository.findAll();
    }

    @Timed(value = "io.hashimati.services.fruitService.update", percentiles = [ 0.5d, 0.95d, 0.99d ], description = "Observing all service metric for update a fruit object")
    Fruit update(Fruit fruit, Principal principle )
    {
        log.info("update {}", fruit)
        return fruitRepository.update(fruit)

    }

}

