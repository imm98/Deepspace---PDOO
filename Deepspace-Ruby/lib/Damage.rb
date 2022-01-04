# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require_relative'DamageToUI'

module Deepspace
class Damage
  
  
  private
  def initialize(s)
    @nShields=s
  end
 
  
  def getUIversion
    DamageToUI.new(self)
  end
  
  public 
  def discardShieldBooster
    if @nShields >0
      @nShields-=1
    end
  end
  

  
  public 
  def nShields
    @nShields
  end
 
end
end
