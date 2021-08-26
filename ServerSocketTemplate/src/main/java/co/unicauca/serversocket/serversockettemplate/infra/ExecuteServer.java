
package co.unicauca.serversocket.serversockettemplate.infra;

import co.unicauca.serversocket.serversockettemplate.helpers.Utilities;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Julio Hurtado, Libardo Pantoja
 */
public class ExecuteServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         ServerSocketTemplate server;
            try {
                String className = Utilities.loadProperty("className");
                server = (ServerSocketTemplate)Class.forName(className).newInstance();
                server.startServer();
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
                Logger.getLogger(ExecuteServer.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
}
