
package co.unicauca.restaurant.client.access;

import co.unicauca.restaurant.client.access.IDishAccess;
import co.unicauca.restaurant.commons.infra.Protocol;
import co.unicauca.restaurant.commons.domain.Dish;
import co.unicauca.restaurant.commons.infra.JsonError;
import co.unicauca.restaurant.client.infra.RestaurantSocket;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Servicio de Cliente. Permite hacer el CRUD de clientes solicitando los
 * servicios con la aplicación server. Maneja los errores devueltos
 *
 * @author Javier
 */
public class DishAccessImplSockets implements IDishAccess {

    /**
     * El servicio utiliza un socket para comunicarse con la aplicación server
     */
    private RestaurantSocket mySocket;

    public DishAccessImplSockets() {
        mySocket = new RestaurantSocket();
    }

    /**
     * Busca un Dish. Utiliza socket para pedir el servicio al servidor
     *
     * @param id identificador del plato
     * @return Objeto Dish
     * @throws Exception cuando no pueda conectarse con el servidor
     */
    @Override
    public Dish findDish(String id) throws Exception {
        String jsonResponse = null;
        String requestJson = findDishRequestJson(id);
        try {
            mySocket.connect();
            jsonResponse = mySocket.sendStream(requestJson);
            mySocket.closeStream();
            mySocket.disconnect();

        } catch (IOException ex) {
            Logger.getLogger(DishAccessImplSockets.class.getName()).log(Level.SEVERE, "No hubo conexión con el servidor", ex);
        }
        if (jsonResponse == null) {
            throw new Exception("No se pudo conectar con el servidor. Revise la red o que el servidor esté escuchando. ");
        } else {
            if (jsonResponse.contains("error")) {
                //Devolvió algún error
                Logger.getLogger(DishAccessImplSockets.class.getName()).log(Level.INFO, jsonResponse);
                throw new Exception(extractMessages(jsonResponse));
            } else {
                //Encontró el dish
                Dish dish = jsonToDish(jsonResponse);
                return dish;
            }
        }

    }

    /**
     * Crea un Dish. Utiliza socket para pedir el servicio al servidor
     *
     * @param dish plato del restaurante
     * @return el id del plato creado
     * @throws Exception error crear el plato
     */
    @Override
    public String createDish(Dish dish) throws Exception {
        String jsonResponse = null;
        String requestJson = createDishRequestJson(dish);
        try {
            mySocket.connect();
            jsonResponse = mySocket.sendStream(requestJson);
            mySocket.closeStream();
            mySocket.disconnect();

        } catch (IOException ex) {
            Logger.getLogger(DishAccessImplSockets.class.getName()).log(Level.SEVERE, "No hubo conexión con el servidor", ex);
        }
        if (jsonResponse == null) {
            throw new Exception("No se pudo conectar con el servidor");
        } else {

            if (jsonResponse.contains("error")) {
                //Devolvió algún error                
                Logger.getLogger(DishAccessImplSockets.class.getName()).log(Level.INFO, jsonResponse);
                throw new Exception(extractMessages(jsonResponse));
            } else {
                //Agregó correctamente, devuelve el id del plato
                return dish.getId();
            }

        }

    }

    /**
     * Extra los mensajes de la lista de errores
     *
     * @param jsonResponse lista de mensajes json
     * @return Mensajes de error
     */
    private String extractMessages(String jsonResponse) {
        JsonError[] errors = jsonToErrors(jsonResponse);
        String msjs = "";
        for (JsonError error : errors) {
            msjs += error.getMessage();
        }
        return msjs;
    }

    /**
     * Convierte el jsonError a un array de objetos jsonError
     *
     * @param jsonError
     * @return objeto MyError
     */
    private JsonError[] jsonToErrors(String jsonError) {
        Gson gson = new Gson();
        JsonError[] error = gson.fromJson(jsonError, JsonError[].class);
        return error;
    }

    /**
     * Crea una solicitud json para ser enviada por el socket
     *
     *
     * @param idDish identificación del plato
     * @return solicitud de consulta del plato en formato Json, algo como:
     * {"resource":"dish","action":"get","parameters":[{"name":"id","value":"2020001"}]}
     */
    private String findDishRequestJson(String idDish) {
        Protocol protocol = new Protocol();
        protocol.setResource("dish");
        protocol.setAction("get");
        protocol.addParameter("id", idDish);

        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);

        return requestJson;
    }

    /**
     * Crea la solicitud json de creación del dish para ser enviado por el
     * socket
     *
     * @param dish objeto dish
     * @return devulve algo como:
     * {"resource":"dish","action":"post","parameters":[{"name":"id","value":"2020001"},{"name":"nombrePlato","value":"Arroz
     * chino"},...}]}
     */
    private String createDishRequestJson(Dish dish) {
        Protocol protocol = new Protocol();
        protocol.setResource("dish");
        protocol.setAction("post");
        protocol.addParameter("id", dish.getId());
        protocol.addParameter("name", dish.getName());
        protocol.addParameter("description", dish.getDescription());
        protocol.addParameter("price", dish.getPrice());
        protocol.addParameter("size", dish.getSize());

        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);
        System.out.println("json: " + requestJson);

        return requestJson;
    }

    /**
     * Convierte jsonDish, proveniente del server socket, de json a un objeto
     * Dish
     *
     * @param jsonDish objeto cliente en formato json
     */
    private Dish jsonToDish(String jsonDish) {

        Gson gson = new Gson();
        Dish dish = gson.fromJson(jsonDish, Dish.class);

        return dish;
    }
}
