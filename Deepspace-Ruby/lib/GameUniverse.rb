# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.


require_relative 'EnemyStarShip'
require_relative 'Dice'
require_relative 'GameStateController'
require_relative 'CardDealer'
require_relative 'SpaceStation'
require_relative 'Loot'
require_relative 'Hangar'
require_relative 'SuppliesPackage'
require_relative 'GameCharacter'
require_relative 'Weapon'
require_relative 'WeaponType'
require_relative 'ShotResult'
require_relative 'ShieldBooster'
require_relative 'Damage'
require_relative 'GameUniverseToUI'
require_relative 'CombatResult'
require_relative 'Transformation'
require_relative 'SpaceCity'
require_relative 'BetaPowerEfficientSpaceStation'
require_relative 'PowerEfficientSpaceStation'


module Deepspace
class GameUniverse
  @@WIN=10
  
  public
  def initialize
    @currentStationIndex=0
    @turns=0
    @currentStation
    @spaceStations=Array.new
    @gameState=GameStateController.new
    @currentEnemy
    @dice=Dice.new
    @haveSpaceCity
  end
  
  
  def createSpaceCity
      
      collaborators=Array.new
      for i in 0...@spaceStations.size
        if i!=@currentStationIndex
          collaborators.push(@spaceStations.at(i))
        end
      end
      @currentStation=SpaceCity.new(@currentStation, collaborators)
      @spaceStations[@currentStationIndex]=SpaceCity.new(@currentStation, collaborators) #Porque tienen deiferentes direcciones de memoria
      @haveSpaceCity=true
    
  end
  
  private
  def makeStationEfficient
     if @dice.extraEfficiency
      @currentStation=BetaPowerEfficientSpaceStation.new(@currentStation)
      @spaceStations[@currentStationIndex]=BetaPowerEfficientSpaceStation.new(@currentStation)
      
    else
      @currentStation=PowerEfficientSpaceStation.new(@currentStation)
      @spaceStations[@currentStationIndex]=PowerEfficientSpaceStation.new(@currentStation)
    end
  end
  public 
  def combatGo(station, enemy)
    
    ch=@dice.firstShot
    if ch==GameCharacter::ENEMYSTARSHIP
      fire=enemy.fire
      result=station.receiveShot(fire)
      if result==ShotResult::RESIST
        fire=station.fire
        result=enemy.receiveShot(fire)
        enemyWins=(result==ShotResult::RESIST)
      else
        enemyWins=true
      end
    else
      fire=station.fire
      result=enemy.receiveShot(fire)
      enemyWins=(result==ShotResult::RESIST)
    end
    
    if enemyWins
      s=station.speed
      moves=@dice.spaceStationMoves(s)
      if(!moves)
        damage=enemy.damage.copy
        station.setPendingDamage(damage)
        combatResult=CombatResult::ENEMYWINS
      else
        station.move
        combatResult=CombatResult::STATIONESCAPES
      end
    else
      aLoot=enemy.loot
      t=station.setLoot(aLoot)
      
      if (t==Transformation::GETEFFICIENT )
        
        combatResult=CombatResult::STATIONWINSANDCONVERTS
       
        makeStationEfficient
      elsif(t==Transformation::SPACECITY && !@haveSpaceCity)
        
        combatResult=CombatResult::STATIONWINSANDCONVERTS
       
        createSpaceCity
      else
        combatResult=CombatResult::STATIONWINS
      end
      
    end
    @gameState.next(@turns, @spaceStations.size)        #Te actualiza el gameState (de BEFORECOMBAT pasa a AFTERCOMBAT...)
    combatResult
  
  end

=begin
  public 
  def getCurrentStation
    @currentStation
  end
