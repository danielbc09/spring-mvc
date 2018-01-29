package dany.springframerwork.spring5recipeapp.services;

import dany.springframerwork.spring5recipeapp.commands.IngredientCommand;
import org.springframework.stereotype.Service;

/**
 * Created by bautisj on 1/24/2018.
 */
@Service
public interface IngredientService {

    IngredientCommand findByRecipeIdAndIngredientId(long recipeId, long ingredientId);
}
