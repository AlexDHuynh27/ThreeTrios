package cs3500.threetrios.provider.model;

/**
 * Enum representing every possible attack value.
 */
public enum Attack {
  ONE(1),
  TWO(2),
  THREE(3),
  FOUR(4),
  FIVE(5),
  SIX(6),
  SEVEN(7),
  EIGHT(8),
  NINE(9),
  A(10);  // A represents 10

  /**
   * Public because the Card class has to access its array of Attack Values and their respective
   * 'value's.
   */
  public final int value;

  /**
   * Constructor for an Attack enum.
   *
   * @param value is the numerical value assigned to each label.
   */
  Attack(int value) {
    this.value = value;
  }

  /**
   * Converts the enum to a representable String.
   *
   * @return the corresponding String value of this Attack.
   */
  @Override
  public String toString() {
    return value == 10 ? "A" : String.valueOf(value);
  }
}
