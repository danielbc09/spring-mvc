package dany.springframerwork.spring5recipeapp.services;

import dany.springframerwork.spring5recipeapp.commands.IngredientCommand;
import dany.springframerwork.spring5recipeapp.converters.IngredienToIngredientCommand;
import dany.springframerwork.spring5recipeapp.domain.Recipe;
import dany.springframerwork.spring5recipeapp.repositories.RecipeRepository;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by bautisj on 1/24/2018.
 */
@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService{

    private final IngredienToIngredientCommand ingredienToIngredientCommand;
    private final RecipeRepository recipeRepository;

    public IngredientServiceImpl(IngredienToIngredientCommand ingredienToIngredientCommand, RecipeRepository recipeRepository) {
        this.ingredienToIngredientCommand = ingredienToIngredientCommand;
        this.recipeRepository = recipeRepository;
    }


    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(long recipeId, long ingredientId) {

        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if(!recipeOptional.isPresent()){
            log.error("Recipe didn't found Id:" + recipeId);
        }

        Recipe recipe = recipeOptional.get();

        Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredients().stream()
                .filter( ingredient -> ingredient.getId().equals(ingredientId))
                .map(ingredient -> ingredienToIngredientCommand.convert(ingredient)).findFirst();

        if(!ingredientCommandOptional.isPresent()){
            log.error("Ingredient didn't found Id:" + ingredientId);
        }

        return ingredientCommandOptional.get();

    }
}
