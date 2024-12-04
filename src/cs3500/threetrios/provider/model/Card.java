package cs3500.threetrios.provider.model;

/**
 * Represents the card used as a building block for the game that players place and own.
 * Each card contains a unique identifiable name, and 4 attack values
 * corresponding to each side of the card (North, South, East, West).
 * Attack values are integers 1-9 and the letter A (representing 10).
 */
public interface Card {
  /**
   * Check if this card is the same as the given object.
   *
   * @param other is some other card to be compared to.
   * @return true if this card is equal in every way to the given card.
   */
  boolean equals(Object other);

  /**
   * Hashes this card object.
   *
   * @return the hashCode of this card object.
   */
  int hashCode();

  /**
   * Helper method to directly compare this Card to a given Card.
   *
   * @param other Card to compare to.
   * @return true if this card is the same as the given Card.
   */
  boolean compare(Card other);

  /**
   * Converts this cards details to string format.
   *
   * @return the contents of this card.
   */
  String toString();

  /**
   * Flips ownership of card.
   */
  void changeOwner();

  /**
   * Gets this card's owner.
   */
  Player getOwner();

  /**
   * Changes (flips) the owner of this card to the given owner.
   *
   * @param owner is the new owner.
   */
  void setOwner(Player owner);

  /**
   * Gets this card's name.
   */
  String getName();

  /**
   * Gets northern attack value of card.
   */
  int getNorth();

  /**
   * Gets eastern attack value of card.
   */
  int getEast();

  /**
   * Gets southern attack value of card.
   */
  int getSouth();

  /**
   * Gets western attack value of card.
   */
  int getWest();
}
