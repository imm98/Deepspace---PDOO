# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
require_relative 'PowerEfficientSpaceStation'
require_relative 'SpaceStation'

module Deepspace
class BetaPowerEfficientSpaceStation < PowerEfficientSpaceStation
  
  @@EXTRAEFFICIENCY=1.2
  def initialize(station)
    super(station)
  end
  
  def fire
    dice=Dice.new
    if dice.extraEfficiency
      factor=@@EXTRAEFFICIENCY
      for i in 0...weapons.size
        w=weapons.at(i)
        factor*=w.useIt
      end
      factor
    else
      factor=super
      factor
    end
  end
  
 def setLoot(loot)
    super(Loot.new(loot.nSupplies, loot.nWeapons, loot.nShields, loot.nHangars, loot.nMedals, false, false))
  end
  
  def to_s
    salida="Beta"+super
    salida
  end
end
end