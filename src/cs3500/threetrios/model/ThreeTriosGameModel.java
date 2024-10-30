package cs3500.threetrios.model;

import java.util.List;

import cs3500.threetrios.model.card.Card;
import cs3500.threetrios.model.card.CardColor;
import cs3500.threetrios.model.card.ThreeTriosCard;
import cs3500.threetrios.model.cell.Cell;
import cs3500.threetrios.model.player.Player;

public class ThreeTriosGameModel implements ThreeTriosModel {
  private List<List<Cell>> grid;
  private List<ThreeTriosCard> deck;
  private Player redPlayer;
  private Player bluePlayer;
  private CardColor colorTurn;

  private boolean battled;
  private boolean playedToGrid;
  private boolean gameStarted;
  private boolean gameOver;

  @Override
  public void startGame(List<List<Cell>> grid, List<ThreeTriosCard> deck, Player redPlayer, Player bluePlayer) {

  }

  @Override
  public void drawHand() {

  }

  @Override
  public void playToGrid(int curPlayerHandIdx, int row, int column) {

  }

  @Override
  public void battle() {

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
