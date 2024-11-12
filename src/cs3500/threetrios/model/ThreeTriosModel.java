package cs3500.threetrios.model;

import java.util.List;

import cs3500.threetrios.model.card.CardColor;
import cs3500.threetrios.model.card.ThreeTriosCard;
import cs3500.threetrios.model.cell.Cell;
import cs3500.threetrios.model.player.Player;

/**
 * Interface representing a mutable Three Trios game model that extends the read-only version.
 * Provides additional methods for game setup, playing moves, and initiating battles.
 */
public interface ThreeTriosModel extends ReadOnlyThreeTriosModel{

  /**
   * Starts the game using given grid, deck, redPlayer, and bluePlayer.
   *
   * @param grid       the grid the game is played on.
   * @param deck       The deck of card that is used to play with.
   * @param redPlayer  The red player
   * @param bluePlayer The blue player
   * @throws IllegalStateException    if the game has already started.
   * @throws IllegalArgumentException if any of the fields are null or contain a null.
   * @throws IllegalArgumentException if the name of the cards in deck are not unique.
   * @throws IllegalArgumentException if the number of cards in the deck is not enough to play to
   *                                  the grid.
   */
  void startGame(List<List<Cell>> grid, List<ThreeTriosCard> deck, Player redPlayer,
                 Player bluePlayer);


  /**
   * Plays the given card to the given row and column coordinates CardCell on the grid.
   *
   * @param curPlayerHandIdx The card to play from the current players hand. Idx0.
   * @param row              the row of which the CardCell to be played on is on. Idx0.
   * @param column           the column of which the CardCell to be played on is on. Idx0.
   * @throws IllegalStateException    if game hasn't started or is over.
   * @throws IllegalStateException    if a card has already been played to the field this turn.
   * @throws IllegalArgumentException if any of the fields are null.
   * @throws IllegalArgumentException if the row or column is out of bounds of the grid.
   * @throws IllegalArgumentException if the row and column coordinates are a hole on the grid or
   *                                  a card has already been placed.
   */
  void playToGrid(int curPlayerHandIdx, int row, int column);

  /**
   * Initiates the battle phase and combo phase, changing the colors of any cards that lost
   * to the current players play.
   * @throws IllegalStateException if the game hasn't started or is over.
   * @throws IllegalStateException if a card hasn't been played to the grid this turn yet.
   */
  void battle();
}
