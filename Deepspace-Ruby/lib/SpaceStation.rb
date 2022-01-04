# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require_relative 'SpaceStationToUI'
require_relative 'SpaceFighter'
require_relative 'Transformation'

module Deepspace
class SpaceStation
  include SpaceFighter
  @@MAXFUEL=100
  @@SHIELDLOSSPERUNITSHOT=0.1
  
  def initialize(n, supplies)      #En el initialize se inicializa el nombre de la estación y el supplies Package (ammoPower, fuelUnits y shieldPower)
    na=n
    @name=na
    s=SuppliesPackage.newCopy(supplies) #El método newCopy es un métdodo de clase que te devuelve una copia de supplies(sin igualar direcciones de memoria)
    @ammoPower=s.ammoPower
    @fuelUnits=s.fuelUnits
    @shieldPower=s.shieldPower
    @nMedals=0
    @pendingDamage=nil
    @weapons=Array.new   
    @shieldBoosters=Array.new
    @hangar=nil
  end
  

  
  private
  def assignFuelValue(f)
    if  f <= @@MAXFUEL
      @fuelUnits=f
    else
      @fuelUnits=100
    end
  end
  
  private
  def cleanPendingDamage
    if @pendingDamage.hasNoEffect 
      @pendingDamage=nil
    end
  end
  
  public 
  def cleanUpMountedItems               #Borra armas y escudos sin usos
    i=0
    while i<@shieldBoosters.size
      if @shieldBoosters.at(i).uses==0
        @shieldBoosters.delete_at(i)
      else 
        i=i+1
      end
    end
    
    i=0
    while i<@weapons.size
      if @weapons.at(i).uses==0
        @weapons.delete_at(i)
      else
        i=i+1
      end
    end
  end
  
  public 
  def discardHangar
    @hangar=nil
  end
  
  public 
  def discardShieldBooster(i)

    size=@shieldBoosters.size
    if i>=0 && i<size
     @shieldBoosters.delete_at(i)
      if @pendingDamage!=nil                                #Si hay un daño pendiente se le decrementa al daño un shieldBooster
        @pendingDamage.discardShieldBooster
        cleanPendingDamage                                  #Se comprueba si el daño pendiente hasNoEffect y si es así se pone el dañoPendiente a nulo
      end
    end

  end
  
  public 
  def discardShieldBoosterInHangar(i)
    if i>=0
    if @hangar!=nil
      @hangar.removeShieldBooster(i)
    end
    end
  end
  
  public 
  def discardWeapon(i)
    
    size=@weapons.size
    if i>=0 && i<size
      w=Weapon.newCopy(@weapons.delete_at(i))         #Esto se hace para saber el tipo de arma que hemos eliminado para ver si se corresponde con la del daño pendiente
      if @pendingDamage!=nil
        @pendingDamage.discardWeapon(w)
        cleanPendingDamage
      end
    end
  end
  
  public 
  def discardWeaponInHangar(i)
    if @hangar!=nil
      @hangar.removeWeapon(i)
    end
  end
  
  public 
  def fire              #Se multiplica el factor 1.0 por la potencia de las distintas armas y luego se multiplica por el ammoPower
    size=@weapons.size
    factor=1.0
    for i in 0...size
      w=@weapons.at(i)
      factor*=w.useIt
    end
    @ammoPower*factor
    
  end
  
  public 
  def ammoPower
    @ammoPower
  end
  
  public 
  def fuelUnits
    @fuelUnits
  end
  
  public 
  def hangar
    @hangar
  end
  
  public 
  def name
    @name
  end
  
  public 
  def nMedals
    @nMedals
  end
  
  public 
  def pendingDamage
    @pendingDamage
  end
  
  public 
  def shieldBoosters
    @shieldBoosters
  end
  
  public 
  def shieldPower
    @shieldPower
  end
  
  public 
  def speed
    return @fuelUnits/@@MAXFUEL
  end
  
  public 
  def getUIversion
    SpaceStationToUI.new(self)
  end
  
  public 
  def weapons
    @weapons
  end
  
  public 
  def mountShieldBooster (i)
    if @hangar!=nil
      if (j=@hangar.removeShieldBooster(i))!=nil
        @shieldBoosters.push(j)
      end
    end
  end
  
  public 
  def mountWeapon(i)
    if @hangar!=nil
      if (j=@hangar.removeWeapon(i))!=nil
        @weapons.push(j)
      end
    end
  end
  
  public 
  def move
    if @fuelUnits>speed
      @fuelUnits-=speed
    else 
      @fuelUnits=0
    end
  end
  
  public 
  def protection                #Lo mismo que el fire pero para la defensa
    size=@shieldBoosters.size
    factor=1.0
    for i in 0...size
      s=@shieldBoosters.at(i)
      factor*=s.useIt
    end
    @shieldPower*factor
  end
  
  public 
  def receiveHangar(h)         #Si el hangar es nulo se establece el hangar al que se le pasa como parámetro
    if @hangar==nil
      @hangar=h
    end
  end
  
  public 
  def receiveShieldBooster(s)
    if @hangar!=nil
      sh=ShieldBooster.newCopy(s)
      @hangar.addShieldBooster(sh)            #Solo te los añade si hay espacio disponible
    else
      false
    end
  end
  
  public 
  def receiveShot(shot)                             #Te devuelve un ShotResult
    myProtection=protection
    if myProtection>=shot
      @shieldPower-=@@SHIELDLOSSPERUNITSHOT*shot
      if(0>@shieldPower)
        @shieldPower=0
      end
      
      ShotResult::RESIST
      
    else
      @shieldPower=0
      ShotResult::DONOTRESIST
    end
  end
  
  public 
  def receiveSupplies(s)
    @ammoPower+=s.ammoPower
    @shieldPower+=s.shieldPower
    @fuelUnits+=s.fuelUnits
  end
  
  public 
  def receiveWeapon(w)
    if @hangar!=nil
      we=Weapon.newCopy(w)
      @hangar.addWeapon(we)
    else
      false
    end
  end
  
  public
  def setLoot(loot)
    dealer=CardDealer.instance                        #Tenemos que crear una instancia de CardDealer para recibir el Loot
    
    h=loot.nHangars                                   #El valor del hangar da igual que valga 1 que 30
    if (h>0)
      hangar=Hangar.newCopy(dealer.nextHangar)
      receiveHangar(hangar)
    end
    
    elements=loot.nWeapons
    
    for i in 1..elements
      weap=Weapon.newCopy(dealer.nextWeapon)
      receiveWeapon(weap)
    end
    
    elements=loot.nShields
    
    for i in 1..elements
      sh=ShieldBooster.newCopy(dealer.nextShieldBooster)
      receiveShieldBooster(sh)
    end
    
    medals=loot.nMedals
    @nMedals+=medals
    
    if loot.spaceCity && !loot.efficient
      t=Transformation::SPACECITY
    elsif !loot.spaceCity && loot.efficient
      t=Transformation::GETEFFICIENT
      
    else
      t=Transformation::NOTRANSFORM
    end
    t
  end
  
  public 
  def setPendingDamage(d)
    if !d.nil?
      @pendingDamage=d.adjust(@weapons, @shieldBoosters)
    end
  end
  
  public 
  def validState
    @pendingDamage==nil || @pendingDamage.hasNoEffect
  end
  
  
  public
  def to_s
    
    salida="["
    if @weapons!=nil
    for i in 0 ... @weapons.size
      salida+=@weapons[i].to_s
    end
    end
    salida+="]"

  
    
    salida2="["
    if @shieldBoosters!=nil
    for j in 0 ... @shieldBoosters.size
      salida2+=@shieldBoosters[j].to_s
    end
    end
    salida2+="]"
    
    return "SpaceStation: name=#{@name}, ammoPower=#{@ammoPower}, nMedals=#{@nMedals}, shieldPower=#{@shieldPower},
              pendingDamage=#{@pendingDamage}, 
              weapons=#{salida}, 
              shieldBoosters=#{salida2}, 
              hangar=#{@hangar}
    "
  end
end
end