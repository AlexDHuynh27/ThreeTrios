package cs3500.threetrios.adapters;

import cs3500.threetrios.model.card.CardColor;
import cs3500.threetrios.model.card.Direction;
import cs3500.threetrios.provider.model.Card;
import cs3500.threetrios.provider.model.Player;

public class CardAdapter implements Card {
  public final cs3500.threetrios.model.card.Card myCard;

  public CardAdapter(cs3500.threetrios.model.card.Card myCard) {
    this.myCard = myCard;
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof CardAdapter) {
      return myCard.equals(((CardAdapter) other).myCard);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return myCard.hashCode();
  }

  @Override
  public boolean compare(Card other) {
    if (other instanceof CardAdapter) {
      return myCard.equals(((CardAdapter) other).myCard);
    }
    return false;
  }

  @Override
  public String toString() {
    return myCard.toString();
  }

  @Override
  public void changeOwner() {
    myCard.flip();
  }

  @Override
  public Player getOwner() {
    CardColor color = myCard.getColor();
    if (color == CardColor.RED) {
      return Player.RED;
    } else if (color == CardColor.BLUE) {
      return Player.BLUE;
    } else {
      return Player.NONE;
    }
  }

  @Override
  public void setOwner(Player owner) {
    CardColor color = mapToMyCardColor(owner);
    myCard.setColor(color);
  }

  @Override
  public String getName() {
    return myCard.getName();
  }

  @Override
  public int getNorth() {
    return myCard.getAttack(Direction.NORTH);
  }

  @Override
  public int getEast() {
    return myCard.getAttack(Direction.EAST);
  }

  @Override
  public int getSouth() {
    return myCard.getAttack(Direction.SOUTH);
  }

  @Override
  public int getWest() {
    return myCard.getAttack(Direction.WEST);
  }

  private CardColor mapToMyCardColor(Player player) {
    switch (player) {
      case RED:
        return CardColor.RED;
      case BLUE:
        return CardColor.BLUE;
      default:
        return null;
    }
  }
}