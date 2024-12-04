package cs3500.threetrios.provider.model;

import java.io.IOException;

import cs3500.threetrios.provider.controllerAndFeatures.ModelStatus;

/**
 * Represents the model for a game of Third Time. Handles the business logic of the game.
 */
public interface Model extends ReadOnlyModel {
  /**
   * Starts a game of Three Trio using config details fetched from a file.
   *
   * @param cardPath corresponds to the configuration file containing details for initial cards.
   * @param gridPath corresponds to the configuration file for initializing the board.
   */
  void startGame(String cardPath, String gridPath) throws IOException;

  /**
   * Plays a card from the given card index to the board at the specified row and column.
   *
   * @param row     is the row to play the card to.
   * @param col     is the column to play the card to.
   * @param cardIdx is the index of the card in the current player's hand.
   */
  void playToBoard(int row, int col, int cardIdx);

  /**
   * Adds a listener to this model to satisfy the observer pattern with the controller.
   *
   * @param listener listens to state changes in the model.
   */
  void setModelStatusListener(ModelStatus listener);

  /**
   * Compiles a list of legal moves a player can make.
   *
   * @param player is the current player who's available moves we are returning
   * @return a list of moves the given player can make
   */
  List<Move> getAvailableMoves(Player player);

  /**
   * Returns the score of the currently winning player. A player's score is
   * defined by the number of cards they own on the board plus the number of
   * cards in their hand.
   * @return the score of the currently winning player
   */
  int getWinningScore();
}