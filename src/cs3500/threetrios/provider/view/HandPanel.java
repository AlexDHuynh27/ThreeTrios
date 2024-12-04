package cs3500.threetrios.provider.view;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import cs3500.threetrios.provider.controllerAndFeatures.PlayerActions;
import cs3500.threetrios.provider.model.Card;
import cs3500.threetrios.provider.model.Hand;
import cs3500.threetrios.provider.model.Player;
import cs3500.threetrios.provider.model.ReadOnlyModel;

/**
 * Represents a panel for a player's hand of cards.
 * Allows card selection and notifies listeners of player actions.
 */
public class HandPanel extends JPanel {
  private Hand hand;
  private final Player windowOwner;
  private final Player handOwner;
  private final ReadOnlyModel model;
  private int selectedCardIndex = -1;
  private PlayerActions actionListener;

  /**
   * Constructor for a hand panel.
   *
   * @param model       is the non-mutable version of the model with game data.
   * @param windowOwner is the owner of the view we are rendering
   * @param handOwner   is the owner of the hand we are rendering
   */
  public HandPanel(ReadOnlyModel model, Player handOwner, Player windowOwner) {
    this.model = model;
    this.windowOwner = windowOwner;
    this.handOwner = handOwner;
  }

  /**
   * Refreshes the hand so that every time a card is popped, it updates accordingly.
   */
  public void updateHand() {
    // get rid of old panels
    removeAll();

    hand = model.getHand(handOwner);
    setLayout(new GridLayout(hand.size(), 1, 5, 5));
    setPreferredSize(new Dimension(120, 400));

    // create and add card panels to the hand panel
    for (int i = 0; i < hand.size(); i++) {
      Card currentCard = hand.getCards().get(i);
      Player owner = currentCard.getOwner();
      CardPanel cardPanel = new CardPanel(currentCard, selectCardColor(owner));
      int index = i;

      // add listener
      cardPanel.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
          handleCardSelection(index, cardPanel);
        }
      });
      add(cardPanel);
    }

    revalidate();
    repaint();
  }

  /**
   * Handles selection of a card and notifies the action listener.
   *
   * @param index     the index of the selected card.
   * @param cardPanel the card panel associated with the selected card.
   */
  private void handleCardSelection(int index, CardPanel cardPanel) {
    // toggle selection
    if (selectedCardIndex == index) {
      selectedCardIndex = -1; // Deselect
      cardPanel.setSelected(false);
    } else {
      // deselect the previously selected card
      if (selectedCardIndex != -1 && selectedCardIndex < hand.size()) {
        ((CardPanel) getComponent(selectedCardIndex)).setSelected(false);
      }

      actionListener.onCardSelected(index, windowOwner, cardPanel.getOwner());

      if (model.toMove() == windowOwner
              && windowOwner == handOwner
              && handOwner == cardPanel.getOwner()
              && !model.isGameOver()) {
        selectedCardIndex = index;
        cardPanel.setSelected(true);
      }
    }
    repaint();
  }

  /**
   * Helper to determine what color to render the card based on ownership.
   *
   * @param player the player owning the card.
   * @return the color representing the player's ownership.
   */
  private Color selectCardColor(Player player) {
    return player.equals(Player.RED) ? Color.PINK : Color.CYAN;
  }

  /**
   * Registers a listener to notify of player actions.
   *
   * @param listener the listener to register.
   */
  public void setPlayerActionListener(PlayerActions listener) {
    this.actionListener = listener;
  }
}
