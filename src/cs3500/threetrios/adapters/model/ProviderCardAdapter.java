package cs3500.threetrios.adapters.model;

import cs3500.threetrios.model.card.Card;
import cs3500.threetrios.model.card.CardColor;
import cs3500.threetrios.model.card.Direction;

public class ProviderCardAdapter implements Card {
  cs3500.threetrios.provider.model.Card card;

  public ProviderCardAdapter(cs3500.threetrios.provider.model.Card card) {
    this.card = card;
  }

  @Override
  public String getName() {
    return card.getName();
  }

  @Override
  public int getAttack(Direction dir) {
    switch (dir) {
      case NORTH:
        return card.getNorth();
      case SOUTH:
        return card.getSouth();
      case EAST:
        return card.getEast();
      case WEST:
        return card.getWest();
      default:
        throw new IllegalArgumentException("Invalid direction: " + dir);
    }
  }

  @Override
  public CardColor getColor() {
    return AdapterUtilities.playerToColor(card.getOwner());
  }

  @Override
  public void setColor(CardColor color) {
    card.setOwner(AdapterUtilities.colorToPlayer(color));
  }

  @Override
  public void flip() {
    card.changeOwner();
  }

  @Override
  public String attackToString(Direction dir) {
    switch (dir) {
      case NORTH:
        if (card.getNorth() == 10) {
          return "A";
        }
        return String.valueOf(card.getNorth());
      case SOUTH:
        if (card.getSouth() == 10) {
          return "A";
        }
        return String.valueOf(card.getSouth());
      case EAST:
        if (card.getEast() == 10) {
          return "A";
        }
        return String.valueOf(card.getEast());
      case WEST:
        if (card.getWest() == 10) {
          return "A";
        }
        return String.valueOf(card.getWest());
      default:
        throw new IllegalArgumentException("Invalid direction: " + dir);
    }
  }

  @Override
  public String colorString() {
    if (getColor() == null) {
      throw new IllegalStateException("Color has not been set");
    } else if (getColor() == CardColor.RED) {
      return "R";
    } else {
      return "B";
    }
  }
}
