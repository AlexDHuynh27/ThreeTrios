package cs3500.threetrios.model;

import java.util.List;

import cs3500.threetrios.model.card.Card;
import cs3500.threetrios.model.cell.Cell;
import cs3500.threetrios.model.player.Player;

public interface ThreeTriosModel {

  /**
   *
   * @param grid
   * @param deck
   * @param player1
   * @param player2
   * @throws IllegalStateException if the game has already started.
   * @throws IllegalArgumentException if any of the fields are null or have a null.
   * @throws IllegalArgumentException if there aren't enough cards in deck for the grid
   */
  void startGame(List<List<Cell>> grid, List<Card> deck, Player player1, Player player2);

  /**
   *
   */
  void drawHand();

  /**
   * Plays the given card to the
   * @param card
   * @param row
   * @param column
   */
  void playToGrid(Card card, int row, int column);

  /**
   *
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
