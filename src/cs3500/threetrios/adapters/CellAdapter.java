package cs3500.threetrios.adapters;

import cs3500.threetrios.model.cell.CardCell;
import cs3500.threetrios.provider.model.Card;
import cs3500.threetrios.provider.model.Cell;

public class CellAdapter implements Cell {
  private final cs3500.threetrios.model.cell.Cell myCell;

  public CellAdapter(cs3500.threetrios.model.cell.Cell myCell) {
    this.myCell = myCell;
  }

  @Override
  public boolean canBePlaced() {
    return myCell instanceof CardCell && myCell.isEmpty();
  }

  @Override
  public void placeCard(Card card) {
    if (!canBePlaced()) {
      throw new IllegalStateException("Cannot place a card in this cell.");
    }
    cs3500.threetrios.model.card.Card myCard = ((CardAdapter) card).myCard;
    myCell.setCard(myCard);
  }

  @Override
  public Card getCard() {
    if (myCell.getCard() == null) {
      return null;
    }
    return new CardAdapter(myCell.getCard());
  }

  @Override
  public boolean equals(Object object) {
    if (object instanceof CellAdapter) {
      return myCell.equals(((CellAdapter) object).myCell);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return myCell.hashCode();
  }

  @Override
  public Cell cloneCell() {
    // Implement if necessary
    return new CellAdapter(myCell);
  }
}