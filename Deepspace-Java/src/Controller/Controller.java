/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import View.View;
import deepspace.GameUniverse;
import deepspace.GameState;
import deepspace.GameUniverseToUI;
import java.util.ArrayList;
import View.GUI.MainView;
import deepspace.CombatResult;
/**
 *
 * @author inaki
 */
public class Controller {
    private View view;
    private GameUniverse game;
    
    
    public Controller (GameUniverse g, View v){
        view=v;                 //Se crea una vista (que es una interfaz) y un GameUniverse
        game=g;
        view.setController(this);       //Se inicializa el controller de la vista a este controller
    }
    
    public void start(){
        game.init(view.getNames());             //Se inicializa el gameUniverse con los nombres que se reciben de la vista
        view.updateView();
        view.showView();
    }
    //Funcion auxiliar
    public void showView(){
        view.showView();
    }
    
    //Para mostrar en la vista de la spaceStation el combatResult
    /*public CombatResult getCombatResult(){
        return result;
    }*/
    
    public void finish(int i){
        if (view.confirmExitMessage())
            System.exit(i);
    }
    
    public GameState getState(){
        return game.getState();
    }
    
    public GameUniverseToUI getModelToUI (){
        return game.getUIversion();
    }
    
    public void montarArmas(ArrayList<Integer> armas){
     
        for (int i=0; i< armas.size();i++){
        
            game.mountWeapon(armas.get(i)-i);
        }
        view.updateView();
        
    }
    
    public void montarEscudos(ArrayList<Integer> escudos){
      
        for (int i=0;i< escudos.size(); i++){
            game.mountShieldBooster(escudos.get(i)-i);
            
        }
        
        view.updateView();
        
    }
    
    public void  desmontarArma(ArrayList<Integer> w){
        
        for (int i=0; i< w.size(); i++){
            game.discardWeapon(w.get(i)-i);
        }
        
       view. updateView();
    }
    
    public void desmontarEscudo(ArrayList<Integer> s){
        for (int i=0; i<s.size(); i++){
            game.discardShieldBooster(s.get(i)-i);
        }
        view.updateView();
    }
    
    public void desmontarArmaHangar(ArrayList<Integer> w){
        for (int i=0; i< w.size(); i++){
            game.discardWeaponInHangar(w.get(i));
        }
        
        view.updateView();
    }
    
    public void desmontarEscudoHangar(ArrayList<Integer> s){
        for (int i=0; i<s.size(); i++)
            game.discardShieldBoosterInHangar(s.get(i));
        
        view.updateView();
    }
    
    public void discardHangar(){
        game.discardHangar();
        view.updateView();
    }
    
    public void nextTurn(){
        game.nextTurn();
    }
    
    public void combatir(){
        CombatResult result=game.combat();
        if (result==CombatResult.ENEMYWINS){
            
            view.MensajePerder();
        }
        else if (result==CombatResult.STATIONESCAPES){
            
            view.MensajeEscapar();
        }
        else if (result==CombatResult.STATIONWINS){
            
            view.MensajeGanar();
        }
        
        else{
            
            view.MensajeSpaceCity();
        }
        
    }
    
    public boolean isAFTERCOMBAT(){
        return (game.getState()==GameState.AFTERCOMBAT);
    }
    
    
    
    public boolean sePuedePasarTurno(){
        /*if (game.getState()==GameState.AFTERCOMBAT && game.getUIversion().getCurrentStation().getPendingDamage()==null)
            System.out.println("\nVerdad");*/

        if (getModelToUI().getCurrentStation().getPendingDamage()==null)
                return  (game.getState()==GameState.AFTERCOMBAT) ;
        else return false;
        
    
        
        }
    
    public boolean sePuedeCombatir(){
        return ((getModelToUI().getCurrentStation().getPendingDamage()==null) &&((game.getState()==GameState.INIT)||(game.getState())==GameState.BEFORECOMBAT));
    }
    
    public boolean sePuedeDesmontar(){
        return ((game.getState()==GameState.AFTERCOMBAT||game.getState()==GameState.INIT)  &&  
                (!getModelToUI().getCurrentStation().getShieldBoosters().isEmpty() || !getModelToUI().getCurrentStation().getWeapons().isEmpty()));
    }
    
    public boolean sePuedeDescartarHangar(){
        if (getModelToUI().getCurrentStation().getHangar()!=null)
        return ((game.getState()==GameState.AFTERCOMBAT||game.getState()==GameState.INIT)   &&
                (!getModelToUI().getCurrentStation().getHangar().getShieldBoosters().isEmpty() || !getModelToUI().getCurrentStation().getHangar().getWeapons().isEmpty()));
        else 
             return false;
    
    }
}
