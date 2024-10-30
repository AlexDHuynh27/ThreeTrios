package cs3500.threetrios.model.card;

public class ThreeTriosCard implements Card {
  private String name;
  private int north;
  private int east;
  private int south;
  private int west;
  private CardColor color;

  ThreeTriosCard(String name, int north, int east, int south, int west) {
    this.name = name;
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

  @Override
  public String getName() {
    return name;
  }

  @Override
  public int getNorthAttack() {
    return north;
  }

  @Override
  public int getSouthAttack() {
    return south;
  }

  @Override
  public int getEastAttack() {
    return east;
  }

  @Override
  public int getWestAttack() {
    return west;
  }

  @Override
  public CardColor getColor() {
    return color;
  }
}
