# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

class Dice
    
  
  def initialize
    @NHANGARSPROB=0.25
    @NSHIELDSPROB=0.25
    @NWEAPONSPROB=0.33
    @FIRSTSHOTPROB=0.5
    @EXTRAEFFICIENCYPROB=0.8
    @generator=Random.new
  end
  
  def extraEfficiency
    p=@generator.rand(0...1.0)
    if p< @EXTRAEFFICIENCYPROB
      true
    else
      false
    end
  end
  
  def initWithNHangars
    resultado=@generator.rand(0.0...1.0)
    if resultado<@NHANGARSPROB
      0
    else 
      1
    end
  end
  
  def initWithNWeapons
    resultado=@generator.rand(0.0...1.0)
    if resultado<@NWEAPONSPROB
      0
    elsif resultado<2*@NWEAPONSPROB
        1
    else 2
    end
  end
  
  def initWithNShields
    resultado=@generator.rand(0.0...1.0)
    if resultado <@NSHIELDSPROB
      0
    else 1
    end
  end
  
  def whoStarts(nPlayers)
    v=@generator.rand(0...nPlayers)
    v
  end
  
  def firstShot 
    resultado=@generator.rand(0.0...1.0)
    if resultado< @FIRSTSHOTPROB
      GameCharacter::SPACESTATION
    else GameCharacter::ENEMYSTARSHIP
    end
  end
  
  def spaceStationMoves(speed)
    resultado=@generator.rand(0.0...1.0)
    if resultado< speed
      true
    else false
    end
  end
  
  def to_s
    return  "NHANGARSPROB: #{@NHANGARSPROB} NSHIELDSPROB: #{@NSHIELDSPROB}, NWEAPONSPROB: #{@NWEAPONSPROB},
      FIRSTSHOTPROB: #{@FIRSTSHOTPROB}"
  end
end
