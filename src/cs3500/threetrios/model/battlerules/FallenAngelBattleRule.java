package cs3500.threetrios.model.battlerules;

import cs3500.threetrios.model.cell.Cell;
import java.util.List;

public class FallenAngelBattleRule implements BattleRule {
  BattleRule rule;

  FallenAngelBattleRule(BattleRule rule) {
    this.rule = rule;
  }
  @Override
  public List<List<Cell>> battle(List<List<Cell>> grid, int row, int col) {
    return rule.battle(grid, row, col);
  }

  @Override
  public boolean processBattle(Cell attackingCard, Cell north, Cell south, Cell east, Cell west) {
    return false;
  }
}
