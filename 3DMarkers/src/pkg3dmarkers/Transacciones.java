package pkg3dmarkers;

import clases.CatalogoCuenta;
import clases.Cuenta;
import clases.Transaccion;
import clases.TransaccionTableModel;
import com.sun.jdi.connect.spi.Connection;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Manuel Alcides Ascencio Aleman
 */
public class Transacciones extends javax.swing.JFrame {

    public TransaccionTableModel transaccionTModel = new TransaccionTableModel();
    Conexion conexion = new Conexion();

    Transaccion transaccionActual = new Transaccion();

    public static final double IVA = 0.13;

    public Transacciones() {
        initComponents();
        //  conexion.conectar();
        inicializarColumnas();
        consultaIncial();
        habilitarControles(false);
        totalizacion();
        txtidTransaccionTra.setVisible(false);
        centrarVentanaEnPantalla();

    }

    public void habilitarControles(boolean f) {

        txtConceptoTransaccion.setEnabled(f);
        // txtidTransaccionTra.setEnabled(f);
        txtMontoTransaccion.setEnabled(f);
        cboCuentaTrasaccion.setEnabled(f);
        cboSaldoTransaccion.setEnabled(f);
        btnGuardarTransaccion.setEnabled(f);
        btnCancelarTransaccion.setEnabled(f);
        btnActualizarTransaccion.setEnabled(f);
        rdTransaccion.setEnabled(f);
    }

    private void inicializarColumnas() {

        TableColumnModel tColumnModel = new DefaultTableColumnModel();

        for (int i = 0; i < 6; i++) {

            TableColumn col = new TableColumn(i);
            switch (i) {
                case 0:
                    col.setHeaderValue("ID");
                    break;

                case 1:
                    col.setHeaderValue("Codigo");

                    break;
                case 2:
                    col.setHeaderValue("Cuenta");
                    break;
                case 3:
                    col.setHeaderValue("Concepto");
                    break;
                case 4:
                    col.setHeaderValue("Debe");
                    break;
                case 5:
                    col.setHeaderValue("Haber");
                    break;

            }
            tColumnModel.addColumn(col);

        }

        tablaTransacion.setColumnModel(tColumnModel);

        TableColumn columna0 = tablaTransacion.getColumnModel().getColumn(0); // Reemplaza 'indiceColumna' con el índice de la columna que deseas ajustar.
        columna0.setPreferredWidth(1);

        TableColumn columna1 = tablaTransacion.getColumnModel().getColumn(1);
        columna1.setPreferredWidth(10);
        TableColumn columna2 = tablaTransacion.getColumnModel().getColumn(2);
        columna2.setPreferredWidth(2);
        TableColumn columna3 = tablaTransacion.getColumnModel().getColumn(3);
        columna3.setPreferredWidth(250);
        TableColumn columna4 = tablaTransacion.getColumnModel().getColumn(4);
        columna4.setPreferredWidth(2);
        TableColumn columna5 = tablaTransacion.getColumnModel().getColumn(5);
        columna5.setPreferredWidth(2);

        /* // Codigo usado para ocultar la columna "id"
        int columnIndexToHide = 0;
        tablaTransacion.getColumnModel().getColumn(columnIndexToHide).setMinWidth(0);
        tablaTransacion.getColumnModel().getColumn(columnIndexToHide).setMaxWidth(0);
        tablaTransacion.getColumnModel().getColumn(columnIndexToHide).setWidth(0);
         */
    }

