package cs3500.threetrios.model.cell;

import cs3500.threetrios.model.card.Card;
import cs3500.threetrios.model.card.ThreeTriosCard;

public class CardCell implements Cell{
  ThreeTriosCard card;

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

  /**
   * Compares the
   * @param other
   * @return
   */
  public boolean battleCell(Cell other) {
    return false;
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
