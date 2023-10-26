package pkg3dmarkers;

import clases.CatalogoCuenta;
import clases.Cuenta;
import clases.Transaccion;
import clases.TransaccionTableModel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Manuel Alcides Ascencio Aleman
 */
public class Transacciones extends javax.swing.JFrame {

    /**
     * Creates new form Transacciones
     */
    //Conexion conexion= new Conexion();
    public TransaccionTableModel transaccionTModel = new TransaccionTableModel();
    Conexion conexion = new Conexion();
    Transaccion transaccionActual = new Transaccion();

    public Transacciones() {
        initComponents();
        //  conexion.conectar();
        inicializarColumnas();
        consultaIncial();
        habilitarControles(false);
        totalizacion();

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
        
      TableColumn   columna1 = tablaTransacion.getColumnModel().getColumn(1);
       columna1.setPreferredWidth(10);
      TableColumn   columna2 = tablaTransacion.getColumnModel().getColumn(2);
       columna2.setPreferredWidth(2);
      TableColumn   columna3 = tablaTransacion.getColumnModel().getColumn(3);
       columna3.setPreferredWidth(250);
       TableColumn  columna4 = tablaTransacion.getColumnModel().getColumn(4);
       columna4.setPreferredWidth(2);
       TableColumn  columna5 = tablaTransacion.getColumnModel().getColumn(5);
       columna5.setPreferredWidth(2);

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

    }

