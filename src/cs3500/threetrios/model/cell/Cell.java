package cs3500.threetrios.model.cell;

import cs3500.threetrios.model.card.Card;
import cs3500.threetrios.model.card.Direction;
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
   * Gets the ThreeTriosCard of this cell. If this cell is a hole or if the cell is empty, it will
   * return null.
   * @return The ThreeTriosCard of this cell or null.
   */
  ThreeTriosCard getCard();

  /**
   * Given a direction and a
   * @param other The cell that is being attacked.
   * @param dir The direction in which this cell is attacking the other cell.
   * @return True if this cell won the battle. False if this cell can't battle, if this cell has the same color as
   * the cell it is attacking, or if the cell that is being attacked can't battle.
   */
  boolean battleCell(Cell other, Direction dir);


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
