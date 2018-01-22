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
public class UnitOfMeasureToUnitOfMeasureCommand implements Converter<UnitOfMeasure, UnitOfMeasureCommand>{


    @Synchronized
    @Nullable
    @Override
    public UnitOfMeasureCommand convert(UnitOfMeasure unitOfMeasure) {

        if (unitOfMeasure != null) {
            final UnitOfMeasureCommand uomCommand = new UnitOfMeasureCommand();
            uomCommand.setId(unitOfMeasure.getId());
            uomCommand.setDescription(unitOfMeasure.getDescription());
            return uomCommand;
        }
        return null;
    }
}
