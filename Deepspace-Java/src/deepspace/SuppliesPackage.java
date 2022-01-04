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
public class SuppliesPackage {
    private float ammoPower;
    private float fuelUnits;
    private float shieldsPower;
    
    SuppliesPackage (float ap, float fu, float sp){
        this.ammoPower=ap;
        this.fuelUnits=fu;
        this.shieldsPower=sp;
    }
    
    SuppliesPackage(SuppliesPackage s){
        this.ammoPower=s.ammoPower;
        this.fuelUnits=s.fuelUnits;
        this.shieldsPower=s.shieldsPower;   
    }
    
    public float getAmmoPower(){
        return this.ammoPower;
    }
    
    public float getFuelUnits(){
        return this.fuelUnits;
    }
    
    public float getShieldsPower(){
        return this.shieldsPower;
    }
    
    public String ToString(){
        return "ammoPower: " + this.ammoPower +
               "fuelUnits: " + this.fuelUnits +
               "shieldsPower: " + this.shieldsPower;
    }
}
