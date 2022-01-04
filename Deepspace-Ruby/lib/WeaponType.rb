# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
module Deepspace
module WeaponType
    
  class Type
    def initialize(pow)
      @power=pow
    end
    
    
    def power
      @power
    end
    
      def to_s
        out=""
        if @power==2.0
        out+="LASER"
        end
        if @power==3.0
        out+="MISSILE"
        end
        if @power==4.0
        out+="PLASMA"
        end
        out
        end
    
  end
  

  
  LASER=Type.new (2.0);
  MISSILE=Type.new (3.0);
  PLASMA=Type.new (4.0);
end
end
