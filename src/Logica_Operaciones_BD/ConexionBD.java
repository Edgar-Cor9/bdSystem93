package Logica_Operaciones_BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author edand
 */
public class ConexionBD {

    //  private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521/XE";
    //  private static final String DB_USER = "EMARTINEZ.";
    //  private static final String DB_PASSWORD = "123456";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/bd_systema";

    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";

    public static Connection iniciarConexion() {
        Connection conexion = null;
        try {
            //clase para oracle
            // Class.forName("oracle.jdbc.driver.OracleDriver");
            //clase para mysql
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = (Connection) DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            if (conexion != null) {
                System.out.println("Estado de conexión a la BD: Correcto.");
            }
        } catch (ClassNotFoundException err) {
            System.out.println("No se localiza la clase del manejador de la BD (OracleDriver).");
            err.printStackTrace();
        } catch (SQLException err) {
            System.out.println("Estado de conexión a la BD: Incorrecto.");
            err.printStackTrace();
        }
        return conexion;
    }
}
