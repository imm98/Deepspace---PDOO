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
public class BetaPowerEfficientSpaceStation extends PowerEfficientSpaceStation{
    private final float EXTRAEFFICIENCY=1.2f;
    Dice dice=new Dice();
    public BetaPowerEfficientSpaceStation(SpaceStation station){
        super(station);
    }
    
    @Override
    public float fire(){
        int size=getWeapons().size();
        if (dice.extraEfficiency()){
            float factor= EXTRAEFFICIENCY;
            for (int i=0; i< size;i++){
                Weapon w=getWeapons().get(i);
                factor*=w.useIt();
            }
            System.out.println("factor "+factor);
            return getAmmoPower()*factor;
        }
        else{
            float fire=super.fire();
            return fire;
        }
    }
    
    public String toString(){
        return "Beta"+ super.toString();
    }
    
}
