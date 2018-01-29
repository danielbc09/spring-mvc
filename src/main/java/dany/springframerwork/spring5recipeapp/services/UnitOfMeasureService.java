package dany.springframerwork.spring5recipeapp.services;

import dany.springframerwork.spring5recipeapp.commands.UnitOfMeasureCommand;

import java.util.Set;

/**
 * Created by bautisj on 1/29/2018.
 */
public interface UnitOfMeasureService {

    Set<UnitOfMeasureCommand> listAllUnitOfMeasure();
}
