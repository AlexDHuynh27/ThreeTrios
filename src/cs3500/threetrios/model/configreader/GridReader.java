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

/**
 * A class that can read configuration files and produce a List of Lists of Cells.
 */
public class GridReader {

  /**
   * Reads a configuration file with a specified format and produces a List of Lists of Cells
   * that represents a Grid. The first line of the configuration file should be broken into two
   * parts seperated by a single space. The first part of the first line should represent the
   * amount of rows in the List of Lists and the second part should represent the amount of columns
   * in the List of Lists. The second line of the configuration file and onwards represents
   * the grid, where each row is seperated by a new line. Each character on the second line and
   * onwards must be a "X" or a "C". "X" represents a Hole and "C" represents a CardCell. Any
   * lines after the row size will be ignored as will any characters after the given column size
   * @param filename The name and path(if necessary) of the configuration file.
   * @return A List of Lists of Cells based on the given grid configuration file and the specified
   * format.
   * @throws IllegalArgumentException If the configuration file doesn't match the specified format.
   * @throws IllegalArgumentException if the card in the deck don't have unique names.
   */
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
