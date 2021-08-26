
package co.unicauca.restaurant.client.domain.services;

import co.unicauca.restaurant.commons.domain.Dish;
import co.unicauca.restaurant.client.access.IDishAccess;

/**
 * Es una fachada para comunicar la presentación con el dominio
 *
 * @author Javier
 */
public class DishService {

    private final IDishAccess service;

    /**
     * Constructor privado que evita que otros objetos instancien
     *
     * @param service implementacion de tipo IDishService
     */
    public DishService(IDishAccess service) {
        this.service = service;
    }

    /**
     * Busca un plato en el servidor remoto
     *
     * @param id identificador del plato
     * @return Objeto tipo plato, null si no lo encuentra
     * @throws java.lang.Exception la excepcio se lanza cuando no logra conexión
     * con el servidor
     */
    public Dish findDish(String id) throws Exception {
        return service.findDish(id);

    }

    /**
     * Crea un plato en el servidor remoto
     *
     * @param dish objeto tipo plato
     * @return el identificador del plato guardado en el servidor remoto
     * @throws Exception si no se pudo guardar el plato
     */
    public String createDish(Dish dish) throws Exception {
        return service.createDish(dish);

    }

}
