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

    // usuario -------------------------------------------------------------------------------------------
    // Para consultar si esta ingresado un usuario
    public ArrayList<Vector<String>> cedulaUsuario(String cedula) throws SQLException {
        this.iniciarConexionBD();

        Statement stm = this.conexion.createStatement();
        ArrayList<Vector<String>> matriz = new ArrayList<>();
        String sql = "select idusuarios, cedula, nombres_usuario, apellidos_usuario, email, telefono, username, password, tipo_nivel, status, registrado_por, fecha_registro, ultima_actualizacion from usuarios where cedula = '" + cedula + "'";
        ResultSet rst = stm.executeQuery(sql);

        if (rst.next()) {

            Vector<String> datos = new Vector<>();
            String iduser, cedul, nombres, apellidos, email, telefono, username, pass, tipo_niv, status, registradox, fecha_regis, ult_actua;

            iduser = rst.getString("idusuarios");
            cedul = rst.getString("cedula");
            nombres = rst.getString("nombres_usuario");
            apellidos = rst.getString("apellidos_usuario");
            email = rst.getString("email");
            telefono = rst.getString("telefono");
            username = rst.getString("username");
            pass = rst.getString("password");
            tipo_niv = rst.getString("tipo_nivel");
            status = rst.getString("status");
            registradox = rst.getString("registrado_por");
            fecha_regis = rst.getString("fecha_registro");
            ult_actua = rst.getString("ultima_actualizacion");

            datos.add(iduser);
            datos.add(cedul);
            datos.add(nombres);
            datos.add(apellidos);
            datos.add(email);
            datos.add(telefono);
            datos.add(username);
            datos.add(pass);
            datos.add(tipo_niv);
            datos.add(status);
            datos.add(registradox);
            datos.add(fecha_regis);
            datos.add(ult_actua);

            matriz.add(datos);

        } else {
            JOptionPane.showMessageDialog(null, "!! Usuario no se encuentra Registrado / Debe ir a la ventana Registro !!\n");
        }
        this.cerrarConexionBD();
        return matriz;
    }

    // Crear Usuario /Administrador
    public void IngresarUsuario(ArrayList<Vector<String>> datos) throws SQLException {
        this.iniciarConexionBD();
        Statement stm = this.conexion.createStatement();
        ArrayList<Vector<String>> matriz = datos;

        for (Vector<String> vector : matriz) {
            String cedula, nombres, apellidos, email, telefono, username, password, tipo_nivel, status, registradox, fecharegis;

            cedula = vector.get(0);
            nombres = vector.get(1);
            apellidos = vector.get(2);
            email = vector.get(3);
            telefono = vector.get(4);
            username = vector.get(5);
            password = vector.get(6);
            tipo_nivel = vector.get(7);
            status = vector.get(8);
            registradox = vector.get(9);
            fecharegis = vector.get(10);

            String sql = "select cedula from usuarios where cedula = '" + cedula + "'";

            ResultSet rst = stm.executeQuery(sql);

            if (rst.next()) {
                JOptionPane.showMessageDialog(null, "!! No puede Guardar Usuario ya se encuentra Registrado !!\n");
                this.cerrarConexionBD();
            } else {
                this.iniciarConexionBD();
                Statement stm2 = this.conexion.createStatement();
                String sql2 = " INSERT INTO USUARIOS (CEDULA, NOMBRES_USUARIO, APELLIDOS_USUARIO, EMAIL, TELEFONO, USERNAME, PASSWORD, TIPO_NIVEL, STATUS, REGISTRADO_POR, FECHA_REGISTRO) VALUES ('" + cedula + "', '" + nombres + "', '" + apellidos + "', '" + email + "', '" + telefono + "','" + username + "', '" + password + "' , '" + tipo_nivel + "'  , '" + status + "' , '" + registradox + "', '" + fecharegis + "')";
                // ResultSet rst2 = stm2.executeQuery(sql2);
                stm2.executeUpdate(sql2);
                JOptionPane.showMessageDialog(null, "!! Usuario Guardado con Exito !!\n");
            }
            this.cerrarConexionBD();
        }
    }

    // Actualizar datos Usuario    
    public void ActualizarUsuario(ArrayList<Vector<String>> datos) throws SQLException {

        this.iniciarConexionBD();
        Statement stm = this.conexion.createStatement();
        Statement stm2 = this.conexion.createStatement();
        ArrayList<Vector<String>> matriz = datos;

        for (Vector<String> vector : matriz) {
            String cedula, nombres, apellidos, email, telefono, username, tipo_nivel, status, usuario, fecha;

            cedula = vector.get(0);
            nombres = vector.get(1);
            apellidos = vector.get(2);
            email = vector.get(3);
            telefono = vector.get(4);
            username = vector.get(5);
            tipo_nivel = vector.get(6);
            status = vector.get(7);
            usuario = vector.get(8);
            fecha = vector.get(9);

            String sql2 = "select cedula from usuarios where cedula ='" + cedula + "'";
            ResultSet rs = stm2.executeQuery(sql2);

            if (rs.next()) {
                String sql = "update usuarios set nombres_usuario ='" + nombres + "',apellidos_usuario = '" + apellidos + "', email = '" + email + "', telefono ='" + telefono + "',username = '" + username + "',tipo_nivel = '" + tipo_nivel + "', status = '" + status + "', registrado_por ='" + usuario + "', ultima_actualizacion = '" + fecha + "' where cedula = '" + cedula + "'";
                stm.executeUpdate(sql);
                JOptionPane.showMessageDialog(null, "!! Datos Actualizados con Exito !!\n");
            } else {
                JOptionPane.showMessageDialog(null, "!! No se puede Actualizar usuario no Registrado !!\n");
            }

        }
        this.cerrarConexionBD();
    }

    // aqui se consulta a la base de datos para el acceso a login
    public void login(String user, String pass) throws SQLException { // acceso a la ventana login
        this.iniciarConexionBD();
        Login lg = new Login();
        String status;
        status = "Activo";
        String statusActual = "Bloqueado";

        int intentos = 3;
        Statement stm = this.conexion.createStatement();
        String sql = "select status from usuarios where username = '" + user
                + "' and password = '" + pass + "'and  status = '" + status + "'";

        ResultSet rst = stm.executeQuery(sql);

        if (rst.next()) {
            lg.dispose();
            new Principal().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "PASSWORD INCORRECTOS");
            intentos--;
            System.out.println(intentos);
            lg.show(true);
        }

        if (intentos == 0) {
            JOptionPane.showMessageDialog(null, "USUARIO BLOQUEADO");
            ActEstadoUser(user, statusActual);

        }

