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
import static org.junit.Assert.assertThrows;

public class TestThreeTriosGameModel {
  private ThreeTriosCard card;
  private ThreeTriosCard maxAttackCard;
  private ThreeTriosCard minAttackCard;

  @Before
  public void setUp() {
    // Initialize different ThreeTriosCard instances for testing
    card = new ThreeTriosCard("Warrior", 5, 3, 7, 6);
    maxAttackCard = new ThreeTriosCard("MaxCard", 10, 10, 10, 10);
    minAttackCard = new ThreeTriosCard("MinCard", 1, 1, 1, 1);
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
    assertEquals(3, card.getSouthAttack());
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

  @Test
  public void testSetColorAlreadySet() {
    card.setColor(CardColor.RED);
    Exception exception = assertThrows(IllegalStateException.class,
            () -> card.setColor(CardColor.BLUE));
    assertEquals("Color has already been set", exception.getMessage());
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

  @Test
  public void testFlipWithoutSettingColor() {
    Exception exception = assertThrows(IllegalStateException.class, () -> card.flip());
    assertEquals("Color has not been set", exception.getMessage());
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

  @Test
  public void testColorStringWithoutSettingColor() {
    Exception exception = assertThrows(IllegalStateException.class, () -> card.colorString());
    assertEquals("Color has not been set", exception.getMessage());
  }

  // Tests for toString method
  @Test
  public void testToStringRepresentation() {
    assertEquals("Warrior 5 3 7 6", card.toString());
    assertEquals("MaxCard A A A A", maxAttackCard.toString());
    assertEquals("MinCard 1 1 1 1", minAttackCard.toString());
  }

  // Edge cases for invalid attack values
  @Test
  public void testNegativeAttackValues() {
    Exception exception = assertThrows(IllegalArgumentException.class, () -> new ThreeTriosCard(
            "NegativeCard", -1, 5, 7, 6));
    assertEquals("Attack values must be between 1 and 10.", exception.getMessage());
  }

  @Test
  public void testExcessiveAttackValues() {
    Exception exception = assertThrows(IllegalArgumentException.class, () -> new ThreeTriosCard(
            "ExcessiveCard", 11, 5, 7, 6));
    assertEquals("Attack values must be between 1 and 10.", exception.getMessage());
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
}
