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
public class TestP2 {
    public static void main(String[] args){
        
        //Prueba del hangar
        /*Funciona perfectamente
        WeaponType misil=WeaponType.MISSILE;
        Weapon w1=new Weapon("misil1", misil, 3);
        WeaponType laser=WeaponType.LASER;
        Weapon w2=new Weapon("laser1", laser, 4);
        Weapon w3=new Weapon("misil2", misil, 5);

        
        ShieldBooster s1=new ShieldBooster ("ss1", 22, 2);
        ShieldBooster s2=new ShieldBooster ("ss2", 44, 4);
        
        
       
        
        Hangar h=new Hangar(5);
        
        h.addWeapon(w1);
        h.addWeapon(w2);
        h.addWeapon(w3);
        
        h.addShieldBooster(s1);
        h.addShieldBooster(s2);
        
        System.out.println(h.toString());
        
        System.out.println(h.getMaxElements());
        
        System.out.println("ShieldBooster 1: " + h.getShieldBoosters().get(0).ToString() + "\nShieldBooster 2: "+h.getShieldBoosters().get(1).ToString());
        
        System.out.println("Weapon 1: " + h.getWeapons().get(0).ToString() + "\nWeapon 2: " + h.getWeapons().get(1).ToString() + "\nWeapon 3: " + h.getWeapons().get(2).ToString());
        
        
        h.removeShieldBooster(0);
                
        
        h.removeWeapon(1);
        
        System.out.println("ShieldBooster 1: " + h.getShieldBoosters().get(0).ToString() );
        
        System.out.println("Weapon 1: " + h.getWeapons().get(0).ToString() + "\nWeapon 2: " + h.getWeapons().get(1).ToString() );
        
        */
        
        // Comprobación del damage. Había un error en el adjust return i-1 y mientras ww no estuviera vacio
        /*
        WeaponType misil=WeaponType.MISSILE;
        WeaponType laser=WeaponType.LASER;
        WeaponType laser2=WeaponType.LASER;

        
        ArrayList<WeaponType> weapons=new ArrayList();
        weapons.add(misil);
        weapons.add(laser);
        weapons.add(laser2);

        
        Damage d=new Damage(weapons, 30);
        
        System.out.println(d.toString());
        

        Weapon w1=new Weapon("misil1", misil, 3);
        Weapon w2=new Weapon("laser1", laser, 4);
        
        ArrayList<Weapon> weaponsadjust=new ArrayList();
        weaponsadjust.add(w1);
        weaponsadjust.add(w2);
        
        ShieldBooster s1=new ShieldBooster ("ss1", 22, 2);
        ShieldBooster s2=new ShieldBooster ("ss2", 44, 4);
        
        ArrayList<ShieldBooster> shieldBoosters=new ArrayList();
        shieldBoosters.add(s1);
        shieldBoosters.add(s2);
        
        
        
        Damage dadjust=d.adjust(weaponsadjust, shieldBoosters);
      
        System.out.println(dadjust.toString());
        
        dadjust.discardWeapon(w2);
        dadjust.discardShieldBooster();
        
        System.out.println(dadjust.toString());
        */
        
        //Comprobación del enemyStarShip. Funciona perfectamente
        /*
        WeaponType misil=WeaponType.MISSILE;
        WeaponType laser=WeaponType.LASER;
        WeaponType laser2=WeaponType.LASER;

        
        ArrayList<WeaponType> weapons=new ArrayList();
        weapons.add(misil);
        weapons.add(laser);
        weapons.add(laser2);

        
        Damage d=new Damage(weapons, 30);
        
        Loot l=new Loot(1,2,3,4,5);
        
        EnemyStarShip ess= new EnemyStarShip( "marcianos", 3, 2, l, d);
        
        System.out.println(ess.toString());
        
        ShotResult sr;
        sr=ess.recieveShot(4);
        
        if (ShotResult.DONOTRESIST==sr)
            System.out.println("No resiste");
        if (ShotResult.RESIST==sr)
            System.out.println("Resiste");
    */
        
        //SpaceStation //Error en el mountWeapon y en el mountShieldBooster por el add
        SuppliesPackage sp=new SuppliesPackage(1.0f,2.0f,3.0f);     //AmmoPower, fuelUnits, shieldPower
        SpaceStation ss=new SpaceStation ("Victor Castro-Raquel", sp);
        
        System.out.println(ss.getSpeed());
        
        
        System.out.println(ss.toString());
        
        Hangar h=new Hangar(7); //7 es la máxima capacidad de un hangar 
        
        WeaponType misil=WeaponType.MISSILE;
        Weapon w1=new Weapon("misil1", misil, 3);
        WeaponType laser=WeaponType.LASER;
        Weapon w2=new Weapon("laser1", laser, 4);
        Weapon w3=new Weapon("misil2", misil, 5);
        
        
        
        ShieldBooster s1=new ShieldBooster ("ss1", 22, 2);
        ShieldBooster s2=new ShieldBooster ("ss2", 44, 4);
        
        h.addShieldBooster(s1);
        h.addShieldBooster(s2);
        h.addWeapon(w1);
        h.addWeapon(w2);
        h.addWeapon(w3);
        
        
        
        ss.receiveHangar(h);    //Monta el hangar en el hangar de la estación espacial
        
        ShieldBooster s3=new ShieldBooster ("ss3", 66, 6);
               
        Weapon w4=new Weapon("misil6", misil, 3);
        
        ss.receiveWeapon(w4);
        
        SuppliesPackage sp2=new SuppliesPackage(1.5f,2.5f,3.5f);
        
        ss.receiveSupplies(sp2);        //Incrementa el ammoPower, fuelUnits y shieldPower en el suppliesPackage que se pasa como argumento 
        
        System.out.println(ss.validState());
        
        System.out.println(ss.toString());
        
        
        WeaponType laser2=WeaponType.LASER;
        ArrayList<WeaponType> weapons=new ArrayList();
        weapons.add(misil);
        weapons.add(laser);
        weapons.add(laser2);

        
        SpecificDamage d=new SpecificDamage(weapons, 30);
        
        System.out.println(ss.getWeapons());
        
        System.out.println(ss.getShieldBoosters());
        
        System.out.println("Weapons del hangar: " + ss.getHangar().getWeapons().toString() + "\nShieldBoosters del hangar: "+ ss.getHangar().getShieldBoosters().toString());
        
        ss.receiveWeapon(w1);
        ss.receiveWeapon(w2);
        ss.receiveWeapon(w3);
        
        System.out.println("Weapons del hangar: " + ss.getHangar().getWeapons().toString() + "\nShieldBoosters del hangar: "+ ss.getHangar().getShieldBoosters().toString());
        
        ss.mountWeapon(0);
        ss.mountShieldBooster(0);
        
        
        
        System.out.println(ss.getShieldBoosters());
        System.out.println(ss.getWeapons());
        
        ss.setPendingDamage(d);
        
        System.out.println ("Danio="+ss.getPendingDamage().toString());
        
        System.out.println(ss.validState());
        
        System.out.println("Weapons del hangar: " + ss.getHangar().getWeapons().toString() + "\nShieldBoosters del hangar: "+ ss.getHangar().getShieldBoosters().toString());
        
        System.out.println(ss.getWeapons());
        
        System.out.println(ss.getShieldBoosters());
        
        SuppliesPackage sp3=new SuppliesPackage(1.5f,2.5f,3.5f);
        
        System.out.println(ss.getFuelUnits());
        
        ss.receiveSupplies(sp3);
        
        System.out.println(ss.getFuelUnits());
        
        Weapon w5=new Weapon("misil6", misil, 0);
        
        ss.receiveWeapon(w5);
        
        ss.mountWeapon(3);
        
        System.out.println(ss.getWeapons());
        
        ss.cleanUpMountedItems();
        
        System.out.println(ss.getWeapons());
        
        //Game Universe. Quedan muchas cosas por comprobar
        /*GameUniverse game=new GameUniverse();
        
        System.out.println(game.haveAWinner());*/
    }
}
