
package co.unicauca.restaurant.commons.infra;

import java.util.ArrayList;
import java.util.List;

/**
 * Protocolo de comunicación entre la aplicación cliente y el servidor
 *
 * @author Javier
 */
public class Protocol {

    private String resource;
    private String action;
    private List<Parameter> parameters;

    /**
     * Constructor por defecto
     */
    public Protocol() {
        parameters = new ArrayList<>();
    }

    /**
     * Metodo getter
     *
     * @return el recurso
     */
    public String getResource() {
        return resource;
    }

    /**
     * Metodo setter
     *
     * @param resource el recurso
     */
    public void setResource(String resource) {
        this.resource = resource;
    }

    /**
     * Metodo getter
     *
     * @return la accion
     */
    public String getAction() {
        return action;
    }

    /**
     * Metodo setter
     *
     * @param action la accion
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * Metodo getter
     *
     * @return la lista de parametros
     */
    public List<Parameter> getParameters() {
        return parameters;
    }

    /**
     * Metodo setter
     *
     * @param parameters la lista de parametros
     */
    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }

    /**
     * Metodo que permite añadir parametros a la lista
     *
     * @param name nombre del parametro
     * @param value valor del parametro
     */
    public void addParameter(String name, String value) {
        parameters.add(new Parameter(name, value));
    }

}
