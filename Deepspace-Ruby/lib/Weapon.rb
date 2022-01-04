# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
require_relative 'WeaponType'
require_relative 'WeaponToUI'
require_relative 'CombatElement'

module Deepspace
class Weapon
  include CombatElement
  def initialize(n, t, u)
    @name=n
    @type=t
    @uses=u
  end
  
  def self.newCopy(w)
    nombre=w.name
    tipo=w.type
    usos=w.uses
    Weapon.new(nombre,tipo,usos)
  end
  
  public
  def type
    @type
  end
  
  def getUIversion
    WeaponToUI.new(self)
  end
  
  public
  def uses
    @uses
  end
  
  public
  def name
    @name
  end
  
  public
  def power
    @type.power
  end
  
  public
  def useIt
    if @uses > 0

      @uses-=1
      power
    else 
      1.0
    end
  end
  
  def to_s
    return "Weapon: name=#{@name}, type=#{@type}, uses=#{@uses}"
  end
end
end
