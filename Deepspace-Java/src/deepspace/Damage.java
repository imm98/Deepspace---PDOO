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
public abstract class Damage {
    private int nShields;
    
    
    
    Damage(int s){
        this.nShields=s;
    }
    
    
    Damage(Damage d){
        this.nShields=d.nShields;
    }
    
    abstract Damage copy();

    abstract DamageToUI getUIversion();
    
    abstract Damage adjust(ArrayList<Weapon>  w, ArrayList<ShieldBooster> s);
            
            
    abstract public void discardWeapon(Weapon w);
            
    public void discardShieldBooster(){
        if (nShields>0) nShields--;
    }
            
    abstract public boolean hasNoEffect();
            
    public int getNShields(){
        return this.nShields;
    }

    @Override
    public String toString() {
        return "Damage{" + "nShields=" + nShields + '}';
    }
    
    
    
    
    
   

    
    
    
            
}
