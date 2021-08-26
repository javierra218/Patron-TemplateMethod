package co.unicauca.restaurant.server.infra;

import co.unicauca.serversocket.serversockettemplate.infra.ServerSocketTemplate;
import co.unicauca.restaurant.commons.domain.Dish;
import co.unicauca.restaurant.commons.infra.JsonError;
import co.unicauca.restaurant.commons.infra.Protocol;
import co.unicauca.restaurant.commons.infra.Utilities;
import co.unicauca.restaurant.server.access.Factory;
import co.unicauca.restaurant.services.DishService;
import co.unicauca.restaurant.server.access.IDishRepository;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

/**
 * Servidor Socket que está escuchando permanentemente solicitudes de los
 * clientes. Cada solicitud la atiende en un hilo de ejecución
 *
 * @author Javier
 */
public class ServerSocketRestaurant extends ServerSocketTemplate {

    /**
     * Servicio de clientes
     */
    private DishService service;

    /**
     * Constructor
     */
    public ServerSocketRestaurant() {
    }

    /**
     * Inicialización
     *
     * @return este mismo objeto
     */
    @Override
    protected ServerSocketTemplate init() {
        PORT = Integer.parseInt(Utilities.loadProperty("server.port"));
        // Se hace la inyección de dependencia
        IDishRepository repository = Factory.getInstance().getRepository();
        this.setService(new DishService(repository));
        return this;
    }

    /**
     * Procesar la solicitud que proviene de la aplicación cliente
     *
     * @param requestJson petición que proviene del cliente socket en formato
     * json que viene de esta manera:
     * "{"resource":"dish","action":"get","parameters":[{"name":"id","value":"1"}]}"
     *
     */
    @Override
    protected void processRequest(String requestJson) {
        // Convertir la solicitud a objeto Protocol para poderlo procesar
        Gson gson = new Gson();
        Protocol protocolRequest = gson.fromJson(requestJson, Protocol.class);

        switch (protocolRequest.getResource()) {
            case "dish":
                if (protocolRequest.getAction().equals("get")) {
                    // Consultar un dish
                    processGetDish(protocolRequest);
                }
                if (protocolRequest.getAction().equals("post")) {
                    // Agregar un dish  
                    processPostDish(protocolRequest);

                }
                break;
        }
    }

    /**
     * Procesa la solicitud de consultar un dish
     *
     * @param protocolRequest Protocolo de la solicitud
     */
    private void processGetDish(Protocol protocolRequest) {
        // Extraer el identificador del primer parámetro
        String id = protocolRequest.getParameters().get(0).getValue();
        Dish customer = getService().findDish(id);
        if (customer == null) {
            String errorJson = generateNotFoundErrorJson();
            respond(errorJson);
        } else {
            respond(objectToJSON(customer));
        }
    }

    /**
     * Procesa la solicitud de agregar un dish
     *
     * @param protocolRequest Protocolo de la solicitud
     */
    private void processPostDish(Protocol protocolRequest) {
        Dish dish = new Dish();
        // Reconstruir el dish a partid de lo que viene en los parámetros
        dish.setId(protocolRequest.getParameters().get(0).getValue());
        dish.setName(protocolRequest.getParameters().get(1).getValue());
        dish.setDescription(protocolRequest.getParameters().get(2).getValue());
        dish.setPrice(protocolRequest.getParameters().get(3).getValue());
        dish.setSize(protocolRequest.getParameters().get(4).getValue());

        String response = getService().createDish(dish);
        respond(response);
    }

    /**
     * Genera un ErrorJson de plato no encontrado
     *
     * @return error en formato json
     */
    private String generateNotFoundErrorJson() {
        List<JsonError> errors = new ArrayList<>();
        JsonError error = new JsonError();
        error.setCode("404");
        error.setError("NOT_FOUND");
        error.setMessage("Plato no encontrado. Plato no existe");
        errors.add(error);

        Gson gson = new Gson();
        String errorsJson = gson.toJson(errors);

        return errorsJson;
    }

    /**
     * @return the service
     */
    public DishService getService() {
        return service;
    }

    /**
     * @param service the service to set
     */
    public void setService(DishService service) {
        this.service = service;
    }
}
