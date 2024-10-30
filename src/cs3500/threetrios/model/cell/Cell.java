package cs3500.threetrios.model.cell;

import cs3500.threetrios.model.card.Card;
import cs3500.threetrios.model.card.Direction;
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

  ThreeTriosCard getCard();

  boolean battleCell(Cell other, Direction dir);

  String toString();

  /**
   * Checks if the cell empty
   */
  boolean isEmpty();
}
