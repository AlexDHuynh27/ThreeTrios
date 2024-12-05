package cs3500.threetrios.adapters.model;

import cs3500.threetrios.model.card.Direction;
import cs3500.threetrios.provider.model.Card;
import cs3500.threetrios.provider.model.Player;

/**
 * Adapts our group's code with the provider's Card interface.
 */
public class CardProviderAdapter implements Card {
  private final cs3500.threetrios.model.card.Card card;

  /**
   * Constructor for CardProviderAdapter in order to adapt our code to their Card interface.
   *
   * @param card an instance of our group's Card interface
   */
  public CardProviderAdapter(cs3500.threetrios.model.card.Card card) {
    this.card = card;
  }

  @Override
  public boolean compare(Card other) {
    return this.getName().equals(other.getName())
        && this.getEast() == other.getEast()
        && this.getNorth() == other.getNorth()
        && this.getSouth() == other.getSouth()
        && this.getWest() == other.getWest();
  }

  @Override
  public void changeOwner() {
    card.flip();
  }

  @Override
  public Player getOwner() {
    return AdapterUtilities.colorToPlayer(card.getColor());
  }

  @Override
  public void setOwner(Player owner) {
    card.setColor(AdapterUtilities.playerToColor(owner));
  }

  @Override
  public String getName() {
    return card.getName();
  }

  @Override
  public int getNorth() {
    return card.getAttack(Direction.NORTH);
  }

  @Override
  public int getEast() {
    return card.getAttack(Direction.EAST);
  }

  @Override
  public int getSouth() {
    return card.getAttack(Direction.SOUTH);
  }

  @Override
  public int getWest() {
    return card.getAttack(Direction.WEST);
  }
}
