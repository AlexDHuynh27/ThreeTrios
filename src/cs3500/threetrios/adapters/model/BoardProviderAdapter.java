package cs3500.threetrios.adapters.model;

import cs3500.threetrios.model.ThreeTriosModel;
import cs3500.threetrios.provider.model.Board;
import cs3500.threetrios.provider.model.Card;
import cs3500.threetrios.provider.model.Cell;
import cs3500.threetrios.provider.model.Player;
import java.util.List;

/**
 * Adapts our group's code with the provider's Board interface.
 */
public class BoardProviderAdapter implements Board {

  /**
   * Constructor for BoardProviderAdapter in order to adapt our code to their Board interface.
   *
   * @param model an instance of our group's ThreeTriosModel interface
   */
  public BoardProviderAdapter(ThreeTriosModel model) {
  }
  @Override
  public Cell getCellAt(int row, int col) {
    return null;
  }

  @Override
  public List<List<Object>> getAllCells() {
    return List.of();
  }

  @Override
  public void executeBattlePhase(int row, int col) {}

  @Override
  public List<Card> getPlayerCards(Player player) {
    return List.of();
  }

  @Override
  public void playToBoard(int row, int col, Card card) {}

  @Override
  public boolean isGameOver() {
    return false;
  }

  @Override
  public Board cloneBoard() {
    return null;
  }
}
