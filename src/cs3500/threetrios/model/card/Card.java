package cs3500.threetrios.model.card;

/**
 * Represents a generic card for ThreeTrios.
 */
public interface Card {

  /**
   * Returns the name of this card.
   *
   * @return The name of this card.
   */
  String getName();

  /**
   * Returns the attack value based on the given direction.
   *
   * @param dir The direction of the attack value to get.
   * @return An Attack Value.
   */
  int getAttack(Direction dir);

  /**
   * Returns the color of this card. Null if the color has not been set.
   *
   * @return The color of this card.
   */
  CardColor getColor();

  /**
   * Sets the color of the card to the given CardColor.
   *
   * @param color Color to set
   * @throws IllegalStateException If color has already been set.
   */
  void setColor(CardColor color);

  /**
   * Changes the current color of the card to the opposite CardColor.
   * If the card color is red it will be changed to blue. Vice Versa.
   *
   * @throws IllegalStateException If color has not been set.
   */
  void flip();

  /**
   * Returns a String that represents the card.
   *
   * @return A String representation of the card.
   */
  String toString();

  /**
   * Produces the string value of the attack value in the given direction.
   * @param dir Direction to retrieve the attack value from.
   * @return String value of the attack value in the given direction.
   */
  String attackToString(Direction dir);

  /**
   * Returns "R" or "B", Representing the CardColors Red and Blue respectively.
   * The String representation of the CardColor of this card.
   *
   * @return A Character that represents the CardColor of this card.
   * @throws IllegalStateException If the color of this card has not been set yet.
   */
  String colorString();
}