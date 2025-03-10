package cs3500.threetrios.model.player;

import cs3500.threetrios.model.card.Card;
import cs3500.threetrios.model.card.CardColor;

import java.util.List;

/**
 * Represents a Player in the game.
 */
public interface Player {

  /**
   * Adds the given card to the hand of this player and sets the color of the card, to the
   * color of this player.
   * @throws IllegalStateException if the color of this player hasn't been set.
   */
  void addToHand(Card card);

  /**
   * Removes and returns the same card from this players hand at the given index.
   * @param idx the idx of the card that should be removed and returned.
   * @return The card at the given idx.
   * @throws IllegalArgumentException If the inx is out of range of the player's hand.
   */
  Card playFromHand(int idx);

  /**
   * Gets an immutable copy of this player's hand.
   * @return A List of ThreeTriosCards that is an immutable copy of this player's hand.
   */
  List<Card> getHand();

  /**
   * Returns the size of this player's current hand.
   * @return The size of this Player's hand
   */
  int getCurrentHandSize();

  /**
   * Gets the CardColor color of this Player.
   * @return The CardColor color of this player
   * @throws IllegalStateException If the card color has not been set.
   */
  CardColor getColor();

  /**
   * Set's the color of this Player.
   *
   * @param color CardColor to set to the Player.
   * @throws IllegalStateException if the card color has already been set.
   */
  void setColor(CardColor color);
}
