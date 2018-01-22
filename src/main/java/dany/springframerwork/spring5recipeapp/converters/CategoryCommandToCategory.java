package dany.springframerwork.spring5recipeapp.converters;

import dany.springframerwork.spring5recipeapp.commands.CategoryCommand;
import dany.springframerwork.spring5recipeapp.domain.Category;
import lombok.Synchronized;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;


import java.lang.annotation.Annotation;
import java.util.Locale;

/**
 * Created by bautisj on 1/22/2018.
 */
@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand, Category>{

    @Synchronized
    @Nullable
    @Override
    public Category convert(CategoryCommand source) {
        if (source == null){
            return null;
        }

        final Category category = new Category();
        category.setId(source.getId());
        category.setDescription(source.getDescription());
        return category;
    }
}
