package cs3500.threetrios.adapters.model;

import cs3500.threetrios.model.card.CardColor;
import cs3500.threetrios.provider.model.Player;

/**
 * An AdapterUtilities class that adapts our groups CardColor enum to the providers player enum.
 */
public class AdapterUtilities {

  /**
   * Adapting provider's player enum to our CardColor enum.
   *
   * @param player a provider's player enum (Player.RED or Player.BLUE)
   * @return a CardColor enum (RED or BLUE)
   */
  public static CardColor playerToColor(Player player) {
    if (player == Player.BLUE) {
      return CardColor.BLUE;
    }
    else if (player == Player.RED) {
      return CardColor.RED;
    }
    return null;
  }

  /**
   * Adapting our group's CardColor enum to the provider's Player enum.
   *
   * @param color a CardColor enum (RED or BLUE)
   * @return the provider's Player enum (Player.RED or Player.BLUE)
   */
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
