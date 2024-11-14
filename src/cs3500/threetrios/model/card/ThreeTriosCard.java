package cs3500.threetrios.model.card;

import java.util.Objects;

/**
 * Represents a card in the ThreeTrios game. A card has a name, and four attack values, one for
 * each side of the card.
 * A card also has a CardColor that is associated with the player that owns the card that can
 * change.
 */
public class ThreeTriosCard implements Card {
  private final String name;
  private final int north;
  private final int east;
  private final int south;
  private final int west;
  private CardColor color;

  /**
   * Constructs a ThreeTriosCard with the given values.
   *
   * @param name  The name of the card.
   * @param north The Attack value of the top side.
   * @param south The Attack value of the bottom side.
   * @param east  The attack value of the right side.
   * @param west  The attack value of the left side.
   */
  public ThreeTriosCard(String name, int north, int south, int east, int west) {
    if (north < 1 || north > 10 || south < 1 || south > 10 || east < 1 || east > 10 || west < 1 ||
            west > 10) {
      throw new IllegalArgumentException("Attack values must be between 1 and 10.");
    }
    this.name = name;
    this.north = north;
    this.south = south;
    this.east = east;
    this.west = west;
  }

  @Override
  public void flip() {
    if (this.color == null) {
      throw new IllegalStateException("Color has not been set");
    } else if (this.color == CardColor.RED) {
      this.color = CardColor.BLUE;
    } else {
      this.color = CardColor.RED;
    }
  }

  /**
   * Returns the name of this card.
   *
   * @return The name of this card.
   */
  public String getName() {
    return name;
  }

  /**
   * Returns the attack value based on the given direction.
   *
   * @param dir The direction of the attack value to get.
   * @return An Attack Value.
   */
  public int getAttack(Direction dir) {
    switch (dir) {
      case NORTH:
        return north;
      case SOUTH:
        return south;
      case EAST:
        return east;
      case WEST:
        return west;
      default:
        throw new IllegalArgumentException("Invalid direction: " + dir);
    }
  }

  /**
   * Returns the color of this card. Null if the color has not been set.
   *
   * @return The color of this card.
   */
  public CardColor getColor() {
    return color;
  }

  @Override
  public void setColor(CardColor color) {
    if (this.color == null) {
      this.color = color;
    } else {
      throw new IllegalStateException("Color has already been set");
    }
  }

  @Override
  public String toString() {
    String northA = String.valueOf(north);
    String southA = String.valueOf(south);
    String eastA = String.valueOf(east);
    String westA = String.valueOf(west);

    if (north == 10) {
      northA = "A";
    }
    if (south == 10) {
      southA = "A";
    }
    if (east == 10) {
      eastA = "A";
    }
    if (west == 10) {
      westA = "A";
    }

    return name + " " + northA + " " + southA + " " + eastA + " " + westA;
  }

  public String attackToString(Direction dir) {
    switch (dir) {
      case NORTH:
        if (north == 10) {
          return "A";
        }
        return String.valueOf(north);
      case SOUTH:
        if(south == 10) {
          return "A";
        }
        return String.valueOf(south);
      case EAST:
        if(east == 10) {
          return "A";
        }
        return String.valueOf(east);
      case WEST:
        if(west == 10) {
          return "A";
        }
        return String.valueOf(west);
      default:
        throw new IllegalArgumentException("Invalid direction: " + dir);
    }
  }

  /**
   * Returns "R" or "B", Representing the CardColors Red and Blue respectively.
   * The String representation of the CardColor of this card.
   *
   * @return A Character that represents the CardColor of this card.
   * @throws IllegalStateException If the color of this card has not been set yet.
   */
  public String colorString() {
    if (color == null) {
      throw new IllegalStateException("Color has not been set");
    } else if (color == CardColor.RED) {
      return "R";
    } else {
      return "B";
    }
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    ThreeTriosCard other = (ThreeTriosCard) obj;
    return north == other.north &&
            south == other.south &&
            east == other.east &&
            west == other.west &&
            Objects.equals(name, other.name) &&
            color == other.color;
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, north, south, east, west, color);
  }
}
