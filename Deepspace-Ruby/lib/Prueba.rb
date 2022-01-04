# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
require_relative 'NumericDamage'
require_relative 'SpecificDamage'
require_relative 'Weapon'
require_relative 'ShieldBooster'
require_relative 'WeaponType'
require_relative 'Hangar'
require_relative 'SpaceStation'
require_relative 'EnemyStarShip'
require_relative 'SuppliesPackage'
require_relative 'GameUniverse'
require_relative 'PowerEfficientSpaceStation'
require_relative 'SpaceCity'

module Deepspace
class Prueba
def main
w=[]
w.push(WeaponType::LASER, WeaponType::MISSILE, WeaponType::MISSILE)   #Se crea el specificDamage
sd=SpecificDamage.new(w, 5)
puts sd.to_s
sd2=sd.copy
puts "COPIA: "+sd2.to_s
weapons=[]
w1=Weapon.new("prueba", Deepspace::WeaponType::LASER, 0)
w2=Weapon.new("prueba2", Deepspace::WeaponType::MISSILE, 2)
w3=Weapon.new("prueba2", Deepspace::WeaponType::LASER, 2)
weapons.push(w1)
weapons.push(w2)
weapons.push(w3)
s=[]
s.push(ShieldBooster.new("1", 3, 4),ShieldBooster.new("2", 3, 4))
puts sd.adjust(weapons, s).to_s
h=Hangar.new(8)
h.addShieldBooster(ShieldBooster.new("1", 3, 4))
h.addShieldBooster(ShieldBooster.new("2", 3, 4))
puts h.to_s

nd=NumericDamage.new(8,2)
puts nd.to_s
nd2=nd.copy
puts "COPIA: "+nd2.to_s
puts nd.adjust(weapons, s).to_s

ss=SpaceStation.new("estacion", SuppliesPackage.new(4.3,2.5,1.2))
puts "Fire: "+ss.fire.to_s
puts ss.protection
puts ss.receiveShot(4)
l=Loot.new(7, 6, 5, 4, 3, true, false)
puts ss.setLoot(l)
ss.mountWeapon(0);
puts ss.to_s

pe=PowerEfficientSpaceStation.new(ss)
puts pe.to_s
puts "Fire: "+pe.fire.to_s
pe.setLoot(l)
puts pe.to_s

ss2=SpaceStation.new("estacion2", SuppliesPackage.new(4.3,2.5,5.2))
ss3=SpaceStation.new("estacion3", SuppliesPackage.new(4.3,2.5,3.2))
ss3.receiveHangar(Hangar.new(4))
ss3.receiveWeapon(Weapon.new("victor", WeaponType::LASER, 3))
ss3.mountWeapon(0)

sss=[ss2,ss3]
sc=SpaceCity.new(ss,sss)
puts sc.to_s

puts "Fire "+sc.fire.to_s
sc.setLoot(l)

gu=GameUniverse.new
names=["estacion1", "2", "3"]
gu.init(names)
gu.createSpaceCity
puts gu.to_s
lootsc=Loot.new(1,1,1,1,1,false,true)
lootes=Loot.new(1,1,1,1,1,true,false)
enemysc=EnemyStarShip.new("sc", 1, 1, lootsc, nd)
enemyes=EnemyStarShip.new("es", 1, 1, lootes, sd)
scity=gu.combatGo(ss2,enemysc)
puts "SpaceCity? "
puts scity
puts gu.to_s
seff=gu.combatGo(ss3,enemyes)
puts "Efficient? "
puts seff
puts gu.to_s

loot=Loot.new(1,2,3,4,5)
loot2=Loot.new(1,2,3,4,5,false,true)
puts loot.to_s
puts loot2.to_s

end
end

p=Prueba.new()
p.main

end

