package cs3500.threetrios.model.cell;

import cs3500.threetrios.model.card.Card;
import cs3500.threetrios.model.card.Direction;
import cs3500.threetrios.model.card.ThreeTriosCard;

public class Hole implements Cell {

  @Override
  public void flipCell() {
  }

  @Override
  public void setCard(ThreeTriosCard card) {

  }

  @Override
  public ThreeTriosCard getCard() {
    return null;
  }

  @Override
  public boolean battleCell(Cell other, Direction dir) {
    return false;
  }

  @Override
  public boolean isEmpty() {
    return false;
  }

  public String toString() {
    return " ";
  }
}