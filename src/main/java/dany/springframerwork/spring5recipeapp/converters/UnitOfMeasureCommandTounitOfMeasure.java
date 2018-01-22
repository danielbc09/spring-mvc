package dany.springframerwork.spring5recipeapp.converters;

import dany.springframerwork.spring5recipeapp.commands.UnitOfMeasureCommand;
import dany.springframerwork.spring5recipeapp.domain.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Created by bautisj on 1/22/2018.
 */
@Component
public class UnitOfMeasureCommandTounitOfMeasure implements Converter<UnitOfMeasureCommand, UnitOfMeasure>{

    @Synchronized
    @Nullable
    @Override
    public UnitOfMeasure convert(UnitOfMeasureCommand source) {
        if (source == null){
            return null;
        }

        final UnitOfMeasure  unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setId(source.getId());
        unitOfMeasure.setDescription(source.getDescription());
        return unitOfMeasure;
    }
}
