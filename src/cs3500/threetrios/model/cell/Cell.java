package cs3500.threetrios.model.cell;

import cs3500.threetrios.model.card.Card;
import cs3500.threetrios.model.card.ThreeTriosCard;

public interface Cell {

  /**
   * Flips the color of the cell, "R" -> "B", and "B" -> "R".
   * @throws IllegalStateException if card is null.
   * @throws IllegalCallerException if called by Hole as Hole should never have any cards
   */
  void flipCell();

  /**
   * Sets a card in a specified Cell.
   * @throws IllegalStateException if card is null.
   * @throws IllegalCallerException if called by a Hole cell as Hole cell should never have a card.
   */
  void setCard(ThreeTriosCard card);

  /**
   * @return "R" for Red card in CardCell, "B" for Blue card in CardCell, "_" for Null card in
   * CardCell, and " " for Hole cell.
   */
  String toString();

  /**
   * Checks if the cell empty, Hole cell is never empty.
   */
  boolean isEmpty();
}
