/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package GUI;

import Logica_Operaciones_BD.OperarcionesCRUD;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import static java.lang.System.exit;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USUARIO
 */
public class IngresoMercaderia extends javax.swing.JInternalFrame {

    String usuario;

    String codPro;

    /**
     * Creates new form Productos
     */
    public IngresoMercaderia() throws SQLException {
        initComponents();

        usuario = Login.user;
        LabelTitulo.setText("Productos> Ingreso Mercaderia");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        labelUSuario.setText(usuario);
        OperarcionesCRUD ops = OperarcionesCRUD.getInstance();
        String coduser = ops.codigoUser(usuario);
//        labelID.setText(coduser);
        IdUserReg.setText(coduser);
        UserRegistro.setText(usuario);
        jPanel6.setVisible(false);

        DefaultTableModel modelos = (DefaultTableModel) TablaProductos.getModel();

        TablaProductos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila_point = TablaProductos.rowAtPoint(e.getPoint());
                int columna_point = 0;

                if (fila_point > -1) {
                    codPro = (String) modelos.getValueAt(fila_point, columna_point);
                    jPanel6.setVisible(true);

                    try {
                        ArrayList<Vector<String>> matriz = ops.CodigoProduto(codPro);

                        for (Vector<String> vector : matriz) {
                            String codiPro, nombreProd, detaProd, tipoProd, fechRegistro, fechAc, prec, iva, rucPro, nombProve, apellProve, idProve, idUser, username;

                            IdUserReg.setText(coduser);
                            IdUserReg.setVisible(false);
                            UserRegistro.setText(usuario);

                            codiPro = vector.get(0);
                            nombreProd = vector.get(1);
                            detaProd = vector.get(2);
                            tipoProd = vector.get(3);
                            fechRegistro = vector.get(4);
                            fechAc = vector.get(5);
                            prec = vector.get(6);
                            iva = vector.get(7);
                            rucPro = vector.get(8);
                            nombProve = vector.get(9);
                            apellProve = vector.get(10);
                            idProve = vector.get(11);
                            username = vector.get(12);
                            idUser = vector.get(13);

                            labelCodigo.setText(codiPro);
                            txtnombre.setText(nombreProd);
                            txtDetalle.setText(detaProd);
                            jComboBox1.setText(tipoProd);
                            labelFecha1.setText(fechRegistro);
                            labelFecha.setText(fechAc);
                            txtprecio.setText(prec);
                            txtiva.setText(iva);
                            txtRuc.setText(rucPro);
                            jLabelNombres.setText(nombProve);
                            jLabelApellidos.setText(apellProve);
                            LabelIDRuc.setText(idProve);
                            labelID.setText(idUser);
                            labelUSuario.setText(username);

                        }

                    } catch (SQLException err) {
                        err.printStackTrace();
                    }

                }
            }
        });

        //aqui se realizo esto para que el jtextfiel solo permita ingresar datos enteros
        String cantidad = txtcantidad.getText();
        txtcantidad.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        //esto es para activar el combo a credito
        labelPlazo.setVisible(false);
        comboplazo.setVisible(false);
        labelplazo.setVisible(false);
        txtotalplazo.setVisible(false);
        jlalabelSubTotal.setVisible(false);
        labelSubTotal.setVisible(false);

        comboformapago.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                Object seleccionado = comboformapago.getSelectedItem();

                if (e.getStateChange() == ItemEvent.SELECTED) {
                    if ("Crédito".equals(seleccionado)) {
                        labelPlazo.setVisible(true);
                        comboplazo.setVisible(true);
                        labelplazo.setVisible(true);
                        txtotalplazo.setVisible(true);
                        labelContado.setVisible(false);
                        txtotal.setVisible(false);
                        jlalabelSubTotal.setVisible(true);
                        labelSubTotal.setVisible(true);
                    } else {
                        labelPlazo.setVisible(false);
                        comboplazo.setVisible(false);
                        labelplazo.setVisible(false);
                        txtotalplazo.setVisible(false);
                        labelContado.setVisible(true);
                        txtotal.setVisible(true);
                        jlalabelSubTotal.setVisible(false);
                        labelSubTotal.setVisible(false);
                    }
                }
            }
        });

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        jButton2 = new javax.swing.JButton();
        LabelTitulo = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TablaProductos = new javax.swing.JTable();
        ConsultarTipo = new javax.swing.JButton();
        jCombobusqueda = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtRucConsultaPRo = new javax.swing.JTextField();
        ConsulatRucProveedor = new javax.swing.JButton();
        Limpiar = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        Calcular = new javax.swing.JButton();
        txtprecio = new javax.swing.JLabel();
        txtiva = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtcantidad = new javax.swing.JTextField();
        labelContado = new javax.swing.JLabel();
        txtotal = new javax.swing.JLabel();
        calcularplazo = new javax.swing.JButton();
        comboformapago = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        comboplazo = new javax.swing.JComboBox<>();
        labelPlazo = new javax.swing.JLabel();
        labelplazo = new javax.swing.JLabel();
        txtotalplazo = new javax.swing.JLabel();
        jlalabelSubTotal = new javax.swing.JLabel();
        labelSubTotal = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabelNombres = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabelApellidos = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        LabelIDRuc = new javax.swing.JLabel();
        txtRuc = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        labelCodigo = new javax.swing.JLabel();
        labelFecha = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        labelUSuario = new javax.swing.JLabel();
        labelFecha1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        labelID = new javax.swing.JLabel();
        txtnombre = new javax.swing.JLabel();
        txtDetalle = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtOrden = new javax.swing.JTextField();
        jToolBar2 = new javax.swing.JToolBar();
        RegistroCompra = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        IdUserReg = new javax.swing.JLabel();
        UserRegistro = new javax.swing.JLabel();

        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/images/agregar-producto.png"))); // NOI18N

        jToolBar1.setRollover(true);
        jToolBar1.setEnabled(false);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/salida.png"))); // NOI18N
        jButton2.setToolTipText("Salir");
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton2);

        LabelTitulo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jToolBar1.add(LabelTitulo);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Ingreso Mercaderia", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Consulta Producto", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        TablaProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Nombre Producto", "Tipo", "Detalle", "Fecha", "Precio", "Iva", "Ruc Proveedor", "Nombre Proveedor", "Usuario"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TablaProductos.getTableHeader().setReorderingAllowed(false);
        TablaProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaProductosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(TablaProductos);
        if (TablaProductos.getColumnModel().getColumnCount() > 0) {
            TablaProductos.getColumnModel().getColumn(0).setResizable(false);
            TablaProductos.getColumnModel().getColumn(1).setResizable(false);
            TablaProductos.getColumnModel().getColumn(2).setResizable(false);
            TablaProductos.getColumnModel().getColumn(3).setResizable(false);
            TablaProductos.getColumnModel().getColumn(4).setResizable(false);
            TablaProductos.getColumnModel().getColumn(5).setResizable(false);
            TablaProductos.getColumnModel().getColumn(6).setResizable(false);
            TablaProductos.getColumnModel().getColumn(7).setResizable(false);
            TablaProductos.getColumnModel().getColumn(8).setResizable(false);
            TablaProductos.getColumnModel().getColumn(9).setResizable(false);
        }

        ConsultarTipo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/consulta.png"))); // NOI18N
        ConsultarTipo.setToolTipText("Buscar Producto");
        ConsultarTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConsultarTipoActionPerformed(evt);
            }
        });

        jCombobusqueda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Electronica", "Ropa", "Accesorios", "Hogar", "Jardin", "Deportes", "Libros", "Entretenimiento", "Aseo", "Salud" }));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setText("Buscar por :");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setText("Tipo :");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel17.setText("Proveedor:");

        txtRucConsultaPRo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        ConsulatRucProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/consulta.png"))); // NOI18N
        ConsulatRucProveedor.setToolTipText("consultaProveedor");
        ConsulatRucProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConsulatRucProveedorActionPerformed(evt);
            }
        });

        Limpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/actualizar.png"))); // NOI18N
        Limpiar.setToolTipText("Limpiar");
        Limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LimpiarActionPerformed(evt);
            }
        });

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/actualizar.png"))); // NOI18N
        jButton6.setText("Limpiar Tabla");
        jButton6.setToolTipText("Limpiar Tabla");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(244, 244, 244)
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addComponent(jCombobusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ConsultarTipo)
                .addGap(61, 61, 61)
                .addComponent(jButton6)
                .addGap(88, 88, 88)
                .addComponent(jLabel17)
                .addGap(18, 18, 18)
                .addComponent(txtRucConsultaPRo, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ConsulatRucProveedor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Limpiar)
                .addContainerGap(365, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(65, 65, 65)
                    .addComponent(jLabel12)
                    .addContainerGap(1300, Short.MAX_VALUE))
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(21, 21, 21)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1418, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(txtRucConsultaPRo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ConsulatRucProveedor)
                            .addComponent(jButton6))
                        .addComponent(ConsultarTipo)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jCombobusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(Limpiar))
                .addContainerGap(228, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(7, 7, 7)
                    .addComponent(jLabel12)
                    .addGap(34, 34, 34)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Iva: ");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Precio: ");

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/actualizar.png"))); // NOI18N
        jButton4.setText("Limpiar");
        jButton4.setToolTipText("Limpiar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        Calcular.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/calcular.png"))); // NOI18N
        Calcular.setText("Calcular");
        Calcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CalcularActionPerformed(evt);
            }
        });

        txtprecio.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        txtiva.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Cantidad:");

        txtcantidad.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        labelContado.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelContado.setText("Total Contado:");

        txtotal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        calcularplazo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/calcular.png"))); // NOI18N
        calcularplazo.setText("Calcular Plazo");
        calcularplazo.setToolTipText("");
        calcularplazo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calcularplazoActionPerformed(evt);
            }
        });

        comboformapago.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Contado", "Crédito" }));

        jLabel20.setText("Forma de Pago:");

        comboplazo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "12 meses", "6 meses", "3 meses", "1 mes" }));
        comboplazo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboplazoActionPerformed(evt);
            }
        });

        labelPlazo.setText("Plazo:");

        labelplazo.setText("Total Plazo:");

        jlalabelSubTotal.setText("Sub Total:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel9)
                    .addComponent(jLabel2))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jlalabelSubTotal)
                    .addComponent(labelplazo)
                    .addComponent(jLabel20)
                    .addComponent(labelPlazo)
                    .addComponent(labelContado))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtprecio, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtcantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(txtiva, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(comboplazo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(comboformapago, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtotalplazo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelSubTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Calcular))
                        .addGap(37, 37, 37))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(calcularplazo)
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtcantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Calcular))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButton4))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtprecio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(9, 9, 9)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtiva, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(calcularplazo)
                    .addComponent(comboformapago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelPlazo)
                    .addComponent(comboplazo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelContado)
                    .addComponent(txtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlalabelSubTotal)
                    .addComponent(labelSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelplazo)
                    .addComponent(txtotalplazo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setText("Proveedor:");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setText("Nombres:");

        jLabelNombres.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setText("Apellidos:");

        jLabelApellidos.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel18.setText("ID:");

        LabelIDRuc.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        txtRuc.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel14)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel16)
                        .addComponent(jLabel15))
                    .addComponent(jLabel18))
                .addGap(34, 34, 34)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelNombres, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                    .addComponent(jLabelApellidos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LabelIDRuc, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRuc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtRuc, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabelNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jLabelApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LabelIDRuc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Código :");

        labelCodigo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        labelFecha.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Tipo :");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Detalle/Observación:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Usuario:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Nombre Producto:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Fecha Registro:");

        labelUSuario.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        labelFecha1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Fecha Actualización:");

        labelID.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        txtnombre.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        txtDetalle.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N

        jComboBox1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel19.setText("Orden Compra:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6)
                    .addComponent(jLabel19))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(txtDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtnombre, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
                            .addComponent(txtOrden))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 599, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelFecha1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(labelID, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(labelUSuario, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(123, 123, 123))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(90, 90, 90)
                    .addComponent(jLabel1)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(labelCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(1158, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(labelUSuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(labelID, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jLabel5)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                            .addComponent(labelFecha1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(labelFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(txtOrden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(txtDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(labelCodigo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING))
                    .addContainerGap(135, Short.MAX_VALUE)))
        );

        jToolBar2.setRollover(true);
        jToolBar2.setEnabled(false);

        RegistroCompra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/disco-flexible.png"))); // NOI18N
        RegistroCompra.setToolTipText("Guardar");
        RegistroCompra.setFocusable(false);
        RegistroCompra.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        RegistroCompra.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        RegistroCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegistroCompraActionPerformed(evt);
            }
        });
        jToolBar2.add(RegistroCompra);

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/salida.png"))); // NOI18N
        jButton8.setToolTipText("Salir");
        jButton8.setFocusable(false);
        jButton8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jToolBar2.add(jButton8);
        jToolBar2.add(IdUserReg);
        jToolBar2.add(UserRegistro);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jToolBar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(20, 20, 20))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 1562, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void ConsultarTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConsultarTipoActionPerformed
        String tipo;
        tipo = jCombobusqueda.getSelectedItem().toString();

        try {

            OperarcionesCRUD op = OperarcionesCRUD.getInstance();
            ArrayList<Vector<String>> matriz = op.BusquedaProducto(tipo);

            DefaultTableModel modelo = (DefaultTableModel) TablaProductos.getModel();
            modelo.setNumRows(0);
            for (Vector<String> vector : matriz) {
                modelo.addRow(vector);
            }
        } catch (SQLException err) {
            err.printStackTrace();
        }

    }//GEN-LAST:event_ConsultarTipoActionPerformed

    public void valirCalcular() {
        String cantidad, precio, iva, total;

        cantidad = txtcantidad.getText();
        precio = txtprecio.getText();
        iva = txtiva.getText();
        int validacion = 0;

        total = txtotal.getText();

        if (precio.equals("")) {
            validacion++;
        }
        if (iva.equals("")) {
            validacion++;
        }
        if (cantidad.equals("")) {
            validacion++;
        }
        if (total.equals("")) {
            validacion++;
        }

        if (validacion >= 1) {
            JOptionPane.showMessageDialog(null, "Ingrese los valores para calculo Producto");

        }
    }

    public void LimpiarRucPro() {
        jLabelNombres.setText("");
        jLabelApellidos.setText("");
        txtRuc.setText("");
        LabelIDRuc.setText("");
    }

    public void LimpiarGeneral() {
        txtnombre.setText("");
        txtDetalle.setText("");

    }
    private void ConsulatRucProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConsulatRucProveedorActionPerformed
        String rucPro = txtRucConsultaPRo.getText().toString();
        int longitud = 13;
        int valor = rucPro.length();

        if (!rucPro.equals("")) {
            if (valor < longitud) {
                JOptionPane.showMessageDialog(null, "!! EL ruc debe contener 13 digitos !!\n");
            } else if (valor > longitud) {
                JOptionPane.showMessageDialog(null, "!! EL ruc debe contener 13 digitos !!\n");
            } else {
                //String ruc = (String.valueOf(valor));
                OperarcionesCRUD op = OperarcionesCRUD.getInstance();

                try {
                    ArrayList<Vector<String>> matriz = op.BusquedaRucProveedor(rucPro);
                    DefaultTableModel modelo = (DefaultTableModel) TablaProductos.getModel();

                    modelo.setNumRows(0);
                    for (Vector<String> vector : matriz) {
                        modelo.addRow(vector);
                    }

                } catch (SQLException err) {
                    err.printStackTrace();
                }

            }
        } else {
            JOptionPane.showMessageDialog(null, "!! Ingrese el ruc por favor !!\n");
        }


    }//GEN-LAST:event_ConsulatRucProveedorActionPerformed

    private void LimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LimpiarActionPerformed
        txtRucConsultaPRo.setText("");
    }//GEN-LAST:event_LimpiarActionPerformed

    public void LimpiarCalculadora() {
        txtcantidad.setText("");
        txtotal.setText("");

    }
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        LimpiarCalculadora();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        jPanel6.setVisible(false);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        DefaultTableModel modelo = (DefaultTableModel) TablaProductos.getModel();
        modelo.setNumRows(0);
        jPanel6.setVisible(false);

    }//GEN-LAST:event_jButton6ActionPerformed

    private void TablaProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaProductosMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_TablaProductosMouseClicked


    private void RegistroCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegistroCompraActionPerformed
        ArrayList<Vector<String>> matriz = new ArrayList<>();
        valirCalcular();

        Date fecha = new Date();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String fechaAc = format.format(fecha);

        String idCod, idUser, idProve, cantidad, totalCompra, orden, observacion;
        int contador = 0;

        orden = txtOrden.getText();
        String detalleCompra = "Compra";
        String stado = "Ingresado";

        idCod = labelCodigo.getText();
        idUser = IdUserReg.getText();
        idProve = LabelIDRuc.getText();
        cantidad = txtcantidad.getText();
        observacion = txtDetalle.getText();

        totalCompra = txtotal.getText();

        if (totalCompra.equals("")) {
            contador++;
        }
        if (orden.equals("")) {
            contador++;
        }

        if (contador == 0) {
            Vector<String> datos = new Vector<>();

            datos.add(idCod);
            datos.add(idUser);
            datos.add(idProve);
            datos.add(orden);
            datos.add(detalleCompra);
            datos.add(observacion);
            datos.add(stado);
            datos.add(fechaAc);
            datos.add(cantidad);
            datos.add(totalCompra);

            matriz.add(datos);
        } else {
            JOptionPane.showMessageDialog(this, "Debe Ingresar todos los datos");
        }

        try {
            OperarcionesCRUD op = OperarcionesCRUD.getInstance();
            op.Mercaderia(matriz);
            LimpiarCalculadora();
        } catch (SQLException err) {
            err.printStackTrace();
        }


    }//GEN-LAST:event_RegistroCompraActionPerformed


    private void CalcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CalcularActionPerformed

        String cantida, precio, iva;

        cantida = txtcantidad.getText();
        precio = txtprecio.getText();
        iva = txtiva.getText();
        if (!cantida.equals("")) {
            int cant = (Integer.parseInt(cantida));
            double prec = (Double.parseDouble(precio));
            double iv = (Double.parseDouble(iva));

            double producto = (cant * prec);
            double subtotal = (producto * iv) / 100;
            double tot = (producto + subtotal);

            String total = (String.valueOf(tot));
            txtotal.setText(total);
        } else {
            valirCalcular();
        }


    }//GEN-LAST:event_CalcularActionPerformed

    private void comboplazoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboplazoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboplazoActionPerformed

    public void ActualizarValores() {
        Object seleccionado = comboformapago.getSelectedItem();
        Object seleccion = comboplazo.getSelectedItem();

        String sumaTo = txtotal.getText();
        double diferido = 0.0;
        if (sumaTo != null && !sumaTo.trim().isEmpty()) {
            double sumaTotal = (Double.parseDouble(sumaTo));

            if ("Crédito".equals(seleccionado)) {
                labelPlazo.setVisible(true);
                comboplazo.setVisible(true);

                if ("12 meses".equals(seleccion)) {
                    diferido = sumaTotal / 12;
                } else if ("6 meses".equals(seleccion)) {
                    diferido = sumaTotal / 6;
                } else if ("3 meses".equals(seleccion)) {
                    diferido = sumaTotal / 3;
                } else if ("1 mes".equals(seleccion)) {
                    diferido = sumaTotal / 1;
                }

                labelSubTotal.setText(sumaTo);
                txtotalplazo.setText(String.format("%.2f", diferido));
            } else {
//                labelPlazo.setVisible(false);
//                comboplazo.setVisible(false);
//
//                txtotal.setText(String.format("%.2f", sumaTotal));

            }
        }

    }
    private void calcularplazoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calcularplazoActionPerformed
        ActualizarValores();
    }//GEN-LAST:event_calcularplazoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Calcular;
    private javax.swing.JButton ConsulatRucProveedor;
    private javax.swing.JButton ConsultarTipo;
    private javax.swing.JLabel IdUserReg;
    private javax.swing.JLabel LabelIDRuc;
    private javax.swing.JLabel LabelTitulo;
    private javax.swing.JButton Limpiar;
    private javax.swing.JButton RegistroCompra;
    private javax.swing.JTable TablaProductos;
    private javax.swing.JLabel UserRegistro;
    private javax.swing.JButton calcularplazo;
    private javax.swing.JComboBox<String> comboformapago;
    private javax.swing.JComboBox<String> comboplazo;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jComboBox1;
    private javax.swing.JComboBox<String> jCombobusqueda;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelApellidos;
    private javax.swing.JLabel jLabelNombres;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JLabel jlalabelSubTotal;
    private javax.swing.JLabel labelCodigo;
    private javax.swing.JLabel labelContado;
    private javax.swing.JLabel labelFecha;
    private javax.swing.JLabel labelFecha1;
    private javax.swing.JLabel labelID;
    private javax.swing.JLabel labelPlazo;
    private javax.swing.JLabel labelSubTotal;
    private javax.swing.JLabel labelUSuario;
    private javax.swing.JLabel labelplazo;
    private javax.swing.JLabel txtDetalle;
    private javax.swing.JTextField txtOrden;
    private javax.swing.JLabel txtRuc;
    private javax.swing.JTextField txtRucConsultaPRo;
    private javax.swing.JTextField txtcantidad;
    private javax.swing.JLabel txtiva;
    private javax.swing.JLabel txtnombre;
    private javax.swing.JLabel txtotal;
    private javax.swing.JLabel txtotalplazo;
    private javax.swing.JLabel txtprecio;
    // End of variables declaration//GEN-END:variables
}
