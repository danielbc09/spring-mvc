package dany.springframerwork.spring5recipeapp.domain;

import lombok.*;

import javax.persistence.*;

/**
 * Created by bautisj on 1/3/2018.
 */
@Data
@Entity
public class Notes{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String recipeNotes;

    @OneToOne
    private Recipe recipe;

    public Notes() {
    }

    protected boolean canEqual(Object other) {
        return other instanceof Notes;
    }

}
