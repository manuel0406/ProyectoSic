/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pkg3dmarkers;
import java.awt.event.KeyEvent;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.Statement;


/**
 *
 * @author samue
 */
public class CatalogoCuentaP extends javax.swing.JFrame {

    /**
     * Creates new form CatalogoCuentaP
     */
    
    
    public CatalogoCuentaP() {
        initComponents();
        
        mostrarCatalogo(tableCatalogo);

    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BtnInicioCatalogo = new javax.swing.JButton();
        BtnTransaCatalogo = new javax.swing.JButton();
        BtnInventarioCatalogo = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableCatalogo = new javax.swing.JTable();
        btnNuevaCuenta = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblData = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtCuentaCodigo = new javax.swing.JTextField();
        txtCuentaNombre = new javax.swing.JTextField();
        btnGuardarCuenta = new javax.swing.JButton();
        btnCancelarNuevaCuenta = new javax.swing.JButton();
        btnModificarCuenta = new javax.swing.JButton();
        btnEliminarCuenta = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtCod = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        BtnInicioCatalogo.setText("Inicio");

        BtnTransaCatalogo.setText("Transacciones");

        BtnInventarioCatalogo.setText("Inventario");

        jLabel1.setText("Catálogo actual");

        tableCatalogo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tableCatalogo.setFocusable(false);
        tableCatalogo = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tableCatalogo.getTableHeader().setResizingAllowed(false);
        tableCatalogo.getTableHeader().setReorderingAllowed(false);
        tableCatalogo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableCatalogoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableCatalogo);

