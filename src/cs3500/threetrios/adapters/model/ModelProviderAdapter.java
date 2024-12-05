package cs3500.threetrios.adapters.model;

import cs3500.threetrios.model.ThreeTriosModel;
import cs3500.threetrios.model.card.Card;

import cs3500.threetrios.model.configreader.CardReader;
import cs3500.threetrios.model.configreader.GridReader;
import cs3500.threetrios.model.player.HumanPlayer;
import cs3500.threetrios.provider.controllerandfeatures.ModelStatus;

import cs3500.threetrios.provider.model.Cell;
import cs3500.threetrios.provider.model.Hand;
import cs3500.threetrios.provider.model.Model;
import cs3500.threetrios.provider.model.Move;
import cs3500.threetrios.provider.model.Player;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Adapts our group's code with the provider's Model interface.
 */
public class ModelProviderAdapter implements Model {
  private final ThreeTriosModel model;
  private boolean gameStarted;
  private final List<ModelStatus> listeners;

  /**
   * Constructor for ModelProviderAdapter in order to adapt our code to their Model interface.
   *
   * @param model an instance of our group's Model interface (ThreeTriosModel)
   */
  public ModelProviderAdapter(ThreeTriosModel model) {
    this.model = model;
    this.listeners = new ArrayList<>();
    this.gameStarted = false;
  }

  @Override
  public void startGame(String cardPath, String gridPath) throws IOException {
    try {
      List<Card> deck = CardReader.getDeckFromConfig(cardPath);
      List<List<cs3500.threetrios.model.cell.Cell>> grid = GridReader.getGridFromConfig(gridPath);
      this.model.startGame(grid, deck, new HumanPlayer(), new HumanPlayer());
      gameStarted = true;
    }
    catch (Exception e) {
      throw new IOException(e.getMessage());
    }


  }

  @Override
  public void playToBoard(int row, int col, int cardIdx) {
    model.playToGrid(cardIdx, row, col);
    model.battle();
    for (ModelStatus listener : listeners) {
      listener.onTurnChanged(AdapterUtilities.colorToPlayer(model.getCurrentPlayerColor()));
    }
  }

  @Override
  public void setModelStatusListener(ModelStatus listener) {
    this.listeners.add(listener);
  }

  @Override
  public List<Move> getAvailableMoves(Player player) {
    return null;
  }

  @Override
  public int getWinningScore() {
    return model.getPlayerScore(model.getWinner());
  }

  @Override
  public boolean isGameStarted() {
    return gameStarted;
  }

  @Override
  public boolean isGameOver() {
    if (model.gameOver()) {
      for (ModelStatus listener : listeners) {
        listener.onGameOver(AdapterUtilities.colorToPlayer(model.getWinner().getColor()));
      }
      return true;
    }
    return false;
  }

  @Override
  public Player toMove() {
    return AdapterUtilities.colorToPlayer(model.getCurrentPlayerColor());
  }

  @Override
  public Player winner() {
    return AdapterUtilities.colorToPlayer(model.getWinner().getColor());
  }

  @Override
  public List<List<Object>> getAllCells() {
    return List.of();
  }

  @Override
  public List<Object> getCurrentHand() {
    return List.of();
  }

  @Override
  public int getNumRows() {
    return model.getGrid().get(0).size();
  }

  @Override
  public int getNumCols() {
    return model.getGrid().size();
  }

  @Override
  public Cell getCell(int row, int col) {
    return new CellProviderAdapter(this.model.getCellAt(row, col));
  }

  @Override
  public Hand getHand(Player player) {
    return new HandProviderAdapter(model.getHand(AdapterUtilities.playerToColor(player)));
  }

  @Override
  public boolean isLegalMove(Move move) {
    return model.isLegalPlay(move.getRow(), move.getCol());
  }

  @Override
  public int numFlips(Move move) {
    return 0;
  }



}
