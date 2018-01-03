package dany.springframerwork.spring5recipeapp.repositories;

import dany.springframerwork.spring5recipeapp.domain.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Created by bautisj on 1/3/2018.
 */
public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long>{

    Optional<UnitOfMeasure> findByDescription(String description);
}
