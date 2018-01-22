package dany.springframerwork.spring5recipeapp.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by bautisj on 1/22/2018.
 */
@Getter
@Setter
@NoArgsConstructor
public class NotesCommand {

    private Long id;
    private String recipeNotes;
}
