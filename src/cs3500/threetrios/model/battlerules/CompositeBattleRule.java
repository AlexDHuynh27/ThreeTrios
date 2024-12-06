package cs3500.threetrios.model.battlerules;

import cs3500.threetrios.model.card.Card;
import cs3500.threetrios.model.card.Direction;
import cs3500.threetrios.model.cell.Cell;

import java.util.ArrayList;
import java.util.List;

/**
 * CompositeBattleRule allows combining multiple BattleRule instances.
 */
public class CompositeBattleRule implements BattleRule {
  private final List<BattleRule> rules;

  public CompositeBattleRule() {
    this.rules = new ArrayList<>();
  }

  /**
   * Adds a BattleRule to the composite.
   *
   * @param rule The BattleRule to add.
   */
  public void addRule(BattleRule rule) {
    this.rules.add(rule);
  }

  /**
   * Retrieves the list of BattleRules.
   *
   * @return List of BattleRule instances.
   */
  public List<BattleRule> getRules() {
    return this.rules;
  }

  @Override
  public boolean shouldFlip(Card cardA, Cell cellB, Direction direction) {
    for (BattleRule rule : rules) {
      if (rule.shouldFlip(cardA, cellB, direction)) {
        return true;
      }
    }
    return false;
  }
}