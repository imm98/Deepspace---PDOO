/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deepspace;

/**
 *
 * @author inaki
 */
public class Weapon implements CombatElement {
    private String name;
    private WeaponType type;
    private int uses;
    
    Weapon(String n, WeaponType t, int u){
        this.name=n;
        this.type=t;
        this.uses=u;
    }
    
    Weapon(Weapon w){
        this.name=w.name;
        this.type=w.type;
        this.uses=w.uses;
    }
    
    WeaponToUI getUIversion(){
        return new WeaponToUI(this);
    }
    
    public WeaponType getType(){
        return this.type;
    }
    
    @Override
    public int getUses(){
        return this.uses;
    }
    
    public float power(){
        return type.getPower();
    }
    
    @Override
    public float useIt (){
        if (this.uses > 0){
            uses --;
            return power();
        }
        else {
            float resultado=1;
            return resultado;
        }
    }

    @Override
    public String toString() {
        return "Weapon{" + "name=" + name + ", type=" + type + ", uses=" + uses + '}';
    }
    

}
