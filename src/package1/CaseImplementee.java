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
public class CaseImplementee implements Case {

  protected int posX;
  protected int posY;
  protected boolean vasy;

  CaseImplementee(int posX, int posY, boolean vasy) {
    this.posX = posX;
    this.posY = posY;
    this.vasy = vasy;
  }

  public CaseImplementee(int posX, int posY) {
    this(posX, posY, false);
  }

  @Override
  public int getPositionX() {
    return posX;
  }

  @Override
  public int getPositionY() {
    return posY;
  }

  @Override
  public boolean canMoveToCase() {
    return vasy;
  }

}
