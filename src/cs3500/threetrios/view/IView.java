package cs3500.threetrios.view;

import cs3500.threetrios.model.card.CardColor;
import cs3500.threetrios.model.card.ThreeTriosCard;
import cs3500.threetrios.model.cell.Cell;

import java.util.List;

/**
 * Represents a graphical view for the ThreeTrios game. Displays relevant information, such
 * as players hands, selected card, and the current state of the grid.
 */
public interface IView {

  void makeVisible();

  /**
   * Provide the view with the current hand
   * of a player with it's associated color, presumably to show it
   *
   * @param color The Color of the player that's hand is being set
   * @param hand The hand to set
   */
  void setHand(CardColor color, List<ThreeTriosCard> hand);

  /**
   * Provide the view with the current grid
   * of the ThreeTriosModel, presumably to show it
   *
   * @param grid the grid of a ThreeTriosModel to show
   */
  void setGrid(List<List<Cell>> grid);


  /**
   * Provide the view with which index of the current player's
   * hand is selected by the user.
   *
   * @param color Represents the color of the current player
   * @param selected Represents the index of the selected Card idx0
   */
  void setSelected(CardColor color, int selected);
}
