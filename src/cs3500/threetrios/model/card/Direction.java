package cs3500.threetrios.model.card;

/**
 * Represents the different direction a card can attack and what sides of a card have an attack
 * value.
 */
public enum Direction {
  NORTH,
  SOUTH,
  EAST,
  WEST;

  public Direction getOpposite() {
    switch (this) {
      case NORTH:
        return SOUTH;
      case SOUTH:
        return NORTH;
      case EAST:
        return WEST;
      case WEST:
        return EAST;
      default:
        throw new IllegalArgumentException("Unknown direction: " + this);
    }
  }
}
