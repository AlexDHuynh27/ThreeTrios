package cs3500.threetrios;

import cs3500.threetrios.model.ThreeTriosGameModel;
import cs3500.threetrios.model.card.ThreeTriosCard;
import cs3500.threetrios.model.cell.Cell;
import cs3500.threetrios.model.configreader.CardReader;
import cs3500.threetrios.model.configreader.GridReader;
import cs3500.threetrios.model.player.HumanPlayer;
import cs3500.threetrios.view.ThreeTriosTextView;

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
    List<ThreeTriosCard> deck = CardReader.getDeckFromConfig(
        "src/cs3500/threetrios/exampleFiles/Hello.txt");
    List<List<Cell>> grid = GridReader.getGridFromConfig(
        "src/cs3500/threetrios/exampleFiles/gridconfig1.txt");
    ThreeTriosGameModel newGame = new ThreeTriosGameModel();
    newGame.startGame(grid, deck, new HumanPlayer(), new HumanPlayer());
    newGame.playToGrid(1, 2, 2);
    newGame.battle();
    new ThreeTriosTextView(newGame, System.out).render();

  }

}
