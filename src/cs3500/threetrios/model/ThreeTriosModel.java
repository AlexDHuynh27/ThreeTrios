package cs3500.threetrios.model;

import java.util.List;

import cs3500.threetrios.model.card.Card;
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
   * @throws IllegalArgumentException if the
   */
  void startGame(List<List<Cell>> grid, List<ThreeTriosCard> deck, Player redPlayer, Player bluePlayer);

  /**
   * Makes the current player draw cards until their hand is full or the deck is out of cards.
   * @throws IllegalStateException if the game hasn't started or is over.
   * @throws IllegalStateException if the current player hasn't played a card or battled yet.
   */
  void drawHand();

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
   * @throws IllegalStateException if a card hasn't been played this turn yet.
   */
  void battle();

  /**
   * Determines if the game is over or not, by seeing if all card cells are filled.
   * @throws IllegalStateException If the game has not started.
   * @return a boolean that represents whether the gmae is over or not
   */
  boolean gameOver();

  /**
   * Gets the winner of the game.
   * @throws IllegalStateException if the game is not over or if the game has not started.
   * @return The player that won the game.
   * */
  Player getWinner();
}
