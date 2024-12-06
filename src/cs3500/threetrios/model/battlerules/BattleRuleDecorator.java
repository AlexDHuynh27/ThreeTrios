package cs3500.threetrios.model.battlerules;

import cs3500.threetrios.model.ThreeTriosGameModel;
import cs3500.threetrios.model.VariantThreeTriosModel;
import cs3500.threetrios.model.card.Card;
import cs3500.threetrios.model.cell.Cell;
import java.util.ArrayList;
import java.util.List;

public abstract class BattleRuleDecorator implements BattleRule {
  protected final BattleRule decoratedRule;

  public BattleRuleDecorator(BattleRule decoratedRule) {
    this.decoratedRule = decoratedRule;
  }

  @Override
  public List<List<Cell>> applyRule(Card placedCard, int row, int col, VariantThreeTriosModel model) {
    if (decoratedRule != null) {
      return decoratedRule.applyRule(placedCard, row, col, model);
    }
    return model.getGrid();
  }
}