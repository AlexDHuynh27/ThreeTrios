package cs3500.threetrios.view;

import cs3500.threetrios.model.card.Card;
import cs3500.threetrios.model.card.Direction;
import cs3500.threetrios.model.card.ThreeTriosCard;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.Objects;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.MouseInputAdapter;

/**
 * Graphical representation of a player's hand.
 * Displays each card of the player's hand in a single row. Each card is represented by a
 * rectangle of the player's color, and each card has it's attack values displayed in the
 * corresponding direction. Cards can also be selected, which is represented by the larger
 * black border around the card.
 */
public class HandPanel extends JPanel {
  private List<Card> hand;
  private Color color;
  int selected;
  private List<ThreeTriosFeatures> featuresListeners;

  /**
   * Constructor for a HandPanel.
   * @param color the color the HandPanel is associated with.
   */
  HandPanel(Color color) {
    this.hand = new ArrayList<>();
    this.color = color;
    this.selected = -1;
    featuresListeners = new ArrayList<>();
    this.addMouseListener(new MouseEventsListener());
  }

  public void addFeaturesListener(ThreeTriosFeatures features) {
    this.featuresListeners.add(Objects.requireNonNull(features));
  }


  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    this.setLayout(new GridLayout(1, this.hand.size()));

    Graphics2D g2d = (Graphics2D) g;
    g2d.setFont(new Font("Times New Roman", Font.PLAIN,
        (this.getWidth() / 4)));

    AffineTransform oldTransform = g2d.getTransform();
    Color oldColor = g2d.getColor();


    for (int i = 0; i < hand.size(); i++) {
      g2d.setColor(this.color);
      if (i == selected) {
        g2d.fillRect(3,
            ((this.getHeight() / hand.size()) * i) + 3,
            this.getWidth() - 6,
            this.getHeight() / hand.size() - 6);
      }
      else {
        g2d.fillRect(1,
            ((this.getHeight() / hand.size()) * i) + 1,
            this.getWidth() - 2,
            this.getHeight() / hand.size() - 2);
      }
      drawAttacks(g2d, i);


    }

  }

  private void drawAttacks(Graphics2D g, int index) {
    g.setColor(Color.BLACK);
    g.drawString(this.hand.get(index).attackToString(Direction.WEST),
        this.getWidth() / 10 * 2,
        ((this.getHeight() / hand.size()) * index)
            + ((this.getHeight() / hand.size()) / 2) + 10);
    g.drawString(this.hand.get(index).attackToString(Direction.EAST),
        this.getWidth() / 10 * 8,
        ((this.getHeight() / hand.size()) * index)
            + ((this.getHeight() / hand.size()) / 2) + 10);
    g.drawString(this.hand.get(index).attackToString(Direction.NORTH),
        this.getWidth() / 2,
        ((this.getHeight() / hand.size()) * index)
            + ((this.getHeight() / hand.size()) / 5) + 10);
    g.drawString(this.hand.get(index).attackToString(Direction.SOUTH),
        this.getWidth() / 2,
        ((this.getHeight() / hand.size()) * (index + 1))
            - ((this.getHeight() / hand.size()) / 5) + 10);
  }

  /**
   * Provide the HandPanel with the current hand
   * of a player with it's associated color, presumably to show it.
   *
   * @param hand The hand to set
   */
  public void setHand(List<Card> hand) {
    this.hand = hand;
  }

  /**
   * Provide the HandPanel with which index of the current player's
   * hand is selected by the user.
   *
   * @param selected Represents the index of the selected Card idx0
   */
  public void setSelected(int selected) {
    this.selected = selected;
  }


  private class MouseEventsListener extends MouseInputAdapter {
    @Override
    public void mousePressed(MouseEvent e) {
      Point physicalP = e.getPoint();

      int scale = HandPanel.this.getHeight() / hand.size();
      for (int i = 0; i < hand.size(); i++) {
        if (physicalP.getY() > scale * i && physicalP.getY() < scale * i + scale ) {
          for (ThreeTriosFeatures feature : featuresListeners) {
            if (HandPanel.this.selected == i) {
              feature.setSelected(HandPanel.this.color, -1);
            }
            else {
              feature.setSelected(HandPanel.this.color, i);
            }
          }

        }
      }
      HandPanel.this.repaint();
    }
  }
}
