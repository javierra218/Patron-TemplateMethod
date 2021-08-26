
package co.unicauca.restaurant.client.access;

import co.unicauca.restaurant.commons.domain.Dish;

/**
 *
 * @author Javier
 */
public interface IDishAccess {

    /**
     * Busca un Dish
     *
     * @param id identificador del plato
     * @return el plato
     * @throws Exception
     */
    public Dish findDish(String id) throws Exception;

    /**
     * Crea un Dish
     *
     * @param dish plato del restaurante
     * @return devuelve el id del plato creado
     * @throws Exception error crear el plato
     */
    public String createDish(Dish dish) throws Exception;
}