        btnNuevaCuenta.setText("Nueva Cuenta");
        btnNuevaCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevaCuentaActionPerformed(evt);
            }
        });

        jLabel5.setText("Para actualizar un registro, haga clic en la fila que desea editar.");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setText("Código:");

        jLabel4.setText("Nombre:");

        txtCuentaCodigo.setEnabled(false);
        txtCuentaCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCuentaCodigoKeyTyped(evt);
            }
        });

        txtCuentaNombre.setEnabled(false);
        txtCuentaNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCuentaNombreKeyTyped(evt);
            }
        });

        btnGuardarCuenta.setText("Guardar");
        btnGuardarCuenta.setEnabled(false);
        btnGuardarCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarCuentaActionPerformed(evt);
            }
        });

        btnCancelarNuevaCuenta.setText("Cancelar");
        btnCancelarNuevaCuenta.setEnabled(false);
        btnCancelarNuevaCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarNuevaCuentaActionPerformed(evt);
            }
        });

        btnModificarCuenta.setText("Actualizar");
        btnModificarCuenta.setEnabled(false);
        btnModificarCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarCuentaActionPerformed(evt);
            }
        });

        btnEliminarCuenta.setText("Eliminar");
        btnEliminarCuenta.setEnabled(false);
        btnEliminarCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarCuentaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnCancelarNuevaCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnEliminarCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(btnGuardarCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnModificarCuenta))
                            .addComponent(txtCuentaNombre)
                            .addComponent(txtCuentaCodigo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblData, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtCuentaCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtCuentaNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGuardarCuenta)
                    .addComponent(btnModificarCuenta))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelarNuevaCuenta)
                    .addComponent(btnEliminarCuenta))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel6.setText("Editor de registros");

        jLabel7.setText("Para eliminar un registro, haga doble clic en la fila que desea eliminar.");

        txtCod.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel1)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 506, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(btnNuevaCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(393, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(BtnInicioCatalogo, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BtnTransaCatalogo)
                        .addGap(18, 18, 18)
                        .addComponent(BtnInventarioCatalogo, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(312, 312, 312))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(21, 21, 21))
                    .addComponent(txtCod, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnInicioCatalogo)
                    .addComponent(BtnTransaCatalogo)
                    .addComponent(BtnInventarioCatalogo))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addComponent(btnNuevaCuenta)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtCod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevaCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevaCuentaActionPerformed
        // TODO add your handling code here:
        txtCuentaCodigo.setEditable(true);
        txtCuentaNombre.setEditable(true);
        txtCuentaCodigo.setText("");
        txtCuentaNombre.setText("");
        txtCuentaCodigo.setEnabled(true);
        txtCuentaNombre.setEnabled(true);
        
        lblData.setText("Estás agregando una cuenta nueva");
        btnGuardarCuenta.setEnabled(true);        
        btnCancelarNuevaCuenta.setEnabled(true); 
        btnModificarCuenta.setEnabled(false);
        btnEliminarCuenta.setEnabled(false);
    }//GEN-LAST:event_btnNuevaCuentaActionPerformed

    //Metodo que bloquea las teclas no numericas Y limita a 5 cifras
    private void txtCuentaCodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCuentaCodigoKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        String text = txtCuentaCodigo.getText();

        if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE) || (text.length() >= 5)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtCuentaCodigoKeyTyped

    //Metodo que bloquea lsa teclas numericas y caracteres especiales
    private void txtCuentaNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCuentaNombreKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!(Character.isLetter(c) || c == ' ' || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE || c == '\u00E1' || c == '\u00E9' || c == '\u00ED' || c == '\u00F3' || c == '\u00FA' || c == '\u00C1' || c == '\u00C9' || c == '\u00CD' || c == '\u00D3' || c == '\u00DA' || c == '\u00F1' || c == '\u00D1')) {
            evt.consume();
        }
    }//GEN-LAST:event_txtCuentaNombreKeyTyped
       
    private void btnGuardarCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarCuentaActionPerformed
        // TODO add your handling code here:
        nuevaCuenta(txtCuentaCodigo, txtCuentaNombre);
        mostrarCatalogo(tableCatalogo);

        txtCuentaCodigo.setText("");
        txtCuentaNombre.setText("");        
    }//GEN-LAST:event_btnGuardarCuentaActionPerformed

    private void btnCancelarNuevaCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarNuevaCuentaActionPerformed
        // TODO add your handling code here:
        txtCuentaCodigo.setText("");
        txtCuentaNombre.setText("");
        txtCuentaCodigo.setEnabled(false);
        txtCuentaNombre.setEnabled(false);

        btnGuardarCuenta.setEnabled(false);
        btnModificarCuenta.setEnabled(false);
        btnCancelarNuevaCuenta.setEnabled(false);
        btnEliminarCuenta.setEnabled(false);

        lblData.setText("");
    }//GEN-LAST:event_btnCancelarNuevaCuentaActionPerformed

    private void tableCatalogoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableCatalogoMouseClicked
        // TODO add your handling code here:
        int clics = evt.getClickCount();
        int row = tableCatalogo.rowAtPoint(evt.getPoint());
        
        
        //En este caso esta eliminando un registro
        if (clics == 2) {
            //Desactivando entradas
            seleccionarRegistro(tableCatalogo, txtCod,txtCuentaCodigo, txtCuentaNombre);
            txtCuentaCodigo.setEditable(false);
            txtCuentaNombre.setEditable(false);
            
            //activando botones necesarios
            btnGuardarCuenta.setEnabled(false);
            btnModificarCuenta.setEnabled(false);
            btnCancelarNuevaCuenta.setEnabled(true);
            btnEliminarCuenta.setEnabled(true);
            lblData.setText("Estás eliminando la siguiente cuenta");
        }
        
        //En este caso esta editando un registro
        else {
            //activando entradas
            seleccionarRegistro(tableCatalogo, txtCod, txtCuentaCodigo, txtCuentaNombre);
            txtCuentaCodigo.setEnabled(true);
            txtCuentaNombre.setEnabled(true);
            txtCuentaCodigo.setEditable(true);
            txtCuentaNombre.setEditable(true);

            //activando botones necesarios
            btnGuardarCuenta.setEnabled(false);
            btnModificarCuenta.setEnabled(true);
            btnCancelarNuevaCuenta.setEnabled(true);
            btnEliminarCuenta.setEnabled(false);
            lblData.setText("Estás actualizando la siguiente cuenta");
        }
    }//GEN-LAST:event_tableCatalogoMouseClicked

    private void btnModificarCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarCuentaActionPerformed
        // TODO add your handling code here:
        editarCuenta(txtCod, txtCuentaCodigo, txtCuentaNombre);
        mostrarCatalogo(tableCatalogo);
    }//GEN-LAST:event_btnModificarCuentaActionPerformed

    private void btnEliminarCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarCuentaActionPerformed
        // TODO add your handling code here:
        eliminarCuenta(txtCuentaCodigo, txtCuentaNombre);
        mostrarCatalogo(tableCatalogo);
    }//GEN-LAST:event_btnEliminarCuentaActionPerformed
    
    
    public void mostrarCatalogo(JTable tablaCatalogo) {
        txtCod.setVisible(false);
        DefaultTableModel modelo = new DefaultTableModel();

        //modelo.addColumn("id");
        modelo.addColumn("Código");
        modelo.addColumn("Nombre");

        tablaCatalogo.setModel(modelo);

        //String sql = "SELECT idcatalgo, codigo, nombrecuenta FROM catalogocuenta ORDER BY codigo::text;";
        String sql = "SELECT codigo, nombrecuenta FROM catalogocuenta ORDER BY codigo::text;";

        
        //String[] datos = new String[3];
        String[] datos = new String[2];

        Statement statement = null;

        Conexion objetoConexion = new Conexion();

        try {
            statement = objetoConexion.conectar().createStatement();


            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                datos[0] = resultSet.getString(1);
                datos[1] = resultSet.getString(2);
                //datos[2] = resultSet.getString(3);
                modelo.addRow(datos);
            }

            // Codigo usado para ocultar la columna "id"
            /*
            int columnIndexToHide = 0;
            tablaCatalogo.getColumnModel().getColumn(columnIndexToHide).setMinWidth(0);
            tablaCatalogo.getColumnModel().getColumn(columnIndexToHide).setMaxWidth(0);
            tablaCatalogo.getColumnModel().getColumn(columnIndexToHide).setWidth(0);*/

            tablaCatalogo.setModel(modelo);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.toString());

        }
    }
    
    
    public void nuevaCuenta(JTextField paramCodigo, JTextField paramNombre) {
        if (paramCodigo.getText().isEmpty() || paramNombre.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "No se puede agregar una nueva cuenta si los campos están vacíos",
                    "Error: Campos vacíos",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int codigo = Integer.parseInt(paramCodigo.getText());
        String nombre = paramNombre.getText();
        
        Conexion objetoConexion = new Conexion();
        
        String consulta = "insert into catalogocuenta (codigo, nombrecuenta) values (?, ?);";
        
        try {
            CallableStatement cs = objetoConexion.conectar().prepareCall(consulta);
            cs.setInt(1, codigo);
            cs.setString(2, nombre);
            
            cs.execute();
            JOptionPane.showMessageDialog(null, "Cuenta agregada");
        } catch (Exception e) {
            if (e.getMessage().contains("llave duplicada viola restricción de unicidad")) {
                JOptionPane.showMessageDialog(null,
                        "No se puede agregar una nueva cuenta con un código que ya se encuentra en uso.\n"
                        + "Sin embargo, puede modificar la cuenta existente asociada a este código.",
                        "Error: Código Duplicado",
                        JOptionPane.ERROR_MESSAGE);
            }
            else {
                JOptionPane.showMessageDialog(null, "Error: " + e.toString());
            }
        }
    }
    
    
    public void seleccionarRegistro(JTable paramTabla, JTextField paramCod,JTextField paramCodigo, JTextField paramNombre) {
        try {
            int fila = paramTabla.getSelectedRow();
            
            if(fila >= 0){
                
                paramCod.setText(paramTabla.getValueAt(fila, 0).toString());
                paramCodigo.setText(paramTabla.getValueAt(fila, 0).toString());
                paramNombre.setText(paramTabla.getValueAt(fila, 1).toString());
            }
            else {
                JOptionPane.showMessageDialog(null, "Ninguna fila ha sido seleccionada aun");
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.toString());
        }   
    }
    
    
    public void editarCuenta(JTextField paramCod, JTextField paramCodigo, JTextField paramNombre) {
        if (paramCodigo.getText().isEmpty() || paramNombre.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "No se puede actualizar una nueva cuenta si los campos están vacíos",
                    "Error: Campos vacíos",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int codigo = Integer.parseInt(paramCodigo.getText());
        int cod = Integer.parseInt(paramCod.getText());
        String nombre = paramNombre.getText();
        
        Conexion objetoConexion = new Conexion();
        
        String consulta = "UPDATE catalogocuenta SET codigo = ?, nombrecuenta = ? WHERE catalogocuenta.codigo = ?;";
        
        int respuesta = JOptionPane.showConfirmDialog(null, "¿Estás seguro de actualizar este registro?", "Actualizar registro", JOptionPane.YES_NO_OPTION);
        
        if (respuesta == JOptionPane.YES_OPTION) {
            try {
                CallableStatement cs = objetoConexion.conectar().prepareCall(consulta);
                cs.setInt(1, codigo);
                cs.setString(2, nombre);
                cs.setInt(3, cod);
                
                cs.execute();
                JOptionPane.showMessageDialog(null, "Cuenta actualizada");
                
                // Limpia del formulario el registro editado  para evitar problemas
                txtCuentaCodigo.setText("");
                txtCuentaNombre.setText("");
                
            } catch (Exception e) {
                if (e.getMessage().contains("llave duplicada viola restricción de unicidad")) {
                    JOptionPane.showMessageDialog(null,
                            "No puedes editar la cuenta actual porque el código que intentas usar ya está en uso por otro registro.\n"
                            + "Por favor, utiliza un código único para esta cuenta.",
                            "Error: Código Duplicado",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Error: " + e.toString());
                }
            }
        }
    }
    
    
    public void eliminarCuenta(JTextField paramCodigo, JTextField paramNombre) {
        int codigo = Integer.parseInt(paramCodigo.getText());
        String nombre = paramNombre.getText();

        Conexion objetoConexion = new Conexion();

        String consulta = "DELETE FROM catalogocuenta WHERE catalogocuenta.codigo = ?;";

        int respuesta = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que deseas eliminar este registro?", "Eliminar registro", JOptionPane.YES_NO_OPTION);

        if (respuesta == JOptionPane.YES_OPTION) {
            try {
                int codigoAEliminar = codigo;
                String nombreAEliminar = nombre;

                CallableStatement cs = objetoConexion.conectar().prepareCall(consulta);
                cs.setInt(1, codigo);

                cs.execute();

                // Mostrando el registro eliminado
                String mensaje = "La cuenta con código: " + codigoAEliminar + " y de nombre: " + nombreAEliminar + " ha sido eliminada.";
                JOptionPane.showMessageDialog(null, mensaje, "Cuenta eliminada", JOptionPane.INFORMATION_MESSAGE);

                // Limpia del formulario el registro eliminado  para evitar problemas
                txtCuentaCodigo.setText("");
                txtCuentaNombre.setText("");
                //txtID.setText("");

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.toString());
            }
        }
    }
    
    
    
    
    
    
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
            java.util.logging.Logger.getLogger(CatalogoCuentaP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CatalogoCuentaP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CatalogoCuentaP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CatalogoCuentaP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CatalogoCuentaP().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnInicioCatalogo;
    private javax.swing.JButton BtnInventarioCatalogo;
    private javax.swing.JButton BtnTransaCatalogo;
    private javax.swing.JButton btnCancelarNuevaCuenta;
    private javax.swing.JButton btnEliminarCuenta;
    private javax.swing.JButton btnGuardarCuenta;
    private javax.swing.JButton btnModificarCuenta;
    private javax.swing.JButton btnNuevaCuenta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblData;
    private javax.swing.JTable tableCatalogo;
    private javax.swing.JTextField txtCod;
    private javax.swing.JTextField txtCuentaCodigo;
    private javax.swing.JTextField txtCuentaNombre;
    // End of variables declaration//GEN-END:variables
}
