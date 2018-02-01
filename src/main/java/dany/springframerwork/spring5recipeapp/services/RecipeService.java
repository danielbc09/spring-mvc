package dany.springframerwork.spring5recipeapp.services;

import dany.springframerwork.spring5recipeapp.commands.RecipeCommand;
import dany.springframerwork.spring5recipeapp.domain.Recipe;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface RecipeService {

    Set<Recipe> getRecipes();

    Recipe findById(Long l);

    RecipeCommand findRecipeCommandById(Long commandId);

    RecipeCommand saveRecipeCommand(RecipeCommand command);

    void deleteById(Long idToDelete);
}
