/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package package1;

/**
 *
 * @author Jerome
 */
public interface Case {

  public int getPositionX(); // retourne la position en X de la case

  public int getPositionY(); // retourne la position en Y de la case

  public boolean canMoveToCase(); // indique s’il est possible ou non d’aller dans la case

}
