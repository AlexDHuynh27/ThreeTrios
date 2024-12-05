package cs3500.threetrios.adapters.model;

import cs3500.threetrios.provider.model.Card;
import cs3500.threetrios.provider.model.Cell;

public class CellProviderAdapter implements Cell {
  private cs3500.threetrios.model.cell.Cell cell;

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
    return null;
  }

  @Override
  public Cell cloneCell() {
    return null;
  }
}
