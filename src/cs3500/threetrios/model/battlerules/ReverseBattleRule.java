package cs3500.threetrios.model.battlerules;

import cs3500.threetrios.model.cell.Cell;
import java.util.List;

public class ReverseBattleRule implements BattleRule {

  @Override
  public List<List<Cell>> battle(List<List<Cell>> grid, int row, int col) {
    return List.of();
  }

  @Override
  public boolean processBattle(Cell attackingCard, Cell defendingCard) {
    return false;
  }

}
