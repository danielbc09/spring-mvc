package dany.springframerwork.spring5recipeapp.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by bautisj on 1/3/2018.
 */
@Data
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    @ManyToMany(mappedBy = "categories")
    private Set<Recipe> recipes;

    public Category() {
    }

    protected boolean canEqual(Object other) {
        return other instanceof Category;
    }

}

