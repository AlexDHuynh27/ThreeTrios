package cs3500.threetrios.model.configreader;

import cs3500.threetrios.model.card.Card;
import cs3500.threetrios.model.card.ThreeTriosCard;
import cs3500.threetrios.model.cell.CardCell;
import cs3500.threetrios.model.cell.Cell;
import cs3500.threetrios.model.cell.Hole;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GridReader {
  public static List<List<Cell>> getGridFromConfig(String filename) {
    List<List<Cell>> grid = new ArrayList<>();
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
      for (int i = 0; i < numRows; i++) {
        grid.add(new ArrayList<>());
        for (int j = 0; j < numCols; j++) {
          if (lines[i + 1].charAt(j) == 'X') {
            grid.get(i).add(new Hole());
          }
          else if (lines[i + 1].charAt(j) == 'C') {
            grid.get(i).add(new CardCell());
          }
          else {
            throw new IllegalArgumentException("Invalid Grid Format");
          }
        }
      }
      return grid;
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
  }
}
