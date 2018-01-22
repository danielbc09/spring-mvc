package dany.springframerwork.spring5recipeapp.converters;

import dany.springframerwork.spring5recipeapp.commands.NotesCommand;
import dany.springframerwork.spring5recipeapp.domain.Notes;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Created by bautisj on 1/22/2018.
 */
@Component
public class NotesCommandToNotes implements Converter<NotesCommand, Notes>{

    @Synchronized
    @Nullable
    @Override
    public Notes convert(NotesCommand source) {
        if(source == null) {
            return null;
        }

        final Notes notes = new Notes();
        notes.setId(source.getId());
        notes.setRecipeNotes(source.getRecipeNotes());
        return notes;
    }
}
