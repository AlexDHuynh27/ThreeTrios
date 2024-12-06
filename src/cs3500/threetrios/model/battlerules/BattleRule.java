package cs3500.threetrios.model.battlerules;

import cs3500.threetrios.model.cell.Cell;
import java.util.List;

public interface BattleRule {

  List<List<Cell>> battle(List<List<Cell>> grid, List<Integer> attackingRows,
                          List<Integer> attackingCols);

  boolean processBattle(Cell attackingCard, Cell north, Cell south, Cell east, Cell west);


}
