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

public class SpaceStation implements SpaceFighter{
    private static int MAXFUEL=100;
    private static double SHIELDLOSSPERUNITSHOT=0.1;
    private float ammoPower;
    private float fuelUnits;
    private String name;
    private int nMedals;
    private float shieldPower;
    private ArrayList<Weapon> weapons=new ArrayList();
    private ArrayList<ShieldBooster> shieldBoosters=new ArrayList();
    private Damage pendingDamage;
    private Hangar hangar;
    
    SpaceStation(String n, SuppliesPackage supplies){
        this.ammoPower=supplies.getAmmoPower();
        this.fuelUnits=supplies.getFuelUnits();
        this.shieldPower=supplies.getShieldsPower();
        this.name=n;
    }
    
    private void assignFuelValue(float f){
        if (f<MAXFUEL && f >=0){
            this.fuelUnits=f;
        }
    }
    
    private void cleanPendingDamage(){
        if (pendingDamage.hasNoEffect()){
            pendingDamage=null;
        }
    }
    
    public void cleanUpMountedItems(){
        int i=0;
        while(i<weapons.size()){
            if(weapons.get(i).getUses()==0) 
                weapons.remove(i);
            
            else
                i++;
            
        }
        
        i=0;
        while(i<shieldBoosters.size()){
            if (shieldBoosters.get(i).getUses()==0)
                shieldBoosters.remove(i);
            else 
                i++;
        }
    }
    
    public void discardHangar(){
        hangar=null;
    }
    
    public void discardShieldBooster(int i){
        int size=shieldBoosters.size();
        if (i>=0 && i <size){
            ShieldBooster s=new ShieldBooster(shieldBoosters.remove(i));
            if (pendingDamage!=null){
                pendingDamage.discardShieldBooster();
                cleanPendingDamage();
            }
        }
    }
    
    public void discardShieldBoosterInHangar (int i){
        if (i>=0)
            if (hangar!=null)
                hangar.removeShieldBooster(i);
        
    }
    
    public void discardWeapon(int i){
        int size=weapons.size();
        
        if (i>=0 && i<size){
            Weapon w=new Weapon(weapons.remove(i));
            if (pendingDamage!=null){
                pendingDamage.discardWeapon(w);
                cleanPendingDamage();
            }
        }
    }
    
    public void discardWeaponInHangar(int i){
        if (i>=0)
            if (hangar!=null)
                hangar.removeWeapon(i);
        
    }
    
    @Override
    public float fire(){
        int size=weapons.size();
        float factor= 1.0f;
        for (int i=0; i< size;i++){
            Weapon w=weapons.get(i);
            factor*=w.useIt();
        }
        
        return ammoPower*factor;
        
    }

    public float getAmmoPower() {
        return ammoPower;
    }

    public float getFuelUnits() {
        return fuelUnits;
    }

    public String getName() {
        return name;
    }

    public int getNMedals() {
        return nMedals;
    }

    public float getShieldPower() {
        return shieldPower;
    }

    public ArrayList<Weapon> getWeapons() {
        return weapons;
    }

    public ArrayList<ShieldBooster> getShieldBoosters() {
        return shieldBoosters;
    }

    public Damage getPendingDamage() {
        return pendingDamage;
    }

    public Hangar getHangar() {
        return hangar;
    }
    
    public float getSpeed(){
        return this.fuelUnits/MAXFUEL;
    }
    
    public SpaceStationToUI getUIversion(){
        return new SpaceStationToUI(this);
    }
    
    public void mountShieldBooster(int i){
        if (i>=0){
        if (hangar!=null && hangar.getShieldBoosters().get(i)!=null)
                shieldBoosters.add(hangar.removeShieldBooster(i));
        }
    }
    
    public void mountWeapon(int i){
        if (i>=0){
            if (hangar!=null && this.hangar.getWeapons().get(i)!=null)               
                 weapons.add(hangar.removeWeapon(i));
            }
        }
    
    public void move(){
        if (this.fuelUnits > getSpeed()){
            fuelUnits-=getSpeed();
        }
        else fuelUnits=0;
    }
    
    
    public float protection(){
        int size=shieldBoosters.size();
        float factor=1;
        for (int i=0; i< size; i++){
            ShieldBooster s=shieldBoosters.get(i);
            factor*=s.useIt();
        }
        return shieldPower*factor;
    }
    
    public void receiveHangar(Hangar h){
        if (hangar==null) 
            hangar=h;
    }
    
    public boolean receiveShieldBooster(ShieldBooster s){
        if (hangar!=null)
            return hangar.addShieldBooster(s);
        else return false;
    }
    
    @Override
    public ShotResult receiveShot(float shot){
        float myProtection=protection();
        
        if (myProtection >=shot){
            shieldPower-=SHIELDLOSSPERUNITSHOT*shot;
            
            if (0>shieldPower)
                shieldPower=0;    
            
            return ShotResult.RESIST;
        }   
        else{
            shieldPower=0;
            return ShotResult.DONOTRESIST;
        }
                   
    }
    
    public void receiveSupplies(SuppliesPackage s){
        this.ammoPower+=s.getAmmoPower();
        this.shieldPower+=s.getShieldsPower();
        this.fuelUnits+=s.getFuelUnits();
        if (fuelUnits>MAXFUEL)
            fuelUnits=MAXFUEL;
    }
    
    public boolean receiveWeapon (Weapon w){
        if (hangar!=null)
            return hangar.addWeapon(w);
        else return false;
    }
    
    public Transformation setLoot(Loot loot){
        Transformation t=Transformation.NOTRANSFORM;
        CardDealer dealer=CardDealer.getInstance();
        int h=loot.getNHangars();
        if(h>0){
            Hangar hangar=new Hangar(dealer.nextHangar());
            receiveHangar(hangar);
        }
        
        int elements=loot.getNSupplies();
        
        for (int i=1; i<=elements ;i++){
            SuppliesPackage sup= new SuppliesPackage(dealer.nextSuppliesPackage());
            receiveSupplies(sup);
        }
        
        elements=loot.getNWeapons();
        
        for (int i=1; i<=elements; i++){
            Weapon weap=new Weapon(dealer.nextWeapon());
            receiveWeapon(weap);
        }
        
        elements=loot.getNShields();
        
        for (int i=1; i<=elements;i++){
            ShieldBooster sh=new ShieldBooster(dealer.nextShieldBooster());
            receiveShieldBooster(sh);
        }
        
        int medals=loot.getNMedals();
      
        nMedals+=medals;
        
        if (loot.spaceCity() && !loot.getEfficient())
            t=Transformation.SPACECITY;
        else if (loot.getEfficient() && !loot.spaceCity())
            t=Transformation.GETEFFICIENT;
        
        return t;
        
        
    
    }
    
    public void setPendingDamage(Damage d){  //Adjust en damage privado?
        if (d!=null)
            pendingDamage=d.adjust(weapons, shieldBoosters);
    }
    
    public boolean validState(){
        return (this.pendingDamage==null||this.pendingDamage.hasNoEffect());
    }

    @Override
    public String toString() {
        return "SpaceStation{" + "ammoPower=" + ammoPower + ", fuelUnits=" + fuelUnits + ", name=" + name + ", nMedals=" + nMedals + ", shieldPower=" + shieldPower + ", weapons=" + weapons + ", shieldBoosters=" + shieldBoosters + ", pendingDamage=" + pendingDamage + ", hangar=" + hangar + '}';
    }
    
    
}
