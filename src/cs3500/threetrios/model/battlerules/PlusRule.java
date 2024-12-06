package cs3500.threetrios.model.battlerules;

import cs3500.threetrios.model.card.Card;
import cs3500.threetrios.model.card.Direction;
import cs3500.threetrios.model.cell.Cell;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Plus battle rule: If at least two adjacent cards have the same sum of attack values in opposing directions,
 * flip opponent's cards involved in the sum.
 */
public class PlusRule implements BattleRule {

  @Override
  public boolean shouldFlip(Card cardA, Cell cellB, Direction direction) {
    // Not directly used; handled within the model with contextual information
    return false;
  }

  /**
   * Applies the PlusRule to the given card and its adjacent cells.
   *
   * @param cardA         The attacking card.
   * @param adjacentCells List of adjacent cells with their directions.
   * @return List of cells to flip.
   */
  public List<Cell> applyPlusRule(Card cardA, List<AdjacentCell> adjacentCells) {
    Map<Integer, Integer> sumCount = new HashMap<>();

    // Calculate sums of attack values
    for (AdjacentCell adj : adjacentCells) {
      Card cardB = adj.cell.getCard();
      int attackA = cardA.getAttack(adj.direction.getOpposite());
      int attackB = cardB.getAttack(adj.direction.getOpposite());
      int sum = attackA + attackB;
      sumCount.put(sum, sumCount.getOrDefault(sum, 0) + 1);
    }

    // Identify sums with at least two occurrences
    List<Integer> targetSums = new ArrayList<>();
    for (Map.Entry<Integer, Integer> entry : sumCount.entrySet()) {
      if (entry.getValue() >= 2) {
        targetSums.add(entry.getKey());
      }
    }

    // Collect cells to flip based on target sums
    List<Cell> cellsToFlip = new ArrayList<>();
    for (AdjacentCell adj : adjacentCells) {
      Card cardB = adj.cell.getCard();
      int attackA = cardA.getAttack(adj.direction.getOpposite());
      int attackB = cardB.getAttack(adj.direction.getOpposite());
      int sum = attackA + attackB;
      if (targetSums.contains(sum) && cardB.getColor() != cardA.getColor()) {
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