    private boolean validando() {

        boolean vacio = false;

        if (txtMontoTransaccion.getText().isEmpty() || txtConceptoTransaccion.getText().isEmpty() || cboSaldoTransaccion.getSelectedIndex() == 0 || cboCuentaTrasaccion.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "No dejar casillas en blanco. Seleccione un tipo de saldo y cuenta", "Error", JOptionPane.ERROR_MESSAGE);
            vacio = true;
        }
        return vacio;
    }

    private void cbo() {

        try {
            Statement statement = conexion.conectar().createStatement();
            String setenciaCBO = "SELECT * FROM catalogocuenta";
            ResultSet resultadoCbo = statement.executeQuery(setenciaCBO);

            while (resultadoCbo.next()) {
                CatalogoCuenta cuenta = new CatalogoCuenta();

                cuenta.nombre = resultadoCbo.getString("nombrecuenta");
                cboCuentaTrasaccion.addItem(cuenta.nombre);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al recuperar las cuentas de la BD");

            ex.printStackTrace();
        }
        // cerrarConexion();

    }

    private void consultaIncial() {

        // Conexion conexion = new Conexion();
        try {

            String setenciaSql = "SELECT s.*, p.nombreCuenta FROM transaccion s JOIN catalogocuenta p ON s.codigo=p.codigo  ";

            Statement statement = conexion.conectar().createStatement();

            ResultSet resultado = statement.executeQuery(setenciaSql);

            while (resultado.next()) {

                Transaccion transaccion = new Transaccion();

                transaccion.idTransaccion = resultado.getInt("idtransaccion");
                transaccion.codigo = resultado.getInt("codigo");
                transaccion.concepto = resultado.getString("concepto");
                transaccion.cuenta = resultado.getString("nombrecuenta");
                transaccion.debe = resultado.getDouble("debe");
                transaccion.haber = resultado.getDouble("haber");

                this.transaccionTModel.transacciones.add(transaccion);

            }

            tablaTransacion.repaint();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al recuperar los productos de la base");

            ex.printStackTrace();
        }

        cbo();

    }

    private void UpdateJTable() {

        transaccionTModel.transacciones.clear();

        try {
            PreparedStatement statement = null;
            String setenciaSql = "SELECT s.*, p.nombreCuenta FROM transaccion s JOIN catalogocuenta p ON s.codigo=p.codigo  ";
            statement = this.conexion.conectar().prepareStatement(setenciaSql);
            ResultSet resultado = statement.executeQuery();

            while (resultado.next()) {

                Transaccion transaccion = new Transaccion();
                transaccion.idTransaccion = resultado.getInt("idtransaccion");
                transaccion.codigo = resultado.getInt("codigo");
                transaccion.concepto = resultado.getString("concepto");
                transaccion.cuenta = resultado.getString("nombrecuenta");
                transaccion.debe = resultado.getDouble("debe");
                transaccion.haber = resultado.getDouble("haber");

                transaccionTModel.transacciones.add(transaccion);

            }

            tablaTransacion.repaint();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al recuperar las transaciones de la base de datos");

        }

    }

    public int buscandoSeleccion(String seleccion) {

        int codigo = 0;

        try {

            Statement statement = this.conexion.conectar().createStatement();

            String setenciaCBO = "SELECT * FROM catalogocuenta Where nombrecuenta = " + "'" + seleccion + "'";

            //  System.out.println(seleccion + " ya en el metodo");
            ResultSet resultadoCbo = statement.executeQuery(setenciaCBO);

            if (resultadoCbo.next()) {
                codigo = resultadoCbo.getInt("codigo");
                // System.out.println(codigo);
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(this, "Error en la seleccion " + e);
        }
        //cerrarConexion();
        return codigo;

    }

    public void limpiar() {

        txtConceptoTransaccion.setText(" ");
        txtMontoTransaccion.setText(" ");
        txtidTransaccionTra.setText(" ");
        cboCuentaTrasaccion.setSelectedIndex(0);
        cboSaldoTransaccion.setSelectedIndex(0);
        rdTransaccion.setSelected(false);

    }

    //CENTRADO DE VENTANA
    private void centrarVentanaEnPantalla() {
        // Obtiene el tamaño de la pantalla
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        // Obtiene el tamaño de la ventana
        Dimension windowSize = getSize();
        // Calcula la posición en la que se debe colocar la ventana para centrarla
        int x = (screenSize.width - windowSize.width) / 2;
        int y = (screenSize.height - windowSize.height) / 2;
        // Establece la ubicación de la ventana
        setLocation(x, y);
    }

    public void totalizacion() {

        ArrayList<Cuenta> listCuenta = new ArrayList<Cuenta>();

        // Statement statement = conexion.conectar().createStatement();
        //Conexion conexion = new Conexion();
        try {

            String setenciaSql = "SELECT   c.codigo AS codigo, c.nombrecuenta AS nombre,  SUM(t.debe) AS debe ,  SUM(t.haber) AS haber\n"
                    + "FROM catalogocuenta c INNER JOIN transaccion t ON c.codigo = t.codigo GROUP BY c.codigo, c.nombrecuenta;";

            Statement statement = conexion.conectar().createStatement();
            ResultSet resultado = statement.executeQuery(setenciaSql);

            while (resultado.next()) {
                Cuenta cuenta = new Cuenta();
                cuenta.codigo = resultado.getInt("codigo");

                cuenta.totalizacion = Math.abs(resultado.getDouble("debe") - resultado.getDouble("haber"));

                if (resultado.getDouble("debe") >= resultado.getDouble("haber")) {
                    cuenta.deudor = true;
                } else {

                    cuenta.deudor = false;

                }

                listCuenta.add(cuenta);

            }

            //tablaTransacion.repaint();
            cerrarConexion();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al recuperar las cuentas de la base");

            ex.printStackTrace();
        }

        try {
            PreparedStatement statement = null;

            for (Cuenta cuenta : listCuenta) {

                String sentenciaSql = "Select * from cuenta where codigo=?";
                statement = this.conexion.conectar().prepareStatement(sentenciaSql);
                statement.setInt(1, cuenta.codigo);
                ResultSet resultado = statement.executeQuery();

                if (resultado.next()) {

                    try {

                        String sentencia = " UPDATE cuenta SET totalizacion= ?, deudor= ? WHERE codigo=? ";

                        PreparedStatement preparedStatement = conexion.conectar().prepareCall(sentencia);

                        preparedStatement.setDouble(1, cuenta.totalizacion);
                        preparedStatement.setBoolean(2, cuenta.deudor);
                        preparedStatement.setInt(3, cuenta.codigo);
                        preparedStatement.executeUpdate();
                        cerrarConexion();

                    } catch (SQLException e) {

                        JOptionPane.showMessageDialog(this, "Error al actualizar " + e);
                    }

                } else {

                    try {
                        String sentenciaIn = " INSERT INTO cuenta(codigo, totalizacion, deudor) Values(?, ?, ?)";

                        PreparedStatement preparedStatement = conexion.conectar().prepareStatement(sentenciaIn);
                        preparedStatement.setInt(1, cuenta.codigo);
                        preparedStatement.setDouble(2, cuenta.totalizacion);
                        preparedStatement.setBoolean(3, cuenta.deudor);
                        preparedStatement.executeUpdate();
                        cerrarConexion();

                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(this, "Error insertar la totalizacion " + e);
                    }

                }

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e);

        }
        // cerrarConexion();
        resetear(listCuenta);

        //resetear(listCuenta);
    }

    private void resetear(ArrayList<Cuenta> listCuenta) {

        ArrayList<Cuenta> listaprueba = new ArrayList<Cuenta>();

        Cuenta cuenta = new Cuenta();
        try {
            PreparedStatement statement = null;
            String sentenciaSql = "select * from cuenta";
            statement = this.conexion.conectar().prepareStatement(sentenciaSql);

            ResultSet resultado = statement.executeQuery();

            while (resultado.next()) {
                Cuenta cuenta1 = new Cuenta();

                cuenta1.idCuenta = resultado.getInt("idcuenta");
                cuenta1.codigo = resultado.getInt("codigo");
                cuenta1.totalizacion = resultado.getDouble("totalizacion");

                listaprueba.add(cuenta1);
            }
            cerrarConexion();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al recuperar los datos1 de cuenta " + e);
        }

        ArrayList<Cuenta> codigosDiferentes = new ArrayList<Cuenta>();

        for (Cuenta cuenta1 : listaprueba) {
            boolean encontrado = false;

            for (Cuenta cuenta2 : listCuenta) {
                if (cuenta1.codigo == cuenta2.codigo) {
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado) {
                codigosDiferentes.add(cuenta1);
            }
        }

        for (Cuenta codigosDiferente : codigosDiferentes) {

            //    System.out.println(codigosDiferente.codigo);
            try {
                String setenciaSql = "DELETE FROM balancecomprobacion Where idcuenta= ?";
                PreparedStatement prepStat = conexion.conectar().prepareStatement(setenciaSql);
                prepStat.setInt(1, codigosDiferente.idCuenta);
                prepStat.executeUpdate();

                UpdateJTable();
                cerrarConexion();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, e);
            }

            try {
                String setenciaSql = "DELETE FROM cuenta Where idcuenta= ?";
                PreparedStatement prepStat = conexion.conectar().prepareStatement(setenciaSql);
                prepStat.setInt(1, codigosDiferente.idCuenta);
                prepStat.executeUpdate();

                UpdateJTable();
                cerrarConexion();

            } catch (SQLException ex) {
                Logger.getLogger(Transacciones.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //  cerrarConexion();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        cboCuentaAjuste = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaTransacion = new javax.swing.JTable();
        btnNuevaTransaccion = new javax.swing.JButton();
        btnEliminarTransaccion = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtMontoTransaccion = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtConceptoTransaccion = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cboCuentaTrasaccion = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        cboSaldoTransaccion = new javax.swing.JComboBox<>();
        btnGuardarTransaccion = new javax.swing.JButton();
        btnCancelarTransaccion = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        btnActualizarTransaccion = new javax.swing.JButton();
        txtidTransaccionTra = new javax.swing.JTextField();
        BtnInicioTransaccion = new javax.swing.JButton();
        BtnInventarioTransaccion = new javax.swing.JButton();
        rdTransaccion = new javax.swing.JRadioButton();
        btnCerrar = new javax.swing.JButton();

        jButton2.setText("jButton2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Transacción");
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cboCuentaAjuste.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Registros de las transacciones");
        cboCuentaAjuste.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 65, 172, -1));

        tablaTransacion.setModel(transaccionTModel);
        tablaTransacion.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tablaTransacion.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tablaTransacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaTransacionMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaTransacion);

        cboCuentaAjuste.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 101, 604, 285));

        btnNuevaTransaccion.setBackground(new java.awt.Color(0, 106, 0));
        btnNuevaTransaccion.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnNuevaTransaccion.setForeground(new java.awt.Color(255, 255, 255));
        btnNuevaTransaccion.setText("Nueva transaccion");
        btnNuevaTransaccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevaTransaccionActionPerformed(evt);
            }
        });
        cboCuentaAjuste.add(btnNuevaTransaccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 430, -1, -1));

        btnEliminarTransaccion.setBackground(new java.awt.Color(175, 0, 0));
        btnEliminarTransaccion.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnEliminarTransaccion.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminarTransaccion.setText("Eliminar transaccion");
        btnEliminarTransaccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarTransaccionActionPerformed(evt);
            }
        });
        cboCuentaAjuste.add(btnEliminarTransaccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(196, 430, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Ingresar nueva transaccion");
        cboCuentaAjuste.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 80, 208, -1));

        jLabel3.setText("Monto: ");
        cboCuentaAjuste.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(663, 120, 104, -1));

        txtMontoTransaccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMontoTransaccionKeyTyped(evt);
            }
        });
        cboCuentaAjuste.add(txtMontoTransaccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(784, 117, 165, -1));

        jLabel4.setText("Concepto");
        cboCuentaAjuste.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(663, 182, 104, -1));
        cboCuentaAjuste.add(txtConceptoTransaccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(784, 179, 165, -1));

        jLabel5.setText("Cuenta afectada:");
        cboCuentaAjuste.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(663, 244, 104, -1));

        cboCuentaTrasaccion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione" }));
        cboCuentaAjuste.add(cboCuentaTrasaccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 240, 165, -1));

        jLabel6.setText("Saldo:");
        cboCuentaAjuste.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(663, 303, 104, 22));

        cboSaldoTransaccion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione", "Debe", "Haber" }));
        cboSaldoTransaccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboSaldoTransaccionActionPerformed(evt);
            }
        });
        cboCuentaAjuste.add(cboSaldoTransaccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 300, 170, 30));

        btnGuardarTransaccion.setBackground(new java.awt.Color(0, 106, 0));
        btnGuardarTransaccion.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnGuardarTransaccion.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardarTransaccion.setText("Guardar");
        btnGuardarTransaccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarTransaccionActionPerformed(evt);
            }
        });
        cboCuentaAjuste.add(btnGuardarTransaccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(783, 386, -1, -1));

        btnCancelarTransaccion.setBackground(new java.awt.Color(175, 0, 0));
        btnCancelarTransaccion.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCancelarTransaccion.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelarTransaccion.setText("Cancelar");
        btnCancelarTransaccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarTransaccionActionPerformed(evt);
            }
        });
        cboCuentaAjuste.add(btnCancelarTransaccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(873, 386, -1, -1));

        jLabel8.setText("Hacer doble clic en un registros para editar");
        cboCuentaAjuste.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 404, -1, -1));

        btnActualizarTransaccion.setBackground(new java.awt.Color(0, 89, 255));
        btnActualizarTransaccion.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnActualizarTransaccion.setForeground(new java.awt.Color(255, 255, 255));
        btnActualizarTransaccion.setText("Actualizar");
        btnActualizarTransaccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarTransaccionActionPerformed(evt);
            }
        });
        cboCuentaAjuste.add(btnActualizarTransaccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(783, 426, 166, -1));

        txtidTransaccionTra.setEnabled(false);
        cboCuentaAjuste.add(txtidTransaccionTra, new org.netbeans.lib.awtextra.AbsoluteConstraints(869, 47, 71, -1));

        BtnInicioTransaccion.setText("Inicio");
        BtnInicioTransaccion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BtnInicioTransaccionMouseExited(evt);
            }
        });
        BtnInicioTransaccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnInicioTransaccionActionPerformed(evt);
            }
        });
        cboCuentaAjuste.add(BtnInicioTransaccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 0, 117, 30));

        BtnInventarioTransaccion.setText("Inventario");
        BtnInventarioTransaccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnInventarioTransaccionActionPerformed(evt);
            }
        });
        cboCuentaAjuste.add(BtnInventarioTransaccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 0, 116, 30));

        rdTransaccion.setText("Credito");
        cboCuentaAjuste.add(rdTransaccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 340, 80, -1));

        btnCerrar.setBackground(new java.awt.Color(255, 0, 0));
        btnCerrar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCerrar.setForeground(new java.awt.Color(255, 255, 255));
        btnCerrar.setText("Cerrar Sesión");
        btnCerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCerrarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCerrarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCerrarMouseExited(evt);
            }
        });
        cboCuentaAjuste.add(btnCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 0, 140, 30));

        getContentPane().add(cboCuentaAjuste, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 990, 470));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnInicioTransaccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnInicioTransaccionActionPerformed
        // TODO add your handling code here:

        Inicio inicio = new Inicio();
        inicio.setVisible(true);
        this.dispose();

        // cerrarConexion();
        //JOptionPane.showMessageDialog(this, "La conexion a la base de datos ha sido cerrada");

    }//GEN-LAST:event_BtnInicioTransaccionActionPerformed

    private void BtnInventarioTransaccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnInventarioTransaccionActionPerformed
        // TODO add your handling code here:

        InventarioCRUD inventario = new InventarioCRUD();
        inventario.setVisible(true);
        this.dispose();

        // cerrarConexion();
        //   JOptionPane.showMessageDialog(this, "La conexion a la base de datos ha sido cerrada");

    }//GEN-LAST:event_BtnInventarioTransaccionActionPerformed

    private void btnNuevaTransaccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevaTransaccionActionPerformed
        // TODO add your handling code here:
        habilitarControles(true);
        btnActualizarTransaccion.setEnabled(false);
    }//GEN-LAST:event_btnNuevaTransaccionActionPerformed

    private void btnEliminarTransaccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarTransaccionActionPerformed
        // TODO add your handling code here:
        habilitarControles(false);
        // indices de las filas seleccionadas
        int[] indices = tablaTransacion.getSelectedRows();
        ArrayList<Transaccion> aEliminar = new ArrayList<Transaccion>();

        for (int i : indices) {
            Transaccion transaccion = transaccionTModel.transacciones.get(i);
            String setenciaSql = "DELETE FROM transaccion Where idtransaccion= ?";
            aEliminar.add(transaccion);
            try {
                PreparedStatement prepStat = conexion.conectar().prepareStatement(setenciaSql);
                prepStat.setInt(1, transaccion.idTransaccion);
                prepStat.executeUpdate();
                JOptionPane.showMessageDialog(this, "Elimino correctamente " + transaccion.idTransaccion);
                UpdateJTable();
                tablaTransacion.repaint();
                cerrarConexion();

            } catch (SQLException ex) {
                Logger.getLogger(Transacciones.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }//GEN-LAST:event_btnEliminarTransaccionActionPerformed
    private void cerrarConexion() {
        try {
            conexion.conectar().close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Ocurrio un error al cerrar la conexion a la base de datos");
        }
    }
    private void btnGuardarTransaccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarTransaccionActionPerformed

        double montoIva = 0;
        if (!validando()) {

            try {

                Transaccion transaccion = new Transaccion();
                String seleccion;

                seleccion = (String) cboCuentaTrasaccion.getSelectedItem();

                // System.out.println(seleccion);
                transaccion.codigo = buscandoSeleccion(seleccion);

                transaccion.concepto = txtConceptoTransaccion.getText();
                if (cboSaldoTransaccion.getSelectedItem() == "Debe") {
                    transaccion.debe = Double.parseDouble(txtMontoTransaccion.getText());
                    transaccion.haber = 0.00;
                } else {
                    transaccion.haber = Double.parseDouble(txtMontoTransaccion.getText());
                    transaccion.debe = 0.00;
                }

                switch (transaccion.codigo) {
                    case 415:
                    case 121:
                    case 412:
                        montoIva = transaccion.debe * IVA;
                        if (rdTransaccion.isSelected()) {
                            try {
                                String setenciaSql = "INSERT INTO transaccion(codigo, concepto, debe, haber) Values (?,?,?,?)";
                                PreparedStatement preparedStatement = conexion.conectar().prepareStatement(setenciaSql);
                                preparedStatement.setInt(1, transaccion.codigo);
                                preparedStatement.setString(2, transaccion.concepto);
                                preparedStatement.setDouble(3, transaccion.debe);
                                preparedStatement.setDouble(4, transaccion.haber);
                                preparedStatement.executeUpdate();
                                cerrarConexion();

                            } catch (SQLException e) {
                                JOptionPane.showMessageDialog(this, e);
                            }
                            try {
                                String setenciaSql = "INSERT INTO transaccion(codigo, concepto, debe, haber) Values (?,?,?,?)";
                                PreparedStatement preparedStatement = conexion.conectar().prepareStatement(setenciaSql);
                                preparedStatement.setInt(1, 117);
                                preparedStatement.setString(2, "Calculo del credito fiscal");
                                preparedStatement.setDouble(3, (montoIva));
                                preparedStatement.setDouble(4, transaccion.haber);
                                preparedStatement.executeUpdate();
                                cerrarConexion();
                            } catch (SQLException e) {
                                JOptionPane.showMessageDialog(this, e);
                            }
                            try {
                                String setenciaSql = "INSERT INTO transaccion(codigo, concepto, debe, haber) Values (?,?,?,?)";
                                PreparedStatement preparedStatement = conexion.conectar().prepareStatement(setenciaSql);
                                preparedStatement.setInt(1, 211);
                                preparedStatement.setString(2, "Ingresando monto total que se debe");
                                preparedStatement.setDouble(3, 0);
                                preparedStatement.setDouble(4, transaccion.debe + montoIva);
                                preparedStatement.executeUpdate();
                                cerrarConexion();

                            } catch (SQLException e) {
                                JOptionPane.showMessageDialog(this, e);
                            }

                        } else {
                            montoIva = transaccion.debe * IVA;
                            try {
                                String setenciaSql = "INSERT INTO transaccion(codigo, concepto, debe, haber) Values (?,?,?,?)";
                                PreparedStatement preparedStatement = conexion.conectar().prepareStatement(setenciaSql);
                                preparedStatement.setInt(1, transaccion.codigo);
                                preparedStatement.setString(2, transaccion.concepto);
                                preparedStatement.setDouble(3, transaccion.debe);
                                preparedStatement.setDouble(4, transaccion.haber);
                                preparedStatement.executeUpdate();
                                cerrarConexion();

                            } catch (SQLException e) {
                                JOptionPane.showMessageDialog(this, e);

                            }

                            try {
                                String setenciaSql = "INSERT INTO transaccion(codigo, concepto, debe, haber) Values (?,?,?,?)";
                                PreparedStatement preparedStatement = conexion.conectar().prepareStatement(setenciaSql);
                                preparedStatement.setInt(1, 117);
                                preparedStatement.setString(2, "Calculo del credito fiscal");
                                preparedStatement.setDouble(3, (montoIva));
                                preparedStatement.setDouble(4, transaccion.haber);
                                preparedStatement.executeUpdate();
                                cerrarConexion();
                            } catch (SQLException e) {
                                JOptionPane.showMessageDialog(this, e);
                            }
                            try {
                                String setenciaSql = "INSERT INTO transaccion(codigo, concepto, debe, haber) Values (?,?,?,?)";
                                PreparedStatement preparedStatement = conexion.conectar().prepareStatement(setenciaSql);
                                preparedStatement.setInt(1, 111);
                                preparedStatement.setString(2, "Ingresando monto total");
                                preparedStatement.setDouble(3, 0);
                                preparedStatement.setDouble(4, transaccion.debe + montoIva);
                                preparedStatement.executeUpdate();
                                cerrarConexion();

                            } catch (SQLException e) {

                                JOptionPane.showConfirmDialog(this, e);

                            }

                        }
                        break;
                    case 511:
                        montoIva = transaccion.haber * IVA;
                        if (rdTransaccion.isSelected()) {
                            try {
                                String setenciaSql = "INSERT INTO transaccion(codigo, concepto, debe, haber) Values (?,?,?,?)";
                                PreparedStatement preparedStatement = conexion.conectar().prepareStatement(setenciaSql);
                                preparedStatement.setInt(1, transaccion.codigo);
                                preparedStatement.setString(2, transaccion.concepto);
                                preparedStatement.setDouble(3, transaccion.debe);
                                preparedStatement.setDouble(4, transaccion.haber);
                                preparedStatement.executeUpdate();
                                cerrarConexion();

                            } catch (SQLException e) {
                                JOptionPane.showMessageDialog(this, e);

                            }

                            try {
                                String setenciaSql = "INSERT INTO transaccion(codigo, concepto, debe, haber) Values (?,?,?,?)";
                                PreparedStatement preparedStatement = conexion.conectar().prepareStatement(setenciaSql);
                                preparedStatement.setInt(1, 213);
                                preparedStatement.setString(2, "Calculo del devito fiscal");
                                preparedStatement.setDouble(3, 0);
                                preparedStatement.setDouble(4, (montoIva));
                                preparedStatement.executeUpdate();
                                cerrarConexion();
                            } catch (SQLException e) {
                                JOptionPane.showMessageDialog(this, e);
                            }
                            try {
                                String setenciaSql = "INSERT INTO transaccion(codigo, concepto, debe, haber) Values (?,?,?,?)";
                                PreparedStatement preparedStatement = conexion.conectar().prepareStatement(setenciaSql);
                                preparedStatement.setInt(1, 115);
                                preparedStatement.setString(2, "Ingresando monto total");
                                preparedStatement.setDouble(3, transaccion.haber + montoIva);
                                preparedStatement.setDouble(4, 0);
                                preparedStatement.executeUpdate();
                                cerrarConexion();

                            } catch (SQLException e) {

                            }

                        } else {

                            montoIva = transaccion.haber * IVA;

                            try {
                                String setenciaSql = "INSERT INTO transaccion(codigo, concepto, debe, haber) Values (?,?,?,?)";
                                PreparedStatement preparedStatement = conexion.conectar().prepareStatement(setenciaSql);
                                preparedStatement.setInt(1, transaccion.codigo);
                                preparedStatement.setString(2, transaccion.concepto);
                                preparedStatement.setDouble(3, transaccion.debe);
                                preparedStatement.setDouble(4, transaccion.haber);
                                preparedStatement.executeUpdate();
                                cerrarConexion();

                            } catch (SQLException e) {
                                JOptionPane.showMessageDialog(this, e);

                            }

                            try {
                                String setenciaSql = "INSERT INTO transaccion(codigo, concepto, debe, haber) Values (?,?,?,?)";
                                PreparedStatement preparedStatement = conexion.conectar().prepareStatement(setenciaSql);
                                preparedStatement.setInt(1, 213);
                                preparedStatement.setString(2, "Calculo del devito fiscal");
                                preparedStatement.setDouble(3, 0);
                                preparedStatement.setDouble(4, (montoIva));
                                preparedStatement.executeUpdate();
                                cerrarConexion();

                            } catch (SQLException e) {
                                JOptionPane.showMessageDialog(this, e);
                            }
                            try {
                                String setenciaSql = "INSERT INTO transaccion(codigo, concepto, debe, haber) Values (?,?,?,?)";
                                PreparedStatement preparedStatement = conexion.conectar().prepareStatement(setenciaSql);
                                preparedStatement.setInt(1, 111);
                                preparedStatement.setString(2, "Ingresando monto total");
                                preparedStatement.setDouble(3, transaccion.haber + montoIva);
                                preparedStatement.setDouble(4, 0);
                                preparedStatement.executeUpdate();
                                cerrarConexion();

                            } catch (SQLException e) {

                            }

                        }
                        break;
                    default:
                        String setenciaSql = "INSERT INTO transaccion(codigo, concepto, debe, haber) Values (?,?,?,?)";
                        PreparedStatement preparedStatement = conexion.conectar().prepareStatement(setenciaSql);
                        preparedStatement.setInt(1, transaccion.codigo);
                        preparedStatement.setString(2, transaccion.concepto);
                        preparedStatement.setDouble(3, transaccion.debe);
                        preparedStatement.setDouble(4, transaccion.haber);
                        preparedStatement.executeUpdate();
                        transaccionTModel.transacciones.add(transaccion);
                        break;
                }
                // tablaTransacion.repaint();
                UpdateJTable();
                totalizacion();
               // cerrarConexion();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error al ingresar los datos");
                e.printStackTrace();
            }

            // consultaIncial();
            limpiar();
            habilitarControles(false);

            TransaccionTableModel model = (TransaccionTableModel) tablaTransacion.getModel();
            model.fireTableDataChanged();
        }
    }//GEN-LAST:event_btnGuardarTransaccionActionPerformed

    private void tablaTransacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaTransacionMouseClicked
        // TODO add your handling code here:
        habilitarControles(false);
        int clics = evt.getClickCount();
        int row = tablaTransacion.rowAtPoint(evt.getPoint());

        if (clics == 2) {
            Transaccion t = transaccionTModel.transacciones.get(row);
            transaccionActual = t;

            txtidTransaccionTra.setText(String.valueOf(t.idTransaccion));
            txtConceptoTransaccion.setText(t.concepto);
            habilitarControles(true);

            if (t.debe > 0) {

                txtMontoTransaccion.setText(t.debe.toString());
            } else {
                txtMontoTransaccion.setText(t.haber.toString());

            }

        }

        btnGuardarTransaccion.setEnabled(false);

    }//GEN-LAST:event_tablaTransacionMouseClicked

    private void btnActualizarTransaccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarTransaccionActionPerformed

        String seleccion = (String) cboCuentaTrasaccion.getSelectedItem();
        int codigo = 0;

        if (!validando()) {
            try {
                String sentenciaSql = "UPDATE transaccion SET codigo = ?, concepto=?, debe=?, haber=? WHERE idtransaccion= ?";

                codigo = buscandoSeleccion(seleccion);
                //System.out.println("probando codigo desde btn +" + codigo);
                PreparedStatement preparedStatement = conexion.conectar().prepareStatement(sentenciaSql);
                preparedStatement.setInt(1, codigo);
                preparedStatement.setString(2, txtConceptoTransaccion.getText());

                if (cboSaldoTransaccion.getSelectedItem() == "Debe") {

                    preparedStatement.setDouble(3, Double.parseDouble(txtMontoTransaccion.getText()));
                    preparedStatement.setDouble(4, 0.00);
                } else {
                    preparedStatement.setDouble(3, 0.00);
                    preparedStatement.setDouble(4, Double.parseDouble(txtMontoTransaccion.getText()));
                }

                preparedStatement.setInt(5, Integer.valueOf(txtidTransaccionTra.getText()));
                preparedStatement.executeUpdate();

                UpdateJTable();
                cerrarConexion();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error al actualizar");
                e.printStackTrace();
            }
        }
        // UpdateJTable();
        limpiar();
        habilitarControles(false);


    }//GEN-LAST:event_btnActualizarTransaccionActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
