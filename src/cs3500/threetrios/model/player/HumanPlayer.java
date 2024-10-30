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
<<<<<<< HEAD
    return null;
=======
    return this.hand.remove(idx);
>>>>>>> 4d7307ad5628c233c3608a71afd7d8a1bde4f91f
  }

  @Override
  public int getCurrentHandSize() {
<<<<<<< HEAD
    return 0;
=======
    return this.hand.size();
>>>>>>> 4d7307ad5628c233c3608a71afd7d8a1bde4f91f
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
