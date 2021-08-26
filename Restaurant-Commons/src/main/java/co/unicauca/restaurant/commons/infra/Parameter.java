
package co.unicauca.restaurant.commons.infra;

/**
 * Parametro. Lo usa la clase Protocol
 *
 * @author Javier
 */
public class Parameter {

    private String name;
    private String value;

    /**
     * Constructor parametrizado
     *
     * @param name nombre del parametro
     * @param value valor del parametro
     */
    public Parameter(String name, String value) {
        this.name = name;
        this.value = value;
    }

    /**
     * Constructor por defecto
     */
    public Parameter() {

    }

    /**
     * Metodo getter
     *
     * @return el nombre del parametro
     */
    public String getName() {
        return name;
    }

    /**
     * Metodo setter
     *
     * @param name el nombre del parametro
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Metodo getter
     *
     * @return el valor del parametro
     */
    public String getValue() {
        return value;
    }

    /**
     * Metodo setter
     *
     * @param value el valor del parametro
     */
    public void setValue(String value) {
        this.value = value;
    }

}
