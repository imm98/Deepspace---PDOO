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
public class TestP1 {
    public static void main(String[] args){        
     
        
        WeaponType wt=WeaponType.MISSILE;
        Weapon w=new Weapon("escopeta", wt, 3);
        System.out.println("Tipo: "+ w.getType());
        System.out.println("Usos: "+ w.getUses());
        System.out.println("Potencia: "+ w.power());
        System.out.println("useIt: "+ w.useIt());
        
        SuppliesPackage su=new SuppliesPackage(1.0f,2.0f,3.0f);
        
        System.out.println("AmmoPower: " + su.getAmmoPower());
        System.out.println("fuelUnits: " + su.getFuelUnits());
        System.out.println("ShieldsPower: " + su.getShieldsPower());
        
        ShieldBooster sh=new ShieldBooster("escudo", 2.0f, 1);
        
        System.out.println("Boost: " + sh.getBoost());
        System.out.println("Uses: " + sh.getUses());
        System.out.println("useIt" + sh.useIt());
        
        Loot l=new Loot(1, 2, 3, 4, 5, false, false);
        
        System.out.println("nSupplies: " + l.getNSupplies());
        System.out.println("nWeapons: " + l.getNWeapons());
        System.out.println("nShields: " + l.getNShields());
        System.out.println("nHangars: " + l.getNHangars());
        System.out.println("nMedals: " + l.getNMedals());
        
        Dice d=new Dice();
        
        System.out.println("FirstShot: " + d.firstShot());
        System.out.println("InitWithNShields: " + d.initWhithNShields());
        System.out.println("InitWithNHangars: " + d.initWithNHangars());
        System.out.println("initWithNWeapons: " + d.initWithNWeapons());
        System.out.println("spaceStation: " + d.spaceStationMoves(0.33f));
        System.out.println("whoStar: " + d.whoStarts(9));
        
        
        int NShields=0;
        int NHangars=0;
        int NWeapons=0;
        int NWeapons2=0;
        for (int i=0; i<100; i++){
            if (d.initWhithNShields()==0){
                NShields++;
            }
            
            if (d.initWithNHangars()==0){
                NHangars++;
            }
            
            if (d.initWithNWeapons()==1){
                NWeapons++;
            }
            
            if(d.initWithNWeapons()==2){
                NWeapons2++;
            }
        }
        
        System.out.println("El numero de veces que se da NSHIELDS es " + NShields + " cuando tendría que ser más o menos 25");
        System.out.println("El numero de veces que se da NHANGARSPROB es " + NHangars + " cuando tendría que ser más o menos 25");
        System.out.println("El numero de veces que se da NWEAPONSPROB es " + NWeapons + "cuando tendría que ser más o menos 33");
        System.out.println("El numero de veces que se da 2*NWEAPONSPROB es " + NWeapons2 + "cuando tendría que ser más o menos 33");
    }
    
       
}
