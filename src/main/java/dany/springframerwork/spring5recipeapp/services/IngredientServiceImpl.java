package dany.springframerwork.spring5recipeapp.services;

import dany.springframerwork.spring5recipeapp.commands.IngredientCommand;
import dany.springframerwork.spring5recipeapp.converters.IngredientToIngredientCommand;
import dany.springframerwork.spring5recipeapp.converters.IngredientCommandToIngredient;
import dany.springframerwork.spring5recipeapp.domain.Ingredient;
import dany.springframerwork.spring5recipeapp.domain.Recipe;
import dany.springframerwork.spring5recipeapp.repositories.IngredientRepository;
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

    private final IngredientToIngredientCommand ingredienToIngredientCommand;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final IngredientRepository ingredientRepository;

    public IngredientServiceImpl(IngredientToIngredientCommand ingredienToIngredientCommand, IngredientCommandToIngredient ingredientCommandToIngredient, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository, IngredientRepository ingredientRepository) {
        this.ingredienToIngredientCommand = ingredienToIngredientCommand;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.ingredientRepository = ingredientRepository;
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
        Optional<Recipe> recipeOptional = recipeRepository.findById(ingredientCommand.getRecipeId());

        if (!recipeOptional.isPresent()){
            //TO-DO Handling errror
            log.error("Recipe not found for id:" + ingredientCommand.getId());
            return new IngredientCommand();
        }else{
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
                ingredientFound.setUnitOfMeasure(unitOfMeasureRepository
                        .findById(ingredientCommand.getUnitOfMeasure().getId())
                        .orElseThrow(()->new RuntimeException("UOM Not FOUND")));
            }else{
                //add new Ingredient
                Ingredient ingredient = ingredientCommandToIngredient.convert(ingredientCommand);
                ingredient.setRecipe(recipe);
                recipe.addIngredient(ingredient);
            }

            Recipe savedRecipe = recipeRepository.save(recipe);

            Optional<Ingredient> savedIngredientOptional = savedRecipe.getIngredients().stream()
                    .filter(recipeIngredients -> recipeIngredients.getId().equals(ingredientCommand.getId()))
                    .findFirst();

            //check by description
            if(!savedIngredientOptional.isPresent()){
                //not totally safe... But best guess
                savedIngredientOptional = savedRecipe.getIngredients().stream()
                        .filter(recipeIngredients -> recipeIngredients.getDescription().equals(ingredientCommand.getDescription()))
                        .filter(recipeIngredients -> recipeIngredients.getAmount().equals(ingredientCommand.getAmount()))
                        .filter(recipeIngredients -> recipeIngredients.getUnitOfMeasure().getId().equals(ingredientCommand.getUnitOfMeasure().getId()))
                        .findFirst();
            }

            //to do check for fail
            return ingredienToIngredientCommand.convert(savedIngredientOptional.get());
        }
    }

    @Override
    public void deleteIngredientById(Long idToDelete) {
        ingredientRepository.deleteById(idToDelete);
    }

}

