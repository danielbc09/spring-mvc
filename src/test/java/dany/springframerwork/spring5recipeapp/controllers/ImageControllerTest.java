package dany.springframerwork.spring5recipeapp.controllers;

import dany.springframerwork.spring5recipeapp.commands.RecipeCommand;
import dany.springframerwork.spring5recipeapp.services.ImageService;
import dany.springframerwork.spring5recipeapp.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by bautisj on 1/31/2018.
 */
public class ImageControllerTest {

    @Mock
    ImageService imageService;

    @Mock
    RecipeService recipeService;

    ImageController imageController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
        imageController = new ImageController(imageService, recipeService);
        mockMvc = MockMvcBuilders.standaloneSetup(imageController)
                                 .setControllerAdvice(new ExceptionHandlerController())
                                 .build();
    }

    @Test
    public void getImageForm() throws Exception {

        //Given
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(1l);

        when(recipeService.findRecipeCommandById(anyLong())).thenReturn(recipeCommand);

        //When
        mockMvc.perform(get("/recipe/1/image"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("recipe"));

        verify(recipeService, times(1)).findRecipeCommandById(anyLong());
    }

    @Test
    public void testGetImageNumberFormatException() throws Exception{

        mockMvc.perform(get("/recipe/adsa/image"))
                .andExpect(status().isBadRequest())
                .andExpect(view().name("400Error"));
    }

    @Test
    public void handleImagePost() throws Exception{
        MockMultipartFile multipartFile =
                new MockMultipartFile("imagefile", "testing.txt",
                                      "text/plain", "Spring Framerwork Guru".getBytes());

        mockMvc.perform(multipart("/recipe/1/image").file(multipartFile))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "/recipe/1/show"));

        verify(imageService, times(1)).saveImageFile(anyLong(), any());
    }

    @Test
    public void renderImageFromDB() throws Exception{
        //given
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(1L);

        String imageName = "fake image text";
        Byte[] bytesBoxed = new Byte[imageName.getBytes().length];

        int i = 0;

        for (byte primByte : imageName.getBytes()){
            bytesBoxed[i++] = primByte;
        }
        recipeCommand.setImage(bytesBoxed);

        when(recipeService.findRecipeCommandById(anyLong())).thenReturn(recipeCommand);

        //When
        MockHttpServletResponse response = mockMvc.perform(get("/recipe/1/recipeimage"))
                                                   .andExpect(status().isOk())
                                                   .andReturn().getResponse();

        byte[] responseBytes = response.getContentAsByteArray();
        assertEquals(imageName.getBytes().length, responseBytes.length);
    }



}
