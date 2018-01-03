package dany.springframerwork.spring5recipeapp.repositories;

import dany.springframerwork.spring5recipeapp.domain.Category;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

/**
 * Created by bautisj on 1/3/2018.
 */
public interface CategoryRepository extends CrudRepository<Category, Long>{

    Optional<Category> findByDescription(String description);

}
