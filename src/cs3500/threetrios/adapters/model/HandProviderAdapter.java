package cs3500.threetrios.adapters.model;

import cs3500.threetrios.provider.model.Card;
import cs3500.threetrios.provider.model.Hand;
import java.util.ArrayList;
import java.util.List;

/**
 * Adapts our group's code with the provider's Hand interface.
 */
public class HandProviderAdapter implements Hand {
  private final List<cs3500.threetrios.model.card.Card> cards;

  /**
   * Constructor for HandProviderAdapter in order to adapt our code to their Hand interface.
   *
   * @param cards an instance of a List of our group's Card interface
   */
  public HandProviderAdapter(List<cs3500.threetrios.model.card.Card> cards) {
    this.cards = cards;
  }

  @Override
  public List<Card> getCards() {
    List<Card> ret = new ArrayList<Card>();
    for (cs3500.threetrios.model.card.Card card : cards) {
      ret.add(new CardProviderAdapter(card));
    }
    return List.copyOf(ret);
  }

  @Override
  public void addCard(Card card) {
    cards.add(new ProviderCardAdapter(card));
  }

  @Override
  public int size() {
    return this.cards.size();
  }

  @Override
  public Card pop(int cardIdx) {
    return new CardProviderAdapter(this.cards.remove(cardIdx));
  }
}
