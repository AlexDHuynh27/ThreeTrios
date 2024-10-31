package cs3500.threetrios.view;

import cs3500.threetrios.model.ThreeTriosModel;
import cs3500.threetrios.model.card.ThreeTriosCard;
import cs3500.threetrios.model.cell.Cell;

import java.io.IOException;
import java.util.List;

/**
 * A class that renders the ThreeTriosGame when given the model and acessing
 */
public class ThreeTriosGameView {
  ThreeTriosModel model;
  Appendable out;

  /**
   * Constructor for the ThreeTriosGame
   * @param model
   * @param out
   */
  public ThreeTriosGameView(ThreeTriosModel model, Appendable out) {
    this.model = model;
    this.out = out;
  }

  public void render() {
    try {
      out.append("Player: ").append(String.valueOf(model.getCurrentPlayerColor())).append("\n");
      List<List<Cell>> grid = model.getGrid();
      for (int i = 0; i < grid.size(); i++) {
        for (int j = 0; j < grid.get(i).size(); j++) {
          out.append(grid.get(i).get(j).toString());
        }
        out.append("\n");
      }
      List<ThreeTriosCard> hand = model.getHand(model.getCurrentPlayerColor());
      out.append("Hand:\n");
      for (int i = 0; i < hand.size(); i++) {
        out.append(hand.get(i).toString()).append("\n");
      };
    }
    catch (IOException e) {
      throw new RuntimeException("Something went wrong with rendering the model");
    }
  }
}
