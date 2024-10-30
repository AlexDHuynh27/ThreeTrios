import org.junit.Before;
import org.junit.Test;

import cs3500.threetrios.model.card.Direction;
import cs3500.threetrios.model.card.ThreeTriosCard;
import cs3500.threetrios.model.card.CardColor;
import cs3500.threetrios.model.card.Card;
import cs3500.threetrios.model.cell.CardCell;
import cs3500.threetrios.model.cell.Cell;
import cs3500.threetrios.model.cell.Hole;
import cs3500.threetrios.model.configreader.CardReader;
import cs3500.threetrios.model.configreader.GridReader;
import cs3500.threetrios.model.player.HumanPlayer;
import cs3500.threetrios.model.player.Player;
import cs3500.threetrios.model.ThreeTriosGameModel;
import cs3500.threetrios.model.ThreeTriosModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

public class TestThreeTriosGameModel {
  private ThreeTriosCard card;
  private ThreeTriosCard maxAttackCard;
  private ThreeTriosCard minAttackCard;
  private ThreeTriosCard filledCard;
  private Cell hole;
  private CardCell emptyCardCell;
  private CardCell filledCardCell;
  private Player redPlayer;
  private Player bluePlayer;
  private ThreeTriosGameModel gameModel;

  @Before
  public void setUp() {
      card = new ThreeTriosCard("Warrior", 5, 5, 7, 6);
      maxAttackCard = new ThreeTriosCard("MaxCard", 10, 10, 10, 10);
      minAttackCard = new ThreeTriosCard("MinCard", 1, 1, 1, 1);
      filledCard = new ThreeTriosCard("Filled", 6, 2, 1, 3);
      hole = new Hole();
      emptyCardCell = new CardCell();
      filledCardCell = new CardCell();

      filledCard.setColor(CardColor.RED);
      filledCardCell.setCard(filledCard);

      redPlayer = new HumanPlayer();
      bluePlayer = new HumanPlayer();
      gameModel = new ThreeTriosGameModel();
    }

  // Test for name retrieval
  @Test
  public void testGetName() {
    assertEquals("Warrior", card.getName());
    assertEquals("MaxCard", maxAttackCard.getName());
    assertEquals("MinCard", minAttackCard.getName());
  }

  // Test for correct retrieval of attack values
  @Test
  public void testGetNorthAttack() {
    assertEquals(5, card.getAttack(Direction.NORTH));
    assertEquals(10, maxAttackCard.getAttack(Direction.NORTH));
    assertEquals(1, minAttackCard.getAttack(Direction.NORTH));
  }

  @Test
  public void testGetSouthAttack() {
    assertEquals(5, card.getAttack(Direction.SOUTH));
    assertEquals(10, maxAttackCard.getAttack(Direction.SOUTH));
    assertEquals(1, minAttackCard.getAttack(Direction.SOUTH));
  }

  @Test
  public void testGetEastAttack() {
    assertEquals(7, card.getAttack(Direction.EAST));
    assertEquals(10, maxAttackCard.getAttack(Direction.EAST));
    assertEquals(1, minAttackCard.getAttack(Direction.EAST));
  }

  @Test
  public void testGetWestAttack() {
    assertEquals(6, card.getAttack(Direction.WEST));
    assertEquals(10, maxAttackCard.getAttack(Direction.WEST));
    assertEquals(1, minAttackCard.getAttack(Direction.WEST));
  }

  // Tests for setting color
  @Test
  public void testSetColorToRed() {
    card.setColor(CardColor.RED);
    assertEquals(CardColor.RED, card.getColor());
  }

  @Test
  public void testSetColorToBlue() {
    card.setColor(CardColor.BLUE);
    assertEquals(CardColor.BLUE, card.getColor());
  }

  @Test(expected = IllegalStateException.class)
  public void testSetColorAlreadySet() {
    card.setColor(CardColor.RED);
    card.setColor(CardColor.BLUE);
  }

  // Tests for flipping the card color
  @Test
  public void testFlipFromRedToBlue() {
    card.setColor(CardColor.RED);
    card.flip();
    assertEquals(CardColor.BLUE, card.getColor());
  }

  @Test
  public void testFlipFromBlueToRed() {
    card.setColor(CardColor.BLUE);
    card.flip();
    assertEquals(CardColor.RED, card.getColor());
  }

  @Test(expected = IllegalStateException.class)
  public void testFlipWithoutSettingColor() {
    card.flip();
  }

  // Test for colorString
  @Test
  public void testColorStringRed() {
    card.setColor(CardColor.RED);
    assertEquals("R", card.colorString());
  }

  @Test
  public void testColorStringBlue() {
    card.setColor(CardColor.BLUE);
    assertEquals("B", card.colorString());
  }

