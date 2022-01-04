/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;
import Controller.Controller;
import java.util.ArrayList;
/**
 *
 * @author inaki
 */
public interface View {
    public void setController (Controller c);
    public void updateView();
    public void showView();
    public ArrayList<String> getNames();
    public boolean confirmExitMessage();
    public void MensajePerder();
    public void MensajeGanar();
    public void MensajeEscapar();
    public void MensajeSpaceCity();
}
