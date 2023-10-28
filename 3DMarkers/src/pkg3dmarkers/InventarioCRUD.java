package pkg3dmarkers;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import clases.Producto;
import java.sql.PreparedStatement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.CallableStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Iván Alexander Carranza Sánchez
 */
public class InventarioCRUD extends javax.swing.JFrame {

    Conexion conexion = new Conexion();
    
    
    
    /**
     * Creates new form InventarioCRUD
     */
    public InventarioCRUD() {
        initComponents();
        centrarVentanaEnPantalla();
        
        //Color de Fuente a Inventario.
        txtCosto.setForeground(new java.awt.Color(187,187,187));
        txtCantidad.setForeground(new java.awt.Color(187,187,187));
        cbxMovimiento.setForeground(new java.awt.Color(187,187,187));
        btnCancelar.setForeground(new java.awt.Color(0,0,0));
        
        //Cabezera de Inventario
        tbInventario.getTableHeader().setReorderingAllowed(false);
        tbInventario.getTableHeader().setFont(new Font("Dubai", Font.BOLD, 18));
        tbInventario.getTableHeader().setBackground(new java.awt.Color(255,255,5));
        
        //Arreglo de etiquetas de tabla
        lblEntrada.setHorizontalAlignment(SwingConstants.CENTER);
        lblEntrada.setBackground(new java.awt.Color(255,255,5));
        lblSalida.setHorizontalAlignment(SwingConstants.CENTER);
        lblSalida.setBackground(new java.awt.Color(0,0,0));
        lblSalida.setForeground(new java.awt.Color(255,255,255));
        lblMonto.setHorizontalAlignment(SwingConstants.CENTER);
        lblMonto.setBackground(new java.awt.Color(255,255,5));
        consultaProductosl();
        
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
    
    //Productos de BD
    private void consultaProductosl() {
        try {
            //Setencia SQL
            String sentenciaSql = "SELECT * FROM producto";
            Statement statement = conexion.conectar().createStatement();
            //Resultado de Tablas
            ResultSet resultado = statement.executeQuery(sentenciaSql);
            
            //Iterador de productos
            while (resultado.next()) {
                Producto productos = new Producto();
                productos.nombre = resultado.getString("nombreproduto");
                cbxProducto.addItem(productos.nombre);
            }
            
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al recuperar los productos de la base");
        }
    }
    
    //VALIDAR ACTUAL ID
    private int consultaIDProducto() 
    {
        String selectedItem = (String) cbxProducto.getSelectedItem();
         try {
               
               //CALCULO DE LA ID DEL PRODUCTO SELECCIONADO
                   //Setencia SQL
                   String sentenciaProducto = "SELECT * FROM producto";
                   Statement statement = conexion.conectar().createStatement();
                   //Resultado de Tablas
                   ResultSet resultado = statement.executeQuery(sentenciaProducto);
                   //Creamos objeto producto
                   Producto productos = new Producto();
                   
                   //Iterador de productos
                   while (resultado.next()) {
                       if(selectedItem.equals(resultado.getString("nombreproduto"))){
                           productos.id = resultado.getInt("idproducto");
                       }
                       
                   }
                  return productos.id;
            }
           catch (SQLException ex) {
               JOptionPane.showMessageDialog(this, "Error al recuperar id del producto: "+selectedItem);
           }
        return 0;
    }
    
   //Metodo CPP
    private void llenadoTabla()
    {
        //Obtenemos ID Producto
       int id = consultaIDProducto();
       
       //MODELO DE TABLA
      DefaultTableModel modelo = new DefaultTableModel()
      {
          @Override
                public boolean isCellEditable(int row, int column) {
                             return false;
            }
                
      };
      modelo.setRowCount(0); //Limpiar tabla
      tbInventario.setModel(modelo);
      
      
      //Creamos encabezados de tabla
       modelo.addColumn("Fecha");
       modelo.addColumn("Unit");
       modelo.addColumn("C/U");
        modelo.addColumn("Total");
        modelo.addColumn("Unit");
        modelo.addColumn("C/U");
        modelo.addColumn("Total");
        modelo.addColumn("Unit");
        modelo.addColumn("C/U");
        modelo.addColumn("Total");
        String[] datos = new String[10];
        
        try {
             PreparedStatement statement = null;
            //Setencia SQL
            String sentenciaSql = " SELECT nombreproduto, fechamovimiento, cantidadmovimiento,totalmovimiento, clasificacion \n" +
                                                  "FROM producto JOIN movimiento ON movimiento.idproducto = producto.idproducto\n" +
                                                  "WHERE producto.idproducto = ? ORDER BY fechamovimiento ASC";
            statement = this.conexion.conectar().prepareStatement(sentenciaSql);
            statement.setInt(1, id);
            ResultSet resultado = statement.executeQuery();
            // Variables para CPP
            double total=0;
            long sumacompra=0; //Para compras
            long restaventa=0; //Para ventas
            double precioAnterior=0;//Precio anterior
            double recalculadorCosto=0; //Para aplicar CPP
            double totalAnterior=0;
            double entradasT =0, salidasT=0, ultimo=0;
            //Ejecutador de  CPP
            while (resultado.next()) {
                String clasificacion = resultado.getString("clasificacion");
                       if("Entrada".equals(clasificacion)){

                                //Total de venta
                                total = resultado.getInt("cantidadmovimiento")*resultado.getDouble("totalmovimiento");
                                sumacompra= sumacompra + resultado.getInt("cantidadmovimiento");
                                
                                 //Información de entrada
                                 datos[0] = resultado.getString("fechamovimiento");
                                 datos[1] =  resultado.getString("cantidadmovimiento");
                                 datos[2] = resultado.getString("totalmovimiento");
                                 datos[3] =Double.toString(total);
                                 entradasT=total+entradasT; //Para totales de entradas
                                 
                                 //Información de salida
                                 datos[4]="";
                                 datos[5]="";
                                 datos[6]="";
                                 
                                 //Información de saldo
                                if (modelo.getRowCount() == 0) {
                                    datos[7] = resultado.getString("cantidadmovimiento");
                                    datos[8]= resultado.getString("totalmovimiento");
                                    precioAnterior = resultado.getDouble("totalmovimiento");
                                    datos[9]=Double.toString(total);
                                    totalAnterior=total;
                                    ultimo=total;
                                } else {
                                    datos[7] = String.valueOf(sumacompra);
                                    recalculadorCosto= (total+totalAnterior)/sumacompra; //Promedio
                                    datos[8] = Double.toString(recalculadorCosto);
                                    precioAnterior = recalculadorCosto;
                                    total = sumacompra*recalculadorCosto;
                                    datos[9] = Double.toString(total);
                                    totalAnterior=total;
                                    ultimo=total;
                                }
                                 modelo.addRow(datos); 
                       }
                       else{
                           
                                //Información de entrada
                                datos[0]= resultado.getString("fechamovimiento");
                                datos[1] = "";
                                datos[2] = "";
                                datos[3] = "";
                                
                                //Información de salida
                                datos[4] = resultado.getString("cantidadmovimiento");
                                datos[5]= String.valueOf(precioAnterior);
                                total = precioAnterior*resultado.getInt("cantidadmovimiento");;
                                datos[6]=String.valueOf(total);
                                salidasT=total+salidasT; //Para totales de salidas
                                
                                //Información de saldo
                                restaventa=sumacompra-resultado.getInt("cantidadmovimiento"); //Restamos compras-ventas
                                sumacompra=restaventa; //Reasignación de total
                                datos[7] = String.valueOf(restaventa);
                                datos[8]= String.valueOf(precioAnterior);
                                total = restaventa*precioAnterior;
                                datos[9]= String.valueOf(total);
                                totalAnterior=total;
                                ultimo=total;
                                modelo.addRow(datos);
                       }
                      
           }
            
            
            tbInventario.setModel(modelo);

            //Totales finales
            lblEntradaT.setText("Total de Entradas: " + entradasT);
            lblSalidaT.setText("Total de Salida: " + salidasT);
            lblMontoT.setText("Total de Saldo: " + ultimo);
            
            //Conexion a base
            String sentenciatotal= "SELECT idinventario, idproducto FROM inventario;";
            statement = this.conexion.conectar().prepareStatement(sentenciatotal);
            ResultSet respuesta = statement.executeQuery();
            
            //Datos a insertar o actualizar
            int idProducto = consultaIDProducto();
            int idinventario = 0;
            boolean bandera = true;

            if(!respuesta.next()){
               bandera = false;  
            }
            else{
                while(respuesta.next())
                { 
                    idinventario = respuesta.getInt("idinventario");
                    if(idinventario != idProducto)
                    {
                        bandera = false;          
                    }
                }
            }
            
           
            if(bandera == true)
            {
                //Actualización de inventario      
                String actualizarInventario = "UPDATE inventario SET cantidadinvetario = ?, preciototal = ?, totalcompras = ?, costovendido = ?, inventariofinal = ? WHERE idinventario = ?;";
                CallableStatement cs = conexion.conectar().prepareCall(actualizarInventario);
                cs.setInt(1, Integer.valueOf(datos[7]));
                cs.setDouble(2, Double.valueOf(datos[8]));
                cs.setDouble(3, entradasT);
                cs.setDouble(4, salidasT);
                cs.setDouble(5, ultimo);
                cs.setInt(6, idProducto);
                cs.execute();
            }
            else
            {
                //Nuevo inventario debido a nuevo producto
                String nuevoInventario = " INSERT INTO inventario (idinventario, idproducto, cantidadinvetario, preciototal, totalcompras, costovendido, inventariofinal) VALUES (?,?,?,?,?,?,?)";
                PreparedStatement pr = conexion.conectar().prepareStatement(nuevoInventario);
                pr.setInt(1, idProducto);
                pr.setInt(2, idProducto);
                pr.setInt(3, Integer.valueOf(datos[7]));
                pr.setDouble(4, Double.valueOf(datos[8]));
                pr.setDouble(5, entradasT);
                pr.setDouble(6, salidasT);
                pr.setDouble(7, ultimo);
                pr.execute();

            }

        }
        catch (SQLException ex) {
            System.out.println(ex);//--------------------------
            JOptionPane.showMessageDialog(this, "Error al recuperar inventario del producto");
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

        btnInicio = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();
        Encabezada = new javax.swing.JLabel();
        cbxProducto = new javax.swing.JComboBox<>();
        lblProductos = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblCantidad = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        lblCostoUnitario = new javax.swing.JLabel();
        lblMovimiento = new javax.swing.JLabel();
        cbxMovimiento = new javax.swing.JComboBox<>();
        txtCantidad = new javax.swing.JTextField();
        txtCosto = new javax.swing.JTextField();
        jdcFecha = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbInventario = new javax.swing.JTable();
        lblMonto = new javax.swing.JLabel();
        lblSalida = new javax.swing.JLabel();
        lblEntrada = new javax.swing.JLabel();
        lblMontoT = new javax.swing.JLabel();
        lblEntradaT = new javax.swing.JLabel();
        lblSalidaT = new javax.swing.JLabel();
        btnNuevoMovimiento = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnIngresar = new javax.swing.JButton();
        lblCuerpo = new javax.swing.JLabel();
        lblMenu = new javax.swing.JLabel();
        lblValidador = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnInicio.setBackground(new java.awt.Color(153, 255, 153));
        btnInicio.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        btnInicio.setForeground(new java.awt.Color(0, 0, 0));
        btnInicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/icoInicio.png"))); // NOI18N
        btnInicio.setText("Inicio");
        btnInicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnInicioMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnInicioMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnInicioMouseExited(evt);
            }
        });
        getContentPane().add(btnInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 120, 40));

        btnCerrar.setBackground(new java.awt.Color(255, 102, 102));
        btnCerrar.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        btnCerrar.setForeground(new java.awt.Color(0, 0, 0));
        btnCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/icoAfueraCerrar.png"))); // NOI18N
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
        getContentPane().add(btnCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 10, 170, 40));

        Encabezada.setFont(new java.awt.Font("Dosis ExtraBold", 0, 24)); // NOI18N
        Encabezada.setForeground(new java.awt.Color(0, 0, 0));
        Encabezada.setText("Realizando Movimiento");
        getContentPane().add(Encabezada, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 120, -1, -1));

        cbxProducto.setBackground(new java.awt.Color(255, 255, 255));
        cbxProducto.setFont(new java.awt.Font("Dubai", 0, 18)); // NOI18N
        cbxProducto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione" }));
        cbxProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxProductoActionPerformed(evt);
            }
        });
        getContentPane().add(cbxProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 80, 200, 36));

        lblProductos.setFont(new java.awt.Font("Dubai", 0, 18)); // NOI18N
        lblProductos.setForeground(new java.awt.Color(0, 0, 0));
        lblProductos.setText("Seleccione Producto:");
        getContentPane().add(lblProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 80, 150, -1));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setEnabled(false);

        lblCantidad.setFont(new java.awt.Font("Dubai", 0, 18)); // NOI18N
        lblCantidad.setForeground(new java.awt.Color(0, 0, 0));
        lblCantidad.setText("Cantidad:");
        lblCantidad.setEnabled(false);

        lblFecha.setFont(new java.awt.Font("Dubai", 0, 18)); // NOI18N
        lblFecha.setForeground(new java.awt.Color(0, 0, 0));
        lblFecha.setText("Seleccion Fecha:");
        lblFecha.setEnabled(false);

        lblCostoUnitario.setFont(new java.awt.Font("Dubai", 0, 18)); // NOI18N
        lblCostoUnitario.setForeground(new java.awt.Color(0, 0, 0));
        lblCostoUnitario.setText("Costo Unitario:");
        lblCostoUnitario.setEnabled(false);

        lblMovimiento.setFont(new java.awt.Font("Dubai", 0, 18)); // NOI18N
        lblMovimiento.setForeground(new java.awt.Color(0, 0, 0));
        lblMovimiento.setText("Tipo de Movimiento:");
        lblMovimiento.setEnabled(false);

        cbxMovimiento.setBackground(new java.awt.Color(255, 255, 255));
        cbxMovimiento.setFont(new java.awt.Font("Dubai", 0, 18)); // NOI18N
        cbxMovimiento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione", "Entrada", "Salida" }));
        cbxMovimiento.setEnabled(false);
        cbxMovimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxMovimientoActionPerformed(evt);
            }
        });

        txtCantidad.setBackground(new java.awt.Color(255, 255, 255));
        txtCantidad.setFont(new java.awt.Font("Dubai", 0, 18)); // NOI18N
        txtCantidad.setText("Ingrese cantidad...");
        txtCantidad.setEnabled(false);
        txtCantidad.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCantidadFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCantidadFocusLost(evt);
            }
        });
        txtCantidad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtCantidadMouseClicked(evt);
            }
        });
        txtCantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantidadActionPerformed(evt);
            }
        });
        txtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadKeyTyped(evt);
            }
        });

        txtCosto.setBackground(new java.awt.Color(255, 255, 255));
        txtCosto.setFont(new java.awt.Font("Dubai", 0, 18)); // NOI18N
        txtCosto.setText("Ingrese costo unitario...");
        txtCosto.setEnabled(false);
        txtCosto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCostoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCostoFocusLost(evt);
            }
        });
        txtCosto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtCostoMouseClicked(evt);
            }
        });
        txtCosto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCostoKeyTyped(evt);
            }
        });

        jdcFecha.setBackground(new java.awt.Color(255, 255, 255));
        jdcFecha.setEnabled(false);
        jdcFecha.setFont(new java.awt.Font("Dubai", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMovimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCostoUnitario)
                    .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtCosto, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(txtCantidad)
                    .addComponent(jdcFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbxMovimiento, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxMovimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMovimiento))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jdcFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblCantidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblCostoUnitario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCosto, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(58, 58, 58))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 150, 390, 180));

        jScrollPane1.setForeground(new java.awt.Color(0, 0, 0));
        jScrollPane1.setFont(new java.awt.Font("Dubai", 1, 18)); // NOI18N

        tbInventario.setForeground(new java.awt.Color(0, 0, 0));
        tbInventario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Fecha", "Unit", "C/U", "Total", "Unit", "C/U", "Total", "Unit", "C/U", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbInventario.setToolTipText("Seleccion la linea a editar");
        tbInventario.setGridColor(new java.awt.Color(0, 0, 0));
        tbInventario.setSelectionBackground(new java.awt.Color(255, 255, 153));
        tbInventario.setSelectionForeground(new java.awt.Color(0, 0, 0));
        tbInventario.setShowGrid(true);
        tbInventario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbInventarioMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbInventario);
        if (tbInventario.getColumnModel().getColumnCount() > 0) {
            tbInventario.getColumnModel().getColumn(0).setResizable(false);
            tbInventario.getColumnModel().getColumn(1).setResizable(false);
            tbInventario.getColumnModel().getColumn(2).setResizable(false);
            tbInventario.getColumnModel().getColumn(3).setResizable(false);
            tbInventario.getColumnModel().getColumn(4).setResizable(false);
            tbInventario.getColumnModel().getColumn(5).setResizable(false);
            tbInventario.getColumnModel().getColumn(6).setResizable(false);
            tbInventario.getColumnModel().getColumn(7).setResizable(false);
            tbInventario.getColumnModel().getColumn(8).setResizable(false);
            tbInventario.getColumnModel().getColumn(9).setResizable(false);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 620, 210));

        lblMonto.setBackground(new java.awt.Color(255, 255, 51));
        lblMonto.setFont(new java.awt.Font("Dosis ExtraBold", 0, 24)); // NOI18N
        lblMonto.setForeground(new java.awt.Color(0, 0, 0));
        lblMonto.setText("Monto");
        lblMonto.setOpaque(true);
        getContentPane().add(lblMonto, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 130, 190, -1));

        lblSalida.setBackground(new java.awt.Color(0, 0, 0));
        lblSalida.setFont(new java.awt.Font("Dosis ExtraBold", 0, 24)); // NOI18N
        lblSalida.setForeground(new java.awt.Color(255, 255, 255));
        lblSalida.setText("Salida");
        lblSalida.setOpaque(true);
        getContentPane().add(lblSalida, new org.netbeans.lib.awtextra.AbsoluteConstraints(252, 131, 190, -1));

        lblEntrada.setBackground(new java.awt.Color(255, 255, 51));
        lblEntrada.setFont(new java.awt.Font("Dosis ExtraBold", 0, 24)); // NOI18N
        lblEntrada.setForeground(new java.awt.Color(0, 0, 0));
        lblEntrada.setText("Entradas");
        lblEntrada.setOpaque(true);
        getContentPane().add(lblEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(71, 131, 190, -1));

        lblMontoT.setFont(new java.awt.Font("Dubai", 0, 18)); // NOI18N
        lblMontoT.setForeground(new java.awt.Color(0, 0, 0));
        lblMontoT.setText("Total de Saldo:");
        getContentPane().add(lblMontoT, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 440, 270, 30));

        lblEntradaT.setFont(new java.awt.Font("Dubai", 0, 18)); // NOI18N
        lblEntradaT.setForeground(new java.awt.Color(0, 0, 0));
        lblEntradaT.setText("Total de Entradas:");
        getContentPane().add(lblEntradaT, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 380, 290, 30));

        lblSalidaT.setFont(new java.awt.Font("Dubai", 0, 18)); // NOI18N
        lblSalidaT.setForeground(new java.awt.Color(0, 0, 0));
        lblSalidaT.setText("Total de Salida:");
        getContentPane().add(lblSalidaT, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 410, 270, 30));

        btnNuevoMovimiento.setBackground(new java.awt.Color(255, 255, 51));
        btnNuevoMovimiento.setFont(new java.awt.Font("Dubai", 1, 18)); // NOI18N
        btnNuevoMovimiento.setForeground(new java.awt.Color(0, 0, 0));
        btnNuevoMovimiento.setText("Nuevo Movimiento");
        btnNuevoMovimiento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnNuevoMovimientoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnNuevoMovimientoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnNuevoMovimientoMouseExited(evt);
            }
        });
        getContentPane().add(btnNuevoMovimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 380, 190, 40));

        btnEliminar.setBackground(new java.awt.Color(0, 0, 0));
        btnEliminar.setFont(new java.awt.Font("Dubai", 1, 18)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setText("Eliminar Movimiento");
        btnEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEliminarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEliminarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEliminarMouseExited(evt);
            }
        });
        getContentPane().add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 430, 190, 40));

        btnCancelar.setBackground(new java.awt.Color(0, 0, 0));
        btnCancelar.setFont(new java.awt.Font("Dubai", 1, 18)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar.setText("Cancelar");
        btnCancelar.setEnabled(false);
        btnCancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCancelarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCancelarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCancelarMouseExited(evt);
            }
        });
        getContentPane().add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 340, 110, 40));

        btnIngresar.setBackground(new java.awt.Color(255, 255, 51));
        btnIngresar.setFont(new java.awt.Font("Dubai", 1, 18)); // NOI18N
        btnIngresar.setForeground(new java.awt.Color(0, 0, 0));
        btnIngresar.setText("Ingresar");
        btnIngresar.setEnabled(false);
        btnIngresar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnIngresarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnIngresarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnIngresarMouseExited(evt);
            }
        });
        getContentPane().add(btnIngresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 340, 110, 40));

        lblCuerpo.setBackground(new java.awt.Color(255, 255, 255));
        lblCuerpo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/FondoBlanco.jpg"))); // NOI18N
        getContentPane().add(lblCuerpo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 1050, 430));

        lblMenu.setBackground(new java.awt.Color(0, 0, 0));
        lblMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/FondoNegro.png"))); // NOI18N
        getContentPane().add(lblMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1050, 60));

        lblValidador.setText("0");
        getContentPane().add(lblValidador, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 80, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarMouseClicked
        // Crea una nueva instancia de NuevoVentana
        Login login = new Login();
        login.setVisible(true);
        // Cierra la ventana actual
        this.dispose();
        try {
            conexion.conectar().close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Ocurrio un error al cerrar la conexion a la base de datos");
        }
    }//GEN-LAST:event_btnCerrarMouseClicked

    private void btnCerrarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarMouseEntered
        btnCerrar.setBackground(new Color(255,255,255));
        btnCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/icoAdentroCerrar.png"))); 
    }//GEN-LAST:event_btnCerrarMouseEntered

    private void btnCerrarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarMouseExited
        // Regresa al color original fuera
        btnCerrar.setBackground(new Color(255,102,102));
        btnCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/icoAfueraCerrar.png"))); 
    }//GEN-LAST:event_btnCerrarMouseExited

    private void btnInicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInicioMouseClicked
         // Crea una nueva instancia de NuevoVentana
        Inicio inicio = new Inicio();
        inicio.setVisible(true);
        // Cierra la ventana actual
        this.dispose();
        try {
            conexion.conectar().close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Ocurrio un error al cerrar la conexion a la base de datos");
        }
    }//GEN-LAST:event_btnInicioMouseClicked

    private void btnInicioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInicioMouseEntered
        // Sobre el boton
        btnInicio.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_btnInicioMouseEntered

    private void btnInicioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInicioMouseExited
          // Regresa al color original fuera
        btnInicio.setBackground(new Color(153,255,153));
    }//GEN-LAST:event_btnInicioMouseExited

    private void txtCantidadFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCantidadFocusGained
        // Verificacion del textbox
        String texto = txtCantidad.getText().trim();
        if (texto.equals("Ingrese cantidad...")) {
            txtCantidad.setText("");
            txtCantidad.setForeground(new java.awt.Color(0,0,0));}
    }//GEN-LAST:event_txtCantidadFocusGained

    private void txtCantidadFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCantidadFocusLost
        // Modificación al quitar el enfoque
        String texto = txtCantidad.getText().trim();
        if (texto.isEmpty() || texto.equals(".")) {
            txtCantidad.setText("Ingrese cantidad...");
            txtCantidad.setForeground(new java.awt.Color(187,187,187));
        }
    }//GEN-LAST:event_txtCantidadFocusLost

    private void txtCantidadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCantidadMouseClicked
        // Verificacion del textbox
        if(txtCosto.isEnabled())
        {
            String texto = txtCantidad.getText().trim();
            if (texto.equals("Ingrese cantidad...")) {
                txtCantidad.setText("");
                txtCantidad.setForeground(new java.awt.Color(0,0,0));
            }
        }
    }//GEN-LAST:event_txtCantidadMouseClicked

    private void txtCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidadActionPerformed

    private void txtCostoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCostoFocusGained
        // Verificacion del textbox
        String texto = txtCosto.getText().trim();
        if (texto.equals("Ingrese costo unitario...")) {
            txtCosto.setText("");
            txtCosto.setForeground(new java.awt.Color(0,0,0));}
    }//GEN-LAST:event_txtCostoFocusGained

    private void txtCostoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCostoFocusLost
        // Modificación al quitar el enfoque
        String texto = txtCosto.getText().trim();
        if (texto.isEmpty() || texto.equals(".")) {
            txtCosto.setText("Ingrese costo unitario...");
            txtCosto.setForeground(new java.awt.Color(187,187,187));
        }
    }//GEN-LAST:event_txtCostoFocusLost

    private void txtCostoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCostoMouseClicked
        // Verificacion del textbox
        if(txtCosto.isEnabled())
        {
             String texto = txtCosto.getText().trim();
            if (texto.equals("Ingrese costo unitario...")) {
            txtCosto.setText("");
            txtCosto.setForeground(new java.awt.Color(0,0,0));
            }
        }
    }//GEN-LAST:event_txtCostoMouseClicked

    private void txtCantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyTyped
       int key = evt.getKeyChar();
       boolean numeros = key >= 48 && key <= 57  ; //Rango de caracteres  numericos    
       if (!numeros)
       {
            evt.consume();
       }
    }//GEN-LAST:event_txtCantidadKeyTyped

    private void txtCostoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCostoKeyTyped
         int key = evt.getKeyChar();
       boolean numeros = key >= 48 && key <= 57 || key == 46 ; //Rango de caracteres  numericos    
       if (!numeros)
       {
            evt.consume();
       }
    }//GEN-LAST:event_txtCostoKeyTyped

    private void cbxMovimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxMovimientoActionPerformed
        // Diferenciación de movimiento
        String selectedItem = (String) cbxMovimiento.getSelectedItem();
        if (selectedItem.equals("Seleccione"))
        {
            txtCosto.setVisible(true);
            lblCostoUnitario.setVisible(true);
        }
        else if (selectedItem.equals("Entrada"))
        {
            txtCosto.setVisible(true);
            lblCostoUnitario.setVisible(true);
        }
        else if (selectedItem.equals("Salida"))
        {
            txtCosto.setVisible(false);
            lblCostoUnitario.setVisible(false);
        }
    }//GEN-LAST:event_cbxMovimientoActionPerformed

    private void btnIngresarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnIngresarMouseClicked
      if (btnIngresar.isEnabled() && "0".equals(lblValidador.getText()) ){
        int id;
        id = consultaIDProducto();
        if( cbxMovimiento.getSelectedItem()!="Seleccione" && jdcFecha.getDate()!=null && !"Ingrese cantidad...".equals(txtCantidad.getText().trim()) )
        {
            //Obtenemos Valores del formulario
            String tipoMov = (String) cbxMovimiento.getSelectedItem();
            int cantidadcompra= Integer.parseInt( txtCantidad.getText().trim());
            Date fecha = jdcFecha.getDate();
            java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
            //Validar dato de costo unitario por compra
            if("Entrada".equals(tipoMov))
            {
                 if(!"Ingrese costo unitario...".equals(txtCantidad.getText().trim()))
                 {
                     double montocompra = Double.parseDouble( txtCosto.getText().trim());
                     try {
                        //Setencia SQL
                        String sentenciaSql = " INSERT INTO movimiento (idproducto, cantidadmovimiento, totalmovimiento, fechamovimiento, clasificacion) VALUES (?,?,?,?,?)";
                        PreparedStatement preparedStatement = conexion.conectar().prepareStatement(sentenciaSql);
                        preparedStatement.setInt(1, id);
                        preparedStatement.setInt(2, cantidadcompra);
                        preparedStatement.setDouble(3, montocompra);
                        preparedStatement.setDate(4, fechaSQL);
                        preparedStatement.setString(5, "Entrada");
                        preparedStatement.execute();
                     } 
                    catch (SQLException e) {
                        JOptionPane.showMessageDialog(this, "Error al crear la nueva compra");
                     }
                 }
                 else {JOptionPane.showMessageDialog(this, "Ingrese los datos pertinentes.", "Información no ingresada.", JOptionPane.INFORMATION_MESSAGE);}
                 
            }
            else if("Salida".equals(tipoMov))
            {
                try {
                        //Setencia SQL
                         String sentenciaSql = " INSERT INTO movimiento (idproducto, cantidadmovimiento, fechamovimiento, clasificacion) VALUES (?,?,?,?)";
                        PreparedStatement preparedStatement = conexion.conectar().prepareStatement(sentenciaSql);
                        preparedStatement.setInt(1, id);
                        preparedStatement.setInt(2, cantidadcompra);
                        preparedStatement.setDate(3, fechaSQL);
                        preparedStatement.setString(4, "Salida");
                        preparedStatement.execute();
                     } 
                    catch (SQLException e) {
                        JOptionPane.showMessageDialog(this, "Error al crear la nueva venta");
                     }
            }
            
            // Restablecimiento de valores por defecto
            txtCosto.setText("Ingrese costo unitario...");
            txtCantidad.setText("Ingrese cantidad...");
            cbxMovimiento.setSelectedItem("Seleccione");
            jdcFecha.setDate(null);
            txtCosto.setVisible(true);
            lblCostoUnitario.setVisible(true);
           lblMovimiento.setEnabled(false);
           lblFecha.setEnabled(false);
           lblCantidad.setEnabled(false);
           lblCostoUnitario.setEnabled(false);
           txtCosto.setEnabled(false);
           jdcFecha.setEnabled(false);
           cbxMovimiento.setEnabled(false);
           txtCantidad.setEnabled(false);
           btnNuevoMovimiento.setEnabled(true);
           btnIngresar.setEnabled(false);
           btnCancelar.setEnabled(false);
           btnCancelar.setForeground(new java.awt.Color(0,0,0));
        }
          else {JOptionPane.showMessageDialog(this, "Ingrese los datos pertinentes.", "Información no ingresada.", JOptionPane.INFORMATION_MESSAGE);}
    }
      //Para actualizar 
       else
       {
           //Obtenemos ID
           int id;
            id = consultaIDProducto();
            if( cbxMovimiento.getSelectedItem()!="Seleccione" && jdcFecha.getDate()!=null && !"Ingrese cantidad...".equals(txtCantidad.getText().trim()) )
            {
                
                //Obtenemos Valores del formulario
                String tipoMov = (String) cbxMovimiento.getSelectedItem();
                int cantidadcompra= Integer.parseInt( txtCantidad.getText().trim());
                Date fecha = jdcFecha.getDate();
                java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
                int idActualizable = Integer.parseInt(lblValidador.getText());
                int respuesta = JOptionPane.showConfirmDialog(null, "¿Estás seguro de actualizar este registro?", "Actualizar registro", JOptionPane.YES_NO_OPTION);
                //Actualizar datos
                if("Entrada".equals(tipoMov))
                { 
                    double montocompra = Double.parseDouble( txtCosto.getText().trim());
                     try {
                        //Setencia SQL
                        String sentenciaSql = "UPDATE movimiento SET cantidadmovimiento = ?, totalmovimiento = ?, fechamovimiento = ? WHERE idmovimiento = ?;";
                        
                         if (respuesta == JOptionPane.YES_OPTION) 
                         {
                                Conexion conexion = new Conexion();
                                CallableStatement cs = conexion.conectar().prepareCall(sentenciaSql);
                                cs.setInt(1, cantidadcompra);
                                cs.setDouble(2,montocompra);
                                cs.setDate(3,fechaSQL);
                                cs.setInt(4, idActualizable);
                                cs.execute();
                         }
                     } 
                    catch (SQLException e) {
                        JOptionPane.showMessageDialog(this, "Error al actualizar la compra");
                     }
                }
                 else if("Salida".equals(tipoMov))
                {
                    try {
                            //Setencia SQL
                        String sentenciaSql = "UPDATE movimiento SET cantidadmovimiento = ?, fechamovimiento = ? WHERE idmovimiento = ?;";
                        
                         if (respuesta == JOptionPane.YES_OPTION) 
                         {
                                Conexion conexion = new Conexion();
                                CallableStatement cs = conexion.conectar().prepareCall(sentenciaSql);
                                cs.setInt(1, cantidadcompra);
                                cs.setDate(2,fechaSQL);
                                cs.setInt(3, idActualizable);
                                cs.execute();
                         }
                    } 
                   catch (SQLException e) {
                       JOptionPane.showMessageDialog(this, "Error al actualizar la venta");
                    }
                }

                // Restablecimiento de valores por defecto
                txtCosto.setText("Ingrese costo unitario...");
                txtCantidad.setText("Ingrese cantidad...");
                cbxMovimiento.setSelectedItem("Seleccione");
                jdcFecha.setDate(null);
                txtCosto.setVisible(true);
                lblCostoUnitario.setVisible(true);
               lblMovimiento.setEnabled(false);
               lblFecha.setEnabled(false);
               lblCantidad.setEnabled(false);
               lblCostoUnitario.setEnabled(false);
               txtCosto.setEnabled(false);
               jdcFecha.setEnabled(false);
               cbxMovimiento.setEnabled(false);
               txtCantidad.setEnabled(false);
               btnNuevoMovimiento.setEnabled(true);
               btnIngresar.setEnabled(false);
               btnCancelar.setEnabled(false);
               btnCancelar.setForeground(new java.awt.Color(0,0,0));
               System.out.printf("Estado: "+ lblValidador.getText()); //--------------------------------------------
               lblValidador.setText("0");
            }
             else {JOptionPane.showMessageDialog(this, "Ingrese los datos pertinentes.", "Información no ingresada.", JOptionPane.INFORMATION_MESSAGE);}
       }
       
     llenadoTabla();
    }//GEN-LAST:event_btnIngresarMouseClicked

    private void btnIngresarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnIngresarMouseEntered
       //Color cambiante
        btnIngresar.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_btnIngresarMouseEntered

    private void btnIngresarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnIngresarMouseExited
        // Color retorno
        btnIngresar.setBackground(new Color(255,255,51));
    }//GEN-LAST:event_btnIngresarMouseExited

    private void btnCancelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarMouseClicked
        // Limpiar 
        txtCosto.setText("Ingrese costo unitario...");
        txtCantidad.setText("Ingrese cantidad...");
        cbxMovimiento.setSelectedItem("Seleccione");
        jdcFecha.setDate(null);
        txtCosto.setVisible(true);
        lblCostoUnitario.setVisible(true);
       lblMovimiento.setEnabled(false);
       lblFecha.setEnabled(false);
       lblCantidad.setEnabled(false);
       lblCostoUnitario.setEnabled(false);
       txtCosto.setEnabled(false);
       jdcFecha.setEnabled(false);
       cbxMovimiento.setEnabled(false);
       txtCantidad.setEnabled(false);
       btnNuevoMovimiento.setEnabled(true);
       btnIngresar.setEnabled(false);
       btnCancelar.setEnabled(false);
       btnCancelar.setForeground(new java.awt.Color(0,0,0));
       lblValidador.setText("0");
    }//GEN-LAST:event_btnCancelarMouseClicked

    private void btnCancelarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarMouseEntered
        //Color cambiante
        if(btnCancelar.isEnabled())
        {
            btnCancelar.setBackground(new Color(255,255,255));
            btnCancelar.setForeground(new Color(0,0,0));
        }
    }//GEN-LAST:event_btnCancelarMouseEntered

    private void btnCancelarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarMouseExited
         // Color retorno
         if(btnCancelar.isEnabled())
        {
            btnCancelar.setBackground(new Color(0,0,0));
            btnCancelar.setForeground(new Color(255,255,255));
        }
    }//GEN-LAST:event_btnCancelarMouseExited

    private void btnNuevoMovimientoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNuevoMovimientoMouseClicked
       String valorSeleccionado = (String) cbxProducto.getSelectedItem();
        if(!"Seleccione".equals(valorSeleccionado))
       {
           lblMovimiento.setEnabled(true);
           lblFecha.setEnabled(true);
           lblCantidad.setEnabled(true);
           lblCostoUnitario.setEnabled(true);
           txtCosto.setEnabled(true);
           jdcFecha.setEnabled(true);
           cbxMovimiento.setEnabled(true);
           txtCantidad.setEnabled(true);
           btnIngresar.setEnabled(true);
           btnCancelar.setEnabled(true);
           btnCancelar.setForeground(new java.awt.Color(255,255,255));
           btnNuevoMovimiento.setEnabled(false);
       }
        else 
        {
            JOptionPane.showMessageDialog(null, "Debe de seleccionar el producto al que se le valuara su inventario", "SELECCIONE EL PRODUCTO", JOptionPane.INFORMATION_MESSAGE);
        }
        
    }//GEN-LAST:event_btnNuevoMovimientoMouseClicked

    private void btnNuevoMovimientoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNuevoMovimientoMouseEntered
        //Color cambiante
        btnNuevoMovimiento.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_btnNuevoMovimientoMouseEntered

    private void btnNuevoMovimientoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNuevoMovimientoMouseExited
         // Color retorno
        btnNuevoMovimiento.setBackground(new Color(255,255,51));
    }//GEN-LAST:event_btnNuevoMovimientoMouseExited

    private void cbxProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxProductoActionPerformed
        // Filtro de Productos
        String selectedItem = (String) cbxProducto.getSelectedItem();
        if (selectedItem.equals("Seleccione"))
        {
            
            //Limpiado de tabla
             tbInventario.setModel(new DefaultTableModel());
            //Restablecido de tabla
            tbInventario.setModel(new javax.swing.table.DefaultTableModel(
                    new Object [][] {{null, null, null, null, null, null, null, null, null, null}},
                    new String [] {"Fecha", "Unit", "C/U", "Total", "Unit", "C/U", "Total", "Unit", "C/U", "Total"})
            { boolean[] canEdit = new boolean [] {false, false, false, false, false, false, false, false, false, false};
                    public boolean isCellEditable(int rowIndex, int columnIndex) { return canEdit [columnIndex];}});
            //FALLO DE CAMBIO-INGRESO
            if( btnCancelar.isEnabled())
            {
                // Limpiar Ingrese costo unitario...
                txtCosto.setText("Ingrese costo unitario...");
                txtCantidad.setText("Ingrese cantidad...");
                cbxMovimiento.setSelectedItem("Seleccione");
                jdcFecha.setDate(null);
                txtCosto.setVisible(true);
                lblCostoUnitario.setVisible(true);
               lblMovimiento.setEnabled(false);
               lblFecha.setEnabled(false);
               lblCantidad.setEnabled(false);
               lblCostoUnitario.setEnabled(false);
               txtCosto.setEnabled(false);
               jdcFecha.setEnabled(false);
               cbxMovimiento.setEnabled(false);
               txtCantidad.setEnabled(false);
               btnNuevoMovimiento.setEnabled(true);
               btnIngresar.setEnabled(false);
               btnCancelar.setEnabled(false);
               btnCancelar.setForeground(new java.awt.Color(0,0,0));
            }
        }
        else
        {
                
           try {
               
               //CALCULO DE LA ID DEL PRODUCTO SELECCIONADO
                   //Setencia SQL
                   String sentenciaProducto = "SELECT * FROM producto";
                   Statement statement = conexion.conectar().createStatement();
                   //Resultado de Tablas
                   ResultSet resultado = statement.executeQuery(sentenciaProducto);
                   //Creamos objeto producto
                   Producto productos = new Producto();
                   
                   //Iterador de productos
                   while (resultado.next()) {
                       if(selectedItem.equals(resultado.getString("nombreproduto"))){
                           productos.id = resultado.getInt("idproducto");
                           
                       }
                   }
                   llenadoTabla();
            }
           catch (SQLException ex) {
               JOptionPane.showMessageDialog(this, "Error al recuperar inventario del producto: "+selectedItem);
           }
        }
    }//GEN-LAST:event_cbxProductoActionPerformed

    private void tbInventarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbInventarioMouseClicked
        if (cbxProducto.getSelectedItem()!="Seleccione")
        {    
            // Contador de clicks
            int clics = evt.getClickCount();
            int filaSeleccionada = tbInventario.getSelectedRow();
           
         if (clics == 3) 
         {   
             
           //  Habilitamos el sistema de edicion
           lblMovimiento.setEnabled(true);
           cbxMovimiento.setEnabled(true);
           lblFecha.setEnabled(true);
           jdcFecha.setEnabled(true);
           lblCantidad.setEnabled(true);
           txtCantidad.setEnabled(true);
           txtCantidad.setForeground(new java.awt.Color(0,0,0));
           lblCostoUnitario.setEnabled(true);
           txtCosto.setEnabled(true);
           txtCosto.setForeground(new java.awt.Color(0,0,0));
           btnIngresar.setEnabled(true);
           btnCancelar.setEnabled(true);
           btnCancelar.setForeground(new java.awt.Color(255,255,255));
           btnNuevoMovimiento.setEnabled(false);
           
           //Obtener datos de fila
            DefaultTableModel modelo = (DefaultTableModel) tbInventario.getModel();
            String[] datos = new String[10];
            int contador=0;
            while (contador<10 )
            { 
               datos [contador]= modelo.getValueAt(filaSeleccionada, contador).toString(); // Valores de la columna
               contador++;
            }
        
            //Asignacion de fila a edicion
             if ("".equals(datos [4]))
             {
                 //Es una entrada
                 try {
                     SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); //Formato fecha
                     Date fecha = formato.parse(datos [0]);
                     jdcFecha.setDate(fecha);
                     txtCantidad.setText(datos [1]);
                     txtCosto.setText(datos [2]);
                     cbxMovimiento.setSelectedItem("Entrada");
                 } catch (ParseException ex) {
                     JOptionPane.showMessageDialog(this, "No se pudo convertir la fecha");
                 }
             }
             else
             {
                 //Es una salida
                  try {
                     SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); //Formato fecha
                     Date fecha = formato.parse(datos [0]);
                     jdcFecha.setDate(fecha);
                     txtCantidad.setText(datos [4]);
                     cbxMovimiento.setSelectedItem("Salida");
                 } catch (ParseException ex) {
                     JOptionPane.showMessageDialog(this, "No se pudo convertir la fecha");
                 }
             }
             
               //Obtener id a modificar
                try 
                {
                    int idActualizable = 0;
                    int id =consultaIDProducto();
                    String idMovimiento = "SELECT idmovimiento, cantidadmovimiento, fechamovimiento, clasificacion FROM movimiento WHERE idproducto = " +id;
                    Conexion conect = new Conexion();
                    Statement statement = conect.conectar().createStatement();
                    //Resultado de Tablas
                   ResultSet resultado = statement.executeQuery(idMovimiento);
                   //Iterador de productos
                   while (resultado.next()) {
                       //Cantidades
                       int cantidadBase = resultado.getInt("cantidadmovimiento");
                       int cantidad= Integer.parseInt(txtCantidad.getText());
                       //Fechas
                       Date fechaBase = resultado.getDate("fechamovimiento");
                       Date fecha = jdcFecha.getDate();
                       java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
                       String fechaB = ""+fechaBase;
                      String fechaI = ""+fechaSQL;
                       //Movimiento
                       String movBase = resultado.getString("clasificacion");
                       String movimiento = ""+cbxMovimiento.getSelectedItem();
                       if( movBase.equals(movimiento) & fechaI.equals(fechaB) & cantidadBase == cantidad){
                               idActualizable = resultado.getInt("idmovimiento");
                       }

                   }
                  lblValidador.setText(Integer.toString(idActualizable));
                } 
                    catch (SQLException e) {
                        JOptionPane.showMessageDialog(this, "Error al buscar id");
                }
         }
        }
    }//GEN-LAST:event_tbInventarioMouseClicked

    private void btnEliminarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarMouseExited
        // Color retorno
        btnEliminar.setBackground(new Color(0,0,0));
        btnEliminar.setForeground(new Color(255,255,255));
    }//GEN-LAST:event_btnEliminarMouseExited

    private void btnEliminarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarMouseEntered
        //Color cambiante
        btnEliminar.setBackground(new Color(255,255,255));
        btnEliminar.setForeground(new Color(0,0,0));
    }//GEN-LAST:event_btnEliminarMouseEntered

    private void btnEliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarMouseClicked
         int filaSeleccionada = tbInventario.getSelectedRow();
         lblValidador.setText("0");
         
         //Datos del movimiento a borrar
         String eleccion="", fecha = "", unidad= "";
         Date fechaS=null;
         
         //Validación de tabla
         if (cbxProducto.getSelectedItem()!="Seleccione" && filaSeleccionada >=0 )
         {
            DefaultTableModel modelo = (DefaultTableModel) tbInventario.getModel();
            String[] datos = new String[10];
            int contador=0;
            while (contador<10 )
            { 
              datos [contador]= modelo.getValueAt(filaSeleccionada, contador).toString(); // Valores de la columna
              contador++;
            }
            //Proceso de eliminación
              try {
                    if ("".equals(datos [4]))
                    {
                         //Es una entrada
                       eleccion="Entrada";
                       fecha=datos [0];
                       unidad =datos [1];
                    }
                    else
                    {
                        //Es una salida
                       eleccion="Salida";
                       fecha=datos [0];
                       unidad =datos [4];
                    }
                    
                    //Conexión a base
                    Conexion conexion = new Conexion();
                    String sentenciaEliminado = "DELETE FROM movimiento\n" + 
                           "WHERE  clasificacion = ? AND cantidadmovimiento = ? AND fechamovimiento = ?;";
                  //Confirmación de borrado
                    int respuesta = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que deseas eliminar este registro?", "Eliminar registro", JOptionPane.YES_NO_OPTION);
                  //Validador de borrado
                    if (respuesta == JOptionPane.YES_OPTION) { 
                         CallableStatement cs = conexion.conectar().prepareCall(sentenciaEliminado);
                         cs.setString(1, eleccion);
                         cs.setInt(2,Integer.parseInt(unidad));
                         
                         //Formato Fecha
                         SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                        try {
                            fechaS= formato.parse(fecha);
                        } catch (ParseException ex) {
                           JOptionPane.showMessageDialog(this, "Error al convertir fecha");
                        }
                         java.sql.Date fechaSQL = new java.sql.Date(fechaS.getTime());
                         cs.setDate(3,fechaSQL);
                         cs.execute();
                          JOptionPane.showMessageDialog(null, "Se ha eliminado el movimiento hecho "+fecha, "Movimiento eliminado", JOptionPane.INFORMATION_MESSAGE);
                         llenadoTabla();
                          // Limpiar 
                            txtCosto.setText("Ingrese costo unitario...");
                            txtCantidad.setText("Ingrese cantidad...");
                            cbxMovimiento.setSelectedItem("Seleccione");
                            jdcFecha.setDate(null);
                            txtCosto.setVisible(true);
                            lblCostoUnitario.setVisible(true);
                           lblMovimiento.setEnabled(false);
                           lblFecha.setEnabled(false);
                           lblCantidad.setEnabled(false);
                           lblCostoUnitario.setEnabled(false);
                           txtCosto.setEnabled(false);
                           jdcFecha.setEnabled(false);
                           cbxMovimiento.setEnabled(false);
                           txtCantidad.setEnabled(false);
                           btnNuevoMovimiento.setEnabled(true);
                           btnIngresar.setEnabled(false);
                           btnCancelar.setEnabled(false);
                           btnCancelar.setForeground(new java.awt.Color(0,0,0));
                           lblValidador.setText("0");

                   }
            }
           catch (SQLException ex) {
               System.out.println(ex);
               JOptionPane.showMessageDialog(this, "Error al eliminar movimiento");
           }
         }
         
    }//GEN-LAST:event_btnEliminarMouseClicked

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
            java.util.logging.Logger.getLogger(InventarioCRUD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InventarioCRUD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InventarioCRUD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InventarioCRUD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InventarioCRUD().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Encabezada;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnIngresar;
    private javax.swing.JButton btnInicio;
    private javax.swing.JButton btnNuevoMovimiento;
    private javax.swing.JComboBox<String> cbxMovimiento;
    private javax.swing.JComboBox<String> cbxProducto;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.calendar.JDateChooser jdcFecha;
    private javax.swing.JLabel lblCantidad;
    private javax.swing.JLabel lblCostoUnitario;
    private javax.swing.JLabel lblCuerpo;
    private javax.swing.JLabel lblEntrada;
    private javax.swing.JLabel lblEntradaT;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblMenu;
    private javax.swing.JLabel lblMonto;
    private javax.swing.JLabel lblMontoT;
    private javax.swing.JLabel lblMovimiento;
    private javax.swing.JLabel lblProductos;
    private javax.swing.JLabel lblSalida;
    private javax.swing.JLabel lblSalidaT;
    private javax.swing.JLabel lblValidador;
    private javax.swing.JTable tbInventario;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtCosto;
    // End of variables declaration//GEN-END:variables
}
