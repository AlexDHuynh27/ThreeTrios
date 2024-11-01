package cs3500.threetrios.view;

import cs3500.threetrios.model.ThreeTriosModel;
import cs3500.threetrios.model.card.ThreeTriosCard;
import cs3500.threetrios.model.cell.Cell;

import java.io.IOException;
import java.util.List;

/**
 * A class that renders the ThreeTriosGame when given the model. More information on what
 * it renders can be found in the render() method
 */
public class ThreeTriosGameView {
  ThreeTriosModel model;
  Appendable out;

  /**
   * Constructor for the ThreeTriosGame.
   * @param model a game model of ThreeTriosModel
   * @param out   an Appendable out
   */
  public ThreeTriosGameView(ThreeTriosModel model, Appendable out) {
    this.model = model;
    this.out = out;
  }

  /**
   * Renders information about the ThreeTrios game model it is given.
   * The information includes, the CurrentPlayer's turn, the current state of the Grid including
   * what color owns what cards, and the hand of the CurrentPlayer
   */
  public void render() {
    try {
      out.append("Player: ").append(String.valueOf(model.getCurrentPlayerColor())).append("\n");
      List<List<Cell>> grid = model.getGrid();
      for (List<Cell> cells : grid) {
        for (Cell cell : cells) {
          out.append(cell.toString());
        }
        out.append("\n");
      }
      List<ThreeTriosCard> hand = model.getHand(model.getCurrentPlayerColor());
      out.append("Hand:\n");
      for (ThreeTriosCard threeTriosCard : hand) {
        out.append(threeTriosCard.toString()).append("\n");
      }
    } catch (IOException e) {
      throw new RuntimeException("Something went wrong with rendering the model");
    }
  }
}
