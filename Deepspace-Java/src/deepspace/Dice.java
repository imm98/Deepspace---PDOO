/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deepspace;

import java.util.Random;

/**
 *
 * @author inaki
 */
public class Dice {
  private final float NHANGARSPROB;
  private final float NSHIELDSPROB;
  private final float NWEAPONSPROB;
  private final float FIRSTSHOTPROB;
  private final float EXTRAEFFICIENCYPROB;
  private Random generator;
    
  Dice (){
      NHANGARSPROB=0.25f;
      NSHIELDSPROB=0.25f;
      NWEAPONSPROB=0.33f;
      FIRSTSHOTPROB=0.5f;
      EXTRAEFFICIENCYPROB=0.8f;
      generator=new Random();      
  }
  
  public int initWithNHangars (){
      float r=generator.nextFloat();
      if (r <=NHANGARSPROB)
          return 0;
      else return 1;
  }
  
  public int initWithNWeapons (){
      float r=generator.nextFloat();
      if (r<=NWEAPONSPROB)
          return 1;
      else if (r<=2*NWEAPONSPROB)
          return 2;
      else return 3;
  }
  
  public int initWhithNShields (){
      float r=generator.nextFloat();
      if (r<=NSHIELDSPROB)
          return 0;
      else return 1;
  }
  
  public int whoStarts(int nPlayers){
      int r=generator.nextInt(nPlayers);
      return r;
  }
  
  public GameCharacter firstShot(){
      float r=generator.nextFloat();
      if ( r <= FIRSTSHOTPROB)
        return GameCharacter.SPACESTATION ;
      else return GameCharacter.ENEMYSTARSHIP;
  }
  
  public boolean spaceStationMoves(float speed){
      float r=generator.nextFloat();
      
      return(r < speed);
 
  }
  
  public boolean extraEfficiency(){
      float r=generator.nextFloat();
      return r<=EXTRAEFFICIENCYPROB;
  }

    @Override
    public String toString() {
        return "Dice{" + "generator=" + generator + '}';
    }
  

  
  
  
  
}
