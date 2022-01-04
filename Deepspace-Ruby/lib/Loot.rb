# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require_relative 'LootToUI'

module Deepspace
class Loot
  def initialize (  nsu, nw, nsh , nh , nm, ef=nil, city=nil)
    @nSupplies=nsu;
    @nWeapons=nw;
    @nShields=nsh;
    @nHangars=nh;
    @nMedals=nm;
    if ef.nil?
      @efficient=false
    else
      @efficient=ef
    end
    if city.nil?
      @SpaceCity=false
    else
      @spaceCity=city
    end
  end
  
  def getUIversion
    LootToUI.new(self)
  end
  
  def efficient
    @efficient
  end
  
  public
  def nSupplies
    @nSupplies
  end
  
  public
  def nWeapons
    @nWeapons
  end
  
  public
  def nShields
    @nShields
  end
  
  public 
  def nHangars
    @nHangars
  end
  
  public 
  def nMedals
    @nMedals
  end
  
  def spaceCity
    @spaceCity
  end
  
  public 
  def to_s
    return "Loot: nMedals=#{@nMedals}, nSupplies=#{@nSupplies}, nWeapons=#{@nWeapons}, 
    nShields=#{@nShields}, nHangars=#{@nHangars}, getEfficient=#{@getEfficient}, spaceCity=#{@spaceCity}"
  end
end
end