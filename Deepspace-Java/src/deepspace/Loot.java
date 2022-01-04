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
public class Loot {
    private int nSupplies;
    private int nWeapons;
    private int nShields;
    private int nHangars;
    private int nMedals;
    private boolean getEfficient;
    private boolean spaceCity;
    
    Loot (int nsu, int nw, int nsh, int nh, int nm, boolean ef, boolean city){
        this.nSupplies=nsu;
        this.nWeapons=nw;
        this.nShields=nsh;
        this.nHangars=nh;
        this.nMedals=nm;
        this.getEfficient=ef;
        this.spaceCity=city;
        
    }
    
    Loot(int nsu, int nw, int nsh, int nh, int nm){
        this.nSupplies=nsu;
        this.nWeapons=nw;
        this.nShields=nsh;
        this.nHangars=nh;
        this.nMedals=nm;
    }
    
    LootToUI getUIversion(){
        return new LootToUI(this);
    }
    
    public boolean getEfficient(){
        return this.getEfficient;
    }
    
    public int getNSupplies(){
        return this.nSupplies;
    }
    
    public int getNWeapons(){
        return this.nWeapons;
    }
    
    public int getNShields(){
        return this.nShields;
    }
    
    public int getNHangars(){
        return this.nHangars;
    }
    
    public int getNMedals(){
        return this.nMedals;
    }
    
    public boolean spaceCity(){
        return this.spaceCity;
    }
    
    public String toString(){
        return "nSuplies: " + this.nSupplies +
               "nWeapons: " + this.nWeapons +
               "nShields: " + this.nShields +
               "nHangars: " + this.nHangars +
               "nMedals: " + this.nMedals +
               "getEfficient: "+ this.getEfficient +
                "spaceCity: "+ this.spaceCity;
    }
}
