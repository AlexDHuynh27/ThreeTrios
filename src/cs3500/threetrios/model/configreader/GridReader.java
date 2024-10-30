package cs3500.threetrios.model.configreader;

import cs3500.threetrios.model.card.ThreeTriosCard;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GridReader {
  public static List<List<ThreeTriosCard>> getGridFromConfig(String filename) {
    List<List<ThreeTriosCard>> grid = new ArrayList<List<ThreeTriosCard>>();
    try {
      FileReader fr = new FileReader(filename);
      StringBuilder fileContent = new StringBuilder();
      int nextChar;
      while ((nextChar = fr.read()) != -1) {
        fileContent.append((char) nextChar);
      }
      String[] lines = fileContent.toString().split("\n");
      String[] gridSize = lines[0].split("\\s");
      int numRows = Integer.parseInt(gridSize[0]);
      int numCols = Integer.parseInt(gridSize[1]);
      for (int i = 1; i < lines.length; i++) {

      }

    }
    catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File not found");
    }
    catch (IOException e) {
      throw new RuntimeException(e);
    }
    catch (NumberFormatException e) {
      throw new IllegalStateException("Invalid grid format");
    }

    return grid;
  }
}
