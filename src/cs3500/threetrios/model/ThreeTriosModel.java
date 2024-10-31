package cs3500.threetrios.model;

import java.util.List;

import cs3500.threetrios.model.card.Card;
import cs3500.threetrios.model.card.CardColor;
import cs3500.threetrios.model.card.ThreeTriosCard;
import cs3500.threetrios.model.cell.Cell;
import cs3500.threetrios.model.player.Player;

/**
 *
 */
public interface ThreeTriosModel {

  /**
   *
   * @param grid the grid the game is played on.
   * @param deck The deck of card that is used to play with.
   * @param redPlayer The red player
   * @param bluePlayer The blue player
   * @throws IllegalStateException if the game has already started.
   * @throws IllegalArgumentException if any of the fields are null or contain a null.
   * @throws IllegalArgumentException if the name of the cards in deck are not unique.
   * @throws IllegalArgumentException if the number of cards in the deck is not enough to play to
   * the grid.
   */
  void startGame(List<List<Cell>> grid, List<ThreeTriosCard> deck, Player redPlayer, Player bluePlayer);


  /**
   * Plays the given card to the given row and column coordinates CardCell on the grid.
   * @param curPlayerHandIdx The card to play from the current players hand. Idx0.
   * @param row the row of which the CardCell to be played on is on. Idx0.
   * @param column the column of which the CardCell to be played on is on. Idx0.
   * @throws IllegalStateException if game hasn't started or is over.
   * @throws IllegalStateException if a card has already been played to the field this turn.
   * @throws IllegalArgumentException if any of the fields are null.
   * @throws IllegalArgumentException if the row or column is out of bounds of the grid.
   * @throws IllegalArgumentException if the row and column coordinates are a hole on the grid.
   */
  void playToGrid(int curPlayerHandIdx, int row, int column);

  /**
   * Initiates the battle phase, changing the colors of any cards that lost to the current players play.
   * @throws IllegalStateException if the game hasn't started or is over.
   * @throws IllegalStateException if a card hasn't been played to the grid this turn yet.
   */
  void battle();

  /**
   * Determines if the game is over or not, by seeing if all card cells are filled.
   * @return a boolean that represents whether the gmae is over or not
   * @throws IllegalStateException If the game has not started.
   */
  boolean gameOver();

  /**
   * Gets the winner of the game.
   * @return The player that won the game.
   * @throws IllegalStateException If the game is not over.
   * */
  Player getWinner();

  /**
   * Returns an immutable list of the hand of the player that is associated with the given color
   * @return The hand of the player that matches the given color.
   * @param color The card color to know which player's hand to retrieve
   */
  List<ThreeTriosCard> getHand(CardColor color);

  /**
   * Returns the CardColor of the player who is taking their turn.
   * @return CardColor of the player that is taking their turn.
   * @throws IllegalStateException If the game has not started or is over.
   */
  CardColor getCurrentPlayerColor();

  /**
   * Gets an immutable copy of the current grid.
   * @return An Immutable copy of the current grid.
   * @throws IllegalStateException if the game has not started or is over.
   */
  List<List<Cell>> getGrid();

}
