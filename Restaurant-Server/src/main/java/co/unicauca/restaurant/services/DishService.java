
package co.unicauca.restaurant.services;

import co.unicauca.restaurant.commons.domain.Dish;
import co.unicauca.restaurant.commons.infra.JsonError;
import co.unicauca.restaurant.commons.infra.Utilities;
import co.unicauca.restaurant.server.access.IDishRepository;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

/**
 * Servicio de clientes. Da acceso a la lógica de negocio
 *
 * @author Javier
 */
public class DishService {

    /**
     * Repositorio de platos
     */
    IDishRepository repo;

    /**
     * Constructor parametrizado. Hace inyeccion de dependencias
     *
     * @param repo repositorio de tipo IDishRepository
     */
    public DishService(IDishRepository repo) {
        this.repo = repo;
    }

    /**
     * Buscar un plato
     *
     * @param id identificador del plato
     * @return objeto tipo Dish
     */
    public Dish findDish(String id) {
        return repo.findDish(id);
    }

    /**
     * Crea un nuevo dish, Aplica validaciones de negocio
     *
     * @param dish el plato a ser guardado
     * @return devuelve el identificador del plato creado
     */
    public String createDish(Dish dish) {
        List<JsonError> errors = new ArrayList<>();

        // Validaciones y reglas de negocio
        if (dish.getId().isEmpty() || dish.getName().isEmpty()
                || dish.getDescription().isEmpty() || dish.getPrice().isEmpty()
                || dish.getSize().isEmpty()) {
            errors.add(new JsonError("400", "BAD_REQUEST", "id, nombre, descripcion, precio y tamaño son obligatorios. "));
        }

        if (dish.getSize().equals("ALL") || dish.getSize().equals("HALF")) {
            errors.add(new JsonError("400", "BAD_REQUEST", "Tamaño debe ser ALL o HALF"));
        }

        // Que no esté repetido
        Dish dishSearched = this.findDish(dish.getId());
        if (dishSearched != null) {
            errors.add(new JsonError("400", "BAD_REQUEST", "El id ya existe. "));
        }

        if (!errors.isEmpty()) {
            Gson gson = new Gson();
            String errorsJson = gson.toJson(errors);
            return errorsJson;
        }
        return repo.createDish(dish);
    }
}
