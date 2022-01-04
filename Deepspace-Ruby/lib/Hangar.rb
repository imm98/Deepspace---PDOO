# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
require_relative 'HangarToUI'
require_relative 'Weapon'
require_relative 'ShieldBooster'
module Deepspace
class Hangar
  
  
  def initialize(capacity)
    c=capacity
    @maxElements=c
    @weapons=Array.new
    @shieldBoosters=Array.new
  end
  
  def self.newCopy(h)
    h1=Hangar.new(h.maxElements)
    for i in 0...h.shieldBoosters.size
      s=ShieldBooster.newCopy(h.shieldBoosters[i])
      h1.addShieldBooster(s)
    end
    for i in 0...h.weapons.size
      w=Weapon.newCopy(h.weapons[i])
      h1.addWeapon(w)
    end
    h1
  end
  
  def getUIversion
    HangarToUI.new(self)
  end
  
  private
  def spaceAvailable
    return @weapons.size+@shieldBoosters.size < @maxElements
  end
  
  public 
  def addWeapon(w)
    if spaceAvailable==true
      we=Weapon.newCopy(w)
      @weapons.push(we)
      true
    else 
      false
    end
  end
  
  public 
  def addShieldBooster(s)
    if spaceAvailable==true
      sa=ShieldBooster.newCopy(s)
      @shieldBoosters.push(sa)
      true
    else
      false
    end
  end
  
  public 
  def maxElements
    @maxElements
  end
  
  public 
  def shieldBoosters
    @shieldBoosters
  end
  
  public 
  def weapons
    @weapons
  end
  
  public 
  def removeShieldBooster(s)
    @shieldBoosters.delete_at(s)
  end
  
  public 
  def removeWeapon(w)
    @weapons.delete_at(w)
  end
  
  def to_s
    if(@weapons!=nil)
      out="["
      for i in 0...@weapons.size
        out+=@weapons[i].to_s
        if i<@weapons.size-1
          out+=","
        end
      end
      out+="]"
    else
      out="[]"
    end
    if(@shieldBoosters!=nil)
      out2="["
      for i in 0...@shieldBoosters.size
        out2+=@shieldBoosters[i].to_s
        if i<@shieldBoosters.size-1
          out2+=","
        end
      end
      out2+="]"
    else
      out2="[]"
    end
    return "Hangar{ maxElements=#{@maxElements}, weapons={"+out+"},shieldBoosters="+out2+" }"
  end
end
end


