package cs3500.threetrios.model.card;

public class ThreeTriosCard implements Card {
  private String name;
  private final int north;
  private final int east;
  private final int south;
  private final int west;
  private CardColor color;

  public ThreeTriosCard(String name, int north, int south, int east, int west) {
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

  public int getNorthAttack() {
    return north;
  }

  public int getSouthAttack() {
    return south;
  }

  public int getEastAttack() {
    return east;
  }

  public int getWestAttack() {
    return west;
  }

  public CardColor getColor() {
    return color;
  }

  @Override
  public String toString() {
    return name + " " + north + " " + south + " " + east + " " + west;
  }

  public String colorString() {
    if (color == null) {
      throw new IllegalStateException("Color has not been set");
    } else if (color == CardColor.RED) {
      return "R";
    } else {
      return "B";
    }
  }
}
