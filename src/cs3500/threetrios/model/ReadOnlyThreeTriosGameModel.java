package cs3500.threetrios.model;

import cs3500.threetrios.model.card.CardColor;
import cs3500.threetrios.model.card.ThreeTriosCard;
import cs3500.threetrios.model.cell.Cell;
import cs3500.threetrios.model.player.Player;

import java.util.List;

/**
 * Represents a Read-only model of a ThreeTriosGame. Provides no mutibility,
 * but provides useful information about the current state of the game.
 */
public class ReadOnlyThreeTriosGameModel implements ReadOnlyThreeTriosModel {
  ThreeTriosGameModel gameModel;

  public ReadOnlyThreeTriosGameModel(ThreeTriosGameModel gameModel) {
    this.gameModel = gameModel;
  }


  @Override
  public boolean gameOver() {
    return this.gameModel.gameOver();
  }

  @Override
  public Player getWinner() {
    return this.gameModel.getWinner();
  }

  @Override
  public List<ThreeTriosCard> getHand(CardColor color) {
    return this.gameModel.getHand(color);
  }

  @Override
  public CardColor getCurrentPlayerColor() {
    return this.gameModel.getCurrentPlayerColor();
  }

  @Override
  public List<List<Cell>> getGrid() {
    return this.gameModel.getGrid();
  }

  @Override
  public int getPlayerScore(Player player) {
    return this.gameModel.getPlayerScore(player);
  }

  @Override
  public Cell getCellAt(int row, int column) {
    return this.gameModel.getCellAt(row, column);
  }

  @Override
  public CardColor getCardOwnerColorAt(int row, int column) {
    return this.gameModel.getCardOwnerColorAt(row, column);
  }

  @Override
  public int getGridSize() {
    return this.gameModel.getGridSize();
  }

  @Override
  public boolean isLegalPlay(int row, int column) {
    return this.gameModel.isLegalPlay(row, column);
  }

  @Override
  public int howManyFlips(ThreeTriosCard card, int row, int column) {
    return this.gameModel.howManyFlips(card, row, column);
  }
}
