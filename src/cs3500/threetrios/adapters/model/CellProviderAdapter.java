package cs3500.threetrios.adapters.model;

import cs3500.threetrios.provider.model.Card;
import cs3500.threetrios.provider.model.Cell;

/**
 * Adapts our group's code with the provider's Cell interface.
 */
public class CellProviderAdapter implements Cell {
  private final cs3500.threetrios.model.cell.Cell cell;

  /**
   * Constructor for CellProviderAdapter in order to adapt our code to their Cell interface.
   *
   * @param cell an instance of our group's Cell interface
   */
  public CellProviderAdapter(cs3500.threetrios.model.cell.Cell cell) {
    this.cell = cell;
  }


  @Override
  public boolean canBePlaced() {
    return cell.isEmpty();
  }

  @Override
  public void placeCard(Card card) {
    cell.setCard(new ProviderCardAdapter(card));
  }

  @Override
  public Card getCard() {
    if (cell.isEmpty()) {
      return null;
    }
    else {
      return new CardProviderAdapter(cell.getCard());
    }

  }

  @Override
  public Cell cloneCell() {
    return null;
  }
}
