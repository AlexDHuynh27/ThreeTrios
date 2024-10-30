package cs3500.threetrios.model.card;

public class ThreeTriosCard implements Card {
  int north;
  int east;
  int south;
  int west;
  CardColor color = null;

  ThreeTriosCard(int north, int east, int south, int west) {
    this.north = north;
    this.east = east;
    this.south = south;
    this.west = east;
  }

  @Override
  public void setColor(CardColor color) {
    if (this.color == null) {
      this.color = color;
    }
    else {
      throw new IllegalStateException("Color has already been set");
    }
  }

  @Override
  public void flip() {
    if (this.color == CardColor.RED) {
      this.color = CardColor.BLUE;
    }
    else if (this.color == CardColor.BLUE) {
      this.color = CardColor.RED;
    }
  }
}
