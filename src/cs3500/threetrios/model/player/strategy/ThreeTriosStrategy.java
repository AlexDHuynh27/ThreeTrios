package cs3500.threetrios.model.player.strategy;

import cs3500.threetrios.model.ThreeTriosModel;
import cs3500.threetrios.model.card.CardColor;

public interface ThreeTriosStrategy {
  HandGridCoord chooseMove(ThreeTriosModel model, CardColor forWhom);

}
