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
public class ShieldBooster implements CombatElement{
    private String name;
    private float boost;
    private int uses;
    
    ShieldBooster(String n, float b, int u){
        this.name=n;
        this.boost=b;
        this.uses=u;
    }
    
    ShieldBooster(ShieldBooster s){
        this.name=s.name;
        this.boost=s.boost;
        this.uses=s.uses;
    }
    
    ShieldToUI getUIversion (){
        return new ShieldToUI(this);
    }
    
    public float getBoost(){
        return this.boost;
    }
    
    @Override
    public int getUses(){
        return this.uses;
    }
    
    @Override
    public float useIt(){
        if (this.uses > 0){
            uses--;
            return boost;
        }
        else {
            float resultado=1;
            return resultado;
        }
    }

    @Override
    public String toString() {
        return "ShieldBooster{" + "name=" + name + ", boost=" + boost + ", uses=" + uses + '}';
    }
    

}
