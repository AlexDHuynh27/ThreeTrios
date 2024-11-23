package cs3500.threetrios;

import cs3500.threetrios.controller.ThreeTriosController;
import cs3500.threetrios.model.ReadOnlyThreeTriosGameModel;
import cs3500.threetrios.model.ThreeTriosGameModel;
import cs3500.threetrios.model.ThreeTriosModel;
import cs3500.threetrios.model.card.Card;
import cs3500.threetrios.model.card.CardColor;
import cs3500.threetrios.model.card.ThreeTriosCard;
import cs3500.threetrios.model.cell.Cell;
import cs3500.threetrios.model.configreader.CardReader;
import cs3500.threetrios.model.configreader.GridReader;
import cs3500.threetrios.model.player.HumanPlayer;
import cs3500.threetrios.model.player.MachinePlayer;
import cs3500.threetrios.model.player.Player;
import cs3500.threetrios.model.player.strategy.StrategyOne;
import cs3500.threetrios.model.player.strategy.StrategyThree;
import cs3500.threetrios.model.player.strategy.StrategyTwo;
import cs3500.threetrios.view.ThreeTriosGraphicsView;


import java.util.ArrayList;
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
    ThreeTriosModel newGame = new ThreeTriosGameModel();
    if (args.length != 2) {
      throw new IllegalArgumentException("Need two arguments of the following: 'human', " +
          "'strategy1', 'strategy2', and 'strategy3'");
    }
    Player redPlayer = typeOfPlayer(args[0], newGame);
    Player bluePlayer = typeOfPlayer(args[1], newGame);

    List<Card> deck = CardReader.getDeckFromConfig(
        "src/cs3500/threetrios/exampleFiles/DeckOfCard(50).txt");
    List<List<Cell>> grid = GridReader.getGridFromConfig(
        "src/cs3500/threetrios/exampleFiles/GridEx(1).txt");
    newGame.startGame(grid, deck, redPlayer, bluePlayer);
    ThreeTriosGraphicsView view = new ThreeTriosGraphicsView(newGame);
    ThreeTriosGraphicsView view2 = new ThreeTriosGraphicsView(newGame);
    ThreeTriosController controller = new ThreeTriosController(newGame, redPlayer, view);
    ThreeTriosController controller2 = new ThreeTriosController(newGame, bluePlayer, view2);

    controller.goPlay();
    controller2.goPlay();
  }

  private static void DeckOfCard(int i) {
  }

  private static Player typeOfPlayer(String playerName, ThreeTriosModel model) {
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
      throw new IllegalArgumentException("Invalid Argument, must be 'human',"
          + " 'strategy1', 'strategy2', or 'strategy3'");
    }
  }

}
