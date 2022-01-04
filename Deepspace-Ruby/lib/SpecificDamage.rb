# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require_relative 'Damage'
require_relative 'SpecificDamageToUI'

module Deepspace
class SpecificDamage < Damage
  def initialize (w, s)
    super(s)              #Se hace super para inicializar los shieldBoosters
    @weapons=w
  end
  
  def copy
    SpecificDamage.new(@weapons, nShields)      #El metdodo copy te devuelve un SpecificDamage
  end
  
  def getUIversion
    SpecificDamageToUI.new(self)
  end
  
  def adjust(w,s)
      weaponTypes = []
      w.each{|elem|
      weaponTypes.push(elem.type)
      }
      
      aux = weaponTypes & @weapons    #Te coge los elementos que esán en ambos vectores y te los mete en aux
      for i in 0...aux.length
      k = [weaponTypes.count(aux[i]), @weapons.count(aux[i])].min #Mira a ver si está repetido varias veces en los dos vectores
      for j in 2..k   #Si es así te los introduce en aux
        aux.push(aux[i])
      end
      end
      d=SpecificDamage.new(aux,[@nShields, s.length].min)
      if (d.hasNoEffect)
        d=nil
      end
      d
  end
  
  private
  def arrayContainsType(w,t)          #No se utiliza esta función

    existe=false
    i=0
    while (i<w.size && existe==false)
      if (w.at(i).type==t)
        existe=true
      end
      i=i+1
    end
    
    if existe

      return i-1
    else
      return -1
    end
  end
  
  public
  def discardWeapon(w)
    if !@weapons.empty?
      while @weapons.delete(w.type)==true
      end
    end
  end
  
  def hasNoEffect
    nShields==0 && (@weapons==nil || @weapons.size==0)
  end
  
  def weapons
    @weapons
  end
  
  
  def to_s
    salida="["
    if (!@weapons.empty?)
      for i in 0...@weapons.size
        salida+=@weapons[i].to_s
        salida +=" "
      end
    end
    salida+="]"
    
    return "{SpecificDamage: nShields=#{@nShields}, weapons=#{salida}"
  end
end
end