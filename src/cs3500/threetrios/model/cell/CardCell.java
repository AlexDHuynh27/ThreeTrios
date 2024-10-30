package cs3500.threetrios.model.cell;

import cs3500.threetrios.model.card.Card;
import cs3500.threetrios.model.card.Direction;
import cs3500.threetrios.model.card.ThreeTriosCard;

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
    this.card = card;
  }

  @Override
  public boolean isEmpty() {
    return card == null;
  }

  /**
   * Compares the
   * @param other
   * @return
   */
  public boolean battleCell(Cell other, Direction dir) {
    if (other.toString().equals("_") || other.toString().equals(toString()) || other.toString().equals(" ")) {
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

  public ThreeTriosCard getCard() {
    return this.card;
  }


  public String toString() {
    if (this.card == null) {
      return "_";
    }
    else {
      return this.card.colorString();
    }
  }
}
