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
public class EnemyStarShip implements SpaceFighter {
    private float ammoPower;
    private String name;
    private float shieldPower;
    private Loot loot;
    private Damage damage;
    
    EnemyStarShip(String n, float a, float s, Loot l, Damage d){
        this.name=n;
        this.ammoPower=a;
        this.shieldPower=s;
        this.loot=l;
        this.damage=d;
    }
    
    EnemyStarShip(EnemyStarShip e){
        this.name=e.name;
        this.ammoPower=e.ammoPower;
        this.shieldPower=e.shieldPower;
        this.loot=e.loot;
        this.damage=e.damage;
    }
    
    EnemyToUI getUIversion(){
        return new EnemyToUI(this);
    }
    
    @Override
    public float fire(){
        return getAmmoPower();
    }

    public float getAmmoPower() {
        return ammoPower;
    }

    public String getName() {
        return name;
    }

    public float getShieldPower() {
        return shieldPower;
    }

    public Loot getLoot() {
        return loot;
    }

    public Damage getDamage() {
        return damage;
    }
    
    @Override
    public float protection(){
        return getShieldPower();
    }
    
    @Override
    public ShotResult receiveShot(float shot){
        if (shot > this.shieldPower)
            return ShotResult.DONOTRESIST;
        else return ShotResult.RESIST;
    }

    @Override
    public String toString() {
        return "EnemyStarShip{" + "ammoPower=" + ammoPower + ", name=" + name + ", shieldPower=" + shieldPower + ", loot=" + loot + ", damage=" + damage + '}';
    }
    
    
}
