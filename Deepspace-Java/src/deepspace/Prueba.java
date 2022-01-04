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
public class Prueba {
public static void main(String[] args){
NumericDamage nd = new NumericDamage(8, 6);
ArrayList<ShieldBooster> sh=new ArrayList();
ArrayList<Weapon> wp=new ArrayList();
System.out.println(nd.toString());
sh.add(new ShieldBooster("escudo", 8.3f, 7));
sh.add(new ShieldBooster("escudo", 8.2f, 3));
wp.add(new Weapon("arma", WeaponType.LASER, 2));
wp.add(new Weapon("arma2", WeaponType.MISSILE, 3));
System.out.println(nd.adjust(wp, sh).toString());
ArrayList<WeaponType> wt=new ArrayList();
wt.add(WeaponType.LASER);
wt.add(WeaponType.MISSILE);
wt.add(WeaponType.PLASMA);
wt.add(WeaponType.MISSILE);
wt.add(WeaponType.LASER);
SpecificDamage sd= new SpecificDamage(wt, 6);
System.out.println(sd.toString());
System.out.println(sd.adjust(wp, sh).toString());

Loot l = new Loot(1,2,3,4,5,true,false);
Loot l2 = new Loot(1,2,3,4,5,false,true);
SuppliesPackage sp=new SuppliesPackage(3.2f,2.1f,6.7f);
SpaceStation s=new SpaceStation("Estacion Espacial Eficiente", sp);
SpaceStation s2=new SpaceStation("Ciudad Espacial", sp);
Transformation t=s.setLoot(l);
Transformation t2=s2.setLoot(l2);
System.out.println(s.toString());
System.out.print("Se transforma?");
System.out.println(t);
System.out.println(s2.toString());
System.out.print("Se transforma?");
System.out.println(t2);
s.mountWeapon(0);
System.out.println(s.toString());

PowerEfficientSpaceStation pe=new PowerEfficientSpaceStation(s);
System.out.println(pe.toString());
GameUniverse gu= new GameUniverse();
ArrayList<String> names= new ArrayList();
names.add("1");
names.add("2");
gu.init(names);
Loot l3 = new Loot(1,2,3,4,5,true,false);
Loot l4 = new Loot(1,2,3,4,5,false,true);
EnemyStarShip e1= new EnemyStarShip("sc", 1, 1, l3, nd);
EnemyStarShip e2= new EnemyStarShip("es", 1, 1, l3, sd);
System.out.println("SpaceCity? ");
System.out.print(gu.combat(s, e1));
System.out.println(" ");
System.out.println("PowerEfficient? ");
System.out.print(gu.combat(s2, e2));
gu.createSpaceCity();
System.out.println(" ");
System.out.println(gu.toString());

}
}
