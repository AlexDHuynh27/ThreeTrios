package cs3500.threetrios.model.battlerules;

import cs3500.threetrios.model.cell.Cell;
import java.util.ArrayList;
import java.util.List;

public class FallenAngelBattleRule implements BattleRule {
  BattleRule rule;

  FallenAngelBattleRule(BattleRule rule) {
    this.rule = rule;
  }
  @Override
  public List<List<Cell>> battle(List<List<Cell>> grid, int row, int col) {
    List<Integer> attackingRows = new ArrayList<>();
    List<Integer> attackingCols = new ArrayList<>();
    attackingRows.add(row);
    attackingCols.add(col);


    while (!attackingRows.isEmpty()) {
      Cell north = null ;
      Cell south = null;
      Cell west = null;
      Cell east = null;
      if (attackingCols.get(0) > 0) {
        north = grid.get(attackingRows.get(0)).get(attackingCols.get(0) - 1);
      }
      if (attackingCols.get(0) < grid.get()) {}
    }
  }

  @Override
  public boolean processBattle(Cell attackingCard, Cell defendingCard) {
    return false;
  }
}