=end
  
  public
  def combat
    state=@gameState.state
    if state==GameState::BEFORECOMBAT || state==GameState::INIT         #Para que solo se pueda combatir si estamos before combat o INIT
      combatGo(@currentStation,@currentEnemy)
      
    else
      CombatResult::NOCOMBAT
    end
  end
  
  public
  def discardHangar
    if @gameState.state==GameState::INIT || @gameState.state==GameState::AFTERCOMBAT
      @currentStation.discardHangar
    end
  end
  
  public 
  def discardShieldBooster(i)
    if @gameState.state==GameState::INIT || @gameState.state==GameState::AFTERCOMBAT
      @currentStation.discardShieldBooster(i)
    end
  end
  
  public 
  def discardShieldBoosterInHangar(i)
    if @gameState.state==GameState::INIT || @gameState.state==GameState::AFTERCOMBAT
      @currentStation.discardShieldBoosterInHangar(i)
    end
  end
  
  public
  def discardWeapon(i)
     if @gameState.state==GameState::INIT || @gameState.state==GameState::AFTERCOMBAT
       @currentStation.discardWeapon(i)
     end
  end
  
  public 
  def discardWeaponInHangar(i)
    if @gameState.state==GameState::INIT || @gameState.state==GameState::AFTERCOMBAT
      @currentStation.discardWeaponInHangar(i)
    end
  end
  
  public 
  def state
    @gameState.state
  end
  
  public
  def getUIversion
    GameUniverseToUI.new(@spaceStations.at(@currentStationIndex),@currentEnemy)
  end
  
  public 
  def haveAWinner
    return  @currentStation.nMedals>=@@WIN
  end
  
  public 
  def init (names)
    state=@gameState.state
    @haveSpaceCity=false
    if state==GameState::CANNOTPLAY
      @spaceStations=Array.new
      dealer=CardDealer.instance
      for i in 1..names.size
        supplies=SuppliesPackage.newCopy(dealer.nextSuppliesPackage)
        station=SpaceStation.new(names.at(i-1),supplies)
        nh=@dice.initWithNHangars
        nw=@dice.initWithNWeapons
        ns=@dice.initWithNShields
        
        l=Loot.new(0,nw,ns,nh,0, false, false)
        station.setLoot(l)
        
        @spaceStations.push(station)
      end
      
      @currentStationIndex=@dice.whoStarts(names.size)
      @currentStation=@spaceStations[@currentStationIndex]
      @currentEnemy=dealer.nextEnemy
      @gameState.next(@turns, @spaceStations.size)
    end
  end
  
  public 
  def mountShieldBooster(i)
    if @gameState.state==GameState::INIT || @gameState.state==GameState::AFTERCOMBAT
      @currentStation.mountShieldBooster(i)
    end
  end
  
  public 
  def mountWeapon(i)
    if @gameState.state==GameState::INIT || @gameState.state==GameState::AFTERCOMBAT
      @currentStation.mountWeapon(i)
    end
  end
  
  public
  def nextTurn
    gameState1=@gameState.state
    final=false
    if gameState1==GameState::AFTERCOMBAT
      stationState=@currentStation.validState
      if stationState
        @currentStationIndex=(@currentStationIndex+1)%@spaceStations.size
        @turns=@turns+1
        @currentStation=@spaceStations.at(@currentStationIndex)
        @currentStation.cleanUpMountedItems                                     #Para que se borren armas y escudos con 0 usos
        dealer=CardDealer.instance
        @currentEnemy=dealer.nextEnemy
        @gameState.next(@turns, @spaceStations.size)
        final=true
      else
        final=false
      end
    else
      final=false
    end
    final
  end
  
  
  public 
  def to_s
     salida="GameUniverse{currentStationIndex=#{@currentStationIndex}, currentStation=#{@currentStation} turns=#{@turns}, currentEnemy=#{@currentEnemy}"
     if (!@spaceStations.nil?)
       for i in 0...@spaceStations.size
         if i!=@currentStationIndex
          salida+=@spaceStation.to_s
         end
       end
     end
     salida += " haveSpaceCity#{@haveSpaceCity}"
     salida
  end
  
end
end