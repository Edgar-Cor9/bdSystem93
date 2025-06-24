package Logica_Operaciones_BD;

import GUI.Cliente;
import GUI.Login;
import GUI.Principal;
import GUI.TransaccionesIngreso;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.locks.StampedLock;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public final class OperarcionesCRUD {

    private static OperarcionesCRUD lainstance = new OperarcionesCRUD();
    private Connection conexion;

    private OperarcionesCRUD() {

    }

    public static OperarcionesCRUD getInstance() {
        return lainstance;
    }

    public void iniciarConexionBD() {
        this.conexion = ConexionBD.iniciarConexion();
    }

    public void cerrarConexionBD() throws SQLException {
        if (this.conexion != null && this.conexion.isClosed() == false) {
            this.conexion.close();
        }
    }
// aqui se consulta a la base de datos para el acceso a login

    public void login(String user, String pass) throws SQLException { // acceso a la ventana login
        this.iniciarConexionBD();
        Login lg = new Login();
        String status;
        status = "Activo";
        Statement stm = this.conexion.createStatement();
        String sql = "select status from usuarios where username = '" + user
                + "' and password = '" + pass + "'and  status = '" + status + "'";

        ResultSet rst = stm.executeQuery(sql);
        if (rst.next()) {
            lg.dispose();
            new Principal().setVisible(true);

        } else {
            JOptionPane.showMessageDialog(null, "USUARIO INACTIVO / PASSWORD INCORRECTOS");
            lg.show(true);
        }
        this.cerrarConexionBD();

    }
    //aqui consultamos los datos a la base de datos si el numero de cedula le pertenece a un cliente

    public ArrayList<Vector<String>> OperacionesTransacciones(String p_cedula) throws SQLException {
        this.iniciarConexionBD();

        ArrayList<Vector<String>> matriz = new ArrayList<>();
        Statement stm = this.conexion.createStatement();
        String sql = "select c.cod_cliente, c.persona_cedula, c.usuario_cod_registro,  p.nombres , p.apellidos  from cliente c join persona p on c.persona_cedula = p.cedula where persona_cedula = '" + p_cedula + "'";

        ResultSet rst = stm.executeQuery(sql);
        if (rst.next()) {
            Vector<String> datos = new Vector<>();
            String cliente, nombres, apellidos, codUsuario;
            cliente = rst.getString("cod_cliente");
            nombres = rst.getString("nombres");
            apellidos = rst.getString("apellidos");
            codUsuario = rst.getString("usuario_cod_registro");

            datos.add(cliente);
            datos.add(nombres);
            datos.add(apellidos);
            datos.add(codUsuario);
            matriz.add(datos);

        } else {
            JOptionPane.showMessageDialog(null, "Persona no se Encuentra Registrado como Cliente");

        }
        this.cerrarConexionBD();
        return matriz;
    }

    // consultar a la base de datos si el socio esta registrado
    public ArrayList<Vector<String>> cedulaCliente(String cedula) throws SQLException {

        this.iniciarConexionBD();
        Statement stm = this.conexion.createStatement();
        ArrayList<Vector<String>> matriz = new ArrayList<>();
        String sql = "SELECT\n"
                + "    c.cedula           AS n_cedula,\n"
                + "    c.nombres          AS n_nombre,\n"
                + "    c.apellidos        AS n_apellios,\n"
                + "    c.edad             AS n_edad,\n"
                + "    c.correo           AS n_correo,\n"
                + "    c.fecha_ingreso    AS n_fechaingreso,\n"
                + "    c.usuario_registro AS n_usuario,\n"
                + "    u.cod_registro     AS n_cod_usua\n"
                + "FROM\n"
                + "    persona c\n"
                + "    \n"
                + "    join usuario u on u.usuario_registro = c.usuario_registro\n"
                + "WHERE\n"
                + "    c.cedula = '" + cedula + "'";
        ResultSet rst = stm.executeQuery(sql);

        while (rst.next()) {

            Vector<String> datos = new Vector<>();
            String cedul, nombres, apellidos, edad, correo, fechaIngreso, usuario, cod_user;

            cedul = rst.getString("n_cedula");
            nombres = rst.getString("n_nombre");
            apellidos = rst.getString("n_apellios");
            edad = rst.getString("n_edad");
            correo = rst.getString("n_correo");
            fechaIngreso = rst.getString("n_fechaIngreso");
            usuario = rst.getString("n_usuario");
            cod_user = rst.getString("n_cod_usua");

            datos.add(cedul);
            datos.add(nombres);
            datos.add(apellidos);
            datos.add(edad);
            datos.add(correo);
            datos.add(fechaIngreso);
            datos.add(usuario);
            datos.add(cod_user);
            matriz.add(datos);

        }
        this.cerrarConexionBD();
        return matriz;
    }

    //para actualizar datos personales a personas
    public void ActulizarDatoS(ArrayList<Vector<String>> datos) throws SQLException {

        this.iniciarConexionBD();
        Statement stm = this.conexion.createStatement();

        ArrayList<Vector<String>> matriz = datos;

        for (Vector<String> vector : matriz) {

            String nombres, apellidos, edad, correo, usuarios, fechaact, cedul;
            cedul = vector.get(0);

            nombres = vector.get(1);
            apellidos = vector.get(2);
            edad = vector.get(3);
            correo = vector.get(4);
            fechaact = vector.get(5);
            usuarios = vector.get(6);

            // String sql = "update persona set nombres = '" + nombres + "' where cedula = '" + cedul + "'";
            String sql = "update persona set nombres ='" + nombres + "', apellidos ='" + apellidos + "', edad ='" + edad + "', correo ='" + correo + "', usuario_registro ='" + usuarios + "', fecha_actual ='" + fechaact + "' where cedula = '" + cedul + "'";
            //  String sql = "update persona set nombres = " + nombres + ", apellidos =" + apellidos + ", edad =" + edad + ", correo =" + correo + ", usuario_registro =" + usuarios + ", fe_actualizacion =" + fechaact + " where cedula = '" + cedul + "'";

            ResultSet rst = stm.executeQuery(sql);

        }

        this.cerrarConexionBD();

    }

    //Crear Persona
    public void InsertarPersona(ArrayList<Vector<String>> datos) throws SQLException {
        this.iniciarConexionBD();
        Statement stm = this.conexion.createStatement();
        ArrayList<Vector<String>> matriz = datos;
        for (Vector<String> vector : matriz) {
            String nombres, apellidos, edad, correo, usuarios, fechaact, cedul, caja;

            cedul = vector.get(0);
            nombres = vector.get(1);
            apellidos = vector.get(2);
            edad = vector.get(3);
            correo = vector.get(4);
            fechaact = vector.get(5);
            usuarios = vector.get(6);
            caja = vector.get(7);

            // String sql = "inset into persona  nombres ='" + nombres + "', apellidos ='" + apellidos + "', edad ='" + edad + "', correo ='" + correo + "', usuario_registro ='" + usuarios + "', fecha_actual ='" + fechaact + "' where cedula = '" + cedul + "'";
            String sql = "select cedula from persona where cedula = '" + cedul + "'";

            ResultSet rst = stm.executeQuery(sql);
            if (rst.next()) {
                JOptionPane.showMessageDialog(null, "!! No puede Guardar a esta Persona ya se encuentra Registrado !!\n");
                this.cerrarConexionBD();
            } else {
                this.iniciarConexionBD();
                Statement stm2 = this.conexion.createStatement();
                String sql1 = " INSERT INTO PERSONA (CEDULA, NOMBRES, APELLIDOS, EDAD, CORREO, FECHA_INGRESO, USUARIO_REGISTRO, CAJAAHORRO_CAJAAHORRO_ID) VALUES ('" + cedul + "', '" + nombres + "', '" + apellidos + "', '" + edad + "', '" + correo + "', TO_DATE('" + fechaact + "'), '" + usuarios + "', '" + caja + "')";
                ResultSet rst2 = stm.executeQuery(sql1);
                JOptionPane.showMessageDialog(null, "!! Persona Guardada con Exito !!\n");
            }
            this.cerrarConexionBD();
        }

    }

    // Crear Usuario /Administrador
    public void IngresarUsuario(ArrayList<Vector<String>> datos) throws SQLException {
        this.iniciarConexionBD();
        Statement stm = this.conexion.createStatement();
        ArrayList<Vector<String>> matriz = datos;

        for (Vector<String> vector : matriz) {

        }
    }

    // Para consultar si esta ingresado un usuario
    public void ConsularUsuario(String cedula) throws SQLException {
        this.iniciarConexionBD();
        Statement stm = this.conexion.createStatement();
        String ced = cedula;
        String sql = "select cedula from usuarios where cedula = '" + ced + "'";

        ResultSet rst = stm.executeQuery(sql);

        if (rst.next()) {
            JOptionPane.showMessageDialog(null, "!! No puede Guardar a esta Persona ya se encuentra Registrado !!\n");
            this.cerrarConexionBD();
        }

    }

    //Crear un Cliente
    public void CrearCliente(ArrayList<Vector<String>> datos) throws SQLException {
        this.iniciarConexionBD();
        Statement stm = this.conexion.createStatement();

        ArrayList<Vector<String>> matriz = datos;

        for (Vector<String> vector : matriz) {
            String cedula, codigo, fechaact;

            cedula = vector.get(0);
            codigo = vector.get(1);
            fechaact = vector.get(2);

            String sql = "select persona_cedula from clientes where persona_cedula = '" + cedula + "'";

            ResultSet rst = stm.executeQuery(sql);
            if (rst.next()) {
                JOptionPane.showMessageDialog(null, "!! No puede Guardar Cliente ya se encuentra Registrado !!\n");
                this.cerrarConexionBD();
            } else {
                this.iniciarConexionBD();
                Statement stm2 = this.conexion.createStatement();
                String sql2 = "INSERT INTO CLIENTES (FECHA_INGRESO, PERSONA_CEDULA, USUARIO_COD_REGISTRO) VALUES (TO_DATE('" + fechaact + "'), '" + cedula + "', '" + codigo + "')";
                ResultSet rs = stm2.executeQuery(sql2);
                JOptionPane.showMessageDialog(null, "!! Cliente guardado con Exito !!\n");
            }
        }
        this.cerrarConexionBD();
    }

    public void realizarTransaccion(ArrayList<Vector<String>> datos) throws SQLException {
        this.iniciarConexionBD();
        Statement stm = this.conexion.createStatement();
        ArrayList<Vector<String>> matriz = datos;

        for (Vector<String> vector : matriz) {
            String apert, codCliente, codUsuario, concepto, ret, dep, fecha;
            int valorActual;
            int saldo;
            int var = 0;
            concepto = vector.get(0);
            dep = vector.get(1);
            ret = vector.get(2);
            fecha = vector.get(3);
            codCliente = vector.get(4);
            codUsuario = vector.get(5);
            apert = vector.get(6);

            switch (concepto) {
                case "Apertura de Cuenta":

                    String sql = "select clientes_cod_cliente from cuenta where clientes_cod_cliente = '" + codCliente + "'";
                    ResultSet rst = stm.executeQuery(sql);
                    if (rst.next()) {
                        JOptionPane.showMessageDialog(null, "!! Socio ya tiene registrado una Cuenta !!");
                        this.cerrarConexionBD();

                    } else {

//                        this.iniciarConexionBD();
//                        Statement stm2 = this.conexion.createStatement();
                        String sql2 = "INSERT INTO CUENTA (CONCEPTO, INGRESO, EGRESO, SALDO, FECHA_TRANSACCION, CLIENTES_COD_CLIENTE, USUARIO_COD_REGISTRO) VALUES ('" + concepto + "','" + apert + "','" + var + "','" + apert + "',TO_DATE('" + fecha + "'), '" + codCliente + "', '" + codUsuario + "')";
                        ResultSet resu = stm.executeQuery(sql2);
                        JOptionPane.showMessageDialog(null, "!! Cuenta registrada Exitosamente !!");
                        this.cerrarConexionBD();
                    }
                    break;
                case "Deposito": {
                    this.iniciarConexionBD();
                    Statement st = this.conexion.createStatement();
                    //  String sq = "select saldo from cuenta where clientes_cod_cliente = '" + codCliente + "'";
                    String sq = "select * from(select cod_transaccion, saldo from cuenta where clientes_cod_cliente = '" + codCliente + "'" + "order by cod_transaccion desc) where rownum =1";
                    ResultSet rs = st.executeQuery(sq);
                    if (rs.next()) {

                        saldo = rs.getInt("saldo");
                        this.cerrarConexionBD();

//                        DecimalFormat formato2 = new DecimalFormat("#.##");
//                        System.out.println(formato2.format(numero1)); // Resultado => 3,3
//                        System.out.println(formato2.format(numero2)); // Resultado => 3,33
                        int vdepo = Integer.parseInt(dep);
                        valorActual = saldo + vdepo;

                        String sqls = "INSERT INTO CUENTA (CONCEPTO, INGRESO, EGRESO, SALDO, FECHA_TRANSACCION, CLIENTES_COD_CLIENTE, USUARIO_COD_REGISTRO) VALUES ('" + concepto + "','" + vdepo + "','" + var + "','" + valorActual + "',TO_DATE('" + fecha + "'), '" + codCliente + "', '" + codUsuario + "')";
                        ResultSet setef = stm.executeQuery(sqls);
                        JOptionPane.showMessageDialog(null, "!! Deposito Realizado Exitosamente !!");
                        this.cerrarConexionBD();
                    } else {
                        JOptionPane.showMessageDialog(null, "!! No puede Depositar / Registre una Apertura al Socio !!");
                    }
                    break;
                }
                case "Retiro": {
//                 
                    String sq = "select * from(select cod_transaccion, saldo from cuenta where clientes_cod_cliente = '" + codCliente + "'" + "order by cod_transaccion desc) where rownum =1";

                    //String sq = "select saldo from cuenta where clientes_cod_cliente = '" + codCliente + "'";
                    ResultSet rs = stm.executeQuery(sq);
                    if (rs.next()) {
                        saldo = rs.getInt("saldo");
                        this.cerrarConexionBD();

                        int vrep = Integer.valueOf(ret);
//                    

                        if (saldo < vrep || saldo == 0) {
                            JOptionPane.showMessageDialog(null, "!! Saldo Insuficiente !!");
                        } else if (saldo >= vrep) {
                            this.iniciarConexionBD();
                            Statement stms = this.conexion.createStatement();
                            valorActual = saldo - vrep;

                            String sqls = "INSERT INTO CUENTA (CONCEPTO,INGRESO, EGRESO, SALDO, FECHA_TRANSACCION, CLIENTES_COD_CLIENTE, USUARIO_COD_REGISTRO) VALUES ('" + concepto + "','" + var + "','" + vrep + "','" + valorActual + "',TO_DATE('" + fecha + "'), '" + codCliente + "', '" + codUsuario + "')";
                            ResultSet rsm = stms.executeQuery(sqls);
                            JOptionPane.showMessageDialog(null, "!! Retiro Realizado Exitosamente !!");
                            this.cerrarConexionBD();

                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "!! No puede Retirar / Registre una Apertura al Socio !!");
                    }
                    break;
                }

                default:
                    break;
            }

        }

    }
}
