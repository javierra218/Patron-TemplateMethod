
package co.unicauca.restaurant.commons.domain;

/**
 *
 * @author Javier
 */
public class Dish {

    private String id;
    private String name;
    private String description;
    private String price;
    private String size;

    /**
     * Constructor por defecto
     */
    public Dish() {
    }

    /**
     * Constructor parametrizado
     *
     * @param id identificador del plato
     * @param name nombre del plato
     * @param description descripcion del plato
     * @param price precio del plato
     * @param size tamaño del plato
     */
    public Dish(String id, String name, String description, String price, String size) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.size = size;
    }

    /**
     * Metodo setter
     *
     * @param id identificador del plato
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Metodo setter
     *
     * @param name nombre del plato
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Metodo setter
     *
     * @param description descripcion del plato
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Metodo setter
     *
     * @param price precio del plato
     */
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     * Metodo setter
     *
     * @param size tamaño del plato
     */
    public void setSize(String size) {
        this.size = size;
    }

    /**
     * Metodo getter
     *
     * @return el identificador del plato
     */
    public String getId() {
        return this.id;
    }

    /**
     * Metodo getter
     *
     * @return el nombre del plato
     */
    public String getName() {
        return this.name;
    }

    /**
     * Metodo getter
     *
     * @return la descripcion del plato
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Metodo getter
     *
     * @return el precio del plato
     */
    public String getPrice() {
        return this.price;
    }

    /**
     * Metodo getter
     *
     * @return el tamaño del plato
     */
    public String getSize() {
        return this.size;
    }
}
