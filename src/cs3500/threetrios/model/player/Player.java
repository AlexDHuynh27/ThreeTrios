package cs3500.threetrios.model.player;

import cs3500.threetrios.model.card.Card;
import cs3500.threetrios.model.card.CardColor;
import cs3500.threetrios.model.card.ThreeTriosCard;

import java.util.List;

public interface Player {
  /**
   *
   */
  void addToHand(ThreeTriosCard card);

  ThreeTriosCard playFromHand(int idx);

  List<ThreeTriosCard> getHand();

  int getCurrentHandSize();

  void setColor(CardColor color);

  CardColor getColor();
}
