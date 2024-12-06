package cs3500.threetrios.model.battlerules;

import cs3500.threetrios.model.cell.Cell;
import java.util.ArrayList;
import java.util.List;

public class BattleRuleHandler implements BattleRule {
  private final List<BattleRule> rules;

  public BattleRuleHandler() {
    this.rules = new ArrayList<>();
  }

  public void addRule(BattleRule rule) {
    this.rules.add(rule);
  }

  @Override
  public List<List<Cell>> battle(List<List<Cell>> grid, List<Integer> attackingRows,
                                 List<Integer> attackingCols) {
    for (BattleRule rule : rules) {
      grid = rule.battle(grid, attackingRows, attackingCols);
    }
    return grid;
  }

  @Override
  public boolean processBattle(Cell attackingCard, Cell north, Cell south, Cell east, Cell west) {
    for (BattleRule rule : rules) {
      if (rule.processBattle(attackingCard, north, south, east, west)) {
        return true;
      }
    }
    return false;
  }
}