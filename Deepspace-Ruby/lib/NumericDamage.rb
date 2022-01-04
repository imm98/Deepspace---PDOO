# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require_relative 'Damage'
require_relative 'NumericDamageToUI'

module Deepspace
class NumericDamage < Damage
  def initialize (w, s)
    super(s)
    @nWeapons=w
  end
  
  def copy
    NumericDamage.new(@nWeapons, nShields)
  end
  
  
  def getUIversion
    NumericDamageToUI.new(self)
  end
    def adjust(w,s)
      puts to_s
      if w!=nil
        wsize=w.size
        if (w.size>@nWeapons)
          wsize=@nWeapons
        end
      else
        wsize=0
      end
      
      if s!=nil
        ssize=s.size
        if s.size>nShields
          ssize=nShields
        end
      else
        ssize=0
      end
      d=NumericDamage.new(wsize,ssize )
      if d.hasNoEffect
        d=nil
      end
      d
    end
    
    def discardWeapon(w)
      if @nWeapons>0
          @nWeapons-=1
      end
    end
    
    def hasNoEffect
       @nWeapons==0 && nShields==0
    end
    
    def nWeapons
      @nWeapons
    end
    
    def to_s
      "{Damage: nShields=#{nShields}, nWeapons=#{@nWeapons}}"
    end
end
end