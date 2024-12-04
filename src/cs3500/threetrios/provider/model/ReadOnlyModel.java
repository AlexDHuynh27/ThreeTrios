package cs3500.threetrios.provider.model;

import java.util.List;

/**
 * Interface for the model, providing methods that are "read-only"
 * in the sense that they cannot mutate the model.
 */
public interface ReadOnlyModel {
  /**
   * Check if the game has begun.
   *
   * @return true if the game has started.
   */
  boolean isGameStarted();

  /**
   * Check if the game is over.
   *
   * @return true if all the empty card cells are filled.
   */
  boolean isGameOver();

  /**
   * Returns the player whose turn it is to play to the board.
   *
   * @return said Player.
   */
  Player toMove();

  /**
   * Return the player who has won the game.
   *
   * @return the Player with the highest number of their own card on the board and in their hand.
   */
  Player winner();

  /**
   * Returns a copy of the grid.
   *
   * @return a copy of the grid.
   */
  List<List<Object>> getAllCells();

  /**
   * Returns a copy of the current hand.
   *
   * @return a copy of the current hand.
   */
  List<Object> getCurrentHand();

  /**
   * Returns the number of rows in the game grid.
   *
   * @return the number of rows in the grid.
   * @throws IllegalStateException if the game has not been started.
   */
  int getNumRows();

  /**
   * Returns the number of columns in the game grid.
   *
   * @return the number of columns in the grid
   * @throws IllegalStateException if the game has not been started
   */
  int getNumCols();

  /**
   * Returns a copy of the cell at the location (row, col).
   *
   * @param row 0-indexed row index
   * @param col 0-indexed column index
   * @return a copy of the cell (row, col)
   * @throws IllegalStateException    if the game has not been started
   * @throws IllegalArgumentException if (row, col) is out of bounds
   *                                  for the grid
   */
  Cell getCell(int row, int col);

  /**
   * Returns a copy of the hand owned by the given player.
   *
   * @param player the owner of the requested hand
   * @return a copy of the requested hand
   * @throws IllegalStateException if the game has not been started
   */
  Hand getHand(Player player);

  /**
   * Returns whether it would be a legal move for the current player to
   * play.
   *
   * @param move the potential move
   * @return whether it is a legal move
   * @throws IllegalStateException if the game has not been started
   */
  boolean isLegalMove(Move move);

  /**
   * Gets the number of flips a move would cause.
   *
   * @param move the potential move
   * @return the number of flips the move would get
   * @throws IllegalStateException    if the game has not been started
   * @throws IllegalArgumentException if the Move is not a legal move
   */
  int numFlips(Move move);
}
