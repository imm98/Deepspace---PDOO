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
public class Hangar {
    private int maxElements;
    private ArrayList<Weapon> weapons=new ArrayList();
    private ArrayList<ShieldBooster> shieldBoosters=new ArrayList();
    
    Hangar (int capacity){
        this.maxElements=capacity;
    }
    
    Hangar(Hangar h){
        this.maxElements=h.maxElements;
        this.weapons=h.weapons;
        this.shieldBoosters=h.shieldBoosters;
    }
    
    HangarToUI getUIversion(){
        return new HangarToUI(this);
    }
    
    private boolean spaceAvailable(){   //Duda
        return ((this.weapons.size() + this.shieldBoosters.size())<this.maxElements);
    }
        
    public boolean addWeapon(Weapon w){
        if (spaceAvailable()){
            return this.weapons.add(w);
        }
        else return false;
    }
            
    public boolean addShieldBooster(ShieldBooster s){
        if (spaceAvailable()){
            return this.shieldBoosters.add(s);
        }
        else return false;
    }
    
    public  int getMaxElements(){
        return this.maxElements;
    }
    public ArrayList<ShieldBooster> getShieldBoosters(){
        
        return this.shieldBoosters;
    }
            
    public ArrayList<Weapon> getWeapons(){
        return this.weapons;
    }
            
    public ShieldBooster removeShieldBooster (int s){
        return this.shieldBoosters.remove(s);
    }
            
    public Weapon removeWeapon(int w){
       
            return weapons.remove(w);
       
    }
    
    @Override
    public String toString() {
        return "Hangar{" + "maxElements=" + maxElements + ", weapons=" + weapons + ", shieldBoosters=" + shieldBoosters + '}';
    }
    
}
