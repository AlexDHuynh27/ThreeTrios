package cs3500.threetrios.model.card;

/**
 * Represents a generic card for ThreeTrios.
 */
public interface Card {

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

}