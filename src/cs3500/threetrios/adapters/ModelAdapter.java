package cs3500.threetrios.adapters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cs3500.threetrios.model.ThreeTriosModel;
import cs3500.threetrios.model.card.Card;
import cs3500.threetrios.model.card.CardColor;
import cs3500.threetrios.provider.controllerAndFeatures.ModelStatus;
import cs3500.threetrios.provider.model.Cell;
import cs3500.threetrios.provider.model.Hand;
import cs3500.threetrios.provider.model.Model;
import cs3500.threetrios.provider.model.Player;

public class ModelAdapter implements Model {
  private final ThreeTriosModel myModel;
  private ModelStatus modelStatusListener;

  public ModelAdapter (ThreeTriosModel myModel) {
    this.myModel = myModel;
  }

  @Override
  public void startGame(String cardPath, String gridPath) throws IOException {
    // Read configurations using your own readers
    List<Card> deck = cs3500.threetrios.model.configreader.CardReader.getDeckFromConfig(cardPath);
    List<List<cs3500.threetrios.model.cell.Cell>> grid =
            cs3500.threetrios.model.configreader.GridReader.getGridFromConfig(gridPath);

    // Create players (adjust based on your Player implementation)
    cs3500.threetrios.model.player.Player redPlayer = new cs3500.threetrios.model.player.HumanPlayer(myModel);
    cs3500.threetrios.model.player.Player bluePlayer = new cs3500.threetrios.model.player.HumanPlayer(myModel);

    // Start the game
    myModel.startGame(grid, deck, redPlayer, bluePlayer);
  }

  @Override
  public void playToBoard(int row, int col, int cardIdx) {
    myModel.playToGrid(cardIdx, row, col);
    myModel.battle();

    // Notify the listener if it's set
    if (modelStatusListener != null) {
      Player nextPlayer = mapToProviderPlayer(myModel.getCurrentPlayerColor());
      modelStatusListener.onTurnChanged(nextPlayer);

      if (myModel.gameOver()) {
        Player winner = mapToProviderPlayer(myModel.getWinner().getColor());
        modelStatusListener.onGameOver(winner);
      }
    }
  }

  @Override
  public void setModelStatusListener(ModelStatus listener) {
    this.modelStatusListener = listener;
  }

  //CANNOT IMPL AS MOVE IS NOT VALID
  @Override
  public List<Move> getAvailableMoves(Player player) {
    // Implement if necessary
    return null;
  }

  @Override
  public int getWinningScore() {
    // Implement based on your model's methods
    // For example, get the score of the winner
    if (myModel.gameOver()) {
      cs3500.threetrios.model.player.Player winner = myModel.getWinner();
      return myModel.getPlayerScore(winner);
    }
    return 0;
  }

  // CANNOT IMPLEMENT
  @Override
  public boolean isGameStarted() {
    return false;
  }

  @Override
  public boolean isGameOver() {
    return myModel.gameOver();
  }

  @Override
  public Player toMove() {
    return mapToProviderPlayer(myModel.getCurrentPlayerColor());
  }

  @Override
  public Player winner() {
    if (!myModel.gameOver()) {
      return null;
    }
    cs3500.threetrios.model.player.Player winner = myModel.getWinner();
    if (winner == null) {
      return Player.NONE;
    }
    return mapToProviderPlayer(winner.getColor());
  }

  @Override
  public List<List<Object>> getAllCells() {
    List<List<Object>> providerGrid = new ArrayList<>();
    List<List<cs3500.threetrios.model.cell.Cell>> myGrid = myModel.getGrid();
    for (List<cs3500.threetrios.model.cell.Cell> myRow : myGrid) {
      List<Object> providerRow = new ArrayList<>();
      for (cs3500.threetrios.model.cell.Cell myCell : myRow) {
        providerRow.add(new CellAdapter(myCell));
      }
      providerGrid.add(providerRow);
    }
    return providerGrid;
  }

  @Override
  public List<Object> getCurrentHand() {
    CardColor currentPlayerColor = myModel.getCurrentPlayerColor();
    List<Card> myHand = myModel.getHand(currentPlayerColor);
    List<Object> providerHand = new ArrayList<>();
    for (Card myCard : myHand) {
      providerHand.add(new CardAdapter(myCard));
    }
    return providerHand;
  }

  @Override
  public int getNumRows() {
    return myModel.getGrid().size();
  }

  @Override
  public int getNumCols() {
    if (myModel.getGrid().isEmpty()) {
      return 0;
    }
    return myModel.getGrid().get(0).size();
  }

  @Override
  public Cell getCell(int row, int col) {
    cs3500.threetrios.model.cell.Cell myCell = myModel.getCellAt(row, col);
    return new CellAdapter(myCell);
  }

  @Override
  public Hand getHand(Player player) {
    CardColor color = mapToMyCardColor(player);
    List<Card> myHand = myModel.getHand(color);
    return new HandAdapter(myHand);
  }

  @Override
  public boolean isLegalMove(model.Move move) {
    // Implement if necessary
    return false;
  }

  @Override
  public int numFlips(model.Move move) {
    // Implement if necessary
    return 0;
  }

  // Helper methods to map between your types and the provider's types
  private Player mapToProviderPlayer(CardColor color) {
    switch (color) {
      case RED:
        return Player.RED;
      case BLUE:
        return Player.BLUE;
      default:
        return Player.NONE;
    }
  }

  private CardColor mapToMyCardColor(Player player) {
    switch (player) {
      case RED:
        return CardColor.RED;
      case BLUE:
        return CardColor.BLUE;
      default:
        return null;
    }
  }
}