/*
        try {
            conexion.conectar().close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Ocurrio un error al cerrar la conexion a la base de datos");
        }
        JOptionPane.showMessageDialog(this, "La conexion a la base de datos ha sido cerrada");
         */

    }//GEN-LAST:event_formWindowClosing

    private void btnCancelarTransaccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarTransaccionActionPerformed
        // TODO add your handling code here:
        habilitarControles(false);
        limpiar();
    }//GEN-LAST:event_btnCancelarTransaccionActionPerformed

    private void txtMontoTransaccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMontoTransaccionKeyTyped

        char c = evt.getKeyChar();
        String text = txtMontoTransaccion.getText();

        if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE || c == '.') || (text.length() >= 10 && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtMontoTransaccionKeyTyped

    private void BtnInicioTransaccionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnInicioTransaccionMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnInicioTransaccionMouseExited

    private void cboSaldoTransaccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboSaldoTransaccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboSaldoTransaccionActionPerformed

    private void btnCerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarMouseClicked
        // Crea una nueva instancia de NuevoVentana
        Login login = new Login();
        login.setVisible(true);
        // Cierra la ventana actual
        this.dispose();

        //JOptionPane.showMessageDialog(this, "La conexion a la base de datos ha sido cerrada");

    }//GEN-LAST:event_btnCerrarMouseClicked

    private void btnCerrarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarMouseEntered
        btnCerrar.setBackground(new Color(255, 102, 102));
        btnCerrar.setForeground(new Color(0, 0, 0));
    }//GEN-LAST:event_btnCerrarMouseEntered

    private void btnCerrarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarMouseExited
        // Regresa al color original fuera
        btnCerrar.setBackground(new Color(255, 0, 0));
        btnCerrar.setForeground(new java.awt.Color(255, 255, 255));
    }//GEN-LAST:event_btnCerrarMouseExited

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Transacciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Transacciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Transacciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Transacciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Transacciones().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnInicioTransaccion;
    private javax.swing.JButton BtnInventarioTransaccion;
    private javax.swing.JButton btnActualizarTransaccion;
    private javax.swing.JButton btnCancelarTransaccion;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnEliminarTransaccion;
    private javax.swing.JButton btnGuardarTransaccion;
    private javax.swing.JButton btnNuevaTransaccion;
    private javax.swing.JPanel cboCuentaAjuste;
    private javax.swing.JComboBox<String> cboCuentaTrasaccion;
    private javax.swing.JComboBox<String> cboSaldoTransaccion;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rdTransaccion;
    private javax.swing.JTable tablaTransacion;
    private javax.swing.JTextField txtConceptoTransaccion;
    private javax.swing.JTextField txtMontoTransaccion;
    private javax.swing.JTextField txtidTransaccionTra;
    // End of variables declaration//GEN-END:variables
}
