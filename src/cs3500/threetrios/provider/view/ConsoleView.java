package cs3500.threetrios.provider.view;

import java.io.IOException;

/**
 * Represents a text-based view for a game.
 */
public interface ConsoleView {

  /**
   * String that represents the current player, game board,
   * and player's hands including their cards.
   *
   * @return the current game state in text form in the console.
   */
  String toString();

  /**
   * Method to add the view's toString to the appendable.
   *
   * @throws IOException if it fails to render the game state for whatever reason.
   */
  void render() throws IOException;
}
