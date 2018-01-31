package dany.springframerwork.spring5recipeapp.repositories;

import dany.springframerwork.spring5recipeapp.domain.Ingredient;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by bautisj on 1/31/2018.
 */
public interface IngredientRepository extends CrudRepository<Ingredient, Long>{
}
