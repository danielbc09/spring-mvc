package dany.springframerwork.spring5recipeapp.services;

import dany.springframerwork.spring5recipeapp.commands.UnitOfMeasureCommand;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by bautisj on 1/29/2018.
 */
@Service
public interface UnitOfMeasureService {

    Set<UnitOfMeasureCommand> listAllUnitOfMeasure();
}
