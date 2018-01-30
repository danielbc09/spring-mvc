package dany.springframerwork.spring5recipeapp.services;

import dany.springframerwork.spring5recipeapp.commands.IngredientCommand;
import dany.springframerwork.spring5recipeapp.converters.IngredienToIngredientCommand;
import dany.springframerwork.spring5recipeapp.converters.IngredientCommandToIngredient;
import dany.springframerwork.spring5recipeapp.domain.Ingredient;
import dany.springframerwork.spring5recipeapp.domain.Recipe;
import dany.springframerwork.spring5recipeapp.repositories.RecipeRepository;
import dany.springframerwork.spring5recipeapp.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Created by bautisj on 1/24/2018.
 */
@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService{

    private final IngredienToIngredientCommand ingredienToIngredientCommand;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public IngredientServiceImpl(IngredienToIngredientCommand ingredienToIngredientCommand, IngredientCommandToIngredient ingredientCommandToIngredient, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.ingredienToIngredientCommand = ingredienToIngredientCommand;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
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

    @Override
    @Transactional
    public IngredientCommand saverIngredientCommand(IngredientCommand ingredientCommand) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(ingredientCommand.getId());

        if (!recipeOptional.isPresent()){
            //TO-DO Handling errror
            log.error("Recipe not found for id:" + ingredientCommand.getId());
        }

        Recipe recipe = recipeOptional.get();
        Optional<Ingredient> ingredientOptional = recipe
                .getIngredients()
                .stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientCommand.getId()))
                .findFirst();
        if (ingredientOptional.isPresent()){
            Ingredient ingredientFound = ingredientOptional.get();
            ingredientFound.setDescription(ingredientCommand.getDescription());
            ingredientFound.setAmount(ingredientCommand.getAmount());
            ingredientFound.setUnitOfMeasure(unitOfMeasureRepository.findById(ingredientCommand.getUnitOfMeasure().getId()).orElseThrow(()->new RuntimeException("UOM Not FOUND")));
        }else{
            //add new Ingredient
            recipe.addIngredient(ingredientCommandToIngredient.convert(ingredientCommand));
        }

        Recipe savedRecipe = recipeRepository.save(recipe);

        return ingredienToIngredientCommand.convert(savedRecipe.getIngredients().stream()
                                            .filter(recipeIngredients -> recipeIngredients.getId().equals(ingredientCommand.getId()))
                                            .findFirst()
                                            .get());

    }

}

