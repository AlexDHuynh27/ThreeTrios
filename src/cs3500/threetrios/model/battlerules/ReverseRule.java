package cs3500.threetrios.model.battlerules;

import cs3500.threetrios.model.card.Card;
import cs3500.threetrios.model.card.Direction;
import cs3500.threetrios.model.cell.Cell;

/**
 * Reverse battle rule: Flip Card B if Card A's attack is less than Card B's opposing attack.
 */
public class ReverseRule implements BattleRule {

  @Override
  public boolean shouldFlip(Card cardA, Cell cellB, Direction direction) {
    if (cellB.getCard() == null) {
      return false;
    }
    Card cardB = cellB.getCard();
    // Reverse condition: A's attack < B's opposing attack
    int attackA = cardA.getAttack(direction);
    Direction opposingDirection = direction.getOpposite();
    int attackB = cardB.getAttack(opposingDirection);
    return attackA < attackB;
  }
}