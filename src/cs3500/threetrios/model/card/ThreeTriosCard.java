package cs3500.threetrios.model.card;

public class ThreeTriosCard implements Card {
  String name;
  int north;
  int south;
  int east;
  int west;
  CardColor color;

  ThreeTriosCard(String name, int north, int south, int east,  int west) {
    this.name = name;
    this.north = north;
    this.south = south;
    this.east = east;
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
    if (this.color == null) {
      throw new IllegalStateException("Color has not been set");
    }
    else if (this.color == CardColor.RED) {
      this.color = CardColor.BLUE;
    }
    else {
      this.color = CardColor.RED;
    }
  }

  public String getName() {
    return name;
  }

}
