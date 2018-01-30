package dany.springframerwork.spring5recipeapp.services;

import dany.springframerwork.spring5recipeapp.commands.IngredientCommand;
import dany.springframerwork.spring5recipeapp.converters.IngredienToIngredientCommand;
import dany.springframerwork.spring5recipeapp.converters.IngredientCommandToIngredient;
import dany.springframerwork.spring5recipeapp.converters.UnitOfMeasureCommandTounitOfMeasure;
import dany.springframerwork.spring5recipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import dany.springframerwork.spring5recipeapp.domain.Ingredient;
import dany.springframerwork.spring5recipeapp.domain.Recipe;
import dany.springframerwork.spring5recipeapp.repositories.RecipeRepository;
import dany.springframerwork.spring5recipeapp.repositories.UnitOfMeasureRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;

/**
 * Created by bautisj on 1/24/2018.
 */
public class IngredientServiceImplTest {

    private final IngredienToIngredientCommand ingredienToIngredientCommand;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    IngredientService ingredientService;


    public IngredientServiceImplTest() {
        this.ingredienToIngredientCommand = new IngredienToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
        this.ingredientCommandToIngredient = new IngredientCommandToIngredient(new UnitOfMeasureCommandTounitOfMeasure());
    }

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
        ingredientService = new IngredientServiceImpl(ingredienToIngredientCommand, ingredientCommandToIngredient, recipeRepository, unitOfMeasureRepository);
    }

    @Test
    public void findByRecipeIdAndIngredientIdTest()  throws  Exception {
        // given
        Recipe recipe = new Recipe();
        recipe.setId(1l);

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(1l);

        Ingredient ingredient2 = new Ingredient();
        ingredient1.setId(2l);

        Ingredient ingredient3 = new Ingredient();
        ingredient1.setId(3l);

        recipe.addIngredient(ingredient1);
        recipe.addIngredient(ingredient2);
        recipe.addIngredient(ingredient3);

        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        //when
        IngredientCommand ingredientCommand = ingredientService.findByRecipeIdAndIngredientId(1l,3l);

        //Then
        assertEquals(Long.valueOf(3l), ingredientCommand.getId());
        assertEquals(Long.valueOf(1l), ingredientCommand.getRecipeId());
        verify(recipeRepository, times(1)).findById(anyLong());

    }
}
