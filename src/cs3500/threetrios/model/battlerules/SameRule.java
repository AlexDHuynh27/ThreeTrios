package cs3500.threetrios.model.battlerules;

import cs3500.threetrios.model.card.Card;
import cs3500.threetrios.model.card.Direction;
import cs3500.threetrios.model.cell.Cell;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Same battle rule: If at least two adjacent cards share the same value in the opposing direction,
 * flip opponent's cards with that value.
 */
public class SameRule implements BattleRule {

  @Override
  public boolean shouldFlip(Card cardA, Cell cellB, Direction direction) {
    // Not directly used; handled within the model with contextual information
    return false;
  }

  /**
   * Applies the SameRule to the given card and its adjacent cells.
   *
   * @param cardA         The attacking card.
   * @param adjacentCells List of adjacent cells with their directions.
   * @return List of cells to flip.
   */
  public List<Cell> applySameRule(Card cardA, List<AdjacentCell> adjacentCells) {
    Map<String, Integer> valueCount = new HashMap<>();

    // Count occurrences of opposing values
    for (AdjacentCell adj : adjacentCells) {
      Card cardB = adj.cell.getCard();
      String oppValue = cardB.attackToString(adj.direction.getOpposite());
      valueCount.put(oppValue, valueCount.getOrDefault(oppValue, 0) + 1);
    }

    // Identify values with at least two occurrences
    List<String> targetValues = new ArrayList<>();
    for (Map.Entry<String, Integer> entry : valueCount.entrySet()) {
      if (entry.getValue() >= 2) {
        targetValues.add(entry.getKey());
      }
    }

    // Collect cells to flip based on target values
    List<Cell> cellsToFlip = new ArrayList<>();
    for (AdjacentCell adj : adjacentCells) {
      Card cardB = adj.cell.getCard();
      String oppValue = cardB.attackToString(adj.direction.getOpposite());
      if (targetValues.contains(oppValue) && cardB.getColor() != cardA.getColor()) {
        cellsToFlip.add(adj.cell);
      }
    }

    return cellsToFlip;
  }

  /**
   * Helper class to associate a cell with its direction relative to the attacking card.
   */
  public static class AdjacentCell {
    public final Cell cell;
    public final Direction direction;

    public AdjacentCell(Cell cell, Direction direction) {
      this.cell = cell;
      this.direction = direction;
    }
  }
}