//         if (rst.next()) {
//            lg.dispose();
//            new Principal().setVisible(true);
//
//        } else if (veces == 3) {
//            JOptionPane.showMessageDialog(null, "USUARIO BLOQUEADO");
//            ActEstadoUser(user, statusActual);
//
//        } else {
//            JOptionPane.showMessageDialog(null, "PASSWORD INCORRECTOS");
//            veces = veces + 1;
//            System.out.println(veces);
//            lg.show(true);
//        }
        this.cerrarConexionBD();

    }

    //para actualizar el estado del usuario
    public void ActEstadoUser(String usuario, String stado) throws SQLException {
        String user = usuario;
        String status = stado;
        this.iniciarConexionBD();

        Statement stm = this.conexion.createStatement();
        String sql = "update usuarios set status ='" + status + "' where username = '" + user + "'";

        stm.executeUpdate(sql);
        this.cerrarConexionBD();
    }

    public void ActPerfilUser(String stado, String nivel, String user) throws SQLException {
        String status = stado;
        String tiponivel = nivel;
        String usuario = user;

        this.iniciarConexionBD();

        Statement stm = this.conexion.createStatement();

        String sql = "update usuarios set tipo_nivel = '" + tiponivel + "', status = '" + status + "' where username = '" + usuario + "'";
        stm.executeUpdate(sql);
        this.cerrarConexionBD();

    }

    // para consultar si el usuario se encuentra en estado activo
    public void EstadoUsuario(String usuario, String password) throws SQLException {
        this.iniciarConexionBD();

        Login lg = new Login();
        String user = usuario;
        String pass = password;
        String status = "Activo";
        Statement stm = this.conexion.createStatement();

        String sql = "select status from usuarios where username ='" + user + "' and status = '" + status + "'";

        ResultSet rst = stm.executeQuery(sql);

        if (rst.next()) {
            login(user, pass);
        } else {
            JOptionPane.showMessageDialog(null, "Usuario no se encuentra Activo, contacte con el administrador !!!!");
            lg.show(true);
        }

    }

    //para consultar si el usuario se encuentra registrado 
    public void UsuarioRegistrado(String usuario, String password) throws SQLException {
        this.iniciarConexionBD();
        Login lg = new Login();

        String user = usuario;
        String pass = password;

        Statement stm = this.conexion.createStatement();
        String sql = "select username from usuarios where username = '" + user + "'";

        ResultSet rs = stm.executeQuery(sql);

        if (rs.next()) {
            EstadoUsuario(usuario, password);
        } else {
            JOptionPane.showMessageDialog(null, "USUARIO NO SE ENCUENTRA REGISTRADO");
            lg.show(true);
        }
        this.cerrarConexionBD();

    }

    //actualizacion de password del usuario
    public void ActPassUsuario(String usuario, String password) throws SQLException {
        this.iniciarConexionBD();

        String user = usuario;
        String pass = password;
        Statement stm = this.conexion.createStatement();
        String sql = "update usuarios set password = '" + pass + "' where username = '" + user + "'";

        stm.executeUpdate(sql);
        this.cerrarConexionBD();
    }

    //para traer el codigo del usuario
    public String codigoUser(String username) throws SQLException {

        this.iniciarConexionBD();
        String coduser = "";
        Statement stm = this.conexion.createStatement();

        String sql = "select idusuarios from usuarios where username = '" + username + "'";
        ResultSet rst = stm.executeQuery(sql);
        if (rst.next()) {

            coduser = rst.getString("idusuarios");
        }
        this.cerrarConexionBD();

        return coduser;

    }

    // cliente ---------------------------------------------------------------------------------------------------
    // consultar a la base de datos si el socio esta registrado
    public ArrayList<Vector<String>> cedulaCliente(String cedula) throws SQLException {

        this.iniciarConexionBD();
        Statement stm = this.conexion.createStatement();
        ArrayList<Vector<String>> matriz = new ArrayList<>();
        String sql = "select idclientes, cedula_cliente, nombres_cliente, apellidos_cliente, email_cliente, direccion_cliente, edad, username, idusuarios, fecha_ingreso,username_Actu, fecha_actualizacion, comentario from clientes where cedula_cliente = '" + cedula + "'";
        ResultSet rst = stm.executeQuery(sql);

        while (rst.next()) {

            Vector<String> datos = new Vector<>();
            String idclient, cedul, nombres, apellidos, email, direccion, edad, username, idusuario, fechaIngr, userActu, fechaAct, comentario;

            idclient = rst.getString("idclientes");
            cedul = rst.getString("cedula_cliente");
            nombres = rst.getString("nombres_cliente");
            apellidos = rst.getString("apellidos_cliente");
            email = rst.getString("email_cliente");
            direccion = rst.getString("direccion_cliente");
            edad = rst.getString("edad");
            username = rst.getString("username");
            idusuario = rst.getString("idusuarios");
            fechaIngr = rst.getString("fecha_ingreso");
            userActu = rst.getString("username_Actu");
            fechaAct = rst.getString("fecha_actualizacion");
            comentario = rst.getString("comentario");

            datos.add(idclient);
            datos.add(cedul);
            datos.add(nombres);
            datos.add(apellidos);
            datos.add(email);
            datos.add(direccion);
            datos.add(edad);
            datos.add(username);
            datos.add(idusuario);
            datos.add(fechaIngr);
            datos.add(userActu);
            datos.add(fechaAct);
            datos.add(comentario);

            matriz.add(datos);

        }
        this.cerrarConexionBD();
        return matriz;
    }

    //Crear Persona
    public void InsertarPersona(ArrayList<Vector<String>> datos) throws SQLException {
        this.iniciarConexionBD();
        Statement stm = this.conexion.createStatement();
        ArrayList<Vector<String>> matriz = datos;
        for (Vector<String> vector : matriz) {
            String cedul, nombres, apellidos, email, direccion, edad, username, idusuario, fechaIngreso, fechaActual, comentario;

            cedul = vector.get(0);
            nombres = vector.get(1);
            apellidos = vector.get(2);
            email = vector.get(3);
            direccion = vector.get(4);
            edad = vector.get(5);
            username = vector.get(6);
            idusuario = vector.get(7);
            fechaIngreso = vector.get(8);
            fechaActual = vector.get(9);
            comentario = vector.get(10);

            String sql = "select cedula_cliente from clientes where cedula_cliente = '" + cedul + "'";

            ResultSet rst = stm.executeQuery(sql);
            if (rst.next()) {
                JOptionPane.showMessageDialog(null, "!! No puede Guardar a esta Persona ya se encuentra Registrado !!\n");
                this.cerrarConexionBD();
            } else {
                this.iniciarConexionBD();
                Statement stm2 = this.conexion.createStatement();
                String sql1 = " INSERT INTO clientes (CEDULA_CLIENTE, NOMBRES_CLIENTE, APELLIDOS_CLIENTE, EMAIL_CLIENTE, DIRECCION_CLIENTE,EDAD,USERNAME, IDUSUARIOS, FECHA_INGRESO,FECHA_ACTUALIZACION, COMENTARIO) VALUES ('" + cedul + "', '" + nombres + "', '" + apellidos + "', '" + email + "', '" + direccion + "', '" + edad + "','" + username + "', '" + idusuario + "', '" + fechaIngreso + "', '" + fechaActual + "', '" + comentario + "')";
                stm2.executeUpdate(sql1);
                JOptionPane.showMessageDialog(null, "!! Persona Guardada con Exito !!\n");
            }
            this.cerrarConexionBD();
        }

    }

    //para actualizar datos personales a personas
    public void ActulizarDatoS(ArrayList<Vector<String>> datos) throws SQLException {

        this.iniciarConexionBD();
        Statement stm = this.conexion.createStatement();

        ArrayList<Vector<String>> matriz = datos;

        for (Vector<String> vector : matriz) {

            String cedul, nombres, apellidos, email, direccion, edad, username, idusuario, fechaA, comentario;

            cedul = vector.get(0);
            nombres = vector.get(1);
            apellidos = vector.get(2);
            email = vector.get(3);
            direccion = vector.get(4);
            edad = vector.get(5);
            username = vector.get(6);
            idusuario = vector.get(7);
            fechaA = vector.get(8);
            comentario = vector.get(9);

            String sql1 = "select cedula_cliente from clientes where cedula_cliente = '" + cedul + "'";
            ResultSet rs = stm.executeQuery(sql1);

            if (rs.next()) {
                // String sql = "update persona set nombres = '" + nombres + "' where cedula = '" + cedul + "'";
                String sql = "update clientes set nombres_cliente ='" + nombres + "', apellidos_cliente ='" + apellidos + "', email_cliente ='" + email + "', edad ='" + edad + "', username_Actu ='" + username + "', fecha_actualizacion ='" + fechaA + "', comentario = '" + comentario + "' where cedula_cliente = '" + cedul + "'";
                //  String sql = "update persona set nombres = " + nombres + ", apellidos =" + apellidos + ", edad =" + edad + ", correo =" + correo + ", usuario_registro =" + usuarios + ", fe_actualizacion =" + fechaact + " where cedula = '" + cedul + "'";
                stm.executeUpdate(sql);
                JOptionPane.showMessageDialog(null, "!! Datos Actualizados con Exito !!\n");
            } else {
                JOptionPane.showMessageDialog(null, "!! No puede Actualizar personsa no se encuentra Registrado !!\n");
            }

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

    // Productos
    //---------------------------------------------------------------------------------------------------------------
    public ArrayList<Vector<String>> BusquedaProducto(String tipo) throws SQLException {
        this.iniciarConexionBD();

        ArrayList<Vector<String>> matriz = new ArrayList<>();
        Statement smt = this.conexion.createStatement();

        String sql = "select tipo_producto from productos where tipo_producto ='" + tipo + "'";

        ResultSet rs = smt.executeQuery(sql);
        while (rs.next()) {
            Vector<String> datos = new Vector<>();

            String codproductos, idusuario, username, nombProducto, tiproducto, detaprod, fechIngre, cantpro, fechAct, precio, iva, total;

            codproductos = rs.getString("codproductos");
            idusuario = rs.getString("idusuarios");
            username = rs.getString("username");
            nombProducto = rs.getString("nombre_producto");
            tiproducto = rs.getString("tipo_producto");
            detaprod = rs.getString("detalle_producto");
            fechIngre = rs.getString("fecha_ingreso");
            cantpro = rs.getString("cantidad_producto");
            fechAct = rs.getString("fecha_Actualizacion");
            precio = rs.getString("precio");
            iva = rs.getString("iva");
            total = rs.getString("total");

            datos.add(codproductos);
            datos.add(idusuario);
            datos.add(username);
            datos.add(nombProducto);
            datos.add(tiproducto);
            datos.add(detaprod);
            datos.add(fechIngre);
            datos.add(cantpro);
            datos.add(fechAct);
            datos.add(precio);
            datos.add(iva);
            datos.add(total);

            matriz.add(datos);

        }

        this.cerrarConexionBD();
        return matriz;
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
