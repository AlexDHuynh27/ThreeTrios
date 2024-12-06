package cs3500.threetrios.model.battlerules;

import cs3500.threetrios.model.VariantThreeTriosModel;
import cs3500.threetrios.model.card.Card;
import cs3500.threetrios.model.card.Direction;
import cs3500.threetrios.model.cell.CardCell;
import cs3500.threetrios.model.cell.Cell;
import java.util.List;

public class ReverseBattleRule extends BattleRuleDecorator {
  public ReverseBattleRule(BattleRule decoratedRule) {
    super(decoratedRule);
  }

  @Override
  public List<List<Cell>> applyRule(Card placedCard, int row, int col, VariantThreeTriosModel model) {
    List<List<Cell>> updatedGrid = super.applyRule(placedCard, row, col, model);
    for (Direction dir : Direction.values()) {
      Cell adjacentCell = model.getAdjacentCell(row, col, dir);
      if (adjacentCell instanceof CardCell) {
        Card adjacentCard =  adjacentCell.getCard();
        if (adjacentCard != null && placedCard.getAttack(dir) < adjacentCard.getAttack(dir.getOpposite())) {
          updatedGrid.get(row).get(col).setCard(adjacentCard);
        }
      }
    }
    return updatedGrid;
  }
}