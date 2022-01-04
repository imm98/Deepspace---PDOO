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
public class NumericDamage extends Damage{
    
    private int nWeapons;
    
    NumericDamage(int w, int s){
        super(s);
        nWeapons=w;
    }
    
    @Override
    NumericDamage copy(){
        NumericDamage np=new NumericDamage(getNWeapons(), getNShields());
        return np;
    }
    
    @Override
    NumericDamageToUI getUIversion(){
        return new NumericDamageToUI(this);
    }
    
    @Override
    NumericDamage adjust (ArrayList<Weapon>  w, ArrayList<ShieldBooster> s){
        
        int sb=getNShields();
        
        
        
        
        if(s.size()<sb)
            sb=s.size();
        
        int nw=getNWeapons();
        if (w.size()<nw)             
            nw=w.size();
            
            
        if (nw!=0 ||sb!=0){
            NumericDamage da;
            da=new NumericDamage(nw,sb);
            return da;
        }
        else{
            NumericDamage da=null;
            return da;
        }
        
    
    }
    
    @Override
    public void discardWeapon(Weapon w){
        if(this.nWeapons>0) this.nWeapons --;
    }
    
    @Override
    public boolean hasNoEffect(){
        return nWeapons==0 && getNShields()==0 ;
    }
    
    public int getNWeapons(){
        return this.nWeapons;
    }

    @Override
    public String toString() {
        return "NumericDamage{" + "nWeapons=" + nWeapons + "nShields=" + getNShields()+'}';
    }
    
    
    
}
