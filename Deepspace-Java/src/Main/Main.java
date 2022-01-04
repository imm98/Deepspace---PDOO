/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;
import Controller.Controller;
import View.GUI.MainView;
import deepspace.GameUniverse;
import View.View;
/**
 *
 * @author inaki
 */
public class Main {
    public static void main(String args[]){
        
        GameUniverse game=new GameUniverse();           //Se crea el gameUniverse
  
        View view=new MainView();   //Inicializo la interfaz a la vista principal, con la cual se inicializan la vista de la spaceStation y del enemigo

        Controller controller=new Controller (game, view);
        controller.start();
        
    }
}
