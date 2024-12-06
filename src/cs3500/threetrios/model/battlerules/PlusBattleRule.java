package cs3500.threetrios.model.battlerules;

import cs3500.threetrios.model.VariantThreeTriosModel;
import cs3500.threetrios.model.card.Card;
import cs3500.threetrios.model.card.Direction;
import cs3500.threetrios.model.cell.CardCell;
import cs3500.threetrios.model.cell.Cell;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlusBattleRule extends BattleRuleDecorator {
  public PlusBattleRule(BattleRule decoratedRule) {
    super(decoratedRule);
  }

  @Override
  public List<List<Cell>> applyRule(Card placedCard, int row, int col, VariantThreeTriosModel model) {
    List<List<Cell>> updatedGrid = super.applyRule(placedCard, row, col, model);
    Map<Direction, Integer> sumMap = new HashMap<>();
    for (Direction dir : Direction.values()) {
      Cell adjacentCell = model.getAdjacentCell(row, col, dir);
      if (adjacentCell instanceof CardCell) {
        Card adjacentCard = ((CardCell) adjacentCell).getCard();
        if (adjacentCard != null) {
          int sum = placedCard.getAttack(dir) + adjacentCard.getAttack(dir.getOpposite());
          sumMap.put(dir, sum);
        }
      }
    }

    // Check for matching sums
    for (Direction dir1 : Direction.values()) {
      for (Direction dir2 : Direction.values()) {
        if (dir1 != dir2 && sumMap.containsKey(dir1) && sumMap.containsKey(dir2)) {
          if (sumMap.get(dir1).equals(sumMap.get(dir2))) {
            ((CardCell) updatedGrid.get(row).get(col)).setCard(model.getAdjacentCell(row, col, dir1).getCard());
            ((CardCell) updatedGrid.get(row).get(col)).setCard(model.getAdjacentCell(row, col, dir2).getCard());
          }
        }
      }
    }
    return updatedGrid;
  }
}