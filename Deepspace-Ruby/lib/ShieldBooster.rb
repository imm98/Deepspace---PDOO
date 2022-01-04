# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require_relative 'ShieldToUI'
require_relative 'CombatElement'


module Deepspace
class ShieldBooster
  include CombatElement
  attr_reader :name
  
  def initialize(n, b, u)
    @name=n
    @boost=b
    @uses=u
  end
  
  
  
  def self.newCopy(s)
    nombre=s.name
    boostt=s.boost
    usess=s.uses
    ShieldBooster.new(s.name, s.boost, s.uses)
  end
  
  def getUIversion
    ShieldToUI.new(self)
  end
  
  public
  def boost
    @boost
  end
  
  public
  def uses
    @uses
  end
  
  public
  def useIt
    if (@uses > 0)
      @uses=@uses-1
      boost
    else 
      resultado=1.0
      resultado
    end
  end
  

  
  
  
  def to_s
    return "ShieldBooster: name=#{@name}, boost=#{@boost}, uses=#{@uses}"
  end
end
end