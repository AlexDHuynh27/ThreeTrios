package cs3500.threetrios.provider.view;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import cs3500.threetrios.provider.controllerAndFeatures.PlayerActions;
import cs3500.threetrios.provider.model.Card;
import cs3500.threetrios.provider.model.Cell;
import cs3500.threetrios.provider.model.Player;
import cs3500.threetrios.provider.model.ReadOnlyModel;

/**
 * Represents a panel containing all the cells on the board and paints them respectively.
 */
public class BoardPanel extends JPanel {
  private final ReadOnlyModel model;
  private PlayerActions actionListener;

  /**
   * Constructor taking in a read-only version of the model to prevent any form of mutation.
   *
   * @param model is the game model with all game data.
   */
  public BoardPanel(ReadOnlyModel model) {
    this.model = model;
    setPreferredSize(new Dimension(400, 400));

    addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        Point point = e.getPoint();
        int cellY = point.y / getCellHeight();
        int cellX = point.x / getCellWidth();
        // notify the action listener of the selected cell
        actionListener.onCellSelected(cellY, cellX);
        repaint();
      }
    });
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    drawBoard(g2d);
  }

  private void drawBoard(Graphics2D g2d) {
    int cellWidth = getCellWidth();
    int cellHeight = getCellHeight();
    for (int i = 0; i < model.getNumCols(); i++) {
      for (int j = 0; j < model.getNumRows(); j++) {
        drawCell(g2d, i, j, cellWidth, cellHeight);
      }
    }
  }

  private void drawCell(Graphics2D g2d, int i, int j, int cellWidth, int cellHeight) {
    Cell cell = model.getCell(i, j);
    if (!cell.canBePlaced() && cell.getCard() != null) {
      g2d.setColor(cell.getCard().getOwner() == Player.RED ? Color.PINK : Color.CYAN);
    } else if (!cell.canBePlaced()) {
      g2d.setColor(Color.YELLOW);
    } else {
      g2d.setColor(Color.GRAY);
    }
    g2d.fillRect(j * cellWidth, i * cellHeight, cellWidth, cellHeight);
    g2d.setColor(Color.BLACK);
    g2d.drawRect(j * cellWidth, i * cellHeight, cellWidth, cellHeight);

    // if the cell has a card, draw its details
    if (cell.getCard() != null) {
      drawCard(g2d, cell.getCard(), j * cellWidth, i * cellHeight, cellWidth, cellHeight);
    }
  }

  private void drawCard(Graphics2D g2d, Card card, int x, int y, int width, int height) {
    // bg
    g2d.setColor(card.getOwner() == Player.RED ? Color.PINK : Color.CYAN);
    g2d.fillRect(x + 5, y + 5, width - 10, height - 10);

    // border
    g2d.setColor(Color.BLACK);
    g2d.drawRect(x, y, width, height);

    // attack values
    int centerX = x + width / 2;
    int centerY = y + height / 2;
    g2d.setFont(new Font("Arial", Font.BOLD, 12));
    g2d.setColor(Color.BLACK);

    g2d.drawString(checkIfA(card.getNorth()), centerX - 5, y + 20);
    g2d.drawString(checkIfA(card.getEast()), x + width - 20, centerY + 5);
    g2d.drawString(checkIfA(card.getSouth()), centerX - 5, y + height - 10);
    g2d.drawString(checkIfA(card.getWest()), x + 10, centerY + 5);
  }

  private String checkIfA(int attackVal) {
    return attackVal == 10 ? "A" : String.valueOf(attackVal);
  }

  private int getCellWidth() {
    return getWidth() / model.getNumCols();
  }

  private int getCellHeight() {
    return getHeight() / model.getNumRows();
  }

  /**
   * Sets the action listener to notify when cells are clicked.
   *
   * @param listener the listener to register.
   */
  public void setPlayerActionListener(PlayerActions listener) {
    this.actionListener = listener;
  }
}
