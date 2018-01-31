package dany.springframerwork.spring5recipeapp.controllers;

import dany.springframerwork.spring5recipeapp.services.ImageService;
import dany.springframerwork.spring5recipeapp.services.RecipeService;
import org.springframework.stereotype.Controller;

/**
 * Created by bautisj on 1/31/2018.
 */
@Controller
public class ImageController {

    private final ImageService imageService;
    private final RecipeService recipeService;

    public ImageController(ImageService imageService, RecipeService recipeService) {
        this.imageService = imageService;
        this.recipeService = recipeService;
    }
}
