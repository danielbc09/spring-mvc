package dany.springframerwork.spring5recipeapp.controllers;

import dany.springframerwork.spring5recipeapp.commands.IngredientCommand;
import dany.springframerwork.spring5recipeapp.commands.RecipeCommand;
import dany.springframerwork.spring5recipeapp.services.IngredientService;
import dany.springframerwork.spring5recipeapp.services.RecipeService;
import dany.springframerwork.spring5recipeapp.services.UnitOfMeasureService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

/**
 * Created by bautisj on 1/23/2018.
 */
public class IngredientControllerTest {

    @Mock
    RecipeService recipeService;

    @Mock
    IngredientService ingredientService;

    @Mock
    UnitOfMeasureService unitOfMeasureService;

    IngredientController controller;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
        controller = new IngredientController(recipeService, ingredientService, unitOfMeasureService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testListIngredients() throws Exception{
        //given
        RecipeCommand recipeCommand = new RecipeCommand();
        when(recipeService.findRecipeCommandById(anyLong())).thenReturn(recipeCommand);

        //when
        mockMvc.perform(get("/recipe/1/ingredients"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/list"))
                .andExpect(model().attributeExists("recipe"));

    }

    @Test
    public void testShowIngredient() throws Exception{
        //Given
        IngredientCommand ingredientCommand = new IngredientCommand();

        //when
        when(ingredientService.findByRecipeIdAndIngredientId(anyLong(), anyLong())).thenReturn(ingredientCommand);

        //then
        mockMvc.perform(get("/recipe/1/ingredient/2/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/show"))
                .andExpect(model().attributeExists("ingredient"));

    }

    @Test
    public void testUpdateIngredientForm() throws Exception {
        //Given
        IngredientCommand ingredientCommand = new IngredientCommand();

        //When
        when(ingredientService.findByRecipeIdAndIngredientId(anyLong(), anyLong())).thenReturn(ingredientCommand);
        when(unitOfMeasureService.listAllUnitOfMeasure()).thenReturn(new HashSet<>());

        mockMvc.perform(get("/recipe/1/ingredient/2/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/ingredientform"))
                .andExpect(model().attributeExists("ingredient"))
                .andExpect(model().attributeExists("uomList"));
    }

    @Test
    public void testSaveOrUpdate() throws Exception{
        //Given
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(3l);
        ingredientCommand.setRecipeId(2l);

        //when
        when(ingredientService.saverIngredientCommand(any())).thenReturn(ingredientCommand);

        //Then
        mockMvc.perform(post("/recipe/2/ingredient")
                .param("id", "")
                .param("description", "some description")
                ).andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/2/ingredient/3/show"));

    }

}
