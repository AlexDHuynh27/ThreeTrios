package cs3500.threetrios.model.player;

import cs3500.threetrios.model.card.Card;
import cs3500.threetrios.model.card.CardColor;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Player that is human and actually playing the game based off of a user input.
 */
public class HumanPlayer implements Player {
  private final List<Card> hand;
  private CardColor color;

  /**
   * The constructor for a HumanPlayer. Sets the hand to an empty ArrayList.
   */
  public HumanPlayer() {
    hand = new ArrayList<>();
  }

  @Override
  public void addToHand(Card card) {
    if (this.color == null) {
      throw new IllegalStateException("Color of this player hasn't been set.");
    }
    card.setColor(this.color);
    this.hand.add(card);
  }

  @Override
  public List<Card> getHand() {
    return List.copyOf(hand);
  }

  @Override
  public Card playFromHand(int idx) {
    if (idx < 0 || idx >= hand.size()) {
      throw new IllegalArgumentException("Index out of bounds " + idx);
    }
    return this.hand.remove(idx);
  }

  @Override
  public int getCurrentHandSize() {
    return this.hand.size();
  }

  @Override
  public CardColor getColor() {
    if (this.color == null) {
      throw new IllegalStateException("Color is not set");
    }
    return this.color;
  }

  @Override
  public void setColor(CardColor color) {
    if (this.color != null) {
      throw new IllegalStateException("Color is already set");
    }
    this.color = color;
  }
}
