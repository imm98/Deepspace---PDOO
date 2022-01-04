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
require_relative 'GameUniverse'

module Deepspace
class TestP2
  def main
    
    
    puts "Hangar: "
    h=Hangar.new(3)
    hui=HangarToUI.new(h)
    
    w=Weapon.new("prueba", WeaponType::LASER, 5)
    s=ShieldBooster.new("escudito", 9.6,3)      
    h.addShieldBooster(s)
    h.addWeapon(w)
    
    print "MaxElements= " , h.maxElements, "\n"
    
    print "Weapons= ", h.weapons, "\n"
    
    print "ShieldBooster= ", h.shieldBoosters, "\n"
    
    print "To String= ", h.to_s, "\n"
    

    
    print "To String= ", h.to_s, "\n"

    hc=Hangar.newCopy(h)
    
    print "To String", hc.to_s
    
  end
  
  def main2
    puts "Damage: "
    
    d=Damage.newNumericWeapons(3,4)
    
    wt=[WeaponType::LASER, WeaponType::MISSILE, WeaponType::PLASMA]
    
    puts "ToString: ", d.to_s
    
    wj=[WeaponType::LASER, WeaponType::MISSILE]
    
    s1=ShieldBooster.new("hola",2,3)
    
    s=[s1]
    
    w1=Weapon.new("arma", WeaponType::LASER, 3)
    
    wv=[w1]
    
    t=d.adjust(wv,s)
    
    puts "ToStringa ", t.to_s
    
    d2=Damage.newSpecificWeapons(wt, 4)
    
    puts "ToString2 ", d2.to_s
    
    t2=d2.adjust(wv, s)
    
    puts "toString2a ", t2.to_s
    
    damage2=Damage.newCopy(d2)
    
    puts "copia ", damage2.to_s
    
    damage2.discardShieldBooster
    
    damage2.discardWeapon(w1)
    
    puts "DiscardWeapon ", damage2.to_s
  end
  
  def main3
     puts "SpaceStation"
    
       h=Hangar.new(3)
      hui=HangarToUI.new(h)
      w=Weapon.new("prueba", WeaponType::LASER, 0)
      s=ShieldBooster.new("n",9.6,0)
      h.addShieldBooster(s)
      h.addWeapon(w)
      print h.to_s
      hh=Hangar.newCopy(h)
       print hh.to_s
        h.removeWeapon(0)
        h.removeShieldBooster(0)

        puts "Damage: "
        wt=[WeaponType::LASER, WeaponType::MISSILE, WeaponType::PLASMA]
        d=SpecificDamage.new(wt, 5)
        d2=NumericDamage.new(6,5)
        dd=d.copy
        print d2.to_s, "\n"
        print d.to_s, "\n"
        print "copy: ", dd.to_s, "\n"
        weapon=Weapon.new("laser", WeaponType::LASER, 5)
        weapon2=Weapon.new("plasma", WeaponType::PLASMA, 0)
        d.discardWeapon(weapon)
        armas=[weapon, weapon2]
        shield=ShieldBooster.new("escudo", 4.3, 7)
        escudos=[shield]
        dadj=d.adjust(armas,escudos)
        print "discard: ", d, "\n"
        print "Ajustado array: ", dadj.to_s, "\n"
        dadj=d2.adjust(armas,escudos)
        print "Ajustado numero: ", dadj.to_s, "\n"
        print "HasNoEffect false: ", dadj.hasNoEffect, "\n"
    
      sp=SuppliesPackage.new(5.6, 2.3, 6.5)
      ss=SpaceStation.new("Raquel", sp)
      #ss.assignFuelValue(80.3)
      print "SpaceStationormal ", ss.to_s, "\n"
      ss.receiveHangar(hh)
      ss.setPendingDamage(d)
      print "SpaceStation con hangar ", ss.to_s, "\n"
      ss.mountWeapon(0)
      ss.mountShieldBooster(0)
      ss.setPendingDamage(d)
      print ss.to_s, "\n"
      ss.cleanUpMountedItems
      print ss.to_s, "\n"
      #ss.discardWeaponInHangar(0)
      #ss.discardShieldBoosterInHangar(0) 
      ss.discardHangar
      print ss.speed, "\n"
      ss.move
      print "PendingDamage? (false)", ss.validState, "\n"
      print ss.to_s, "\n"
      
    
    li=Loot.new(1,2,3,4,5,false,true)
    puts ss.setLoot(li)
    
  end
  
  def main4
    l=Loot.new(1,2,3,4,5)
    wt=[WeaponType::LASER, WeaponType::MISSILE, WeaponType::PLASMA]
    d=Damage.newSpecificWeapons(wt, 5)
    es=EnemyStarShip.new("hola",1,2, l, d)
    esh=EnemyStarShip.newCopy(es)
    
    if es.receiveShot(6)==ShotResult::RESIST
      puts "resiste"
    else puts "no resiste"
    end
    
    puts "copia: " , esh.to_s
  end
  
  def main5
    puts "Prueba"
    h=Hangar.new(20)
    w1=Weapon.new("prueba", WeaponType::LASER, 5)
    w2=Weapon.new("Misil ACME",WeaponType::MISSILE,1)
    w3=Weapon.new("prueba", WeaponType::PLASMA, 5)
    h.addWeapon(w1)
    h.addWeapon(w2)
    h.addWeapon(w3)
    s1=ShieldBooster.new("Escudo ACME",1.5,2)
    s2=ShieldBooster.new("Escudo normal",3.0,2)
    h.addShieldBooster(s1)
    h.addShieldBooster(s2)
    su=SuppliesPackage.new(20,20,20)
    sp=SpaceStation.new("pepe",su )
    sp.receiveHangar(h)
    
    sp.mountShieldBooster(0)
    sp.mountWeapon(0)
    sp.mountShieldBooster(0)
    sp.mountWeapon(0)
    sp.mountWeapon(0)
    
    for i in 0...3
      puts sp.weapons[i].to_s
    end
    
    for i in 0...2
      puts sp.shieldBoosters[i].to_s
    end
    
    
    
    
    
    puts  sp.pendingDamage.to_s
    
    for i in 0...3
      puts sp.weapons[i].to_s
    end
    
    for i in 0...2
      puts sp.shieldBoosters[i].to_s
    end
    
    weaponsTypes=Array.new  
    weaponsTypes.push(WeaponType::LASER)
    weaponsTypes.push(WeaponType::MISSILE)
    d=Damage.newSpecificWeapons(weaponsTypes,4 )
    puts d.to_s
    
    l=Loot.new(3,2,1,4,1)

    
    enemy=EnemyStarShip.new("pepe",40, 40,l,d )
    
    gameUniverse=GameUniverse.new
    
    array=Array.new

    array.push("inaki")
    array.push("raquel")
    
    gameUniverse.init(array)
    
 
  end
end

t2=TestP2.new
t2.main3
end


