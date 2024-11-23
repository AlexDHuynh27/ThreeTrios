package cs3500.threetrios;

import cs3500.threetrios.controller.ThreeTriosController;
import cs3500.threetrios.model.ReadOnlyThreeTriosGameModel;
import cs3500.threetrios.model.ThreeTriosGameModel;
import cs3500.threetrios.model.card.Card;
import cs3500.threetrios.model.card.CardColor;
import cs3500.threetrios.model.card.ThreeTriosCard;
import cs3500.threetrios.model.cell.Cell;
import cs3500.threetrios.model.configreader.CardReader;
import cs3500.threetrios.model.configreader.GridReader;
import cs3500.threetrios.model.player.HumanPlayer;
import cs3500.threetrios.model.player.MachinePlayer;
import cs3500.threetrios.model.player.Player;
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
    ThreeTriosGameModel newGame = new ThreeTriosGameModel();
    if (args.length != 2) {
      System.out.println("Need two arguments");
    }
    List<Card> deck = CardReader.getDeckFromConfig(
        "src/cs3500/threetrios/exampleFiles/Hello.txt");
    List<List<Cell>> grid = GridReader.getGridFromConfig(
        "src/cs3500/threetrios/exampleFiles/gridconfig1.txt");

    Player redPlayer = new HumanPlayer();
    Player bluePlayer = new HumanPlayer();
    newGame.startGame(grid, deck, redPlayer, bluePlayer);
    ThreeTriosGraphicsView view = new ThreeTriosGraphicsView(newGame);
    ThreeTriosGraphicsView view2 = new ThreeTriosGraphicsView(newGame);
    ThreeTriosController controller = new ThreeTriosController(newGame, redPlayer, view);
    ThreeTriosController controller2 = new ThreeTriosController(newGame, bluePlayer, view2);
    controller.goPlay();
    controller2.goPlay();
  }

  public static Player typeOfPlayer(String playerName) {
    if (playerName.equals("human")) {
      return new HumanPlayer();
    }
    else if (playerName.equals("strategy1")) {
      System.out.print("strategy1");
    }
    else if (playerName.equals("strategy2")) {
      System.out.print("strategy2");
    }
    else if (playerName.equals("strategy3")) {
      System.out.print("strategy3");
    }
    return null;
  }

}
