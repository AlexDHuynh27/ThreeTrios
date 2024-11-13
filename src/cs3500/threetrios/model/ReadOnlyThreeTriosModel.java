package cs3500.threetrios.model;

import java.util.List;

import cs3500.threetrios.model.card.CardColor;
import cs3500.threetrios.model.card.ThreeTriosCard;
import cs3500.threetrios.model.cell.Cell;
import cs3500.threetrios.model.player.Player;

/**
 * The ReadOnlyThreeTriosModel interface provides a read-only view of the Three Trios game model,
 * allowing observation of the game's state without modifying it. This interface supports the
 * MVC pattern, enabling the view to access the model safely.
 */
public interface ReadOnlyThreeTriosModel {
  /**
   * Determines if the game is over or not, by seeing if all card cells are filled.
   *
   * @return a boolean that represents whether the gmae is over or not
   * @throws IllegalStateException If the game has not started.
   */
  boolean gameOver();

  /**
   * Gets the winner of the game.
   *
   * @return The player that won the game.
   * @throws IllegalStateException If the game is not over.
   */
  Player getWinner();

  /**
   * Returns an immutable list of the hand of the player that is associated with the given color.
   *
   * @param color The card color to know which player's hand to retrieve
   * @return The hand of the player that matches the given color.
   */
  List<ThreeTriosCard> getHand(CardColor color);

  /**
   * Returns the CardColor of the player who is taking their turn.
   *
   * @return CardColor of the player that is taking their turn.
   * @throws IllegalStateException If the game has not started or is over.
   */
  CardColor getCurrentPlayerColor();

  /**
   * Gets an immutable copy of the current grid.
   *
   * @return An Immutable copy of the current grid.
   * @throws IllegalStateException if the game has not started or is over.
   */
  List<List<Cell>> getGrid();

  /**
   * Calculates the current score of the given player.
   *
   * @param player The player whose score is to be calculated.
   * @return The score of the player, which is the sum of cards in hand and cards owned on the grid.
   */
  int getPlayerScore(Player player);

  /**
   * Retrieves the cell at the specified coordinates.
   *
   * @param row    the row index.
   * @param column the column index.
   * @return the cell at the specified coordinates.
   * @throws IllegalStateException    if the game hasn't started.
   * @throws IllegalArgumentException if the coordinates are out of bounds.
   */
  Cell getCellAt(int row, int column);

  /**
   * Determines which player owns the card at the specified coordinates.
   *
   * @param row    the row index.
   * @param column the column index.
   * @return the CardColor of the player owning the card, or null if the cell is empty or a hole.
   * @throws IllegalStateException    if the game hasn't started.
   * @throws IllegalArgumentException if the coordinates are out of bounds.
   */
  CardColor getCardOwnerColorAt(int row, int column);

  /**
   * Returns the number of CardCells in the grid.
   *
   * @return the number of CardCells present in the grid.
   * @throws IllegalStateException if the game hasn't started.
   */
  int getGridSize();

  /**
   * Checks if it is legal for the current player to play at the given coordinates.
   *
   * @param row    the row index.
   * @param column the column index.
   * @return true if it is legal to play; false otherwise.
   * @throws IllegalStateException    if the game hasn't started or is over.
   * @throws IllegalArgumentException if the coordinates are out of bounds.
   */
  boolean isLegalPlay(int row, int column);

  /**
   * Simulates playing a card at the given coordinates and returns the number of cards that would be flipped.
   *
   * @param card   the card to play.
   * @param row    the row index.
   * @param column the column index.
   * @return the number of cards that would be flipped.
   * @throws IllegalStateException    if the game hasn't started or is over.
   * @throws IllegalArgumentException if the coordinates are out of bounds or the cell is invalid.
   */
  int howManyFlips(ThreeTriosCard card, int row, int column);
}
