# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require_relative 'loot'
require_relative 'supplies_package'
require_relative 'shield_booster'
require_relative 'weapon'
require_relative 'weapon_type'
require_relative 'dice'
require_relative 'game_character'


class TestP1
  def initialize
    
  end
  
  def main
    l=Loot.new(1, 2, 3, 4, 5)
    
    print "nSupplies", l.nSupplies, "nWeapons", l.nWeapons, "nShields", l.nShields, "nHangars", l.nHangars, "nMedals", l.nMedals
    shields= ShieldBooster.new("escudo", 2.0, 1)
    print "Boost", shields.boost,"Uses", shields.uses, "UseIt", shields.useIt
    su=SuppliesPackage.new(1,2,3)
    print "ammo", su.ammoPower,"fuel", su.fuelUnits, "shield", su.shieldPower
    misil=WeaponType::MISSILE
    weapon=Weapon.new("pistola", misil, 8)
    print "type", weapon.type, "uses", weapon.uses, "power",weapon.power, "useIt", weapon.useIt
    
    dado=Dice.new
    nShields=0
    nHangars=0
    nWeapons=0
    nWeapons2=0
    
    for i in 0..100 do
      if dado.initWithNShields==0
        nShields+=1
      end
      if dado.initWithNHangars==0
        nHangars+=1
      end
      if dado.initWithNWeapons==1
        nWeapons+=1
      elsif dado.initWithNWeapons==2
        nWeapons2+=1
      end
    end
    
    print "El numero de veces que se ha dado nShields es ", nShields, " cuando tiene que estar alrededor de 25."
    print "El numero de veces que se ha dado nHangars es ", nHangars, " cuando tiene que estar alrededor de 25."
    print "El numero de veces que se ha dado nWeapons es ", nWeapons, " cuando tiene que estar alrededor de 33"
    print "El numero de veces que se ha dado nWeapons2 es ", nWeapons2, " cuando tiene que estar alrededor de 33"
    dado.to_s
  end
  
end

nuevo=TestP1.new
nuevo.main