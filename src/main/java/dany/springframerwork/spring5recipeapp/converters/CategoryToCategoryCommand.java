package dany.springframerwork.spring5recipeapp.converters;

import dany.springframerwork.spring5recipeapp.commands.CategoryCommand;
import dany.springframerwork.spring5recipeapp.domain.Category;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Created by bautisj on 1/22/2018.
 */
@Component
public class CategoryToCategoryCommand implements Converter<Category, CategoryCommand>{

    @Synchronized
    @Nullable
    @Override
    public CategoryCommand convert(Category source) {
        if (source == null) {
            return null;
        }

        final CategoryCommand categoryCommand = new CategoryCommand();

        categoryCommand.setId(source.getId());
        categoryCommand.setDescription(source.getDescription());

        return categoryCommand;
    }

}
