package cs3500.threetrios.model.battlerules;

import cs3500.threetrios.model.cell.Cell;
import java.util.List;

public class StandardBattleRule implements BattleRule {

  @Override
  public List<List<Cell>> battle(List<List<Cell>> grid, List<Integer> attackingRows, List<Integer> attackingCols) {
    return null;
  }

  @Override
  public boolean processBattle(Cell attackingCard, Cell north, Cell south, Cell east, Cell west) {
    return false;
  }


}
