package cs3500.threetrios.model.cell;

import cs3500.threetrios.model.card.Card;
import cs3500.threetrios.model.card.Direction;

/**
 * Represents a cell in the ThreeTrios game board. A cell can either be a CardCell, which holds a
 * card, or a Hole, which cannot hold any cards. This interface defines the basic operations
 * available for manipulating cells such as flipping, setting, and getting cards among others.
 */
public interface Cell {

  /**
   * Flips the color of the cell, "R" -> "B", and "B" -> "R".
   *
   * @throws IllegalStateException  if card is null.
   * @throws IllegalCallerException if called by Hole as Hole should never have any cards
   */
  void flipCell();

  /**
   * Gets the ThreeTriosCard of this cell. If this cell is a hole or if the cell is empty, it will
   * return null.
   *
   * @return The ThreeTriosCard of this cell or null.
   */
  Card getCard();

  /**
   * Sets a card in a specified Cell.
   *
   * @throws IllegalStateException  if card is null.
   * @throws IllegalCallerException if called by a Hole cell as Hole cell should never have a card.
   */
  void setCard(Card card);

  /**
   * Compares an attack value of this Cell's card to an attack value of other's card based on the
   * given direction.
   * North attacks South, West attacks East and vice versa for both.
   *
   * @param other The cell that is being attacked
   * @param dir   The direction of this Cell's card that is attacking
   * @return True if this cell's attack value is greater in the direction it is attacking. False
   *         if the other cell's attack value is higher in the direction it is being attacked if
   *         the other cell is empty, if the other cell is the same color, or if the other or
   *         this cell is a hole.
   */
  boolean battleCell(Cell other, Direction dir);


  /**
   * toString method for a given Cell.
   *
   * @return "R" for Red card in CardCell, "B" for Blue card in CardCell, "_" for Null card in
   *         CardCell, and " " for Hole cell.
   */
  String toString();

  /**
   * Checks if the cell empty, Hole cell is never empty.
   */
  boolean isEmpty();
}
