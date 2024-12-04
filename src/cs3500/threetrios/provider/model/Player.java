package cs3500.threetrios.provider.model;

/**
 * Represents the two Players.
 */
public enum Player {
  RED("RED"), BLUE("BLUE"), NONE("TIED");

  private final String color;

  /**
   * Constructs a Player enum.
   *
   * @param color corresponds to the identifying color of this player, or TIED,
   *              which represents a tie.
   */
  Player(String color) {
    this.color = color;
  }

  @Override
  public String toString() {
    return color;
  }

}
