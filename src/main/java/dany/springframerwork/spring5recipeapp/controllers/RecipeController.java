package dany.springframerwork.spring5recipeapp.controllers;

import dany.springframerwork.spring5recipeapp.commands.RecipeCommand;
import dany.springframerwork.spring5recipeapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by bautisj on 1/10/2018.
 */
@Slf4j
@Controller
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {

        this.recipeService = recipeService;
    }

    @GetMapping("/recipe/{id}/show")
    public String showById(@PathVariable String id, Model model){

        model.addAttribute("recipe", recipeService.findById(new Long(id)));
        return "recipe/show";
    }

    @PostMapping("recipe/new")
    public String newRecipe(Model model){
        model.addAttribute("recipe", new RecipeCommand());
        return "recipe/recipeform";
    }

    @GetMapping("recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model){
        model.addAttribute("recipe", recipeService.findRecipeCommandById(Long.valueOf(id)));
        return "recipe/recipeform";
    }

    @PostMapping("recipe")
    public String SaveOrUpdate(@ModelAttribute RecipeCommand command){
        RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);
        return "redirect:/recipe/"+savedCommand.getId() +"/show";
    }

    @GetMapping("recipe/{id}/delete")
    public String delete(@PathVariable String id){
        log.debug("Deleting id" + id);

        recipeService.deleteById(Long.valueOf(id));
        return "redirect:/";
    }

}
