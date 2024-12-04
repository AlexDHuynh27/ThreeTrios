package cs3500.threetrios.provider.view;

import java.awt.*;

import javax.swing.*;

import cs3500.threetrios.provider.model.Card;
import cs3500.threetrios.provider.model.Player;

/**
 * Represents a panel for a given card and paints.
 * - the background color indicating player ownership
 * - light gray highlighting to indicate selection
 * - the attack values
 */
public class CardPanel extends JPanel {
  private final Card card;
  private boolean isSelected;
  private final Color backgroundColor;
  private static final int BORDER_RADIUS = 13;

  /**
   * Constructor for the card panel.
   *
   * @param card            is the given card with all the necessary information.
   * @param backgroundColor is color of the card indicating ownership.
   */
  public CardPanel(Card card, Color backgroundColor) {
    this.card = card;
    this.backgroundColor = backgroundColor;
    this.isSelected = false;

    setPreferredSize(new Dimension(100, 160));
  }

  /**
   * Method to change the selected state of the card.
   * It is public because the hand panel needs to modify the selected state of its own selected
   * card.
   *
   * @param selected is the boolean state of this card being selected.
   */
  public void setSelected(boolean selected) {
    this.isSelected = selected;
    repaint();
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;

    // antialiasing to make it pretty (had to google it cus it looked weird)
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    // bg color
    g2d.setColor(isSelected ? Color.LIGHT_GRAY : backgroundColor);
    g2d.fillRoundRect(2, 2, getWidth() - 4, getHeight() - 4, BORDER_RADIUS, BORDER_RADIUS);

    // draw border
    g2d.setColor(Color.BLACK);
    g2d.drawRoundRect(2, 2, getWidth() - 5, getHeight() - 5, BORDER_RADIUS, BORDER_RADIUS);

    // attack values
    int topValue = card.getNorth();
    int rightValue = card.getEast();
    int bottomValue = card.getSouth();
    int leftValue = card.getWest();

    // draw attack values
    g2d.setColor(Color.BLACK);
    g2d.setFont(new Font("Arial", Font.BOLD, 12));

    // north
    g2d.drawString(checkIfA(topValue), getWidth() / 2 - 5, 20);

    // east
    g2d.drawString(checkIfA(rightValue), getWidth() - 20, getHeight() / 2 + 5);

    // south
    g2d.drawString(checkIfA(bottomValue), getWidth() / 2 - 5, getHeight() - 10);

    // west
    g2d.drawString(checkIfA(leftValue), 10, getHeight() / 2 + 5);
  }

  // helper method/pipe to convert attack value 10 to "A"
  private String checkIfA(int attackVal) {
    return attackVal == 10 ? "A" : String.valueOf(attackVal);
  }

  /**
   * Return the owner of this card.
   *
   * @return the player who owns this card.
   */
  public Player getOwner() {
    return this.card.getOwner();
  }
}
