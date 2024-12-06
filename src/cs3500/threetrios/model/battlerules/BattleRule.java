package cs3500.threetrios.model.battlerules;

import cs3500.threetrios.model.VariantThreeTriosModel;
import cs3500.threetrios.model.card.Card;
import cs3500.threetrios.model.cell.Cell;
import java.util.List;

public interface BattleRule {
    List<List<Cell>> applyRule(Card placedCard, int row, int col, VariantThreeTriosModel model);
}
