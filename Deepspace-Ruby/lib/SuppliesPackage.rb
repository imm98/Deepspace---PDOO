# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
module Deepspace
class SuppliesPackage
  def initialize(a, f, s)
    @ammoPower=a;
    @fuelUnits=f;
    @shieldPower=s;
  end
  
  def self.newCopy(sp)
    a=sp.ammoPower
    f=sp.fuelUnits
    s=sp.shieldPower
    SuppliesPackage.new(a, f, s)
  end
  
  def ammoPower
     @ammoPower
  end
  
  def fuelUnits
    @fuelUnits
  end
  
  def shieldPower
    @shieldPower
  end
  
  def to_s
    return "SuppliesPackage: ammoPower=#{@ammoPower}, fuelUnits=#{@fuelUnits}"
  end
end
end