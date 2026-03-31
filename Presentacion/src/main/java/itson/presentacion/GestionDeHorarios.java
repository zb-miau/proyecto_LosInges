/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package itson.presentacion;

import asignarHorario.IAsignarHorario;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.temporal.TemporalAdjusters;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Zaira
 */
public class GestionDeHorarios extends javax.swing.JFrame {
    Long idEmpleado;
    JButton btnDia;
    /**
     * Creates new form GestionDeHorarios
     */
    public GestionDeHorarios(Long id) {
        initComponents();
        this.idEmpleado = id;
        pnlCalendario.setMinimumSize(new Dimension(627, 421));
        configurarEtiquetas();
        rdbtnMensual.setSelected(true);
        rdbtnMensual.setEnabled(true);
        configurarCalendario();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                GestionDeHorariosMain main = new GestionDeHorariosMain();
                main.setVisible(true);
                dispose();
            }
        });
        
        txtMes.addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    convertirEtiquetaMes();
                }
            }
        });
        
        txtAnio.addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    convertirEtiquetaAnio();
                }
            }
        });
    }

    /**
     * Este método crea el calendario utilizando GridLayout para crear los días
     * según la preferencia establecida por el usuario.
     */
    public void configurarCalendario(){
        pnlCalendario.removeAll();
         
        if (rdbtnSemanal.isSelected()){
            pnlCalendario.setLayout(new GridLayout(1, 7, 5, 5)); 
            LocalDate lunes = LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
            llenarDias(lunes);

        } else if (rdbtnMensual.isSelected()){
            pnlCalendario.setLayout(new GridLayout(0, 7, 5, 5)); 
            llenarDias();
        }
        
        pnlCalendario.revalidate();
        pnlCalendario.repaint();
    }
    
    /**
     * Calcula que día de la semana cae el primer día del mes
     * @return Valor entero que representa el día de la semana del
     * primer día del mes
     */
    public int primerDiaMes(){
        int anio = Integer.parseInt(txtAnio.getText());
        Month mes = Month.valueOf(txtMes.getText().toUpperCase());
        
        LocalDate primerDia = LocalDate.of(anio, mes, 1);
        return primerDia.getDayOfWeek().getValue();
    }
    
    /**
     * Este método privado es auxiliar para configurar el horario mensual.
     * Calcula cuántas casillas debe crear según el año y mes de las etiquetas
     * y las llena de color en caso de existir un Turno.
     */
    private void llenarDias(){
        int primerDia = primerDiaMes();
        int totalDias = getAnio().atMonth(getMes()).lengthOfMonth();
        
        for (int i = 1; i < primerDia; i++) {
        pnlCalendario.add(new JLabel(""));
        }
        
        for (int i = 1; i <= totalDias; i++) {
            btnDia = new JButton(String.valueOf(i));
            btnDia.setPreferredSize(new Dimension(80, 60));

//            LocalDate fechaActual = LocalDate.of(getAnio().getValue(), getMes(), i);
//            Color colorTurno = AQUI VA EL NOMBRE DE LA DAO.obtenerColorTurno(fechaActual);
//            if (colorTurno != null) {
//                btnDia.setBackground(colorTurno);
//                btnDia.setForeground(Color.WHITE); 
//                btnDia.setToolTipText(AQUI VA EL NOMBRE DEL Turno); 
//            } else {
//                btnDia.setBackground(Color.WHITE);
//            }

//            btnDia.addActionListener(e -> {
//            int filaSeleccionada = tablaTurnosDisponibles.getSelectedRow();
//            if (filaSeleccionada != -1) {
//                String titulo = tablaTurnosDisponibles.getValueAt(filaSeleccionada, 0).toString();
//                AQUI VA EL NOMBRE DE LA DAO o BO.guardarTurno(fechaActual, titulo);
//                configurarCalendario();
//            }
//        });
            
            pnlCalendario.add(btnDia);
        }
    }
    
    /**
     * Este método privado es auxiliar para configurar el horario.
     * En este caso el número de días a llenar es personalizable. Utilizado
     * en este caso para caclular las casillas a llenar en una semana.
     * @param inicio dia de inicio del rango
     * @param fin dia final del rango
     */
    private void llenarDias(LocalDate lunes){
        
        for (int i = 0; i < 7; i++) {
         LocalDate diaActual = lunes.plusDays(i);

         String textoBoton = String.valueOf(diaActual.getDayOfMonth());
         
         JButton btnDia = new JButton(textoBoton);
         btnDia.setPreferredSize(new Dimension(80, 400));
//            LocalDate fechaActual = LocalDate.of(getAnio().getValue(), getMes(), i);
//            Color colorTurno = AQUI VA EL NOMBRE DE LA DAO.obtenerColorTurno(fechaActual);
//            if (colorTurno != null) {
//                btnDia.setBackground(colorTurno);
//                btnDia.setForeground(Color.WHITE); 
//                btnDia.setToolTipText(AQUI VA EL NOMBRE DEL Turno); 
//            } else {
//                btnDia.setBackground(Color.WHITE);
//            }

            //btnDia.addActionListener(e -> {
//            int filaSeleccionada = tablaTurnosDisponibles.getSelectedRow();
//            if (filaSeleccionada != -1) {
//                String titulo = tablaTurnosDisponibles.getValueAt(filaSeleccionada, 0).toString();
//                AQUI VA EL NOMBRE DE LA DAO o BO.guardarTurno(fechaActual, titulo);
//                configurarCalendario();
//            }
//        });
            
            pnlCalendario.add(btnDia);
        }
    }
    
    /**
     * Ajusta las etiquetas al iniciar el sistema
     */
    public void configurarEtiquetas(){
        txtAnio.setText(String.valueOf(LocalDate.now().getYear()));
        txtMes.setText(String.valueOf(LocalDate.now().getMonth()));
    }
    
    /**
     * Método que convierte lo escrito en el textField de Año al año
     * establecido por el usuario.
     */
    public void convertirEtiquetaAnio(){
        String anioTxt = txtAnio.getText();
        
        try {
            Year anio = Year.parse(anioTxt);
            txtAnio.setText(String.valueOf(anio));
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                    this, 
                    "Formato de año inválido. El año sólo acepta números.", 
                    "Error de Formato", 
                    JOptionPane.ERROR_MESSAGE);
        }
            
        configurarCalendario();
    }
    
    /**
     * Método que convierte lo escrito en el textField de Mes al mes
     * establecido por el usuario.
     */
    public void convertirEtiquetaMes(){
        String mesTxt = txtMes.getText().trim().toLowerCase();
        Month mes = null;
        
        switch (mesTxt){
            case "enero":
                mes = Month.JANUARY;
                break;
            case "febrero":
                mes = Month.FEBRUARY;
                break;
            case "marzo":
                mes = Month.MARCH;
                break;
            case "abril":
                 mes = Month.APRIL;
                break;
            case "mayo":
                 mes = Month.MAY;
                break;
            case "junio":
                 mes = Month.JUNE;
                break;
            case "julio":
                 mes = Month.JULY;
                break;
            case "agosto":
                 mes = Month.AUGUST;
                break;
            case "septiembre":
                 mes = Month.SEPTEMBER;
                break;
            case "octubre":
                 mes = Month.OCTOBER;
                break;
            case "noviembre":
                 mes = Month.NOVEMBER;
                break;
            case "diciembre":
                 mes = Month.DECEMBER;
                break;
            default:
                JOptionPane.showMessageDialog(
                    this, 
                    "Formato de mes inválido. No se reconoció el mes introducido.", 
                    "Error de Formato", 
                    JOptionPane.ERROR_MESSAGE);
        }
        
        txtMes.setText(mes.name());
        configurarCalendario();
    }
    
    /**
     * Obtiene el año que está presentado en la etiqueta de año
     * @return objeto tipo Year con el año de la etiqueta
     */
    public Year getAnio(){
        String anio = txtAnio.getText();
        return Year.parse(anio);
    }
    
    /**
     * Obtiene el mes que está presentado en la etiqueta de mes
     * @return objeto tipo Month con el mes de la etiqueta
     */
    public Month getMes(){
        String mes = txtMes.getText();
        return Month.valueOf(mes.toUpperCase());
    }
   
  
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlGestionHorario = new javax.swing.JPanel();
        jScrollPane = new javax.swing.JScrollPane();
        tablaTurnosDisponibles = new javax.swing.JTable();
        pnlCalendario = new javax.swing.JPanel();
        lblVista = new javax.swing.JLabel();
        rdbtnSemanal = new javax.swing.JRadioButton();
        rdbtnMensual = new javax.swing.JRadioButton();
        lblLunes = new javax.swing.JLabel();
        lblMartes = new javax.swing.JLabel();
        lblMiercoles = new javax.swing.JLabel();
        lblJueves = new javax.swing.JLabel();
        lblViernes = new javax.swing.JLabel();
        lblSabado = new javax.swing.JLabel();
        lblDomingo = new javax.swing.JLabel();
        btnMesSiguiente = new javax.swing.JButton();
        btnMesAnterior = new javax.swing.JButton();
        pnlTurno = new javax.swing.JPanel();
        txtMes = new javax.swing.JTextField();
        txtAnio = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Gestión De Horarios");
        setResizable(false);

        pnlGestionHorario.setBackground(new java.awt.Color(255, 255, 255));
        pnlGestionHorario.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablaTurnosDisponibles.setBackground(new java.awt.Color(39, 71, 125));
        tablaTurnosDisponibles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nombre", "Hora Inicio", "Hora Fin", "Días"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane.setViewportView(tablaTurnosDisponibles);

        pnlGestionHorario.add(jScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 288, 296, 305));

        pnlCalendario.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnlCalendarioLayout = new javax.swing.GroupLayout(pnlCalendario);
        pnlCalendario.setLayout(pnlCalendarioLayout);
        pnlCalendarioLayout.setHorizontalGroup(
            pnlCalendarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 627, Short.MAX_VALUE)
        );
        pnlCalendarioLayout.setVerticalGroup(
            pnlCalendarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 421, Short.MAX_VALUE)
        );

        pnlGestionHorario.add(pnlCalendario, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 172, -1, -1));

        lblVista.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblVista.setForeground(new java.awt.Color(39, 71, 125));
        lblVista.setText("Vista:");
        pnlGestionHorario.add(lblVista, new org.netbeans.lib.awtextra.AbsoluteConstraints(776, 31, 37, -1));

        rdbtnSemanal.setBackground(new java.awt.Color(255, 255, 255));
        rdbtnSemanal.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rdbtnSemanal.setForeground(new java.awt.Color(39, 71, 125));
        rdbtnSemanal.setText("Semanal");
        rdbtnSemanal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbtnSemanalActionPerformed(evt);
            }
        });
        pnlGestionHorario.add(rdbtnSemanal, new org.netbeans.lib.awtextra.AbsoluteConstraints(839, 14, 98, -1));

        rdbtnMensual.setBackground(new java.awt.Color(255, 255, 255));
        rdbtnMensual.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rdbtnMensual.setForeground(new java.awt.Color(39, 71, 125));
        rdbtnMensual.setText("Mensual");
        rdbtnMensual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbtnMensualActionPerformed(evt);
            }
        });
        pnlGestionHorario.add(rdbtnMensual, new org.netbeans.lib.awtextra.AbsoluteConstraints(839, 41, 98, -1));

        lblLunes.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblLunes.setForeground(new java.awt.Color(39, 71, 125));
        lblLunes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLunes.setText("Lunes");
        pnlGestionHorario.add(lblLunes, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 138, 79, -1));

        lblMartes.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblMartes.setForeground(new java.awt.Color(39, 71, 125));
        lblMartes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMartes.setText("Martes");
        pnlGestionHorario.add(lblMartes, new org.netbeans.lib.awtextra.AbsoluteConstraints(395, 138, 81, -1));

        lblMiercoles.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblMiercoles.setForeground(new java.awt.Color(39, 71, 125));
        lblMiercoles.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMiercoles.setText("Miércoles");
        pnlGestionHorario.add(lblMiercoles, new org.netbeans.lib.awtextra.AbsoluteConstraints(482, 138, 81, -1));

        lblJueves.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblJueves.setForeground(new java.awt.Color(39, 71, 125));
        lblJueves.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblJueves.setText("Jueves");
        pnlGestionHorario.add(lblJueves, new org.netbeans.lib.awtextra.AbsoluteConstraints(568, 138, 79, -1));

        lblViernes.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblViernes.setForeground(new java.awt.Color(39, 71, 125));
        lblViernes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblViernes.setText("Viernes");
        pnlGestionHorario.add(lblViernes, new org.netbeans.lib.awtextra.AbsoluteConstraints(653, 138, 80, -1));

        lblSabado.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblSabado.setForeground(new java.awt.Color(39, 71, 125));
        lblSabado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSabado.setText("Sábado");
        pnlGestionHorario.add(lblSabado, new org.netbeans.lib.awtextra.AbsoluteConstraints(739, 138, 80, -1));

        lblDomingo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblDomingo.setForeground(new java.awt.Color(39, 71, 125));
        lblDomingo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDomingo.setText("Domingo");
        pnlGestionHorario.add(lblDomingo, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 140, 90, -1));

        btnMesSiguiente.setBackground(new java.awt.Color(255, 166, 43));
        btnMesSiguiente.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnMesSiguiente.setForeground(new java.awt.Color(39, 71, 125));
        btnMesSiguiente.setText("Siguiente");
        btnMesSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesSiguienteActionPerformed(evt);
            }
        });
        pnlGestionHorario.add(btnMesSiguiente, new org.netbeans.lib.awtextra.AbsoluteConstraints(733, 97, -1, -1));

        btnMesAnterior.setBackground(new java.awt.Color(255, 166, 43));
        btnMesAnterior.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnMesAnterior.setForeground(new java.awt.Color(39, 71, 125));
        btnMesAnterior.setText("Anterior");
        btnMesAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMesAnteriorActionPerformed(evt);
            }
        });
        pnlGestionHorario.add(btnMesAnterior, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 97, -1, -1));

        pnlTurno.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnlTurnoLayout = new javax.swing.GroupLayout(pnlTurno);
        pnlTurno.setLayout(pnlTurnoLayout);
        pnlTurnoLayout.setHorizontalGroup(
            pnlTurnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 290, Short.MAX_VALUE)
        );
        pnlTurnoLayout.setVerticalGroup(
            pnlTurnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 180, Short.MAX_VALUE)
        );

        pnlGestionHorario.add(pnlTurno, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 290, 180));

        txtMes.setBackground(new java.awt.Color(255, 255, 255));
        txtMes.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtMes.setForeground(new java.awt.Color(39, 71, 125));
        txtMes.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMes.setBorder(null);
        pnlGestionHorario.add(txtMes, new org.netbeans.lib.awtextra.AbsoluteConstraints(563, 100, 130, -1));

        txtAnio.setBackground(new java.awt.Color(255, 255, 255));
        txtAnio.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtAnio.setForeground(new java.awt.Color(39, 71, 125));
        txtAnio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAnio.setBorder(null);
        pnlGestionHorario.add(txtAnio, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 50, 130, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlGestionHorario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlGestionHorario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Permite actualizar el calendario al mes siguiente al que se esta visualizando en
     * ese momento.
     * @param evt click en el botón siguiente
     */
    private void btnMesSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesSiguienteActionPerformed
        Month mesActual = getMes();
        Month mesSiguiente = mesActual.plus(1);
        txtMes.setText(mesSiguiente.name());
        if (mesSiguiente.equals(Month.JANUARY)){
            Year anioActual = getAnio();
            Year anioSiguiente = anioActual.plusYears(1);
            txtAnio.setText(String.valueOf(anioSiguiente));
        }
        configurarCalendario();
    }//GEN-LAST:event_btnMesSiguienteActionPerformed

    /**
     * Método que le da acción al radio button de la vista Semanal.
     * Al activarlo, desactiva la vista mensual y configura el calendario.
     * @param evt click al radio button semanal
     */
    private void rdbtnSemanalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbtnSemanalActionPerformed
        rdbtnSemanal.setSelected(true);
        rdbtnMensual.setSelected(false);
        configurarCalendario();
    }//GEN-LAST:event_rdbtnSemanalActionPerformed

    /**
     * Método que le da acción al radio button de la vista mensual.
     * Al activarlo, desactiva la vista semanal y configura el calendario.
     * @param evt click al radio button mensual
     */
    private void rdbtnMensualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbtnMensualActionPerformed
        rdbtnSemanal.setSelected(false);
        rdbtnMensual.setSelected(true);
        configurarCalendario();
    }//GEN-LAST:event_rdbtnMensualActionPerformed

    /**
     * Permite actualizar el calendario al mes anterior al que se esta visualizando en
     * ese momento.
     * @param evt click en el botón anterior
     */
    private void btnMesAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMesAnteriorActionPerformed
        Month mesActual = getMes();
        Month mesAnterior = mesActual.minus(1);
        txtMes.setText(mesAnterior.name());
        if (mesAnterior.equals(Month.DECEMBER)){
            Year anioActual = getAnio();
            Year anioAnterior = anioActual.minusYears(1);
            txtAnio.setText(String.valueOf(anioAnterior));
        }
        configurarCalendario();
    }//GEN-LAST:event_btnMesAnteriorActionPerformed
    
    


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMesAnterior;
    private javax.swing.JButton btnMesSiguiente;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JLabel lblDomingo;
    private javax.swing.JLabel lblJueves;
    private javax.swing.JLabel lblLunes;
    private javax.swing.JLabel lblMartes;
    private javax.swing.JLabel lblMiercoles;
    private javax.swing.JLabel lblSabado;
    private javax.swing.JLabel lblViernes;
    private javax.swing.JLabel lblVista;
    private javax.swing.JPanel pnlCalendario;
    private javax.swing.JPanel pnlGestionHorario;
    private javax.swing.JPanel pnlTurno;
    private javax.swing.JRadioButton rdbtnMensual;
    private javax.swing.JRadioButton rdbtnSemanal;
    private javax.swing.JTable tablaTurnosDisponibles;
    private javax.swing.JTextField txtAnio;
    private javax.swing.JTextField txtMes;
    // End of variables declaration//GEN-END:variables
}
