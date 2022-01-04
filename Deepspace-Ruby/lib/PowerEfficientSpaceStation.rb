# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
require_relative 'SpaceStation'

module Deepspace
class PowerEfficientSpaceStation < SpaceStation
  
  @@EFFICIENCYFACTOR=1.1
  
  def initialize(station)
    super(station.name, SuppliesPackage.new(station.ammoPower, station.fuelUnits, station.shieldPower))
    @station=station
    setLoot(Loot.new(0,0,0,0,station.nMedals, false, false))
    
    h=Hangar.new(1)
    receiveHangar(h)
    for i in 0...station.weapons.size
      receiveWeapon(station.weapons.at(i))
      mountWeapon(0)
    end
    
    for i in 0...station.shieldBoosters.size
      receiveShieldBooster(station.shieldBoosters.at(i))
      mountShieldBooster(0)
    end
    discardHangar
    receiveHangar(station.hangar)
    setPendingDamage(station.pendingDamage)

  end
  
  def fire
    factor=@@EFFICIENCYFACTOR
    for i in 0...weapons.size
      w=weapons.at(i)
      factor*=w.useIt
    end
    factor
  end
  
  def protection
    factor=@@EFFICIENCYFACTOR
    for i in 0...shieldBoosters.size
      s=shielBoosters.at(i)
      factor*=s.useIt
    end
    factor
  end
  
  def setLoot(loot)
    super(Loot.new(loot.nSupplies, loot.nWeapons, loot.nShields, loot.nHangars, loot.nMedals, loot.efficient, false))
  end
  
  def to_s
    string="PowerEfficient"+super
    string 
  end
end
end