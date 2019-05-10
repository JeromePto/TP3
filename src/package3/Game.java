/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package package3;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import package2.*;

/**
 *
 * @author Jerome
 */
public class Game {
  Labyrinthe lab;
  File labFile;
      
  public Game() {
    lab = new Labyrinthe();
    menu();
  }

  private void menu() {
    boolean cont = true;
    Scanner sc = new Scanner(System.in);
    int choice;
    do {
      System.out.println("1 : Play manual move");
      System.out.println("2 : Play automatic intelligent move (not yet)");
      System.out.println("3 : Play automatic random move");
      System.out.println("4 : Leave");
      try {
        choice = Integer.parseInt(sc.nextLine());
      } catch (NumberFormatException ex) {
        continue;
      }
      switch (choice) {
        case 1:
          try {
            playMan();
          } catch (Exception ex) {
            if (!ex.getMessage().equals("Unable to read the file")) {
              cont = false;
              Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
          } break;
        case 2:
          break;
        case 3:
          break;
        case 4:
          cont = false;
          break;
        default:
          break;
      }
    } while (cont);
  }
  
  private void playMan() throws Exception {
    labChoice();
    lab.initFromFile(labFile);
    printLab();
  }
  
  private void labChoice() throws IOException {
    System.out.print("Name of the maze file : ");
    labFile = new File(new Scanner(System.in).nextLine());
    if (!labFile.canRead()) {
      throw new IOException("Unable to read the file");
    }
  }
  
  private void printLab() throws FileNotFoundException {
    Scanner sc = new Scanner(labFile);
    sc.nextLine();
    while(sc.hasNextLine()) {
      System.out.println(sc.nextLine());
    }
  }
  
  public static void main(String[] args) {
    new Game();
  }
}
