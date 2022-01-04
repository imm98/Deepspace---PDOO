/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deepspace;

import static deepspace.GameState.AFTERCOMBAT;
import static deepspace.GameState.INIT;
import java.util.ArrayList;

/**
 *
 * @author inaki
 */
public class GameUniverse {
    private static int WIN=10;
    private int currentStationIndex;
    private int turns;
    private SpaceStation currentStation;
    private GameStateController gameState;
    private EnemyStarShip currentEnemy;
    private ArrayList<SpaceStation> spaceStations=new ArrayList();
    private Dice dice;
    private boolean haveSpaceCity;
    
    private void makeStationEfficient(){
        if(dice.extraEfficiency())
            currentStation=new BetaPowerEfficientSpaceStation(currentStation);
        else 
            currentStation=new PowerEfficientSpaceStation(currentStation);       
    }
    
    void createSpaceCity(){
        if (haveSpaceCity==false){
            ArrayList<SpaceStation> collaborators=new ArrayList();
            for (int i=0; i< spaceStations.size(); i++)
                if (i!=currentStationIndex)
                    collaborators.add(spaceStations.get(i));
            
            currentStation=new SpaceCity(currentStation, collaborators);
            haveSpaceCity=true;
        }
    }
    
    public GameUniverse(){
        gameState=new GameStateController();
        this.turns=0;
        dice=new Dice();
    }
    
    CombatResult combat (SpaceStation station, EnemyStarShip enemy){
        GameCharacter ch=dice.firstShot();
        CombatResult combatResult;
        boolean enemyWins;
        if (ch==GameCharacter.ENEMYSTARSHIP){
            float fire=enemy.fire();    //Fire nivel de disparo de la nave enemiga
            ShotResult result=station.receiveShot(fire);
            if (result==ShotResult.RESIST){
                fire=station.fire();
                result=enemy.receiveShot(fire);
                enemyWins=(result==ShotResult.RESIST);
            }
            else
                enemyWins=true;
        }
        else {
            float fire=station.fire();
            ShotResult result=enemy.receiveShot(fire);
            enemyWins=(result==ShotResult.RESIST);
        }
        
        if (enemyWins){
            float s=station.getSpeed();
            boolean moves=dice.spaceStationMoves(s);
                if(!moves){
                    Damage damage=enemy.getDamage().copy();
                    station.setPendingDamage(damage);
                    combatResult=CombatResult.ENEMYWINS;
                }else{
                    station.move();
                    combatResult=CombatResult.STATIONESCAPES;
                }
        }
        else {
            Loot aLoot=enemy.getLoot();
            System.out.println("Loot del enemigo: "+ aLoot.toString());
            Transformation t=station.setLoot(aLoot);
            if (t==Transformation.GETEFFICIENT){
                combatResult=CombatResult.STATIONWINSANDCONVERTS;
                makeStationEfficient();
            }
            else if (t==Transformation.SPACECITY){
                combatResult=CombatResult.STATIONWINSANDCONVERTS;
                createSpaceCity();
            }
            else
                combatResult=CombatResult.STATIONWINS;
        }
        
        gameState.next(turns, spaceStations.size());
        return combatResult;
    }
    
    public CombatResult combat(){
        GameState state=gameState.getState();
        if (state==GameState.BEFORECOMBAT || state==GameState.INIT){
            CombatResult combatResult=combat(currentStation, currentEnemy);
            return combatResult;
        }
        else
            return CombatResult.NOCOMBAT;
    }
    
    public void discardHangar(){
        if (gameState.getState()==INIT || gameState.getState()==AFTERCOMBAT)
            currentStation.discardHangar();
    }
    
    public void discardShieldBooster(int i){
        if (gameState.getState()==INIT || gameState.getState()==AFTERCOMBAT)
            currentStation.discardShieldBooster(i);
    }
    
    public void discardShieldBoosterInHangar(int i){
        if (i>=0){
            if (gameState.getState()==INIT || gameState.getState()==AFTERCOMBAT)
                currentStation.discardShieldBoosterInHangar(i);
        }
    }
    
    public void discardWeapon(int i){
        if (i>=0){
            if (gameState.getState()==INIT || gameState.getState()==AFTERCOMBAT)
                currentStation.discardWeapon(i);
        }
    }
   
    public void discardWeaponInHangar(int i){
        
        if (gameState.getState()==INIT || gameState.getState()==AFTERCOMBAT)
            currentStation.discardWeaponInHangar(i);
    }
    
    public GameState getState(){
        return this.gameState.getState();
    }
    
    public GameUniverseToUI getUIversion(){
        return new GameUniverseToUI(spaceStations.get(currentStationIndex),currentEnemy);
    }
    
    public boolean haveAWinner(){
        return (this.currentStation.getNMedals()==WIN);
    }
    
    public void init(ArrayList<String> names){
        GameState state=gameState.getState();   //Por ser un tipo enumerado
        haveSpaceCity=false;
        if (state==GameState.CANNOTPLAY){
            spaceStations=new ArrayList();
            CardDealer dealer=CardDealer.getInstance();
            for (int i=1; i<=names.size();i++){
                SuppliesPackage supplies=new SuppliesPackage(dealer.nextSuppliesPackage());
                SpaceStation station=new SpaceStation(names.get(i-1), supplies);
                
                int nh=dice.initWithNHangars();
                int nw=dice.initWithNWeapons();
                int ns=dice.initWhithNShields();
                
                Loot I =new Loot(0,nh,nw,ns,0, false, false);
                
                station.setLoot(I);
                
                spaceStations.add(station);
            }
            
            currentStationIndex=dice.whoStarts(names.size());
            
            currentStation=spaceStations.get(currentStationIndex);
            
            currentEnemy=dealer.nextEnemy();
            
            gameState.next(turns, spaceStations.size());
                
            
        }
        
    }
    
    public void mountShieldBooster(int i){
        if (gameState.getState()==GameState.INIT || gameState.getState()==GameState.AFTERCOMBAT)
            spaceStations.get(currentStationIndex).mountShieldBooster(i);
    }
    
    public void mountWeapon(int i){
        if (gameState.getState()==GameState.INIT || gameState.getState()==GameState.AFTERCOMBAT)
            spaceStations.get(currentStationIndex).mountWeapon(i);
    }
    
    public boolean nextTurn(){
        GameState gameState1=gameState.getState();
        if (gameState1==GameState.AFTERCOMBAT){
            boolean stationState=currentStation.validState();
            if (stationState){
                currentStationIndex=(currentStationIndex+1)%spaceStations.size();
                turns++;
                currentStation=spaceStations.get(currentStationIndex);
                currentStation.cleanUpMountedItems();
                CardDealer dealer=CardDealer.getInstance();
                currentEnemy=dealer.nextEnemy();
                gameState.next(turns, spaceStations.size());
                return true;
            }
            return false;
        }
        return false;
    }
    
    public String toString(){
        String salida="GameUniverse={ \n\t";
        salida+="currentStationIndex= "+currentStationIndex+ "\n\t"+currentStation.toString()+"\n\t"+ "turns= "+turns+"\n\tcurrentEnemy= "+currentEnemy.toString()+ "\n\tSpaceStations "+spaceStations+ "\n\thaveSpaceCity= "+haveSpaceCity;
        return salida;
    }
}
