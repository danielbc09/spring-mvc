package dany.springframerwork.spring5recipeapp.controllers;

import dany.springframerwork.spring5recipeapp.domain.Category;
import dany.springframerwork.spring5recipeapp.domain.UnitOfMeasure;
import dany.springframerwork.spring5recipeapp.repositories.CategoryRepository;
import dany.springframerwork.spring5recipeapp.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

/**
 * Created by bautisj on 1/2/2018.
 */
@Controller
public class IndexController {

    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(){
        Optional<Category> categoryOptional= categoryRepository.findByDescription("Italian");
        Optional<UnitOfMeasure> unitOfMeasureOptional= unitOfMeasureRepository.findByDescription("Teaspoon");

        System.out.println("Cat id is:"+ categoryOptional.get().getId());
        System.out.println("Unit of meassure id is:"+ unitOfMeasureOptional.get().getId());

        return "index";
    }
}

