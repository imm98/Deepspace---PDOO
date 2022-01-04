/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.GUI;
import Controller.Controller;
import deepspace.SpaceStationToUI;
import deepspace.ShieldToUI;
import deepspace.HangarToUI;
import deepspace.WeaponToUI;
import java.awt.Component;
import java.util.ArrayList;

/**
 *
 * @author inaki
 */
public class SpaceStationView extends javax.swing.JPanel {

    /**
     * Creates new form SpaceStationView
     */
    private Controller controller;
    private int nWeaponsHangar=0;
    private int nShieldsHangar=0;
    
    public SpaceStationView() {
        initComponents();
        repaint();
        revalidate();
    }
    
    public void setController (Controller c){
        controller=c;
    }
   
    
    public void setPotenciaAtaque(SpaceStationToUI station){       
        PotenciaAtaque.setText(Float.toString(station.getAmmoPower()));
       
    }
    
    public void setPotenciaDefensa(SpaceStationToUI station){
        PotenciaDefensa.setText(Float.toString(station.getShieldPower()));
    }
    
    public void setCombustible(SpaceStationToUI station){
        NiveldeCombustible.setText(Float.toString(station.getFuelUnits()));
    }
    
    public void setMedallas(SpaceStationToUI station){
        Medallas.setText(Integer.toString(station.getnMedals()));
    }
    
    public void setName(SpaceStationToUI station){
        Nombre.setText(station.getName());
    }
    
    
    void setStation(SpaceStationToUI station){
        PanelArmas.removeAll();
        PanelShieldBoosters.removeAll();
        PanelHangar.removeAll();
        nWeaponsHangar=0;
        nShieldsHangar=0;
        
        
        setPotenciaAtaque(station);
        
        setPotenciaDefensa(station);
        setCombustible(station);
        setMedallas(station);
        setName(station);
        
        ArrayList<WeaponToUI> armas=station.getWeapons();
        ArrayList<ShieldToUI> escudos=station.getShieldBoosters();
        WeaponView weaponview;
        
        for (int i=0; i< armas.size(); i++){
            weaponview=new WeaponView();
            weaponview.setArma(armas.get(i));
            PanelArmas.add(weaponview);
        }
        ShieldBoosterView shieldView;
        for (int i=0; i<escudos.size(); i++){
            shieldView=new ShieldBoosterView();
            shieldView.setEscudo(escudos.get(i));
            PanelShieldBoosters.add(shieldView);
        }
        
        if (station.getHangar()!=null){
    
            HangarToUI hangar=station.getHangar();
            for (int i=0; i<hangar.getShieldBoosters().size(); i++){
                shieldView=new ShieldBoosterView();
                shieldView.setEscudo(hangar.getShieldBoosters().get(i));
                
                PanelHangar.add(shieldView);
                nShieldsHangar++;
                
            }
            
            
            for (int i=0; i<hangar.getWeapons().size();i++){
                weaponview=new WeaponView();
                weaponview.setArma(hangar.getWeapons().get(i));
                PanelHangar.add(weaponview);
                nWeaponsHangar++;
                
            }
            
            

            
        }
        
        Weapons.removeAll();                    //Se borran las armas y los escudos del pendingDamage
        ShieldBoosters.removeAll();
        
        if (controller.getModelToUI().getCurrentStation().getPendingDamage()!=null){
            if (controller.getModelToUI().getCurrentStation().getPendingDamage().getWeaponInfo()!=null)
                Weapons.setText(controller.getModelToUI().getCurrentStation().getPendingDamage().getWeaponInfo());
            if (  controller.getModelToUI().getCurrentStation().getPendingDamage().getNShields()!=0  )
                ShieldBoosters.setText(Integer.toString(controller.getModelToUI().getCurrentStation().getPendingDamage().getNShields()));
        }
        
        
        

        repaint();
        revalidate();
        
        DescartarHangarCompleto.setEnabled(controller.sePuedeDescartarHangar());
        Desmontar.setEnabled(controller.sePuedeDesmontar());
        Montar.setEnabled(controller.sePuedeDescartarHangar());
        

        
    }

    
    ArrayList<Integer> getSelectedShieldsInHangar(){
       
        ArrayList<Integer> selectedShields=new ArrayList();
 
        for (int i=0; i<nShieldsHangar; i++){
            Component c=PanelHangar.getComponent(i);
            if (((ShieldBoosterView)c).isSelected()){
                selectedShields.add(i);
            }
        }
     
        return selectedShields;
    }
    
    ArrayList<Integer> getSelectedWeaponsInHangar(){
        
        ArrayList<Integer> selectedWeapons=new ArrayList();
        for (int i=nShieldsHangar; i<(nWeaponsHangar+nShieldsHangar); i++){
            Component c=PanelHangar.getComponent(i);
            if (((WeaponView)c).isSelected()){
                selectedWeapons.add(i-nShieldsHangar);
            }
        }
        return selectedWeapons;
    }
    
    ArrayList<Integer> getSelectedWeapons(){
        ArrayList<Integer> selectedWeapons=new ArrayList();
        int i=0;
        for (Component c : PanelArmas.getComponents()){
          
            if (((WeaponView)c).isSelected()){
                selectedWeapons.add(i);
            }
            i++;
        }
        return selectedWeapons;
    }
    
