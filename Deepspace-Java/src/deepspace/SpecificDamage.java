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
public class SpecificDamage extends Damage{
    
    private ArrayList<WeaponType> weapons;
    
    SpecificDamage( ArrayList<WeaponType> w, int s){
        super(s);
        weapons=w;
    }
    
    @Override
    SpecificDamage copy(){
        SpecificDamage sd=new SpecificDamage(getWeapons(), getNShields());
        return sd;
    }
    
    @Override
    SpecificDamageToUI getUIversion(){
        return new SpecificDamageToUI(this);
    }
    
    @Override
    SpecificDamage adjust (ArrayList<Weapon>  w, ArrayList<ShieldBooster> s){
        
        int sb=getNShields();
        
        
        
        
        if(s.size()<sb)
            sb=s.size();
        
        
        
   
        ArrayList<WeaponType> t=new ArrayList();
        ArrayList<Weapon> ww=new ArrayList(w);

        int j;
        for(int i=0; i<getWeapons().size() && !ww.isEmpty(); i++){

            if((j=arrayContainsType(ww, getWeapons().get(i)))>=0) {

                t.add(ww.get(j).getType());
                ww.remove(j);
            }
        }
        if (!t.isEmpty()||sb!=0){
            SpecificDamage d;
            d=new SpecificDamage(t,sb);
            return d;
        }
        else{
            SpecificDamage d=null;
            return d;
        }
        
        }
    
        protected int arrayContainsType(ArrayList<Weapon> w, WeaponType t){
            boolean existe=false;
            int i=0;
            for (; i<w.size() && !existe; i++){
                if (w.get(i).getType()==t){
                    existe=true;
                }
            }

            if (existe) return i-1;
            else return -1;
        }
    
        @Override
        public void discardWeapon(Weapon w){

                    while (((weapons.indexOf(w.getType()))!=-1)){
                        int i=weapons.indexOf(w.getType());
                        weapons.remove(i);
                    }
                
        }
        
        @Override
        public boolean hasNoEffect(){
            return getNShields()==0 && weapons.isEmpty();
        }
        
        public ArrayList<WeaponType> getWeapons(){
            return this.weapons;
        }

    @Override
    public String toString() {
        return "SpecificDamage{" + "weapons=" + weapons + " nSHields="+ getNShields()+ '}';
    }
        
        
        
        
}
