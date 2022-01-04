# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
require_relative 'ShotResult'
require_relative 'EnemyToUI'
require_relative 'SpaceFighter'

module Deepspace
class EnemyStarShip
  include SpaceFighter
  def initialize(n, a, s, l, d)
    
    na=n
    am=a
    sp=s
    lo=l
    da=d
    
    @name=na
    @ammoPower=am
    @shieldPower=sp
    @loot=lo
    @damage=da
    
  end
  
  def self.newCopy(e)
    EnemyStarShip.new(e.name, e.ammoPower, e.shieldPower, e.loot, e.damage)
  end
  
  def getUIversion
    EnemyToUI.new(self)
  end
  
  public
  def fire
    @ammoPower
  end
  
  public 
  def ammoPower
    @ammoPower
  end
  
  public 
  def damage
    @damage
  end
  
  public 
  def loot
    @loot
  end
  
  public 
  def name
    @name
  end
  
  public 
  def shieldPower
    @shieldPower
  end
  
  public 
  def protection
    @shieldPower
  end
  
  public 
  def receiveShot(shot)
    if shot > @shieldPower
      ShotResult::DONOTRESIST
    else
      ShotResult::RESIST
    end
  end
  
  
  public 
  def to_s
     return "EnemyStarShip{ammoPower=#{@ammoPower}, name=#{@name}, shieldPower=#{@shieldPower}, loot=#{@loot}, damage=#{@damage}";
  end
end
end