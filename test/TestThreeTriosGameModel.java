import org.junit.Before;
import org.junit.Test;

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

public class TestThreeTriosGameModel {
  private ThreeTriosCard card;
  private ThreeTriosCard maxAttackCard;
  private ThreeTriosCard minAttackCard;
  private ThreeTriosCard filledCard;
  private Cell hole;
  private CardCell emptyCardCell;
  private CardCell filledCardCell;

  @Before
  public void setUp() {
    card = new ThreeTriosCard("Warrior", 5, 5, 7, 6);
    maxAttackCard = new ThreeTriosCard("MaxCard", 10, 10, 10, 10);
    minAttackCard = new ThreeTriosCard("MinCard", 1, 1, 1, 1);
    filledCard = new ThreeTriosCard("Filled", 6, 2, 1 ,3);
    hole = new Hole();
    emptyCardCell = new CardCell();
    filledCardCell = new CardCell();

    filledCard.setColor(CardColor.RED);
    filledCardCell.setCard(filledCard);
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
    assertEquals(5, card.getNorthAttack());
    assertEquals(10, maxAttackCard.getNorthAttack());
    assertEquals(1, minAttackCard.getNorthAttack());
  }

  @Test
  public void testGetSouthAttack() {
    assertEquals(5, card.getSouthAttack());
    assertEquals(10, maxAttackCard.getSouthAttack());
    assertEquals(1, minAttackCard.getSouthAttack());
  }

  @Test
  public void testGetEastAttack() {
    assertEquals(7, card.getEastAttack());
    assertEquals(10, maxAttackCard.getEastAttack());
    assertEquals(1, minAttackCard.getEastAttack());
  }

  @Test
  public void testGetWestAttack() {
    assertEquals(6, card.getWestAttack());
    assertEquals(10, maxAttackCard.getWestAttack());
    assertEquals(1, minAttackCard.getWestAttack());
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
    assertEquals(1, minAttackCard.getNorthAttack());
    assertEquals(1, minAttackCard.getSouthAttack());
    assertEquals(1, minAttackCard.getEastAttack());
    assertEquals(1, minAttackCard.getWestAttack());
  }

  @Test
  public void testMaximumAttackValues() {
    assertEquals(10, maxAttackCard.getNorthAttack());
    assertEquals(10, maxAttackCard.getSouthAttack());
    assertEquals(10, maxAttackCard.getEastAttack());
    assertEquals(10, maxAttackCard.getWestAttack());
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
}