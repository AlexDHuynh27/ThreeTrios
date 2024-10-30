package cs3500.threetrios.model.player;

import cs3500.threetrios.model.card.Card;
import cs3500.threetrios.model.card.CardColor;
import cs3500.threetrios.model.card.ThreeTriosCard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HumanPlayer implements Player {
  List<ThreeTriosCard> hand;
  CardColor color;

  public HumanPlayer() {
    hand = new ArrayList<ThreeTriosCard>();
  }
  @Override
  public void addToHand(ThreeTriosCard card) {
    hand.add(card);
  }

  public List<ThreeTriosCard> getHand() {
    return List.copyOf(hand);
  }

  @Override
  public ThreeTriosCard playFromHand(int idx) {
    return this.hand.remove(idx);

  }

  @Override
  public int getCurrentHandSize() {
    return this.hand.size();

  }

  @Override
  public void setColor(CardColor color) {
    if (this.color != null) {
      throw new IllegalStateException("Color is already set");
    }
    this.color = color;
  }

  @Override
  public CardColor getColor() {
    if (this.color == null) {
      throw new IllegalStateException("Color is not set");
    }
    return this.color;
  }
}