  @Test(expected = IllegalStateException.class)
  public void testColorStringWithoutSettingColor() {
    card.colorString();
  }

  // Tests for toString method
  @Test
  public void testToStringRepresentation() {
    assertEquals("Warrior 5 5 7 6", card.toString());
    assertEquals("MaxCard A A A A", maxAttackCard.toString());
    assertEquals("MinCard 1 1 1 1", minAttackCard.toString());
  }

  // Edge cases for invalid attack values
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeAttackValues() {
    // Should throw IllegalArgumentException as attack value is out of range
    new ThreeTriosCard("NegativeCard", -1, 5, 7, 6);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testExcessiveAttackValues() {
    // Should throw IllegalArgumentException as attack value is out of range
    new ThreeTriosCard("ExcessiveCard", 11, 5, 7, 6);
  }

  // Boundary tests for min and max attack values
  @Test
  public void testMinimumAttackValues() {
    assertEquals(1, minAttackCard.getAttack(Direction.NORTH));
    assertEquals(1, minAttackCard.getAttack(Direction.SOUTH));
    assertEquals(1, minAttackCard.getAttack(Direction.EAST));
    assertEquals(1, minAttackCard.getAttack(Direction.WEST));
  }

  @Test
  public void testMaximumAttackValues() {
    assertEquals(10, maxAttackCard.getAttack(Direction.NORTH));
    assertEquals(10, maxAttackCard.getAttack(Direction.SOUTH));
    assertEquals(10, maxAttackCard.getAttack(Direction.EAST));
    assertEquals(10, maxAttackCard.getAttack(Direction.WEST));
  }

  /**
   * Tests for the Hole class
   */
  @Test
  public void testHoleToString() {
    // Verify that hole's toString() returns a space
    assertEquals(" ", hole.toString());
  }

  @Test
  public void testHoleIsAlwaysNotEmpty() {
    // Verify that hole is never considered empty
    assertFalse(hole.isEmpty());
  }

  @Test(expected = IllegalCallerException.class)
  public void testHoleFlipCellThrowsException() {
    hole.flipCell();
  }

  @Test(expected = IllegalCallerException.class)
  public void testHoleSetCardThrowsException() {
    hole.setCard(card);
  }

  /**
   * Tests for the CardCell class using existing cards
   */
  @Test
  public void testEmptyCardCellToString() {
    // Verify that empty CardCell's toString() returns "_"
    assertEquals("_", emptyCardCell.toString());
  }

  @Test
  public void testFilledCardCellToString() {
    // Verify that a CardCell with a red card returns "R"
    assertEquals("R", filledCardCell.toString());
  }

  @Test
  public void testCardCellIsEmpty() {
    // Verify that an empty CardCell is considered empty
    assertTrue(emptyCardCell.isEmpty());

    // Verify that a filled CardCell is not empty
    assertFalse(filledCardCell.isEmpty());
  }

  @Test
  public void testSetCardInEmptyCell() {
    // Set minAttackCard in an empty CardCell and verify
    minAttackCard.setColor(CardColor.BLUE);
    emptyCardCell.setCard(minAttackCard);
    assertEquals("B", emptyCardCell.toString());
  }

  @Test(expected = IllegalStateException.class)
  public void testSetCardInFilledCellThrowsException() {
    // Attempting to set a second card in a filled CardCell should throw IllegalStateException
    filledCardCell.setCard(minAttackCard);
  }

  @Test(expected = IllegalStateException.class)
  public void testFlipEmptyCardCellThrowsException() {
    // Attempting to flip an empty CardCell should throw IllegalStateException
    emptyCardCell.flipCell();
  }

  @Test
  public void testFlipCardCellWithRedCard() {
    // Flip a red card in a filled CardCell and verify it turns blue
    filledCardCell.flipCell();
    assertEquals("B", filledCardCell.toString());
  }

  @Test
  public void testFlipCardCellWithMaxAttackCard() {
    // Set maxAttackCard to blue, flip it, and verify it changes to red
    emptyCardCell.setCard(maxAttackCard);
    maxAttackCard.setColor(CardColor.BLUE);
    emptyCardCell.flipCell();
    assertEquals("R", emptyCardCell.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetNullCardInCardCell() {
    emptyCardCell.setCard(null);
  }

  @Test
  public void testRepeatedFlipping() {
    // Flip a card multiple times and verify the color toggles
    filledCardCell.flipCell();  // RED -> BLUE
    assertEquals("B", filledCardCell.toString());

    filledCardCell.flipCell();  // BLUE -> RED
    assertEquals("R", filledCardCell.toString());
  }

  /**
   * Test that starting the game twice throws an IllegalStateException.
   */
  @Test(expected = IllegalStateException.class)
  public void testStartGameTwiceThrowsException() {
    List<List<Cell>> grid = createValidGrid(3, 3);
    List<ThreeTriosCard> deck = createDeckWithEnoughCards(10);

    gameModel.startGame(grid, deck, redPlayer, bluePlayer);
    gameModel.startGame(grid, deck, redPlayer, bluePlayer);
  }

  /**
   * Test that starting the game with insufficient cards throws IllegalArgumentException.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testStartGameInsufficientCards() {
    List<List<Cell>> grid = createValidGrid(3, 3);
    List<ThreeTriosCard> deck = createDeckWithEnoughCards(5);

    gameModel.startGame(grid, deck, redPlayer, bluePlayer);
  }

  /**
   * Test drawing hands for both players.
   */
  @Test
  public void testDrawHand() {
    List<List<Cell>> grid = createValidGrid(3, 3);
    List<ThreeTriosCard> deck = createDeckWithEnoughCards(10);

    gameModel.startGame(grid, deck, redPlayer, bluePlayer);
    gameModel.drawHand();

    assertTrue(redPlayer.getCurrentHandSize() > 0);
    assertTrue(bluePlayer.getCurrentHandSize() > 0);
  }

  /**
   * Test playing a card to the grid.
   */
  @Test
  public void testPlayToGrid() {
    List<List<Cell>> grid = createValidGrid(3, 3);
    List<ThreeTriosCard> deck = createDeckWithEnoughCards(10);

    gameModel.startGame(grid, deck, redPlayer, bluePlayer);
    gameModel.drawHand();

    int handIndex = 0;
    gameModel.playToGrid(handIndex, 1, 1);

    assertFalse(grid.get(1).get(1).isEmpty());
  }

  /**
   * Test playing to a filled CardCell throws an IllegalStateException.
   */
  @Test(expected = IllegalStateException.class)
  public void testPlayToGridFilledCellThrowsException() {
    List<List<Cell>> grid = createValidGrid(3, 3);
    List<ThreeTriosCard> deck = createDeckWithEnoughCards(10);

    gameModel.startGame(grid, deck, redPlayer, bluePlayer);
    gameModel.drawHand();

    int handIndex = 0;
    gameModel.playToGrid(handIndex, 1, 1); // Play first card

    gameModel.playToGrid(handIndex, 1, 1); // Attempt to play again on the same cell
  }

  /**
   * Test battle phase with a scenario where no cards should flip.
   */
  @Test
  public void testBattleNoFlip() {
    List<List<Cell>> grid = createValidGrid(3, 3);
    List<ThreeTriosCard> deck = createDeckWithEnoughCards(10);

    gameModel.startGame(grid, deck, redPlayer, bluePlayer);
    gameModel.drawHand();

    int handIndex = 0;
    gameModel.playToGrid(handIndex, 1, 1);
    gameModel.battle();

    assertEquals("R", grid.get(1).get(1).toString());
  }

  /**
   * Test gameOver method returns true when all cells are filled.
   */
  @Test
  public void testGameOverTrue() {
    List<List<Cell>> grid = createValidGrid(3, 3);
    List<ThreeTriosCard> deck = createDeckWithEnoughCards(10);

    gameModel.startGame(grid, deck, redPlayer, bluePlayer);

    // Fill all cells
    for (int row = 0; row < grid.size(); row++) {
      for (int col = 0; col < grid.get(row).size(); col++) {
        if (grid.get(row).get(col) instanceof CardCell && grid.get(row).get(col).isEmpty()) {
          gameModel.playToGrid(0, row, col);
          gameModel.drawHand();
        }
      }
    }

    assertTrue(gameModel.gameOver());
  }

  /**
   * Test getWinner method when red player wins.
   */
  @Test
  public void testGetWinnerRedPlayerWins() {
    List<List<Cell>> grid = createValidGrid(3, 3);
    List<ThreeTriosCard> deck = createDeckWithEnoughCards(10);

    gameModel.startGame(grid, deck, redPlayer, bluePlayer);

    // Simulate Red winning by controlling most cells
    for (int row = 0; row < grid.size(); row++) {
      for (int col = 0; col < grid.get(row).size(); col++) {
        if (grid.get(row).get(col) instanceof CardCell && grid.get(row).get(col).isEmpty()) {
          if (row % 2 == 0) {
            grid.get(row).get(col).setCard(new ThreeTriosCard("RedCard", 3, 3, 3, 3));
            grid.get(row).get(col).getCard().setColor(CardColor.RED);
          } else {
            grid.get(row).get(col).setCard(new ThreeTriosCard("BlueCard", 2, 2, 2, 2));
            grid.get(row).get(col).getCard().setColor(CardColor.BLUE);
          }
        }
      }
    }

    assertEquals(redPlayer, gameModel.getWinner());
  }
}