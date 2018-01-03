package dany.springframerwork.spring5recipeapp.repositories;

import dany.springframerwork.spring5recipeapp.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by bautisj on 1/3/2018.
 */
public interface RecipeRepository extends CrudRepository<Recipe, Long>{

}
