package chem_lab;

import java.awt.*;
import java.io.FileNotFoundException;

import javax.swing.*;

public class ChemLabGraphics {
  public static void main(String[] args) throws FileNotFoundException {
    int w = 800;
    int h = 535;
    JFrame frame = new JFrame("Chemistry Lab");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().add(new ChemLabPanel(w, h));
    frame.pack();
    frame.setVisible(true);
  }
}
