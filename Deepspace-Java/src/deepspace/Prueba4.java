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
public class Prueba4 {
    public static void main(String[] args){
        NumericDamage nd=new NumericDamage(3,4);
        System.out.println( nd.toString());
        ArrayList<ShieldBooster> sb=new ArrayList();
        ShieldBooster s=new ShieldBooster("pepe", 4, 4);
        sb.add(s);
        ArrayList<Weapon> aw=new ArrayList();
        Weapon w=new Weapon("juan", WeaponType.MISSILE, 3);
        aw.add(w);
        System.out.println(nd.adjust(aw, sb).toString());
 
        Weapon w2=new Weapon("pepito", WeaponType.LASER, 3);
        aw.add(w2);
        
        ArrayList<WeaponType> wt=new ArrayList();
        wt.add(WeaponType.LASER);
        wt.add(WeaponType.MISSILE);
        wt.add(WeaponType.MISSILE);
        SpecificDamage sd=new SpecificDamage(wt, 4);
        System.out.println(sd.toString());
        
        System.out.println(sd.adjust(aw, sb).toString());
    }
}

