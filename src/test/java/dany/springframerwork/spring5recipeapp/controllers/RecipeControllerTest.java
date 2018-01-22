package dany.springframerwork.spring5recipeapp.controllers;

import dany.springframerwork.spring5recipeapp.domain.Recipe;
import dany.springframerwork.spring5recipeapp.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
/**
 * Created by bautisj on 1/10/2018.
 */
public class RecipeControllerTest {

    @Mock
    RecipeService recipeService;

    RecipeController recipeController;

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
        this.recipeController = new RecipeController(recipeService);
    }

    @Test
    public void testGetRecipe() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
        mockMvc.perform(get("/recipe/show/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/show"));
    }
}