      ArrayList<Integer> getSelectedShields(){
        ArrayList<Integer> selectedShieldBoosters=new ArrayList();
        int i=0;
        for (Component c : PanelShieldBoosters.getComponents()){
          
            if (((ShieldBoosterView)c).isSelected()){
                selectedShieldBoosters.add(i);
            }
            i++;
        }
        return selectedShieldBoosters;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ScrollPanel1 = new javax.swing.JScrollPane();
        PanelArmas = new javax.swing.JPanel();
        ScrollPanel2 = new javax.swing.JScrollPane();
        PanelShieldBoosters = new javax.swing.JPanel();
        ScrollPanel3 = new javax.swing.JScrollPane();
        PanelHangar = new javax.swing.JPanel();
        Montar = new javax.swing.JButton();
        Desmontar = new javax.swing.JButton();
        DescartarHangarCompleto = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        PotenciaAtaque = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        PotenciaDefensa = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        Medallas = new javax.swing.JLabel();
        NiveldeCombustible = new javax.swing.JLabel();
        PanelPendingDamage = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        Weapons = new javax.swing.JLabel();
        ShieldBoosters = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        Nombre = new javax.swing.JLabel();

        PanelArmas.setBorder(javax.swing.BorderFactory.createTitledBorder("Armas Montadas"));
        PanelArmas.setPreferredSize(new java.awt.Dimension(20, 20));
        ScrollPanel1.setViewportView(PanelArmas);

        PanelShieldBoosters.setBorder(javax.swing.BorderFactory.createTitledBorder("ShieldBoosters"));
        PanelShieldBoosters.setPreferredSize(new java.awt.Dimension(20, 20));
        ScrollPanel2.setViewportView(PanelShieldBoosters);

        PanelHangar.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("Hangar")));
        PanelHangar.setPreferredSize(new java.awt.Dimension(20, 20));
        ScrollPanel3.setViewportView(PanelHangar);

        Montar.setText("Montar");
        Montar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MontarActionPerformed(evt);
            }
        });

        Desmontar.setText("Desmontar");
        Desmontar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DesmontarActionPerformed(evt);
            }
        });

        DescartarHangarCompleto.setText("Descartar Hangar Completo");
        DescartarHangarCompleto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DescartarHangarCompletoActionPerformed(evt);
            }
        });

        jLabel1.setText("Potencia de ataque: ");

        jLabel2.setText("Potencia de defensa: ");

        jLabel3.setText("Nivel de Combustible: ");

        jLabel5.setText("Medallas: ");

        PanelPendingDamage.setBorder(javax.swing.BorderFactory.createTitledBorder("PendingDamage"));

        jLabel4.setText("Weapons: ");

        jLabel6.setText("ShieldBoosters: ");

        javax.swing.GroupLayout PanelPendingDamageLayout = new javax.swing.GroupLayout(PanelPendingDamage);
        PanelPendingDamage.setLayout(PanelPendingDamageLayout);
        PanelPendingDamageLayout.setHorizontalGroup(
            PanelPendingDamageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelPendingDamageLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelPendingDamageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelPendingDamageLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(44, 44, 44)
                        .addComponent(Weapons))
                    .addGroup(PanelPendingDamageLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(ShieldBoosters)))
                .addContainerGap(130, Short.MAX_VALUE))
        );
        PanelPendingDamageLayout.setVerticalGroup(
            PanelPendingDamageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelPendingDamageLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelPendingDamageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(Weapons))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(PanelPendingDamageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(ShieldBoosters))
                .addContainerGap())
        );

        jLabel7.setText("Nombre: ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ScrollPanel3)
                    .addComponent(ScrollPanel1)
                    .addComponent(ScrollPanel2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Montar, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Desmontar, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(DescartarHangarCompleto, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(PotenciaDefensa, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel3)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(NiveldeCombustible, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel5)
                                                .addGap(72, 72, 72)
                                                .addComponent(Medallas, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel1))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(PotenciaAtaque, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(46, 46, 46)
                                                .addComponent(Nombre)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                                .addComponent(PanelPendingDamage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 91, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(Nombre))
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(PotenciaAtaque, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(NiveldeCombustible, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PotenciaDefensa, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Medallas, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(PanelPendingDamage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(ScrollPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64)
                .addComponent(ScrollPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(ScrollPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(96, 96, 96)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Montar, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Desmontar, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DescartarHangarCompleto, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void DescartarHangarCompletoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DescartarHangarCompletoActionPerformed
        controller.discardHangar();
        controller.showView();
    }//GEN-LAST:event_DescartarHangarCompletoActionPerformed

    private void DesmontarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DesmontarActionPerformed
        controller.desmontarArma(getSelectedWeapons());
        controller.desmontarEscudo(getSelectedShields());
        controller.desmontarArmaHangar(getSelectedWeaponsInHangar());
        controller.desmontarEscudoHangar(getSelectedShieldsInHangar());
        controller.showView();
        
    }//GEN-LAST:event_DesmontarActionPerformed

    private void MontarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MontarActionPerformed

        controller.montarArmas(getSelectedWeaponsInHangar());
        controller.montarEscudos(getSelectedShieldsInHangar());
         controller.showView();
    }//GEN-LAST:event_MontarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton DescartarHangarCompleto;
    private javax.swing.JButton Desmontar;
    private javax.swing.JLabel Medallas;
    private javax.swing.JButton Montar;
    private javax.swing.JLabel NiveldeCombustible;
    private javax.swing.JLabel Nombre;
    private javax.swing.JPanel PanelArmas;
    private javax.swing.JPanel PanelHangar;
    private javax.swing.JPanel PanelPendingDamage;
    private javax.swing.JPanel PanelShieldBoosters;
    private javax.swing.JLabel PotenciaAtaque;
    private javax.swing.JLabel PotenciaDefensa;
    private javax.swing.JScrollPane ScrollPanel1;
    private javax.swing.JScrollPane ScrollPanel2;
    private javax.swing.JScrollPane ScrollPanel3;
    private javax.swing.JLabel ShieldBoosters;
    private javax.swing.JLabel Weapons;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    // End of variables declaration//GEN-END:variables
}
