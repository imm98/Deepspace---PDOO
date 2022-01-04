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
public class PowerEfficientSpaceStation extends SpaceStation{
    private final float EFFICIENCYFACTOR=(float) 1.1;
    
    public PowerEfficientSpaceStation(SpaceStation station){
        super(station.getName(), new SuppliesPackage(station.getAmmoPower(), station.getFuelUnits(), station.getShieldPower()));
        super.setLoot(new Loot(0,0,0,0,station.getNMedals(),false, false ));
        
        super.setPendingDamage(station.getPendingDamage());
        Hangar h=new Hangar(1);
        super.receiveHangar(h);
        
        if (station.getWeapons()!=null){
            for (int i=0; i< station.getWeapons().size();i++){
                  super.receiveWeapon(station.getWeapons().get(i));
                  super.mountWeapon(station.getHangar().getWeapons().size()-1);
            }
        }
        if (station.getShieldBoosters()!=null){
            for (int i=0; i<station.getShieldBoosters().size(); i++){
                super.receiveShieldBooster(station.getShieldBoosters().get(i));
                super.mountShieldBooster(station.getHangar().getShieldBoosters().size()-1);
            }
        }
        super.discardHangar();
        super.receiveHangar(station.getHangar());
    }
    
    @Override //Queremos que se use el fire de esta clase en vez del de SpaceStation ya que tenemos que el factor de inicio será 1.1
    public float fire(){
        int size=getWeapons().size();
        float factor= EFFICIENCYFACTOR;
        for (int i=0; i< size;i++){
            Weapon w=getWeapons().get(i);
            factor*=w.useIt();
        }
        System.out.println("factor "+factor);
        return getAmmoPower()*factor;
    }
    
    @Override
    public float protection(){
        int size=getShieldBoosters().size();
        float factor=EFFICIENCYFACTOR;
        for (int i=0; i< size; i++){
            ShieldBooster s=getShieldBoosters().get(i);
            factor*=s.useIt();
        }
        return getShieldPower()*factor;
    }
    
    @Override
    public Transformation setLoot(Loot loot){
        Transformation t=super.setLoot(new Loot(loot.getNSupplies(), loot.getNWeapons(), loot.getNShields(), loot.getNHangars(), loot.getNMedals(), loot.getEfficient(), false));
        return t;
    }
    
    @Override 
    public String toString(){
        return "PowerEfficient "+ super.toString();
    }
}
