package cs3500.threetrios.view;

import cs3500.threetrios.model.ThreeTriosModel;
import cs3500.threetrios.model.card.ThreeTriosCard;
import cs3500.threetrios.model.cell.Cell;

import java.io.IOException;
import java.util.List;

public class ThreeTriosGameView {
  ThreeTriosModel model;
  Appendable out;
  public ThreeTriosGameView(ThreeTriosModel model, Appendable out) {
    this.model = model;
    this.out = out;
  }

  public void render() throws IOException {
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
      for (int i = 0; i < hand.size(); i++) {
        out.append(hand.get(i).toString()).append("\n");
      };
    }
    catch (Exception e) {
      e.printStackTrace();
    }

  }
}
