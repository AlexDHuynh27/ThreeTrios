package cs3500.threetrios.model.battlerules;

import cs3500.threetrios.model.cell.Cell;
import java.util.List;

public class FallenAceBattleRule implements BattleRule {
  private final BattleRule baseRule;

  public FallenAceBattleRule(BattleRule baseRule) {
    this.baseRule = baseRule;
  }

  @Override
  public List<List<Cell>> battle(List<List<Cell>> grid, List<Integer> attackingRows,
                                 List<Integer> attackingCols) {
    return baseRule.battle(grid, attackingRows, attackingCols);
  }

  @Override
  public boolean processBattle(Cell attackingCard, Cell north, Cell south, Cell east, Cell west) {
    // Implement Fallen Angel logic: A 1 beats an Ace
    return false; // Implement actual logic
  }
}