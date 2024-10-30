package cs3500.threetrios.model.card;

public interface Card {

  /**
   * Sets the color of the card to the given CardColor
   * @param color Color to set
   * @throws IllegalStateException If color has already been set.
   */
  void setColor(CardColor color);

  /**
   * Changes the current color of the card to the other CardColor.
   */
  void flip();
}
