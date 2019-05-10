/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package package2;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import package1.*;

/**
 *
 * @author Jerome
 */
public class Labyrinthe {
  private List<List<Case>> cases;
  private int sizeX = 0;
  private int sizeY = 0;
  private int startX = 0;
  private int startY = 0;
  private int endX = 0;
  private int endY = 0;
  private int posX = 0;
  private int posY = 0;

  public Labyrinthe() {
    try {
      initFromFile(new File("labyrinthe.txt"));
    } catch (FileFormatException ex) {
      System.err.println("File error : " + ex.getMessage());
    }
  }
  
  /** Lit un labyrinthe avec un fichier en paramètre (voir classe File en annexe) au format décrit plus  haut
      Initialise tous les attributs avec les valeurs lues dans le fichier puis instancie la collection de cases et chaque case
      Déclenche l’exception FileFormatException si le fichier ne peut être lu ou si son format est incorrect */
  public void initFromFile(File lab) throws FileFormatException{
    try {
      Scanner sc = new Scanner(lab);
      
      // Load parameters
      String line = sc.nextLine();
      sizeX = Integer.parseInt(line.substring(0, 1));
      sizeY = Integer.parseInt(line.substring(2, 3));
      startX = Integer.parseInt(line.substring(4, 5));
      startY = Integer.parseInt(line.substring(6, 7));
      endX = Integer.parseInt(line.substring(8, 9));
      endY = Integer.parseInt(line.substring(10, 11));
      posX = startX;
      posY = startY;
      
      //Load grid
      cases = new ArrayList<>();
      for (int i = 0 ; i < sizeY ; i++) {
        line = sc.nextLine();
        cases.add(new ArrayList<>());
        for (int j = 0 ; j < sizeX ; j++) {
          String element = line.substring(j, j+1);
          if("_".equals(element)) {
            cases.get(i).add(new CaseTrou(j, i));
          }
          else if ("X".equals(element)) {
            cases.get(i).add(new CaseMur(j, i));
          }
        }
      }
    } catch (FileNotFoundException ex) {
      throw new FileFormatException(ex.getMessage());
    } catch (NumberFormatException ex) {
      throw new FileFormatException("Error in file content : " + ex.getMessage());
    }
  }
  
  /** Tente de bouger le curseur dans la case (x, y) en paramètres. Déclenche l’exception ImpossibleMoveException si la
       case déborde du labyrinthe ou si on ne peut pas aller dans la case : 
       voir la méthode canMoveToCase() de la classe Case */
  public void move (int x, int y) throws ImpossibleMoveException {
    if (x < 0 || x >= sizeX || y < 0 || y >= sizeY) {
      throw new ImpossibleMoveException("Out of maze");
    } else {
      if (!cases.get(y).get(x).canMoveToCase()) {
        throw new ImpossibleMoveException("Move to wall");
      }
    }
    posX = x;
    posY = y;
  }

  /**  Se déplace aléatoirement d’une seule case (direction en x et y aléatoire) de la position courante (posX, posY) sauf si 
        si ce déplacement sort du labyrinthe ou va dans un mur */
  public void autoMove() {
    List<int[]> direction = new ArrayList<>();
    direction.add(new int[]{posX, posY-1});
    direction.add(new int[]{posX+1, posY});
    direction.add(new int[]{posX, posY+1});
    direction.add(new int[]{posX-1, posY});
    
    Random rn = new Random();
    int rnInt;
    boolean cont;
    do {
      cont = false;
      rnInt = rn.nextInt(direction.size());
      try {
        move(direction.get(rnInt)[0], direction.get(rnInt)[1]);
      } catch (ImpossibleMoveException ex) {
        direction.remove(rnInt);
        cont = true;
      }
    } while(cont);
  }

  public int getCurrentPositionX() {
    return posX;
  }

  public int getCurrentPositionY() {
    return posY;
  }
  
  public boolean getWin() {
    return posX == endX && posY == endY;
  }
  
  public void printLab(File labFile) throws FileNotFoundException {
    Scanner sc = new Scanner(labFile);
    sc.nextLine();
    while(sc.hasNextLine()) {
      System.out.println(sc.nextLine());
    }
  }
}
