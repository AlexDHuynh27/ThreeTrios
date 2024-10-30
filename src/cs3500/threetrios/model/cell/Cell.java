package cs3500.threetrios.model.cell;

import cs3500.threetrios.model.card.Card;
import cs3500.threetrios.model.card.ThreeTriosCard;

public interface Cell {

  /**
   *
   */
  void flipCell();

  /**
   *
   * @param card
   */
  void setCard(ThreeTriosCard card);

  String toString();
}
