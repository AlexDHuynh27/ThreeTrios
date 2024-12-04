package cs3500.threetrios.provider.controllerAndFeatures;

import cs3500.threetrios.provider.model.Player;

/**
 * Represents callback methods in the controller that the model calls.
 * These callbacks are status updates that the controller listens for.
 * The controller is expected to respond to these updates, bridging
 * the gaps between the model and the views/players.
 */
public interface ModelStatus {
  /**
   * Called when the turn changes to a new player. Equivalently, this method
   * is called after each move. In response to this call, the controller
   * should query the view to update the new game state, and query the player
   * who didn't move that it is now their turn.
   * @param currentPlayer the player whose turn it now is (not the player who
   *                      just moved)
   */
  void onTurnChanged(Player currentPlayer);

  /**
   * Called when the game ends.
   * @param winner the player who won the game.
   */
  void onGameOver(Player winner);
}
