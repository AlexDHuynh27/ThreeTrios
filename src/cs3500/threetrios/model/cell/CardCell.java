package cs3500.threetrios.model.cell;

import cs3500.threetrios.model.card.Card;

public class CardCell {
  Card card;

  /**
   * Flips the
   */
  public void flipCell() {
    if (this.card == null) {
      throw new IllegalStateException("The card in the cell is null.");
    }
    this.card.flip();
  }

  public void setCard(Card card) {
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

  }
}
