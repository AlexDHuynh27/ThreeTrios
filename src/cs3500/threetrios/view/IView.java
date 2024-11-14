package cs3500.threetrios.view;

import cs3500.threetrios.model.card.Card;
import cs3500.threetrios.model.card.CardColor;
import cs3500.threetrios.model.card.ThreeTriosCard;
import cs3500.threetrios.model.cell.Cell;

import java.util.List;

public interface IView {

  void makeVisible();

  /**
   * Provide the view with the current hand
   * of a player with it's associated color, presumably to show it
   *
   * @param color
   * @param hand
   */
  void setHand(CardColor color, List<ThreeTriosCard> hand);

  /**
   * Provide the view with the current grid
   * of the ThreeTriosModel, presumably to show it
   *
   * @param grid
   */
  void setGrid(List<Cell> grid);


  void setSelected(CardColor color, int selected);
}
