package cs3500.threetrios.model.battlerules;

import cs3500.threetrios.model.VariantThreeTriosModel;
import cs3500.threetrios.model.card.Card;
import cs3500.threetrios.model.card.Direction;
import cs3500.threetrios.model.cell.CardCell;
import cs3500.threetrios.model.cell.Cell;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SameBattleRule extends BattleRuleDecorator {
  public SameBattleRule(BattleRule decoratedRule) {
    super(decoratedRule);
  }

  @Override
  public List<List<Cell>> applyRule(Card placedCard, int row, int col, VariantThreeTriosModel model) {
    List<List<Cell>> updatedGrid = super.applyRule(placedCard, row, col, model);
    Map<Direction, Integer> valueMap = new HashMap<>();
    for (Direction dir : Direction.values()) {
      Cell adjacentCell = model.getAdjacentCell(row, col, dir);
      if (adjacentCell instanceof CardCell) {
        Card adjacentCard = ((CardCell) adjacentCell).getCard();
        if (adjacentCard != null) {
          valueMap.put(dir, adjacentCard.getAttack(dir.getOpposite()));
        }
      }
    }

    // Check for matching values
    for (Direction dir1 : Direction.values()) {
      for (Direction dir2 : Direction.values()) {
        if (dir1 != dir2 && valueMap.containsKey(dir1) && valueMap.containsKey(dir2)) {
          if (valueMap.get(dir1).equals(valueMap.get(dir2))) {
            ((CardCell) updatedGrid.get(row).get(col)).setCard(model.getAdjacentCell(row, col, dir1).getCard());
            ((CardCell) updatedGrid.get(row).get(col)).setCard(model.getAdjacentCell(row, col, dir2).getCard());
          }
        }
      }
    }
    return updatedGrid;
  }
}