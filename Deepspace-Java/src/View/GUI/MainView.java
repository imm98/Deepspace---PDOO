/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.GUI;
import View.View;
import Controller.Controller;
import deepspace.SpaceStationToUI;
import deepspace.EnemyToUI;
import View.GUI.EnemyView;
import View.GUI.SpaceStationView;
import deepspace.GameState;
import deepspace.GameState;
import deepspace.CombatResult;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 *
 * @author inaki
 */


public class MainView extends javax.swing.JFrame implements View{ //La vista principal implementa la interfaz, tiene que implementar todos sus metodos
    static Controller controller;
    String nombreJuego= "Deepspace";
    public SpaceStationView spaceStation;
    private EnemyView enemy;
    
    
    
   
    /**
     * Creates new form MainView
     */
    public MainView() {
        initComponents();                           
       
        spaceStation=new SpaceStationView();            //Inicializo la vista de la estación espacial y del enemigo
        enemy=new EnemyView();
        
        SpaceStationPanel.add(spaceStation);
        EnemyStarShipPanel.add(enemy);
        
        setTitle(nombreJuego);                          //Le pongo de título el nombre del juego 
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);      //Hago que cuando se cierre no haga nada
        
        repaint();
        
        Combatir.setEnabled(true);
        SiguienteTurno.setEnabled(false);
        
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                controller.finish(0);
            }
        });
        
        
        
        
    }
    @Override
    public ArrayList<String> getNames(){
        NamesCapture namesCapture=new NamesCapture (this);
        ArrayList<String> names=namesCapture.getNames();
        return names;
    }
    
    @Override
    public void setController (Controller c){ //Se establece el controller
        controller=c;
        
    }
    
    @Override
    public void updateView(){
        setSpaceStation();
        setEnemy();
        if (!controller.isAFTERCOMBAT()){
            EnemyStarShipPanel.setVisible(false);
        }
        else 
            EnemyStarShipPanel.setVisible(true);
        Combatir.setEnabled(controller.sePuedeCombatir());
        SiguienteTurno.setEnabled(controller.sePuedePasarTurno());      
    }
    
    @Override
    public void showView(){
        setVisible(true);
    }
    
    public String getAppName(){
        return this.nombreJuego;
    }
    
    @Override
    public void MensajePerder(){
        JOptionPane.showMessageDialog (this, "Has PERDIDO el combate.\nCumple tu castigo", getAppName(), JOptionPane.INFORMATION_MESSAGE);
    }
    @Override
    public void MensajeEscapar(){
        JOptionPane.showMessageDialog (this, "Has escapado, \neres una gallina espacial.", getAppName(), JOptionPane.INFORMATION_MESSAGE);
    }
    
    @Override
    public void MensajeGanar(){
        JOptionPane.showMessageDialog (this, "Has ganado, \nRecibe el hangar", getAppName(), JOptionPane.INFORMATION_MESSAGE);
    }
    
    @Override
    public void MensajeSpaceCity(){
        JOptionPane.showMessageDialog (this, "Te has convertido\n en una SpaceCity.", getAppName(), JOptionPane.INFORMATION_MESSAGE);
    }
    @Override
    public boolean confirmExitMessage() {
        return (JOptionPane.showConfirmDialog(this, "¿Estás segur@ que deseas salir?", getAppName(), JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
    }
    
    public void setSpaceStation(){
        SpaceStationPanel.removeAll(); //Borra todo lo que haya en el panel
        spaceStation=new SpaceStationView();
        spaceStation.setController(controller);  
        spaceStation.setStation(controller.getModelToUI().getCurrentStation());
        SpaceStationPanel.add(spaceStation);
        
       
    }
    
    public void setEnemy(){
        EnemyStarShipPanel.removeAll();             //Aquí no se llama al controlador porque solo se muestra
        enemy=new EnemyView();
        enemy.setEnemy(controller.getModelToUI().getCurrentEnemy());
        
        EnemyStarShipPanel.add(enemy);
        
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        SpaceStationPanel = new javax.swing.JPanel();
        EnemyStarShipPanel = new javax.swing.JPanel();
        Combatir = new javax.swing.JButton();
        SiguienteTurno = new javax.swing.JButton();
        Salir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(200, 200));
        setPreferredSize(new java.awt.Dimension(1800, 1000));
        setResizable(false);

        SpaceStationPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        SpaceStationPanel.setMinimumSize(new java.awt.Dimension(1377, 813));
        SpaceStationPanel.setPreferredSize(new java.awt.Dimension(1377, 813));

        EnemyStarShipPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Combatir.setText("COMBATIR");
        Combatir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CombatirActionPerformed(evt);
            }
        });

        SiguienteTurno.setText("SIGUIENTE TURNO");
        SiguienteTurno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SiguienteTurnoActionPerformed(evt);
            }
        });

        Salir.setText("SALIR");
        Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(SpaceStationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 913, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(SiguienteTurno, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Salir, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Combatir, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addComponent(EnemyStarShipPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(SpaceStationPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(34, 34, 34))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(EnemyStarShipPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 617, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 104, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(SiguienteTurno, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
                                    .addComponent(Salir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(34, 34, 34))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(Combatir, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(21, 21, 21))))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CombatirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CombatirActionPerformed
        // TODO add your handling code here:
        controller.combatir();
        updateView();  //Para que se muestre como queda todo despúes del combate
        showView();
    }//GEN-LAST:event_CombatirActionPerformed

    private void SiguienteTurnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SiguienteTurnoActionPerformed
        // TODO add your handling code here:
        controller.nextTurn();
        updateView();   
        showView();
    }//GEN-LAST:event_SiguienteTurnoActionPerformed

    private void SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalirActionPerformed
        // TODO add your handling code here:
        controller.finish(0);
    }//GEN-LAST:event_SalirActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Combatir;
    private javax.swing.JPanel EnemyStarShipPanel;
    private javax.swing.JButton Salir;
    private javax.swing.JButton SiguienteTurno;
    private javax.swing.JPanel SpaceStationPanel;
    // End of variables declaration//GEN-END:variables
}