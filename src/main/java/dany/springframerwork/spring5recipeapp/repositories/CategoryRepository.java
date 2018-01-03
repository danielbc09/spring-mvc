package dany.springframerwork.spring5recipeapp.repositories;

import org.springframework.data.repository.CrudRepository;
import dany.springframerwork.spring5recipeapp.domain.Category;

/**
 * Created by bautisj on 1/3/2018.
 */
public interface CategoryRepository extends CrudRepository<Category, Long>{

}
