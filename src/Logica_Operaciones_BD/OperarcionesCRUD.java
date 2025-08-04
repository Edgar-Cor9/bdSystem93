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

    //-----------------------------------------------------------------------------------------------------------------
    // Usuarios
    //-----------------------------------------------------------------------------------------------------------------
    // Para consultar si esta ingresado un usuario
    public ArrayList<Vector<String>> cedulaUsuario(String cedula) throws SQLException {
        this.iniciarConexionBD();
        Statement stm = this.conexion.createStatement();
        ArrayList<Vector<String>> matriz = new ArrayList<>();

        try {
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
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "!! Error al Consultar Usuario" + e);
            System.out.println("Error al consultar usuario" + e);
        }

        this.cerrarConexionBD();
        return matriz;
    }

    // Crear Usuario /Administrador
    public void IngresarUsuario(ArrayList<Vector<String>> datos) throws SQLException {
        this.iniciarConexionBD();
        Statement stm = this.conexion.createStatement();
        ArrayList<Vector<String>> matriz = datos;

        try {
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
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "!! Error al Crear Usuario" + e);
            System.out.println("Error al Crear usuario" + e);
        }

    }

    // Actualizar datos Usuario    
    public void ActualizarUsuario(ArrayList<Vector<String>> datos) throws SQLException {

        this.iniciarConexionBD();
        Statement stm = this.conexion.createStatement();
        Statement stm2 = this.conexion.createStatement();
        ArrayList<Vector<String>> matriz = datos;

        try {
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
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "!! Error al Actualizar datos de Usuario" + e);
            System.out.println("Error al Actualizar datos de Usuario" + e);
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
        try {
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
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "!! Error al Acesso del Usuario" + e);
            System.out.println("Error al Acceso del Usuario" + e);
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
        try {
            String sql = "update usuarios set status ='" + status + "' where username = '" + user + "'";
            stm.executeUpdate(sql);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "!! Error al Actualizar estado del Usuario" + e);
            System.out.println("Error al Actualizar estado del Usuario" + e);
        }

        this.cerrarConexionBD();
    }

    public void ActPerfilUser(String stado, String nivel, String user) throws SQLException {
        String status = stado;
        String tiponivel = nivel;
        String usuario = user;

        this.iniciarConexionBD();
        Statement stm = this.conexion.createStatement();

        try {
            String sql = "update usuarios set tipo_nivel = '" + tiponivel + "', status = '" + status + "' where username = '" + usuario + "'";
            stm.executeUpdate(sql);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "!! Error al Actualizar Perfil del Usuario" + e);
            System.out.println("Error al Actualizar Perfil del Usuario" + e);
        }

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

        try {
            String sql = "select status from usuarios where username ='" + user + "' and status = '" + status + "'";
            ResultSet rst = stm.executeQuery(sql);

            if (rst.next()) {
                login(user, pass);
            } else {
                JOptionPane.showMessageDialog(null, "Usuario no se encuentra Activo, contacte con el administrador !!!!");
                lg.show(true);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "!! Error al Consultar si Usuario esta Activo" + e);
            System.out.println("Error al Consultar si Usuario esta Activo" + e);

        }
        this.cerrarConexionBD();
    }

    //para consultar si el usuario se encuentra registrado 
    public void UsuarioRegistrado(String usuario, String password) throws SQLException {
        this.iniciarConexionBD();
        Login lg = new Login();

        String user = usuario;
        String pass = password;
        Statement stm = this.conexion.createStatement();

        try {
            String sql = "select username from usuarios where username = '" + user + "'";
            ResultSet rs = stm.executeQuery(sql);

            if (rs.next()) {
                EstadoUsuario(usuario, password);
            } else {
                JOptionPane.showMessageDialog(null, "USUARIO NO SE ENCUENTRA REGISTRADO");
                lg.show(true);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "!! Error al Consultar si Usuario esta Registrado" + e);
            System.out.println("Error al Consultar si Usuario esta Registrado" + e);
        }

        this.cerrarConexionBD();

    }

    //actualizacion de password del usuario
    public void ActPassUsuario(String usuario, String password) throws SQLException {
        this.iniciarConexionBD();

        String user = usuario;
        String pass = password;
        Statement stm = this.conexion.createStatement();
        try {
            String sql = "update usuarios set password = '" + pass + "' where username = '" + user + "'";
            stm.executeUpdate(sql);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "!! Error al Actualizar Password del Usuario" + e);
            System.out.println("Error al Actualizar Password del Usuario" + e);
        }

        this.cerrarConexionBD();
    }

    //para traer el codigo del usuario
    public String codigoUser(String username) throws SQLException {

        this.iniciarConexionBD();
        String coduser = "";
        Statement stm = this.conexion.createStatement();

        try {
            String sql = "select idusuarios from usuarios where username = '" + username + "'";
            ResultSet rst = stm.executeQuery(sql);
            if (rst.next()) {
                coduser = rst.getString("idusuarios");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "!! Error al Consultar codigo del Usuario" + e);
            System.out.println("Error al Consultar codigo del Usuario" + e);
        }

        this.cerrarConexionBD();
        return coduser;
    }

    //-----------------------------------------------------------------------------------------------------------------
    // Clientes
    //-----------------------------------------------------------------------------------------------------------------
    // consultar a la base de datos si el socio esta registrado
    public ArrayList<Vector<String>> cedulaCliente(String cedula) throws SQLException {

        this.iniciarConexionBD();
        Statement stm = this.conexion.createStatement();
        ArrayList<Vector<String>> matriz = new ArrayList<>();
        try {
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
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "!! Error al Consultar datos del Socio" + e);
            System.out.println("Error al Consultar datos del Socio" + e);
        }

        this.cerrarConexionBD();
        return matriz;
    }

    //Crear Persona
    public void InsertarPersona(ArrayList<Vector<String>> datos) throws SQLException {
        this.iniciarConexionBD();
        Statement stm = this.conexion.createStatement();
        ArrayList<Vector<String>> matriz = datos;
        try {
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
                comentario = vector.get(9);

                String sql = "select cedula_cliente from clientes where cedula_cliente = '" + cedul + "'";

                ResultSet rst = stm.executeQuery(sql);
                if (rst.next()) {
                    JOptionPane.showMessageDialog(null, "!! No puede Guardar a esta Persona ya se encuentra Registrado !!\n");
                    this.cerrarConexionBD();
                } else {
                    this.iniciarConexionBD();
                    Statement stm2 = this.conexion.createStatement();
                    String sql1 = " INSERT INTO clientes (CEDULA_CLIENTE, NOMBRES_CLIENTE,"
                            + " APELLIDOS_CLIENTE, EMAIL_CLIENTE, DIRECCION_CLIENTE,EDAD,USERNAME, "
                            + "IDUSUARIOS, FECHA_INGRESO, COMENTARIO) VALUES ('" + cedul + "', '" + nombres + "',"
                            + " '" + apellidos + "', '" + email + "', '" + direccion + "', '" + edad + "','" + username + "', "
                            + "'" + idusuario + "', '" + fechaIngreso + "','" + comentario + "')";
                    stm2.executeUpdate(sql1);
                    JOptionPane.showMessageDialog(null, "!! Persona Guardada con Exito !!\n");
                }
                this.cerrarConexionBD();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "!! Error al crear Persona" + e);
            System.out.println("Error al crear Persona" + e);
        }

    }

    //para actualizar datos personales a personas
    public void ActulizarDatoS(ArrayList<Vector<String>> datos) throws SQLException {

        this.iniciarConexionBD();
        Statement stm = this.conexion.createStatement();
        ArrayList<Vector<String>> matriz = datos;

        try {
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

                    String sql = "update clientes set nombres_cliente ='" + nombres + "', "
                            + "apellidos_cliente ='" + apellidos + "', email_cliente ='" + email + "', "
                            + "edad ='" + edad + "', username_Actu ='" + username + "', fecha_actualizacion ='" + fechaA + "',"
                            + " comentario = '" + comentario + "' where cedula_cliente = '" + cedul + "'";

                    stm.executeUpdate(sql);
                    JOptionPane.showMessageDialog(null, "!! Datos Actualizados con Exito !!\n");
                } else {
                    JOptionPane.showMessageDialog(null, "!! No puede Actualizar personsa no se encuentra Registrado !!\n");
                }

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "!! Error al actualizar datos de Persona" + e);
            System.out.println("Error al actualizar datos de Persona" + e);
        }
        this.cerrarConexionBD();

    }

    //---------------------------------------------------------------------------------------------------------------
    // Productos
    //---------------------------------------------------------------------------------------------------------------
    //busqueda de producto por tipodeproducto
    public ArrayList<Vector<String>> BusquedaProducto(String tipo) throws SQLException {
        this.iniciarConexionBD();

        ArrayList<Vector<String>> matriz = new ArrayList<>();
        Statement smt = this.conexion.createStatement();

        try {
            // String sql = "select tipo_producto from productos where tipo_producto ='" + tipo + "'";
            String sql = "SELECT p.codproductos, p.nombre_producto, p.tipo_producto,p.detalle_producto, p.fecha_ingreso, p.precio, p.iva, pro.ruc, pro.nombres, u.username\n"
                    + "FROM bd_systema.productos p \n"
                    + "INNER JOIN bd_systema.proveedor pro \n"
                    + "on p.idusuarios = pro.idusuarios \n"
                    + "INNER JOIN bd_systema.usuarios u \n"
                    + "on p.idusuarios = u.idusuarios\n"
                    + "where p.tipo_producto ='" + tipo + "'";

            ResultSet rs = smt.executeQuery(sql);
            while (rs.next()) {
                Vector<String> datos = new Vector<>();

                String codproductos, username, nombProducto, tiproducto, detalle, ruc, nombresPro, fechIngre, precio, iva;

                codproductos = rs.getString("codproductos");
                nombProducto = rs.getString("nombre_producto");
                tiproducto = rs.getString("tipo_producto");
                detalle = rs.getString("detalle_producto");
                fechIngre = rs.getString("fecha_ingreso");
                precio = rs.getString("precio");
                iva = rs.getString("iva");
                ruc = rs.getString("ruc");
                nombresPro = rs.getString("nombres");
                //  idusuario = rs.getString("idusuarios");
                username = rs.getString("username");

                datos.add(codproductos);
                datos.add(nombProducto);
                datos.add(tiproducto);
                datos.add(detalle);
                datos.add(fechIngre);
                datos.add(precio);
                datos.add(iva);
                datos.add(ruc);
                datos.add(nombresPro);
                //  datos.add(idusuario);
                datos.add(username);
                matriz.add(datos);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "!! Error al Buscar producto por tipo de Producto" + e);
            System.out.println("Error al Buscar producto por tipo de Producto" + e);
        }

        this.cerrarConexionBD();
        return matriz;
    }

    //busqueda de producto por Proveedor
    public ArrayList<Vector<String>> BusquedaRucProveedor(String ruc) throws SQLException {
        this.iniciarConexionBD();
        ArrayList<Vector<String>> matriz = new ArrayList<>();
        Statement stm = this.conexion.createStatement();

        try {
            String sql = "SELECT p.codproductos, p.nombre_producto, p.tipo_producto,p.detalle_producto, p.fecha_ingreso, p.precio, p.iva, pro.ruc, pro.nombres, pro.idProveedor, u.username\n"
                    + "FROM bd_systema.productos p \n"
                    + "INNER JOIN bd_systema.proveedor pro \n"
                    + "on p.idProveedor = pro.idProveedor \n"
                    + "INNER JOIN bd_systema.usuarios u \n"
                    + "on p.idusuarios = u.idusuarios\n"
                    + "where pro.ruc = '" + ruc + "'";

            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                Vector<String> datos = new Vector<>();

                String codproductos, username, nombProducto, tiproducto, detalle, rucPro, nombresPro, fechIngre, precio, iva;

                codproductos = rs.getString("codproductos");
                nombProducto = rs.getString("nombre_producto");
                tiproducto = rs.getString("tipo_producto");
                detalle = rs.getString("detalle_producto");
                fechIngre = rs.getString("fecha_ingreso");
                precio = rs.getString("precio");
                iva = rs.getString("iva");
                rucPro = rs.getString("ruc");
                nombresPro = rs.getString("nombres");
                //  idusuario = rs.getString("idusuarios");
                username = rs.getString("username");

                datos.add(codproductos);
                datos.add(nombProducto);
                datos.add(tiproducto);
                datos.add(detalle);
                datos.add(fechIngre);
                datos.add(precio);
                datos.add(iva);
                datos.add(rucPro);
                datos.add(nombresPro);
                //  datos.add(idusuario);
                datos.add(username);
                matriz.add(datos);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "!! Error al Buscar producto por Ruc Proveedor" + e);
            System.out.println("Error al Buscar producto por Ruc Proveedor" + e);
        }

        return matriz;
    }

    //busqueda de producto por nombre Proveedor
    public ArrayList<Vector<String>> BusquedaNombresProveedor(String nombre, String apellido) throws SQLException {
        this.iniciarConexionBD();
        ArrayList<Vector<String>> matriz = new ArrayList<>();
        Statement stm = this.conexion.createStatement();

        try {
            String sql = "SELECT p.codproductos, p.nombre_producto, p.tipo_producto,p.detalle_producto, p.fecha_ingreso, p.precio, p.iva, pro.ruc, pro.nombres,pro.apellidos, pro.idProveedor, u.username\n"
                    + "FROM bd_systema.productos p \n"
                    + "INNER JOIN bd_systema.proveedor pro \n"
                    + "on p.idProveedor = pro.idProveedor \n"
                    + "INNER JOIN bd_systema.usuarios u \n"
                    + "on p.idusuarios = u.idusuarios\n"
                    + "where pro.nombres = '" + nombre + "' and pro.apellidos ='" + apellido + "'";

            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                Vector<String> datos = new Vector<>();

                String codproductos, username, nombProducto, tiproducto, detalle, rucPro, nombresPro, fechIngre, precio, iva;

                codproductos = rs.getString("codproductos");
                nombProducto = rs.getString("nombre_producto");
                tiproducto = rs.getString("tipo_producto");
                detalle = rs.getString("detalle_producto");
                fechIngre = rs.getString("fecha_ingreso");
                precio = rs.getString("precio");
                iva = rs.getString("iva");
                rucPro = rs.getString("ruc");
                nombresPro = rs.getString("nombres");
                //  idusuario = rs.getString("idusuarios");
                username = rs.getString("username");

                datos.add(codproductos);
                datos.add(nombProducto);
                datos.add(tiproducto);
                datos.add(detalle);
                datos.add(fechIngre);
                datos.add(precio);
                datos.add(iva);
                datos.add(rucPro);
                datos.add(nombresPro);
                //  datos.add(idusuario);
                datos.add(username);
                matriz.add(datos);

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "!! Error al Buscar producto por Nombre Proveedor" + e);
            System.out.println("Error al Buscar producto por Nombre Proveedor" + e);
        }

        return matriz;
    }

    // para registrar un producto    
    public void InsertarProducto(ArrayList<Vector<String>> datos) throws SQLException {
        this.iniciarConexionBD();
        Statement stm = this.conexion.createStatement();
        ArrayList<Vector<String>> matriz = datos;

        try {
            for (Vector<String> vector : matriz) {
                String iduser, idProve, nombre, tipo, detalle, fechaIn, precio, iva;

                iduser = vector.get(0);
                idProve = vector.get(1);
                nombre = vector.get(2);
                tipo = vector.get(3);
                detalle = vector.get(4);
                fechaIn = vector.get(5);
                precio = vector.get(6);
                iva = vector.get(7);

                String sql = "INSERT into productos "
                        + "(idusuarios, idProveedor, nombre_producto,"
                        + " tipo_producto, detalle_producto,"
                        + "fecha_ingreso,"
                        + "precio, iva) VALUES ('" + iduser + "','" + idProve + "', "
                        + "'" + nombre + "', '" + tipo + "', '" + detalle + "',"
                        + "'" + fechaIn + "', '" + precio + "', '" + iva + "')";
                JOptionPane.showMessageDialog(null, "!! Producto Guardado con Exito !!\n");
                stm.executeUpdate(sql);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "!! Error al Registrar producto" + e);
            System.out.println("Error al Registrar producto" + e);
        }
        this.cerrarConexionBD();
    }

    //consultar un producto por codigo
    public ArrayList<Vector<String>> CodigoProduto(String codProdu) throws SQLException {
        this.iniciarConexionBD();
        ArrayList<Vector<String>> matriz = new ArrayList<>();
        Statement stm = this.conexion.createStatement();

        try {
            String sql = "SELECT p.codproductos, p.nombre_producto, p.detalle_producto, p.tipo_producto,"
                    + " p.fecha_ingreso, p.fecha_Actualizacion, p.precio, p.iva, pro.ruc, pro.nombres, "
                    + "pro.apellidos, pro.idProveedor, u.username, u.idusuarios\n"
                    + "FROM bd_systema.productos p \n"
                    + "INNER JOIN bd_systema.proveedor pro \n"
                    + "on p.idProveedor = pro.idProveedor\n"
                    + "INNER JOIN bd_systema.usuarios u \n"
                    + "on p.idusuarios = u.idusuarios\n"
                    + "where p.codproductos = '" + codProdu + "'";
            ResultSet rst = stm.executeQuery(sql);

            while (rst.next()) {
                Vector<String> datos = new Vector<>();
                String codiPro, nombreProd, detaProd, tipoProd, fechRegistro, fechAc, prec, iva, rucPro, nombProve, apellProve, idProve, idUser, username;
                codiPro = rst.getString("codproductos");
                nombreProd = rst.getString("nombre_producto");
                detaProd = rst.getString("detalle_producto");
                tipoProd = rst.getString("tipo_producto");
                fechRegistro = rst.getString("fecha_ingreso");
                fechAc = rst.getString("fecha_Actualizacion");
                prec = rst.getString("precio");
                iva = rst.getString("iva");
                rucPro = rst.getString("ruc");
                nombProve = rst.getString("nombres");
                apellProve = rst.getString("apellidos");
                idProve = rst.getString("idProveedor");
                username = rst.getString("username");
                idUser = rst.getString("idusuarios");

                datos.add(codiPro);
                datos.add(nombreProd);
                datos.add(detaProd);
                datos.add(tipoProd);
                datos.add(fechRegistro);
                datos.add(fechAc);
                datos.add(prec);
                datos.add(iva);
                datos.add(rucPro);
                datos.add(nombProve);
                datos.add(apellProve);
                datos.add(idProve);
                datos.add(username);
                datos.add(idUser);
                matriz.add(datos);

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "!! Error al Consultar un producto por código" + e);
            System.out.println("Error al Consultar un producto por código" + e);
        }
        return matriz;
    }

    //editar un producto
    public void ActualizarProducto(ArrayList<Vector<String>> datos) throws SQLException {
        this.iniciarConexionBD();
        Statement stm = this.conexion.createStatement();
        ArrayList<Vector<String>> matriz = datos;

        try {
            for (Vector<String> vector : matriz) {
                String codiPro, idProve, nombreProd, detaProd, tipoProd, fechAc, prec, iva;

                codiPro = vector.get(0);
                idProve = vector.get(1);
                nombreProd = vector.get(2);
                detaProd = vector.get(3);
                tipoProd = vector.get(4);
                fechAc = vector.get(5);
                prec = vector.get(6);
                iva = vector.get(7);

                String sql = "select codproductos from productos where codproductos = '" + codiPro + "'";

                ResultSet rst = stm.executeQuery(sql);

                if (rst.next()) {
                    String sql2 = "update productos set idProveedor ='" + idProve + "', "
                            + "nombre_producto = '" + nombreProd + "', tipo_producto = '" + tipoProd + "',"
                            + "detalle_producto = '" + detaProd + "', fecha_Actualizacion = '" + fechAc + "',"
                            + "precio = '" + prec + "', iva = '" + iva + "' where codproductos = '" + codiPro + "'";
                    stm.executeUpdate(sql2);
                    JOptionPane.showMessageDialog(null, "!! Producto Actualizado con Exito !!\n");
                }

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "!! Error al Editar un producto" + e);
            System.out.println("Error al Editar un producto" + e);
        }

        this.cerrarConexionBD();
    }

    //Extraer el tipo de producto ingresado
    public Vector<String> TipoProducto() throws SQLException {
        Vector<String> vector = new Vector<>();
        this.iniciarConexionBD();
        Statement stm = this.conexion.createStatement();

        try {
            String sql = "SELECT distinct tipo_producto as tipo_pr FROM bd_systema.productos";
            ResultSet rst = stm.executeQuery(sql);

            while (rst.next()) {
                String nombre = rst.getString("tipo_pr");
                vector.add(nombre);
            }
            this.cerrarConexionBD();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "!! Error al Extraer el tipo de un producto" + e);
            System.out.println("Error al Extraer el tipo de un producto" + e);
        }
        return vector;
    }

    //-----------------------------------------------------------------------------------------------------------------
    // Inventario
    //-----------------------------------------------------------------------------------------------------------------
    //Ingreso de mercaderia al inventario estado Ingresado
    public void Mercaderia(ArrayList<Vector<String>> datos) throws SQLException {
        this.iniciarConexionBD();
        Statement stm = this.conexion.createStatement();
        ArrayList<Vector<String>> matriz = datos;

        for (Vector<String> vector : matriz) {
            String codProd, idUser, idProve, orden, detalle, stado, fechIngre, observacion, cantidad, stock, total;
            int saldo;
            int resultado;

            codProd = vector.get(0);
            idUser = vector.get(1);
            idProve = vector.get(2);
            orden = vector.get(3);
            detalle = vector.get(4);
            observacion = vector.get(5);
            stado = vector.get(6);
            fechIngre = vector.get(7);
            cantidad = vector.get(8);
            total = vector.get(9);

            switch (detalle) {
                case "Compra":
                    try {
                    //  String sql2 = "select * from inventario(select idinventario, stock from inventario where codproductos = '" + codProd + "'" + "order by idinventario desc) where rownum =1";

                    String sq = "select orden from inventario where orden = '" + orden + "'";
                    ResultSet rs = stm.executeQuery(sq);
                    if (rs.next()) {
                        JOptionPane.showMessageDialog(null, "!! Orden ya se encuentra registrada/ Espera de aprobación !!\n");
                        this.cerrarConexionBD();
                    } else {
                        String sql2 = "SELECT idinventario, stock FROM ("
                                + "SELECT idinventario, stock FROM inventario "
                                + "WHERE codproductos = '" + codProd + "' "
                                + "ORDER BY idinventario DESC"
                                + ") AS inv LIMIT 1";

                        ResultSet rst = stm.executeQuery(sql2);
                        if (rst.next()) {
                            saldo = rst.getInt("stock");
                            int canti = (Integer.parseInt(cantidad));
                            resultado = saldo + canti;

                            String sql3 = "insert into inventario "
                                    + "(codproductos,idusuarios,idproveedor,orden,detalle, observacion, estado,"
                                    + " fecha_registro,fecha_aprobacion,ingreso,stock,total) values ('" + codProd + "','" + idUser + "','" + idProve + "','" + orden + "',"
                                    + "'" + detalle + "', '" + observacion + "','" + stado + "','" + fechIngre + "','" + fechIngre + "','" + cantidad + "','" + resultado + "','" + total + "')";
                            stm.executeUpdate(sql3);
                            JOptionPane.showMessageDialog(null, "!! Compra de Mercadería Guardada con Exito !!\n");

                        } else {
                            stock = cantidad;
                            String sql = "insert into inventario "
                                    + "(codproductos,idusuarios,idproveedor,orden,detalle,observacion, estado,"
                                    + " fecha_registro,fecha_aprobacion,ingreso,stock,total) values ('" + codProd + "','" + idUser + "','" + idProve + "','" + orden + "',"
                                    + "'" + detalle + "', '" + observacion + "','" + stado + "','" + fechIngre + "','" + fechIngre + "','" + cantidad + "','" + stock + "','" + total + "')";
                            stm.executeUpdate(sql);
                            JOptionPane.showMessageDialog(null, "!! Compra de Mercadería Guardada con Exito !!\n");
                            this.cerrarConexionBD();
                        }
                    }

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "!! Error al registrar compra/Inventario !!\n" + e);
                    System.out.println("Error al registrar compra/Inventario !!" + e);
                }

                break;

                case "Venta":
                    this.iniciarConexionBD();
                    Statement stm4 = this.conexion.createStatement();

                    String sql4 = "SELECT idinventario, stock FROM ("
                            + "SELECT idinventario, stock FROM inventario "
                            + "WHERE codproductos = '" + codProd + "' "
                            + "ORDER BY idinventario DESC"
                            + ") AS inv LIMIT 1";
                    ResultSet rst4 = stm4.executeQuery(sql4);

                    if (rst4.next()) {
                        saldo = rst4.getInt("stock");
                        int canti = (Integer.getInteger(cantidad));

                        if (saldo < canti || saldo == 0) {
                            JOptionPane.showMessageDialog(null, "!! No existe la cantidad Requerida !!");
                        } else if (saldo >= canti) {

                            resultado = saldo - canti;

                            String sql5 = "insert into inventario "
                                    + "(codproductos,idusuarios,detalle,"
                                    + " fecha_registro,ingreso,stock,total) values ('" + codProd + "','" + idUser + "',"
                                    + "'" + detalle + "', '" + fechIngre + "','" + cantidad + "','" + resultado + "','" + total + "')";
                            stm4.executeUpdate(sql5);
                            JOptionPane.showMessageDialog(null, "!! Venta realizada con Exito !!");
                            this.cerrarConexionBD();
                        }

                        break;

                    } else {
                        JOptionPane.showMessageDialog(null, "!! Producto no se encuentra en el Inventario !!");
                    }
                default:
                    break;
            }

            this.cerrarConexionBD();

        }
    }

    // Extraer los valores de un compra del inventario por Estado
    public ArrayList<Vector<String>> InventarioEstado(String estado) throws SQLException {
        this.iniciarConexionBD();
        Statement stm = this.conexion.createStatement();
        ArrayList<Vector<String>> matriz = new ArrayList<>();
        try {
            String sql = "SELECT i.codproductos,i.orden, i.detalle,i.observacion, i.estado, i.fecha_registro,i.fecha_aprobacion, i.ingreso, i.total, u.username, pro.nombres, p.nombre_producto\n"
                    + "FROM bd_systema.inventario i\n"
                    + "INNER JOIN bd_systema.proveedor pro \n"
                    + "on i.idProveedor = pro.idProveedor\n"
                    + "INNER JOIN bd_systema.usuarios u \n"
                    + "on i.idusuarios = u.idusuarios\n"
                    + "INNER JOIN bd_systema.productos p \n"
                    + "on i.codproductos = p.codproductos\n"
                    + "where i.estado = '" + estado + "'";

            ResultSet rst = stm.executeQuery(sql);
            while (rst.next()) {
                Vector<String> vector = new Vector<>();
                String codProd, orden, detalle, stado, fecharesgitro, fechaAproba, cantidad, observacion, totalcompra, usuario, nombreProveedor, nombreProducto;

                codProd = rst.getString("codproductos");
                orden = rst.getString("orden");
                nombreProducto = rst.getString("nombre_producto");
                observacion = rst.getString("observacion");
                usuario = rst.getString("username");
                nombreProveedor = rst.getString("nombres");
                detalle = rst.getString("detalle");
                fecharesgitro = rst.getString("fecha_registro");
                fechaAproba = rst.getString("fecha_aprobacion");
                cantidad = rst.getString("ingreso");
                totalcompra = rst.getString("total");

                vector.add(codProd);
                vector.add(orden);
                vector.add(nombreProducto);
                vector.add(observacion);
                vector.add(usuario);
                vector.add(nombreProveedor);
                vector.add(detalle);
                vector.add(fecharesgitro);
                vector.add(fechaAproba);
                vector.add(cantidad);
                vector.add(totalcompra);

                matriz.add(vector);

            }
            this.cerrarConexionBD();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "!! Error al Extraer los valores de una compra del inventario por estado" + e);
            System.out.println("Error al Extraer los valores de una compra del inventario por estado" + e);
        }

        return matriz;
    }

    // Extraer los valores de un compra del inventario por fecha
    public ArrayList<Vector<String>> InventarioFecha(String fecha, String estado) throws SQLException {
        this.iniciarConexionBD();
        Statement stm = this.conexion.createStatement();
        ArrayList<Vector<String>> matriz = new ArrayList<>();

        try {
            String sql = "SELECT i.codproductos,i.orden, i.detalle, i.estado, i.fecha_registro,i.fecha_aprobacion, i.ingreso, i.total, u.username, pro.nombres, p.nombre_producto\n"
                    + "FROM bd_systema.inventario i\n"
                    + "INNER JOIN bd_systema.proveedor pro \n"
                    + "on i.idProveedor = pro.idProveedor\n"
                    + "INNER JOIN bd_systema.usuarios u \n"
                    + "on i.idusuarios = u.idusuarios\n"
                    + "INNER JOIN bd_systema.productos p \n"
                    + "on i.codproductos = p.codproductos\n"
                    + "where i.fecha_aprobacion = '" + fecha + "' and i.estado = '" + estado + "'";

            ResultSet rst = stm.executeQuery(sql);
            while (rst.next()) {
                Vector<String> vector = new Vector<>();
                String codProd, orden, detalle, stado, fecharesgitro, cantidad, totalcompra, usuario, nombreProveedor, nombreProducto;

                codProd = rst.getString("codproductos");
                orden = rst.getString("orden");
                nombreProducto = rst.getString("nombre_producto");
                usuario = rst.getString("username");
                nombreProveedor = rst.getString("nombres");
                detalle = rst.getString("detalle");
                fecharesgitro = rst.getString("fecha_registro");
                cantidad = rst.getString("ingreso");
                totalcompra = rst.getString("total");

                vector.add(codProd);
                vector.add(orden);
                vector.add(nombreProducto);
                vector.add(usuario);
                vector.add(nombreProveedor);
                vector.add(detalle);
                vector.add(fecharesgitro);
                vector.add(cantidad);
                vector.add(totalcompra);

                matriz.add(vector);

            }
            this.cerrarConexionBD();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "!! Error al Extraer los valores de una compra del inventario por fecha" + e);
            System.out.println("Error al Extraer los valores de una compra del inventario por fecha" + e);
        }

        return matriz;
    }

    // Actualizar estado de una Compra al Inventario
    public void ActualizarEstadoCompra(String orden, String stado, String fecha) throws SQLException {
        this.iniciarConexionBD();
        try {
            Statement stm = this.conexion.createStatement();
            String sql = "select * from inventario where orden = '" + orden + "'";
            ResultSet rst = stm.executeQuery(sql);

            if (rst.next()) {
                switch (stado) {
                    case "Anulado":
                        String sql2 = "update inventario set estado ='" + stado + "' where orden = '" + orden + "'";
                        stm.executeUpdate(sql2);
                        JOptionPane.showMessageDialog(null, "!! Compra Anulada con Exito !!\n");
                        break;
                    case "Aprobado":
                        String sql3 = "update inventario set estado ='" + stado + "' where orden = '" + orden + "'";
                        stm.executeUpdate(sql3);
                        JOptionPane.showMessageDialog(null, "!! Compra Aprobada con Exito !!\n");
                        break;
                    case "Procesado":
                        String sql4 = "update inventario set estado ='" + stado + "',fecha_aprobacion ='" + fecha + "' where orden = '" + orden + "'";
                        stm.executeUpdate(sql4);
                        JOptionPane.showMessageDialog(null, "!! Compra Procesada con Exito !!\n");
                        break;
                    case "IngresoAnular":
                        String sql5 = "update inventario set estado ='" + stado + "' where orden = '" + orden + "'";
                        stm.executeUpdate(sql5);
                        JOptionPane.showMessageDialog(null, "!! Compra En Espera de Aprobación para Anulación con Exito !!\n");
                        break;
                    case "AprobadoAnular":
                        String sql6 = "update inventario set estado ='" + stado + "' where orden = '" + orden + "'";
                        stm.executeUpdate(sql6);
                        JOptionPane.showMessageDialog(null, "!! Aprobación de Anulación con Exito !!\n");
                        break;
                    default:
                        throw new AssertionError();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "!! Error al guardar estado Compra" + e);
            System.out.println("Error al guardar estado Compra" + e);
        }
        this.cerrarConexionBD();
    }

    //busqueda en el inventario por Numero de Orden
    public ArrayList<Vector<String>> InventarioPorOrden(String orden, String fecha, String stado) throws SQLException {
        ArrayList<Vector<String>> matriz = new ArrayList<>();

        this.iniciarConexionBD();
        Statement stm = this.conexion.createStatement();

        try {
            String status = "IngresoAnular";
            String sql2 = "select orden from inventario where orden = '" + orden + "' and fecha_aprobacion = '" + fecha + "' and estado = '" + status + "'";

            ResultSet rst2 = stm.executeQuery(sql2);
            if (rst2.next()) {
                JOptionPane.showMessageDialog(null, "!! Orden En Espera de Aprobacion !!\n");
            } else {
                String sql = "SELECT i.codproductos, i.orden, i.observacion, i.estado, i.fecha_aprobacion, i.ingreso, i.total, u.username,  pro.nombres, p.nombre_producto\n"
                        + "FROM bd_systema.inventario i\n"
                        + "INNER JOIN bd_systema.usuarios u \n"
                        + "on i.idusuarios = u.idusuarios\n"
                        + "INNER JOIN bd_systema.proveedor pro \n"
                        + "on i.idproveedor = pro.idProveedor\n"
                        + "INNER JOIN bd_systema.productos p \n"
                        + "on i.codproductos = p.codproductos\n"
                        + "where i.estado = '" + stado + "' and i.fecha_aprobacion = '" + fecha + "' and i.orden = '" + orden + "'";

                ResultSet rst = stm.executeQuery(sql);

                if (rst.next()) {
                    Vector<String> datos = new Vector<>();

                    String ordenCompr, nomProdcuto, observacion, fechaComp, cantidaCompra, valor, usuario, nombrProvee;

                    ordenCompr = rst.getString("orden");
                    nomProdcuto = rst.getString("nombre_producto");
                    observacion = rst.getString("observacion");
                    fechaComp = rst.getString("fecha_aprobacion");
                    cantidaCompra = rst.getString("ingreso");
                    valor = rst.getString("total");
                    usuario = rst.getString("username");
                    nombrProvee = rst.getString("nombres");

                    datos.add(ordenCompr);
                    datos.add(nomProdcuto);
                    datos.add(observacion);
                    datos.add(fechaComp);
                    datos.add(cantidaCompra);
                    datos.add(valor);
                    datos.add(usuario);
                    datos.add(nombrProvee);

                    matriz.add(datos);

                } else {
                    JOptionPane.showMessageDialog(null, "!! Orden no se encuentra Registrado !!\n");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "!! Error al buscar en el invetario por numero de Orden" + e);
            System.out.println("Error al buscar en el invetario por numero de Orden" + e);
        }
        this.cerrarConexionBD();
        return matriz;
    }

    //-----------------------------------------------------------------------------------------------------------------
    // Proveedores
    //-----------------------------------------------------------------------------------------------------------------
    //consultar si proveedor se encuentra registrado
    public ArrayList<Vector<String>> RucProveedor(String ruc) throws SQLException {
        this.iniciarConexionBD();
        Statement stm = this.conexion.createStatement();
        ArrayList<Vector<String>> matriz = new ArrayList<>();

        try {
            String sql = "select idProveedor,"
                    + " ruc,idusuarios, nombres,"
                    + " apellidos, email, direccion,fecha_registro,"
                    + " fecha_actualizacion, comentario, userRegistro,"
                    + "userActualizacion from proveedor where ruc ='" + ruc + "'";
            ResultSet rst = stm.executeQuery(sql);
            if (rst.next()) {
                Vector<String> datos = new Vector<>();
                String idprovee, rucPro, iduser, nombres, apellidos, email, direccion, fech_regis, fecha_actu, comentario, userRegis, userActual;

                idprovee = rst.getString("idProveedor");
                rucPro = rst.getString("ruc");
                iduser = rst.getString("idusuarios");
                nombres = rst.getString("nombres");
                apellidos = rst.getString("apellidos");
                email = rst.getString("email");
                direccion = rst.getString("direccion");
                fech_regis = rst.getString("fecha_registro");
                fecha_actu = rst.getString("fecha_actualizacion");
                comentario = rst.getString("comentario");
                userRegis = rst.getString("userRegistro");
                userActual = rst.getString("userActualizacion");

                datos.add(idprovee);
                datos.add(rucPro);
                datos.add(iduser);
                datos.add(nombres);
                datos.add(apellidos);
                datos.add(email);
                datos.add(direccion);
                datos.add(fech_regis);
                datos.add(fecha_actu);
                datos.add(comentario);
                datos.add(userRegis);
                datos.add(userActual);

                matriz.add(datos);

            } else {
                JOptionPane.showMessageDialog(null, "!! Proveedor no se encuentra Registrado !!\n");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "!! Error al Consultar si Proveedor se encuentra Registrado" + e);
            System.out.println("Error al Consultar si Proveedor se encuentra Registrado" + e);
        }
        this.cerrarConexionBD();
        return matriz;
    }

    // aqui Registramos un Proveedor
    public void InsertarProveedor(ArrayList<Vector<String>> datos) throws SQLException {
        this.iniciarConexionBD();
        Statement stm = this.conexion.createStatement();
        ArrayList<Vector<String>> matriz = datos;

        try {
            for (Vector<String> vector : matriz) {
                String rucPro, iduser, nombres, apellidos, email, direccion, fecha_registro, comentario, userRegis;

                rucPro = vector.get(0);
                iduser = vector.get(1);
                nombres = vector.get(2);
                apellidos = vector.get(3);
                email = vector.get(4);
                direccion = vector.get(5);
                fecha_registro = vector.get(6);
                comentario = vector.get(7);
                userRegis = vector.get(8);

                String sql = "select ruc from proveedor where ruc = '" + rucPro + "'";

                ResultSet rst = stm.executeQuery(sql);

                if (rst.next()) {
                    JOptionPane.showMessageDialog(null, "!! No puede Guardar a este Proveedor ya se encuentra Registrado !!\n");
                    this.cerrarConexionBD();
                } else {
                    this.iniciarConexionBD();
                    Statement stm1 = this.conexion.createStatement();
                    String sql1 = " INSERT INTO proveedor (RUC, "
                            + "IDUSUARIOS, NOMBRES, "
                            + "APELLIDOS, EMAIL,DIRECCION,"
                            + "FECHA_REGISTRO, COMENTARIO, "
                            + "USERREGISTRO) VALUES ('" + rucPro + "', '" + iduser + "',"
                            + " '" + nombres + "', '" + apellidos + "', '" + email + "', "
                            + "'" + direccion + "','" + fecha_registro + "', "
                            + "'" + comentario + "', '" + userRegis + "')";
                    stm1.executeUpdate(sql1);
                    JOptionPane.showMessageDialog(null, "!! Proveedor Guardado con Exito !!\n");
                }

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "!! Error al Registrar un proveedor" + e);
            System.out.println("Error al Registrar un proveedor" + e);
        }

        this.cerrarConexionBD();
    }

    //aqui Actualizamos un proveedor
    public void AtualizarProveedor(ArrayList<Vector<String>> datos) throws SQLException {
        this.iniciarConexionBD();
        Statement stm = this.conexion.createStatement();
        ArrayList<Vector<String>> matriz = datos;

        try {
            for (Vector<String> vector : matriz) {
                String rucPro, iduser, nombres, apellidos, email, direccion, comentario, fechaActual, userActual;
                rucPro = vector.get(0);
                iduser = vector.get(1);
                nombres = vector.get(2);
                apellidos = vector.get(3);
                email = vector.get(4);
                direccion = vector.get(5);
                fechaActual = vector.get(6);
                comentario = vector.get(7);
                userActual = vector.get(8);

                String sql = "select ruc from proveedor where ruc = '" + rucPro + "'";

                ResultSet rst = stm.executeQuery(sql);

                if (rst.next()) {
                    String sql2 = "update proveedor set nombres ='" + nombres + "', "
                            + "apellidos ='" + apellidos + "',"
                            + " email ='" + email + "', direccion ='" + direccion + "', "
                            + "fecha_actualizacion ='" + fechaActual + "', "
                            + "comentario ='" + comentario + "',"
                            + " userActualizacion = '" + userActual + "' where ruc = '" + rucPro + "'";
                    stm.executeUpdate(sql2);
                    JOptionPane.showMessageDialog(null, "!! Datos Actualizados con Exito !!\n");

                } else {
                    JOptionPane.showMessageDialog(null, "!! No puede Actualizar Proveedor no se encuentra Registrado !!\n");
                }

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "!! Error al Actualizar un proveedor" + e);
            System.out.println("Error al Actualizar un proveedor" + e);
        }

        this.cerrarConexionBD();
    }

    //Extraer los Proveedores Registrados
    public ArrayList<Vector<String>> NombresCompletosProveedor() throws SQLException {
        ArrayList<Vector<String>> nombre = new ArrayList<>();
        this.iniciarConexionBD();
        Statement stm = this.conexion.createStatement();

        try {
            String sql = "SELECT distinct nombres as nomb , apellidos as aplle FROM bd_systema.proveedor";
            ResultSet rst = stm.executeQuery(sql);
            while (rst.next()) {
                Vector<String> datos = new Vector<>();
                String nombres, apellidos;
                nombres = rst.getString("nomb");
                apellidos = rst.getString("aplle");

                datos.add(nombres);
                datos.add(apellidos);

                nombre.add(datos);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "!! Error al Extraer los proveedores Registrados" + e);
            System.out.println("Error al Extraer los proveedores Registrados" + e);
        }

        this.cerrarConexionBD();
        return nombre;
    }

    //----------------------------------------------------------------------------------------------------------------------
    //transacciones
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
}
