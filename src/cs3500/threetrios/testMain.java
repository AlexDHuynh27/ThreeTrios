package cs3500.threetrios;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class testMain {
  public static void main(String[] args) {
    try {
      new FileReader("Hello.txt");
    }
    catch (FileNotFoundException e) {
      System.out.println("File not found");
    }
  }
}
