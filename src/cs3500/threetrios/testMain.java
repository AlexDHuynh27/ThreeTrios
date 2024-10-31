package cs3500.threetrios;

import cs3500.threetrios.model.ThreeTriosGameModel;
import cs3500.threetrios.model.card.ThreeTriosCard;
import cs3500.threetrios.model.cell.Cell;
import cs3500.threetrios.model.configreader.CardReader;
import cs3500.threetrios.model.configreader.GridReader;
import cs3500.threetrios.model.player.HumanPlayer;
import cs3500.threetrios.model.player.Player;
import cs3500.threetrios.view.ThreeTriosGameView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class testMain {
  public static void main(String[] args) {
    List<ThreeTriosCard> deck = CardReader.getDeckFromConfig("src/cs3500/threetrios/exampleFiles/Hello.txt");
    System.out.println(deck);

    List<List<Cell>> grid = GridReader.getGridFromConfig("src/cs3500/threetrios/exampleFiles/gridconfig1.txt");
    System.out.println(grid);
    ThreeTriosGameModel newGame = new ThreeTriosGameModel();
    newGame.startGame(grid, deck, new HumanPlayer(), new HumanPlayer());
    newGame.playToGrid(1, 2, 2);
    newGame.battle();
    ThreeTriosGameView view = new ThreeTriosGameView(newGame, System.out);
    view.render();

  }

}
