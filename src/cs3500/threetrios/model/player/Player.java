package cs3500.threetrios.model.player;

import cs3500.threetrios.model.card.Card;
import cs3500.threetrios.model.card.ThreeTriosCard;

public interface Player {
  /**
   *
   */
  void addToHand(Card card);

  ThreeTriosCard playFromHand(int idx);

  int getCurrentHandSize();

  void setColor();

  void getColor();
}
