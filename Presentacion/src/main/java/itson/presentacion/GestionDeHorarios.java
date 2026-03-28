/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package itson.presentacion;

import java.awt.GridLayout;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import javax.swing.JButton;

/**
 *
 * @author Zaira
 */
public class GestionDeHorarios extends javax.swing.JFrame {

    /**
     * Creates new form GestionDeHorarios
     */
    public GestionDeHorarios() {
        initComponents();
        configurarCalendario();
    }

    /**
     * Este método crea el calendario utilizando GridLayout para crear los días
     * según la preferencia establecida por el usuario.
     */
    public void configurarCalendario(){
        pnlCalendario.removeAll();
         
        if (rdbtnSemanal.isSelected()){
            pnlCalendario.setLayout(new GridLayout(1, 7, 5, 5)); 
            llenarDias(7);
        } else if (rdbtnMensual.isSelected()){
            pnlCalendario.setLayout(new GridLayout(0, 7, 5, 5)); 
            if (lblMes.equals("FEBRERO")){
                if(Year.of(LocalDate.now().getYear()).isLeap()){
                    llenarDias(29);
                } else {
                    llenarDias(28);
                }
            }
            llenarDias(31);
        }
        
        pnlCalendario.revalidate();
        pnlCalendario.repaint();
    }
    
    /**
     * Este método privado es auxiliar para configurar el horario
     * ya que toma el número de días y crea un botón para cada uno para 
     * después añadirlo al panel del calendario.
     * @param dias número de días de la semana o el mes
     */
    private void llenarDias(int dias){
        for (int i = 1; i <= dias; i++) {
            JButton btnDia = new JButton(String.valueOf(i));
            pnlCalendario.add(btnDia);
        }
    }
    
    public void configurarEtiquetas(){
        lblAnio.setText(String.valueOf(LocalDate.now().getYear()));
        lblMes.setText(String.valueOf(LocalDate.now().getMonth()));
    }
    
    public Year getAnio(){
        String anio = lblAnio.getText();
        return Year.parse(anio);
    }
    
    public Month getMes(){
        String mes = lblMes.getText();
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
        lblMes = new javax.swing.JLabel();
        lblLunes = new javax.swing.JLabel();
        lblMartes = new javax.swing.JLabel();
        lblMiercoles = new javax.swing.JLabel();
        lblJueves = new javax.swing.JLabel();
        lblViernes = new javax.swing.JLabel();
        lblSabado = new javax.swing.JLabel();
        lblDomingo = new javax.swing.JLabel();
        btnSiguiente = new javax.swing.JButton();
        btnAnterior = new javax.swing.JButton();
        lblAnio = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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

        pnlCalendario.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnlCalendarioLayout = new javax.swing.GroupLayout(pnlCalendario);
        pnlCalendario.setLayout(pnlCalendarioLayout);
        pnlCalendarioLayout.setHorizontalGroup(
            pnlCalendarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlCalendarioLayout.setVerticalGroup(
            pnlCalendarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 421, Short.MAX_VALUE)
        );

        lblVista.setText("Vista:");

        rdbtnSemanal.setText("Semanal");
        rdbtnSemanal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbtnSemanalActionPerformed(evt);
            }
        });

        rdbtnMensual.setText("Mensual");
        rdbtnMensual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbtnMensualActionPerformed(evt);
            }
        });

        lblMes.setText("MES");

        lblLunes.setText("Lunes");

        lblMartes.setText("Martes");

        lblMiercoles.setText("Miércoles");

        lblJueves.setText("Jueves");

        lblViernes.setText("Viernes");

        lblSabado.setText("Sábado");

        lblDomingo.setText("Domingo");

        btnSiguiente.setText("Siguiente");
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteActionPerformed(evt);
            }
        });

        btnAnterior.setText("Anterior");

        lblAnio.setText("Año");

        javax.swing.GroupLayout pnlGestionHorarioLayout = new javax.swing.GroupLayout(pnlGestionHorario);
        pnlGestionHorario.setLayout(pnlGestionHorarioLayout);
        pnlGestionHorarioLayout.setHorizontalGroup(
            pnlGestionHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGestionHorarioLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlGestionHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlCalendario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlGestionHorarioLayout.createSequentialGroup()
                        .addGroup(pnlGestionHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlGestionHorarioLayout.createSequentialGroup()
                                .addComponent(lblLunes, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(lblMartes, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)
                                .addComponent(lblMiercoles))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlGestionHorarioLayout.createSequentialGroup()
                                .addComponent(btnAnterior)
                                .addGap(10, 10, 10)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                        .addGroup(pnlGestionHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblMes, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblJueves, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblAnio, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16)
                        .addGroup(pnlGestionHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(pnlGestionHorarioLayout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(lblViernes)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblSabado)
                                .addGap(39, 39, 39)
                                .addComponent(lblDomingo)
                                .addGap(8, 8, 8))
                            .addGroup(pnlGestionHorarioLayout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addGroup(pnlGestionHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnSiguiente)
                                    .addComponent(lblVista, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(26, 26, 26)
                                .addGroup(pnlGestionHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rdbtnSemanal, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(rdbtnMensual, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(21, 21, 21)))))
                .addContainerGap())
        );
        pnlGestionHorarioLayout.setVerticalGroup(
            pnlGestionHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlGestionHorarioLayout.createSequentialGroup()
                .addGroup(pnlGestionHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlGestionHorarioLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(pnlGestionHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlGestionHorarioLayout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(lblVista))
                            .addGroup(pnlGestionHorarioLayout.createSequentialGroup()
                                .addComponent(rdbtnSemanal)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rdbtnMensual)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                        .addComponent(lblAnio)
                        .addGap(18, 18, 18)
                        .addGroup(pnlGestionHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblMes)
                            .addComponent(btnSiguiente)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlGestionHorarioLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAnterior)))
                .addGap(18, 18, 18)
                .addGroup(pnlGestionHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblLunes, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblMartes, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblMiercoles, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblJueves, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblDomingo, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblViernes, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblSabado, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(pnlGestionHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlCalendario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

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

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSiguienteActionPerformed

    private void rdbtnSemanalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbtnSemanalActionPerformed
        rdbtnSemanal.setSelected(true);
        rdbtnMensual.setSelected(false);
        configurarCalendario();
    }//GEN-LAST:event_rdbtnSemanalActionPerformed

    private void rdbtnMensualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbtnMensualActionPerformed
        rdbtnSemanal.setSelected(false);
        rdbtnMensual.setSelected(true);
        configurarCalendario();
    }//GEN-LAST:event_rdbtnMensualActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnterior;
    private javax.swing.JButton btnSiguiente;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JLabel lblAnio;
    private javax.swing.JLabel lblDomingo;
    private javax.swing.JLabel lblJueves;
    private javax.swing.JLabel lblLunes;
    private javax.swing.JLabel lblMartes;
    private javax.swing.JLabel lblMes;
    private javax.swing.JLabel lblMiercoles;
    private javax.swing.JLabel lblSabado;
    private javax.swing.JLabel lblViernes;
    private javax.swing.JLabel lblVista;
    private javax.swing.JPanel pnlCalendario;
    private javax.swing.JPanel pnlGestionHorario;
    private javax.swing.JRadioButton rdbtnMensual;
    private javax.swing.JRadioButton rdbtnSemanal;
    private javax.swing.JTable tablaTurnosDisponibles;
    // End of variables declaration//GEN-END:variables
}
