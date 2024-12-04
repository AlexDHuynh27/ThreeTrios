package cs3500.threetrios.provider.view;

import cs3500.threetrios.provider.controllerAndFeatures.PlayerActions;
import cs3500.threetrios.provider.model.Player;

/**
 * Represents a GUI-based view that uses Swing and appropriate panels/frames to display the game.
 */
public interface SwingView {
  /**
   * Method that refreshes the view so that game state can be updated and reflected in real time.
   */
  void updateView();

  /**
   * Register the given listener as a subscriber for events from this view.
   * @param listener controller that is signing up for notifications
   */
  void setPlayerActionListener(PlayerActions listener);

  /**
   * Set the current player as the label for the view.
   * @param currentPlayer the player whose turn it is
   */
  void updateCurrentPlayer(Player currentPlayer);

  /**
   * Display a message to the user.
   * @param message the text to show to the user
   */
  void showMessage(String message);
}
