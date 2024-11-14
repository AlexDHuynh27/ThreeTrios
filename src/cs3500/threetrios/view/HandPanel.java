package cs3500.threetrios.view;

import cs3500.threetrios.model.card.Card;
import cs3500.threetrios.model.card.CardColor;
import cs3500.threetrios.model.card.Direction;
import cs3500.threetrios.model.card.ThreeTriosCard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class HandPanel extends JPanel {
  List<ThreeTriosCard> hand;
  Color color;
  int selected;

  HandPanel(Color color) {
    this.hand = new ArrayList<>();
    this.color = color;
    this.selected = -1;
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
        ((this.getHeight() / hand.size()) * index) + ((this.getHeight() / hand.size()) / 2) + 10);
    g.drawString(this.hand.get(index).attackToString(Direction.EAST),
        this.getWidth() / 10 * 8,
        ((this.getHeight() / hand.size()) * index) + ((this.getHeight() / hand.size()) / 2) + 10);
    g.drawString(this.hand.get(index).attackToString(Direction.NORTH),
        this.getWidth() / 2,
        ((this.getHeight() / hand.size()) * index) + ((this.getHeight() / hand.size()) / 5) + 10);
    g.drawString(this.hand.get(index).attackToString(Direction.SOUTH),
        this.getWidth() / 2,
        ((this.getHeight() / hand.size()) * (index + 1)) - ((this.getHeight() / hand.size()) / 5) + 10);
  }

  public void setHand(List<ThreeTriosCard> hand) {
    this.hand = hand;
  }

  public void setSelected(int selected) {
    this.selected = selected;
  }
}
