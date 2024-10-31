package cs3500.threetrios.model.cell;

import cs3500.threetrios.model.card.Direction;
import cs3500.threetrios.model.card.ThreeTriosCard;

/**
 * Represents a Cell that is a Hole which is always filled so no Cards can be placed inside of it.
 */
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
}