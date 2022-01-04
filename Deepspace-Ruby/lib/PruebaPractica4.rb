# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require_relative 'CardDealer'
require_relative 'SpaceStation'
require_relative 'EnemyStarShip'
require_relative 'Dice'
require_relative 'GameStateController'
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
require_relative 'GameUniverse'

class PruebaPractica4
  def initialize
    
  end
  
  public 
  def main
    instancia=Deepspace::CardDealer.instance
    supplies=instancia.nextSuppliesPackage
    station=Deepspace::SpaceStation.new("pepe", supplies)
    loot=Deepspace::Loot.new(4,3,2,1,1,false,true)  #nsu, nw, nsh , nh , nm, ef=nil, city=nil
     d=Deepspace::NumericDamage.new(4,3)
    enemy=Deepspace::EnemyStarShip.new("pepe", 0, 0, loot,  d)#n, a, s, l, d
    
    
    game=Deepspace::GameUniverse.new 
    array=Array.new 
    array.push("Pepe")
    array.push("Juan")
    game.init(array)
    
=begin                                    # Se hace lo de la haveSpaceCity
    c=game.combatGo(station, enemy)
    puts game.getCurrentStation.to_s
    game.nextTurn

    
    c=game.combatGo(station, enemy)
    puts c.to_s
    puts game.getCurrentStation.to_s
    game.nextTurn

    puts game.getCurrentStation.to_s
    c=game.combatGo(station, enemy)
    puts c.to_s
    puts game.getCurrentStation.to_s
=end    

   
    station2=Deepspace::SpaceStation.new("pepito", supplies)
    #loot=Deepspace::Loot.new(4,3,2,1,1)  
     d=Deepspace::NumericDamage.new(4,3)
    enemy2=Deepspace::EnemyStarShip.new("enemigo", 0, 0, loot,  d)
    
    c=game.combatGo(station2, enemy2)
   puts c.to_s
   puts game.to_s
   
    #puts game.getCurrentStation.to_s
    game.nextTurn
    puts game.to_s
    
    c=game.combatGo(station2, enemy2)
    puts c.to_s
    #puts game.getCurrentStation.to_s
    
    
    game.nextTurn
    puts game.to_s
    
    
    
    
    c=game.combatGo(station2, enemy2)
    puts c.to_s
    puts game.to_s
  
    
  end
  
  
  
end
m=PruebaPractica4.new
m.main

