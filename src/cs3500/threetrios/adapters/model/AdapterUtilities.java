package cs3500.threetrios.adapters.model;

import cs3500.threetrios.model.card.CardColor;
import cs3500.threetrios.provider.model.Player;

public class AdapterUtilities {

  public static CardColor playerToColor(Player player) {
    if (player == Player.BLUE) {
      return CardColor.BLUE;
    }
    else if (player == Player.RED) {
      return CardColor.RED;
    }
    return null;
  }

  public static Player colorToPlayer(CardColor color) {
    if (color == CardColor.BLUE) {
      return Player.BLUE;
    }
    else if (color == CardColor.RED) {
      return Player.RED;
    }
    return Player.NONE;
  }
}
