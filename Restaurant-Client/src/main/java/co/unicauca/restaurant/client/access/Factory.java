
package co.unicauca.restaurant.client.access;

import co.unicauca.restaurant.commons.infra.Utilities;

/**
 * Fabrica que se encarga de instanciar DishServiceImplSockets o cualquier otro
 * que se cree en el futuro.
 *
 * @author Javier
 */
public class Factory {

    private static Factory instance;

    private Factory() {
    }

    /**
     * Clase singleton
     *
     * @return
     */
    public static Factory getInstance() {

        if (instance == null) {
            instance = new Factory();
        }
        return instance;

    }

    /**
     * Método que crea una instancia concreta de la jerarquia IDishService
     *
     * @return una clase hija de la abstracción IDishRepository
     */
    public IDishAccess getDishService() {

        IDishAccess result = null;
        String type = Utilities.loadProperty("dish.service");

        switch (type) {
            case "default":
                result = new DishAccessImplSockets();
                break;
        }

        return result;

    }
}
