package cs3500.threetrios.model.player.strategy;

import cs3500.threetrios.model.ThreeTriosModel;
import cs3500.threetrios.model.player.Player;

public interface ThreeTriosStrategy {
  HandGridCoord chooseMove(ThreeTriosModel model, Player player);
}
