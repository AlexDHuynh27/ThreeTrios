package cs3500.threetrios.model.battlerules;

import cs3500.threetrios.model.cell.Cell;
import java.util.List;

public class PlusBattleRule implements BattleRule {

  @Override
  public List<List<Cell>> battle(List<List<Cell>> grid, Cell attackingCard) {
    return List.of();
  }

  @Override
  public boolean processBattle(List<List<Cell>> grid, Cell attackingCard) {
    return false;
  }
}
