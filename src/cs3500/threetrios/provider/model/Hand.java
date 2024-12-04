package cs3500.threetrios.provider.model;

import java.util.List;

/**
 * Represents the hand of a Player. It contains a list of playable cards.
 */
public interface Hand {
  /**
   * Gets all the cards in this Hand in a new list.
   *
   * @return a list of cards.
   */
  List<Card> getCards();

  /**
   * Adds the given card to this hand of cards.
   *
   * @param card is to be added.
   */
  void addCard(Card card);

  /**
   * Returns the number of cards in the hand.
   *
   * @return the number of cards in the hand.
   */
  int size();

  /**
   * Pop and return the card at the specified index.
   * @param cardIdx the 0-indexed location of the card
   * @return the card requested
   * @throws IllegalArgumentException if the card index is out of bounds
   */
  Card pop(int cardIdx);
}
