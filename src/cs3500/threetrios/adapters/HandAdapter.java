package cs3500.threetrios.adapters;

import java.util.ArrayList;
import java.util.List;

import cs3500.threetrios.provider.model.Card;
import cs3500.threetrios.provider.model.Hand;

public class HandAdapter implements Hand {
  private final List<cs3500.threetrios.model.card.Card> myHand;

  public HandAdapter(List<cs3500.threetrios.model.card.Card> myHand) {
    this.myHand = myHand;
  }

  @Override
  public List<Card> getCards() {
    List<Card> cards = new ArrayList<>();
    for (cs3500.threetrios.model.card.Card myCard : myHand) {
      cards.add(new CardAdapter(myCard));
    }
    return cards;
  }

  @Override
  public void addCard(Card card) {
    cs3500.threetrios.model.card.Card myCard = ((CardAdapter) card).myCard;
    myHand.add(myCard);
  }

  @Override
  public int size() {
    return myHand.size();
  }

  @Override
  public Card pop(int cardIdx) {
    cs3500.threetrios.model.card.Card myCard = myHand.remove(cardIdx);
    return new CardAdapter(myCard);
  }
}
