package cs3500.threetrios;

import cs3500.threetrios.controller.ThreeTriosController;
import cs3500.threetrios.model.ThreeTriosGameModel;
import cs3500.threetrios.model.card.Card;
import cs3500.threetrios.model.cell.Cell;
import cs3500.threetrios.model.configreader.CardReader;
import cs3500.threetrios.model.configreader.GridReader;
import cs3500.threetrios.model.player.HumanPlayer;
import cs3500.threetrios.model.player.MachinePlayer;
import cs3500.threetrios.model.player.strategy.StrategyOne;
import cs3500.threetrios.model.player.strategy.StrategyThree;
import cs3500.threetrios.model.player.strategy.StrategyTwo;
import cs3500.threetrios.adapters.controller.ControllerAdapter;
import cs3500.threetrios.adapters.model.ModelProviderAdapter;
import cs3500.threetrios.provider.model.Player;
import cs3500.threetrios.provider.view.ThreeTriosSwingView;

import cs3500.threetrios.view.ThreeTriosGraphicsView;
import java.util.List;

/**
 * Runs a turn in ThreeTriosGame.
 */
public class TestMain {

  /**
   * Example of the game running.
   * @param args Don't give args.
   */
  public static void main(String[] args) {
    List<Card> deck;
    List<List<Cell>> grid;
    try {
      deck = CardReader.getDeckFromConfig(
              "src/cs3500/threetrios/exampleFiles/DeckOfCard(50).txt");
      grid = GridReader.getGridFromConfig(
              "src/cs3500/threetrios/exampleFiles/GridEx(1).txt");
    }
    catch (Exception e) {
      deck = CardReader.getDeckFromConfig(
          "Assignment5/src/cs3500/threetrios/exampleFiles/DeckOfCard(50).txt");
      grid = GridReader.getGridFromConfig(
          "Assignment5/src/cs3500/threetrios/exampleFiles/GridEx(1).txt");
    }

    ThreeTriosGameModel newGame = new ThreeTriosGameModel();

    if (args.length != 2) {
      throw new IllegalArgumentException("Invalid number of arguments.");
    }
    cs3500.threetrios.model.player.Player redPlayer = typeOfPlayer(args[0], newGame);
    cs3500.threetrios.model.player.Player bluePlayer = new HumanPlayer();

    newGame.startGame(grid, deck, redPlayer, bluePlayer);
    ThreeTriosGraphicsView view = new ThreeTriosGraphicsView(newGame);
    ThreeTriosController controller = new ThreeTriosController(newGame, redPlayer, view);
    controller.goPlay();

    ModelProviderAdapter modelProvider = new ModelProviderAdapter(newGame);
    ThreeTriosSwingView view1 = new ThreeTriosSwingView(modelProvider,
        Player.BLUE);
    ControllerAdapter ca = new ControllerAdapter(view1, newGame, Player.BLUE);

  }

  private static cs3500.threetrios.model.player.Player typeOfPlayer(String playerName,
                                                                    ThreeTriosGameModel model) {
    if (playerName.equals("human")) {
      return new HumanPlayer();
    }
    else if (playerName.equals("strategy1")) {
      return new MachinePlayer(new StrategyOne(), new HumanPlayer(), model);
    }
    else if (playerName.equals("strategy2")) {
      return new MachinePlayer(new StrategyTwo(), new HumanPlayer(), model);
    }
    else if (playerName.equals("strategy3")) {
      return new MachinePlayer(new StrategyThree(), new HumanPlayer(), model);
    }
    else {
      throw new IllegalArgumentException("Invalid argument.");
    }
  }
}