package cs3500.threetrios.model.player.strategy;

import cs3500.threetrios.model.ThreeTriosModel;
import cs3500.threetrios.model.player.Player;

/**
 * Represents a type of game strategy for ThreeTriosGame.
 */
public interface ThreeTriosStrategy {
  /**
   * Determines the best move for the AI bot based on the given strategy. This method evaluates
   * the current game model state and the player's hand to decide the best card to play and the
   * position on the grid to place it. The decision-making process varies depending on the
   * specific implementation of the strategy class that uses it.
   *
   * @param model the current game model state
   * @param player the current player
   * @return HandGridCoord telling the AI bot what handIdx, rowIdx, and colIdx to move
   */
  HandGridCoord chooseMove(ThreeTriosModel model, Player player);
}