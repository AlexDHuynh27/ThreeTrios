package cs3500.threetrios.model.cell;

import cs3500.threetrios.model.card.Card;
import cs3500.threetrios.model.card.Direction;
import cs3500.threetrios.model.card.ThreeTriosCard;

/**
 * Represents a Cell that can have a card within it, CardCell can either have a null card (Empty
 * CardCell) or nonnull card (Filled CardCell).
 */
public class CardCell implements Cell{
  private ThreeTriosCard card;

  @Override
  public void flipCell() {
    if (this.card == null) {
      throw new IllegalStateException("The card in the cell is null.");
    }
    this.card.flip();
  }

  @Override
  public void setCard(ThreeTriosCard card) {
    if (this.card != null) {
      throw new IllegalStateException("The card in the cell is already set.");
    }
    if (card == null) {
      throw new IllegalArgumentException("The card cannot be null");
    }
    this.card = card;
  }

  @Override
  public boolean isEmpty() {
    return card == null;
  }

  @Override
  public boolean battleCell(Cell other, Direction dir) {
    if (other.isEmpty() || other.toString().equals(toString()) || other.toString().equals(" ")) {
      return false;
    }
    switch(dir) {
      case NORTH:
        return this.card.getAttack(dir) > other.getCard().getAttack(Direction.SOUTH);
      case SOUTH:
        return this.card.getAttack(dir) > other.getCard().getAttack(Direction.NORTH);
      case EAST:
        return this.card.getAttack(dir) > other.getCard().getAttack(Direction.WEST);
      case WEST:
        return this.card.getAttack(dir) > other.getCard().getAttack(Direction.EAST);
      default:
        throw new IllegalStateException("Unexpected value: " + dir);
    }
  }

  /**
   * Returns the card of this cell.
   * @return The card of this cell.
   */
  public ThreeTriosCard getCard() {
    return this.card;
  }

  /**
   * Returns the string representation of this cell.
   * @return The String representation of this cell. "_" if the cell doesn't have a card. Or the String CardColor
   * representation of this cell's card
   */
  public String toString() {
    if (this.card == null) {
      return "_";
    }
    else {
      return this.card.colorString();
    }
  }
}
