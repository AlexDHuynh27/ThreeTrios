package cs3500.threetrios.model.player;

import cs3500.threetrios.model.card.Card;

public interface Player {
  /**
   *
   */
  void addToHand(Card card);

  void playFromHand(int idx);

  int getCurrentHandSize();

  void setColor();

  void getColor();
}