    private void consultaIncial() {

        Conexion conexion = new Conexion();
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
                System.out.println(codigo);
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(this, "Error en la seleccion ");
        }
        return codigo;

    }

    public void limpiar() {

        txtConceptoTransaccion.setText(" ");
        txtMontoTransaccion.setText(" ");
        txtidTransaccionTra.setText(" ");
        cboCuentaTrasaccion.setSelectedIndex(0);
        cboSaldoTransaccion.setSelectedIndex(0);

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

        Conexion conexion = new Conexion();
        try {

            String setenciaSql = "SELECT   c.codigo AS codigo, c.nombrecuenta AS nombre,  SUM(t.debe) AS debe ,  SUM(t.haber) AS haber\n"
                    + "FROM catalogocuenta c INNER JOIN transaccion t ON c.codigo = t.codigo GROUP BY c.codigo, c.nombrecuenta;";

            Statement statement = conexion.conectar().createStatement();
            ResultSet resultado = statement.executeQuery(setenciaSql);

            while (resultado.next()) {
                Cuenta cuenta = new Cuenta();
                cuenta.codigo = resultado.getInt("codigo");

                cuenta.totalizacion = Math.abs(resultado.getDouble("debe") - resultado.getDouble("haber"));

                if (resultado.getDouble("debe") > resultado.getDouble("haber")) {
                    cuenta.deudor = true;
                } else {

                    cuenta.deudor = false;

                }

                listCuenta.add(cuenta);

            }

            //tablaTransacion.repaint();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al recuperar las cuentas de la base");

            ex.printStackTrace();
        }

        for (Cuenta cuenta1 : listCuenta) {
            System.out.println(cuenta1.codigo + " deudor: " + cuenta1.deudor + " Totalizacion: " + cuenta1.totalizacion);
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
                        preparedStatement.execute();

                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(this, "Error insertar la totalizacion " + e);
                    }

                }

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e);

        }

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
        jRadioButton1 = new javax.swing.JRadioButton();

        jButton2.setText("jButton2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Transacción");
        setBackground(new java.awt.Color(255, 255, 255));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Registros de las transacciones");

        tablaTransacion.setModel(transaccionTModel);
        tablaTransacion.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tablaTransacion.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tablaTransacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaTransacionMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaTransacion);

        btnNuevaTransaccion.setText("Nueva transaccion");
        btnNuevaTransaccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevaTransaccionActionPerformed(evt);
            }
        });

        btnEliminarTransaccion.setText("Eliminar transaccion");
        btnEliminarTransaccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarTransaccionActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Ingresar nueva transaccion");

        jLabel3.setText("Monto: ");

        jLabel4.setText("Concepto");

        jLabel5.setText("Cuenta afectada:");

        cboCuentaTrasaccion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione" }));

        jLabel6.setText("Saldo:");

        cboSaldoTransaccion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione", "Debe", "Haber" }));

        btnGuardarTransaccion.setText("Guardar");
        btnGuardarTransaccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarTransaccionActionPerformed(evt);
            }
        });

        btnCancelarTransaccion.setText("Cancelar");
        btnCancelarTransaccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarTransaccionActionPerformed(evt);
            }
        });

        jLabel8.setText("Hacer doble clic en un registros para editar");

        btnActualizarTransaccion.setText("Actualizar");
        btnActualizarTransaccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarTransaccionActionPerformed(evt);
            }
        });

        txtidTransaccionTra.setEnabled(false);

        BtnInicioTransaccion.setText("Inicio");
        BtnInicioTransaccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnInicioTransaccionActionPerformed(evt);
            }
        });

        BtnInventarioTransaccion.setText("Inventario");
        BtnInventarioTransaccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnInventarioTransaccionActionPerformed(evt);
            }
        });

        jRadioButton1.setText("IVA");

        javax.swing.GroupLayout cboCuentaAjusteLayout = new javax.swing.GroupLayout(cboCuentaAjuste);
        cboCuentaAjuste.setLayout(cboCuentaAjusteLayout);
        cboCuentaAjusteLayout.setHorizontalGroup(
            cboCuentaAjusteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cboCuentaAjusteLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(cboCuentaAjusteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cboCuentaAjusteLayout.createSequentialGroup()
                        .addComponent(btnNuevaTransaccion)
                        .addGap(33, 33, 33)
                        .addComponent(btnEliminarTransaccion)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cboCuentaAjusteLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtidTransaccionTra, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38))
                    .addGroup(cboCuentaAjusteLayout.createSequentialGroup()
                        .addGroup(cboCuentaAjusteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(cboCuentaAjusteLayout.createSequentialGroup()
                                .addGap(353, 353, 353)
                                .addComponent(BtnInicioTransaccion, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(BtnInventarioTransaccion, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(cboCuentaAjusteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(cboCuentaAjusteLayout.createSequentialGroup()
                                    .addGap(21, 21, 21)
                                    .addComponent(jLabel8))
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 604, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                        .addGroup(cboCuentaAjusteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(cboCuentaAjusteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(cboCuentaAjusteLayout.createSequentialGroup()
                                    .addGroup(cboCuentaAjusteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(16, 16, 16)
                                    .addGroup(cboCuentaAjusteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(cboCuentaAjusteLayout.createSequentialGroup()
                                            .addGroup(cboCuentaAjusteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txtConceptoTransaccion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtMontoTransaccion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cboCuentaAjusteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(btnActualizarTransaccion, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(cboCuentaAjusteLayout.createSequentialGroup()
                                                        .addComponent(btnGuardarTransaccion)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(btnCancelarTransaccion)))
                                                .addComponent(cboCuentaTrasaccion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(29, 29, 29))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cboCuentaAjusteLayout.createSequentialGroup()
                                            .addComponent(cboSaldoTransaccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jRadioButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(62, 62, 62))
        );
        cboCuentaAjusteLayout.setVerticalGroup(
            cboCuentaAjusteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cboCuentaAjusteLayout.createSequentialGroup()
                .addGroup(cboCuentaAjusteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cboCuentaAjusteLayout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(txtidTransaccionTra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cboCuentaAjusteLayout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(jLabel1)
                        .addGap(14, 14, 14))
                    .addGroup(cboCuentaAjusteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(BtnInicioTransaccion)
                        .addComponent(BtnInventarioTransaccion)))
                .addGap(6, 6, 6)
                .addGroup(cboCuentaAjusteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(cboCuentaAjusteLayout.createSequentialGroup()
                        .addGroup(cboCuentaAjusteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtMontoTransaccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(cboCuentaAjusteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtConceptoTransaccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(cboCuentaAjusteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(cboCuentaTrasaccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(cboCuentaAjusteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboSaldoTransaccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jRadioButton1))
                        .addGap(61, 61, 61)
                        .addGroup(cboCuentaAjusteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnGuardarTransaccion)
                            .addComponent(btnCancelarTransaccion))
                        .addGap(12, 12, 12))
                    .addGroup(cboCuentaAjusteLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8)))
                .addGroup(cboCuentaAjusteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cboCuentaAjusteLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(cboCuentaAjusteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnEliminarTransaccion)
                            .addComponent(btnNuevaTransaccion)))
                    .addGroup(cboCuentaAjusteLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnActualizarTransaccion)))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        getContentPane().add(cboCuentaAjuste, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 4, 1040, 480));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnInicioTransaccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnInicioTransaccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnInicioTransaccionActionPerformed

    private void BtnInventarioTransaccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnInventarioTransaccionActionPerformed
        // TODO add your handling code here:
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

            } catch (SQLException ex) {
                Logger.getLogger(Transacciones.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        tablaTransacion.repaint();
    }//GEN-LAST:event_btnEliminarTransaccionActionPerformed

    private void btnGuardarTransaccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarTransaccionActionPerformed
        // TODO add your handling code here:
        try {

            Transaccion transaccion = new Transaccion();
            String seleccion;

            seleccion = (String) cboCuentaTrasaccion.getSelectedItem();

            System.out.println(seleccion);

            transaccion.codigo = buscandoSeleccion(seleccion);

            transaccion.concepto = txtConceptoTransaccion.getText();
            if (cboSaldoTransaccion.getSelectedItem() == "Debe") {
                transaccion.debe = Double.parseDouble(txtMontoTransaccion.getText());
                transaccion.haber = 0.00;
            } else {
                transaccion.haber = Double.parseDouble(txtMontoTransaccion.getText());
                transaccion.debe = 0.00;
            }

            String setenciaSql = "INSERT INTO transaccion(codigo, concepto, debe, haber) Values (?,?,?,?)";
            PreparedStatement preparedStatement = conexion.conectar().prepareStatement(setenciaSql);
            preparedStatement.setInt(1, transaccion.codigo);
            preparedStatement.setString(2, transaccion.concepto);
            preparedStatement.setDouble(3, transaccion.debe);
            preparedStatement.setDouble(4, transaccion.haber);
            preparedStatement.execute();

            transaccionTModel.transacciones.add(transaccion);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al ingresar los datos");
            e.printStackTrace();
        }
        UpdateJTable();

        // consultaIncial();
        limpiar();
        habilitarControles(false);
        totalizacion();
        TransaccionTableModel model = (TransaccionTableModel) tablaTransacion.getModel();
        model.fireTableDataChanged();

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
        // TODO add your handling code here:
        String seleccion = (String) cboCuentaTrasaccion.getSelectedItem();
        int codigo = 0;

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
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al actualizar");
            e.printStackTrace();
        }
        // UpdateJTable();
        limpiar();
        habilitarControles(false);


    }//GEN-LAST:event_btnActualizarTransaccionActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:

        try {
            conexion.conectar().close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Ocurrio un error al cerrar la conexion a la base de datos");
        }
        JOptionPane.showMessageDialog(this, "La conexion a la base de datos ha sido cerrada");
        
        
    }//GEN-LAST:event_formWindowClosing

    private void btnCancelarTransaccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarTransaccionActionPerformed
        // TODO add your handling code here:
        habilitarControles(false);
        limpiar();
    }//GEN-LAST:event_btnCancelarTransaccionActionPerformed

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
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaTransacion;
    private javax.swing.JTextField txtConceptoTransaccion;
    private javax.swing.JTextField txtMontoTransaccion;
    private javax.swing.JTextField txtidTransaccionTra;
    // End of variables declaration//GEN-END:variables
}
