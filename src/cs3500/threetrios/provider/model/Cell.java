package cs3500.threetrios.provider.model;

/**
 * Represents a cell, being a position on the Board, that can either be
 * a hole cell or a card cell. Cards can only be placed in empty cells
 * that are also not holes.
 */
public interface Cell {
  /**
   * Check if this cell can have a card placed in it - checks if a card is already present
   * and this is not a hole.
   *
   * @return true if this cell is an empty card cell.
   */
  boolean canBePlaced();

  /**
   * Places a given card in this cell.
   *
   * @param card is the card to be contained in this cell.
   * @throws IllegalStateException if a card cannot exist in this cell.
   */
  void placeCard(Card card);

  /**
   * Returns the card in this cell, if it exists.
   *
   * @throws IllegalStateException if there is no card on this cell.
   */
  Card getCard();

  /**
   * Checks if the given object is an equal cell.
   *
   * @param object is the other object being compared to
   * @return true if both cells are the same type, and have identical fields.
   */
  boolean equals(Object object);

  /**
   * Hashes this current cell object.
   *
   * @return a hashcode based on this cell's fields (if any) or a fixed value for holes.
   */
  int hashCode();

  /**
   * Return a deep copy of this object.
   * @return a deep copy of this object.
   */
  Cell cloneCell();
}
