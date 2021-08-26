
package co.unicauca.restaurant.server.access;

import co.unicauca.restaurant.commons.domain.Dish;

/**
 *
 * @author Javier
 */
public interface IDishRepository {

    /**
     * Metodo abstracto que permite buscar un plato en el repositorio
     *
     * @param id el identificador del plato
     * @return objeto de tipo Dish
     */
    public Dish findDish(String id);

    /**
     * Metodo abstracto que permite guardar un plato en el repositorio
     *
     * @param dish objeto tipo Dish
     * @return el identificador del plato guardado
     */
    public String createDish(Dish dish);
}
