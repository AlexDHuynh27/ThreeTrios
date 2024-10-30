package cs3500.threetrios.model;

import java.util.List;

import cs3500.threetrios.model.card.Card;
import cs3500.threetrios.model.cell.Cell;
import cs3500.threetrios.model.player.Player;

public class ThreeTriosGameModel implements ThreeTriosModel {
  Player redPlayer;
  Player bluePlayer;
  boolean redTurn;

  @Override
  public void startGame(List<List<Cell>> grid, List<Card> deck, Player player1, Player player2) {

  }

  @Override
  public void drawHand() {

  }

  @Override
  public void playToGrid(Card card, int row, int column) {

  }

  @Override
  public boolean gameOver() {
    return false;
  }

  @Override
  public Player getWinner() {
    return null;
  }
}
