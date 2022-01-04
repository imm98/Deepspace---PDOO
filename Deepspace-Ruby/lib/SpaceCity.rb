# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
require_relative 'SpaceStation'
module Deepspace
class SpaceCity<SpaceStation
 
  def initialize(base, rest)
    super(base.name, SuppliesPackage.new(base.ammoPower, base.fuelUnits, base.shieldPower))
    @base=base
    setLoot(Loot.new(0,0,0,0,base.nMedals, false, false))   #Hacemos base.medals para que se guarden las medallas que había en la estación espacial
    
    h=Hangar.new(1)
    receiveHangar(h)
    for i in 0...base.weapons.size
      receiveWeapon(base.weapons.at(i))
      
      mountWeapon(0)
    end
    
    for i in 0...base.shieldBoosters.size
      receiveShieldBooster(base.shieldBoosters.at(i))
      indice=base.hangar.shieldBoosters.size-1
      mountShieldBooster(indice)
    end
    discardHangar
    receiveHangar(base.hangar)
    setPendingDamage(base.pendingDamage)
    @collaborators=rest
  end
  
  def getCollaborators
    @collaborators
  end
  
  def fire
    fire=@base.fire
    if (@collaborators!=nil)
    for i in 0...@collaborators.size
      fire+=@collaborators[i].fire
    end
    end
    fire
  end
  
  def protection 
    protection=@base.protection
    if (@collaborators!=nil)
      for i in 0 ... @collaborators.size
        protection+=@collaborators[i].protection
      end
    end
  end
  
  def setLoot(loot)
    super(Loot.new(loot.nSupplies, loot.nWeapons, loot.nShields,  loot.nHangars, loot.nMedals, false, false));
  end
  
  def to_s
    salida="SpaceCity={"
    salida+="Base= "+super
    if !@collaborators.nil?
      for i in 0...@collaborators.size
        salida+="       Colaborador "+i.to_s+ "{ "+@collaborators[i].to_s + "}\n"
      end
    end
    salida
    
  end
end
end