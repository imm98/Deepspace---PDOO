/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deepspace;

import java.util.ArrayList;

/**
 *
 * @author inaki
 */
public class SpaceCity extends SpaceStation{
    ArrayList<SpaceStation> collaborators;
    SpaceStation base;
    
    public SpaceCity(SpaceStation base, ArrayList<SpaceStation> rest){
        super(base.getName(),new SuppliesPackage(base.getAmmoPower(), base.getFuelUnits(), base.getShieldPower()) );
        this.base=base;
        setLoot(new Loot(0,0,0,0,base.getNMedals(), false, false));
        
        setPendingDamage(base.getPendingDamage());
        this.collaborators=new ArrayList(rest);
                Hangar h=new Hangar(1);
        receiveHangar(h);
        
        if (base.getWeapons()!=null){
            for (int i=0; i< base.getWeapons().size();i++){
                  super.receiveWeapon(base.getWeapons().get(i));
                  super.mountWeapon(base.getHangar().getWeapons().size()-1);
            }
        }
        if (base.getShieldBoosters()!=null){
            for (int i=0; i<base.getShieldBoosters().size(); i++){
                super.receiveShieldBooster(base.getShieldBoosters().get(i));
                super.mountShieldBooster(base.getHangar().getShieldBoosters().size()-1);
            }
        }
        super.discardHangar();
        super.receiveHangar(base.getHangar());
        
        
    }
    
    public ArrayList<SpaceStation> getCollaborators(){
        return this.collaborators;
    }
    
    @Override
    public float fire(){
        float firetotal=0;
        firetotal+=base.fire();
        for (int i=0; i < collaborators.size(); i++)
            firetotal+=collaborators.get(i).fire();
        return firetotal;       
    }
    
    @Override       //Redefinirlo: Mismos parametros, sobreescribirlos: distintos 
    public float protection(){
        float protectiontotal=0;
        protectiontotal+=base.protection();
        for (int i=0; i< collaborators.size(); i++)
            protectiontotal+=collaborators.get(i).protection();
        
        return protectiontotal;
    }
    
    @Override
    public Transformation setLoot(Loot loot){
        Transformation t=super.setLoot(new Loot(loot.getNSupplies(), loot.getNWeapons(), loot.getNShields(), loot.getNHangars(), loot.getNMedals(), false, false));
        return t;
    }
    
    @Override 
    public String toString(){
        String salida="SpaceCity={ Base= ";
        salida+=base;
        salida+=collaborators;
        return salida;
    }
           
    
    
}
