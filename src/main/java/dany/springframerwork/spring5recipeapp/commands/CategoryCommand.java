package dany.springframerwork.spring5recipeapp.commands;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by bautisj on 1/22/2018.
 */
@Setter
@Getter
@NoArgsConstructor
public class CategoryCommand {
    private  long id;
    private String description;
}
