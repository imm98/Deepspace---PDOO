/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deepspace;

/**
 *
 * @author imm98
 */
public enum WeaponType {
    
    LASER (2), MISSILE (3), PLASMA (4);
    private float power;
    
    WeaponType(float p){
        this.power=p;
    }
    
   float getPower(){
        return this.power;
    }
   
   
}


