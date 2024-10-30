package cs3500.threetrios.model.cell;

import cs3500.threetrios.model.card.ThreeTriosCard;

public class Hole implements Cell {

  @Override
  public void flipCell() {
    throw new IllegalCallerException("Hole cannot be accessed!");
  }

  @Override
  public void setCard(ThreeTriosCard card) {
    throw new IllegalCallerException("Hole cannot be accessed!");
  }

  @Override
  public String toString() {
    return " ";
  }

  @Override
  public boolean isEmpty() {
    return false;
  }
}