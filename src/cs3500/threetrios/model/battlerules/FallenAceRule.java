package cs3500.threetrios.model.battlerules;

import cs3500.threetrios.model.card.Card;
import cs3500.threetrios.model.card.Direction;
import cs3500.threetrios.model.cell.Cell;

/**
 * Fallen Ace battle rule: A '1' on Card A can beat an 'A' on Card B.
 */
public class FallenAceRule implements BattleRule {

  @Override
  public boolean shouldFlip(Card cardA, Cell cellB, Direction direction) {
    if (cellB.getCard() == null) {
      return false;
    }
    Card cardB = cellB.getCard();
    // Check if Card A has a '1' in the attacking direction and Card B has an 'A' in the opposing direction
    int attackA = cardA.getAttack(direction);
    String attackBStr = cardB.attackToString(direction.getOpposite());

    return attackA == 1 && attackBStr.equals("A");
  }
}